package com.jwtly10.discountFinder.service;

import com.jwtly10.discountFinder.exceptions.StoreServiceException;
import com.jwtly10.discountFinder.models.Gender;
import com.jwtly10.discountFinder.models.Product;
import com.jwtly10.discountFinder.models.Site;
import com.jwtly10.discountFinder.utils.Formatting;
import com.jwtly10.discountFinder.utils.Validate;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
@Log4j2
public class ThePerfumeShopService implements IStoreService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CacheManager cacheManager;
    private final Site site;

    private final HttpHeaders headers;


    public ThePerfumeShopService() {
        this.site = new Site(
                "The Perfume Shop",
                "https://www.theperfumeshop.com",
                "https://media.theperfumeshop.com"
        );
        this.headers = new HttpHeaders();
    }


    public List<Product> getDiscountedProducts(String apiUrl, String gender) {
        if (!Validate.isGender(gender)) {
            throw new StoreServiceException("Invalid Gender: " + gender);
        }

        try {
            String apiUrlIn = switch (Gender.valueOf(gender)) {
                case mens -> apiUrl.replace("gender", "C102");
                case womens -> apiUrl.replace("gender", "C101");
                default -> apiUrl;
            };

            log.debug("ThePerfumeShop API URL: {}", apiUrlIn);

            headers.set("User-Agent", "PostmanRuntime/7.32.3");
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

            ResponseEntity<?> result =
                    restTemplate.exchange(apiUrlIn, HttpMethod.GET, entity, String.class);
            JSONObject jsonObject = new JSONObject(result.getBody().toString());

            int totalPages = jsonObject.getJSONObject("pagination").getInt("totalPages");
            int totalResults = jsonObject.getJSONObject("pagination").getInt("totalResults");

            log.info("ThePerfumeShop Total pages to parse for {}: {} ", gender, totalPages);
            log.info("ThePerfumeShop Total products to parse: {}", totalResults);

            List<Product> productList = new ArrayList<>();

            List<CompletableFuture<Collection<Product>>> futures = new ArrayList<>();

            for (int i = 0; i <= totalPages; i++) {
                futures.add(fetchProducts(apiUrlIn, i));
            }

            futures.forEach(future -> {
                try {
                    productList.addAll(future.get());
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            });

            log.info("Total products returned: {}", productList.size());

            return productList;
        } catch (RuntimeException e) {
            log.error("Error Parsing {} Store JSON, {}", site.getName(), e.getMessage(), e);
            throw new StoreServiceException("Error Parsing " + site.getName() + ". Please try again.");
        }
    }

    @Override
    @Scheduled(fixedRateString = "${caching.spring.productsTTL}")
    public void clearCache() {
        if (Objects.requireNonNull(cacheManager.getCacheNames()).isEmpty()) {
            log.info("Cache is empty");
            return;
        }
        log.info("Clearing {} Cache", site.getName());
        cacheManager.getCacheNames().forEach(cacheName -> Objects.requireNonNull(cacheManager.getCache(cacheName)).clear());
    }

    public Product parseJson(JSONObject json) {

        Product product;

        try {
            String url = site.getUrl() + json.getString("url");
            String brand = json.getJSONObject("masterBrand").has("name") ? json.getJSONObject("masterBrand").getString("name") : "UNKNOWN";
            String itemName = json.getString("rangeName");
            double currentPrice = json.getJSONObject("price").getDouble("value");
            String type = json.getString("name");
            double oldPrice = json.getJSONObject("price").has("oldValue") ? json.getJSONObject("price").getDouble("oldValue") : 0;
            double saving = json.getJSONObject("price").has("savePriceValue") ? json.getJSONObject("price").getDouble("savePriceValue") : 0;
            String size = json.has("size") ? json.getString("size") : "NA";
            String imageUrl = site.getMediaUrl() + json.getJSONArray("images").getJSONObject(0).getString("url");
            boolean inStock = json.getJSONObject("stock").getString("stockLevelStatus").equals("inStock");
            double discountPerc = Formatting.round(oldPrice != 0 ? (oldPrice - currentPrice) / oldPrice * 100 : 0, 2);


            product = Product.builder()
                    .url(url)
                    .brand(brand)
                    .itemName(itemName)
                    .currentPrice(currentPrice)
                    .type(type)
                    .oldPrice(oldPrice)
                    .saving(saving)
                    .discount(discountPerc)
                    .imageUrl(imageUrl)
                    .inStock(inStock)
                    .size(size)
                    .site(site)
                    .build();

            return product;

        } catch (JSONException e) {
            log.error("Error parsing JSON Product Object: {}", e.getMessage(), e);
        }

        return Product.builder().build();

    }

    private CompletableFuture<Collection<Product>> fetchProducts(String url, int page) {
        String newUrl = url + "&currentPage=" + page;
        return CompletableFuture.supplyAsync(() -> {
            List<Product> productList = new ArrayList<>();

            HttpHeaders headers = new HttpHeaders();
            headers.set("User-Agent", "PostmanRuntime/7.32.3");

            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
            ResponseEntity<?> result = restTemplate.exchange(newUrl, HttpMethod.GET, entity, String.class);
            JSONObject jsonObject = new JSONObject(result.getBody().toString());
            JSONArray products = jsonObject.getJSONArray("products");
            for (int j = 0; j < products.length(); j++) {
                Product product = parseJson(products.getJSONObject(j));
                // Only return if product is in stock and discounted
                if (product.isInStock() && product.getDiscount() > 0 && !Objects.equals(product.getBrand(), "UNKNOWN")) {
                    productList.add(product);
                }
            }

            log.debug("ThePerfumeShop Parsed {} products from page {}", productList.size(), page);

            return productList;

        });
    }
}
