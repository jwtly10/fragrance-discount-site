package com.jwtly10.discountFinder.serviceTests;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.jwtly10.discountFinder.exceptions.SortServiceException;
import com.jwtly10.discountFinder.exceptions.StoreServiceException;
import com.jwtly10.discountFinder.models.Product;
import com.jwtly10.discountFinder.service.SortService;
import com.jwtly10.discountFinder.service.ThePerfumeShopService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@WireMockTest(httpPort = 8089)
public class ThePerfumeShopServiceTest {

    @Autowired
    private ThePerfumeShopService thePerfumeShopService;


    @Test
    void Should_ParseJsonFromAPIAndGetFirstDiscountedProduct() {

        stubFor(get(urlMatching("/theperfumeshoptest.*")).willReturn(aResponse().withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBodyFile("theperfumeshop.json")));

        List<Product> products = thePerfumeShopService.getDiscountedProducts("http://localhost:8089/theperfumeshoptest", "mens");
        assertEquals(products.get(0).getBrand(), "Calvin Klein");
        assertEquals(products.get(0).getOldPrice(), 90);
        assertEquals(products.get(0).getOldPrice(), 90);
        assertEquals(products.get(0).getDiscount(), 55.57);
        assertEquals(products.size(), 6);
    }

    @Test
    void Should_Sort_ByDiscountAndPrice() {
        stubFor(get(urlMatching("/theperfumeshoptest.*")).willReturn(aResponse().withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBodyFile("theperfumeshop.json")));

        List<Product> products = SortService.sort(thePerfumeShopService.getDiscountedProducts("http://localhost:8089/theperfumeshoptest", "mens"), "none");
        assertEquals(products.get(0).getBrand(), "Calvin Klein");

        List<Product> max_discount_products = SortService.sort(products, "max_discount");
        assertEquals(max_discount_products.get(0).getBrand(), "Elizabeth Taylor");
        assertEquals(max_discount_products.get(0).getItemName(), "White Diamonds Legacy");

        List<Product> max_price_products = SortService.sort(products, "max_price");
        assertEquals(max_price_products.get(1).getBrand(), "Britney Spears");
        assertEquals(max_price_products.get(1).getItemName(), "Midnight Fantasy");
    }

    @Test
    void Should_Validate_Sort() {
        stubFor(get(urlMatching("/theperfumeshoptest.*")).willReturn(aResponse().withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBodyFile("theperfumeshop.json")));

        SortServiceException thrown = assertThrows(SortServiceException.class, () -> {
            List<Product> products = SortService.sort(thePerfumeShopService.getDiscountedProducts("http://localhost:8089/theperfumeshoptest", "mens"), "invalidSort");
        });

        assertEquals(thrown.getMessage(), "Invalid Sort: invalidSort");
    }

    @Test
    void Should_Validate_Gender() {
        stubFor(get(urlMatching("/theperfumeshoptest.*")).willReturn(aResponse().withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBodyFile("theperfumeshop.json")));
        
        StoreServiceException thrown = assertThrows(StoreServiceException.class, () -> {
            List<Product> products = SortService.sort(thePerfumeShopService.getDiscountedProducts("http://localhost:8089/theperfumeshoptest", "invalidGender"), "none");
        });

        assertEquals(thrown.getMessage(), "Invalid Gender: invalidGender");
    }

    @Test
    void Should_ConcurrentlyRequestMultiplePages() {

        stubFor(get(urlMatching("/theperfumeshoptest.*")).willReturn(aResponse().withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBodyFile("theperfumeshopinitial.json")));

        stubFor(get(urlMatching("/theperfumeshoptest.*&currentPage=0")).willReturn(aResponse().withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBodyFile("theperfumeshoppage0.json")));

        stubFor(get(urlMatching("/theperfumeshoptest.*&currentPage=1")).willReturn(aResponse().withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBodyFile("theperfumeshoppage1.json")));

        List<Product> products = thePerfumeShopService.getDiscountedProducts("http://localhost:8089/theperfumeshoptest", "womens");
        assertEquals(products.size(), 10);
    }
}
