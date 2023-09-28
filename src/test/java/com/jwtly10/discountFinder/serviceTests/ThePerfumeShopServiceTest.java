package com.jwtly10.discountFinder.serviceTests;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.jwtly10.discountFinder.models.Gender;
import com.jwtly10.discountFinder.models.Product;
import com.jwtly10.discountFinder.models.Sort;
import com.jwtly10.discountFinder.service.SortService;
import com.jwtly10.discountFinder.service.ThePerfumeShopService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

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

        List<Product> products = thePerfumeShopService.getDiscountedProducts("http://localhost:8089/theperfumeshoptest", Gender.mens);
        assertThat(products.get(0).getBrand()).isEqualTo("Calvin Klein");
        assertThat(products.get(0).getOldPrice()).isEqualTo(90);
        assertThat(products.get(0).getOldPrice()).isEqualTo(90);
        assertThat(products.get(0).getDiscount()).isEqualTo(55.57);
        assertThat(products.size()).isEqualTo(6);
    }

    @Test
    void Should_Sort_ByDiscountAndPrice() {
        stubFor(get(urlMatching("/theperfumeshoptest.*")).willReturn(aResponse().withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBodyFile("theperfumeshop.json")));

        List<Product> products = SortService.sort(thePerfumeShopService.getDiscountedProducts("http://localhost:8089/theperfumeshoptest", Gender.mens), Sort.none);
        assertThat(products.get(0).getBrand()).isEqualTo("Calvin Klein");

        List<Product> max_discount_products = SortService.sort(products, Sort.max_discount);
        assertThat(max_discount_products.get(0).getBrand()).isEqualTo("Elizabeth Taylor");
        assertThat(max_discount_products.get(0).getItemName()).isEqualTo("White Diamonds Legacy");

        List<Product> max_price_products = SortService.sort(products, Sort.max_price);
        assertThat(max_price_products.get(1).getBrand()).isEqualTo("Britney Spears");
        assertThat(max_price_products.get(1).getItemName()).isEqualTo("Midnight Fantasy");
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

        List<Product> products = thePerfumeShopService.getDiscountedProducts("http://localhost:8089/theperfumeshoptest", Gender.womens);
        assertThat(products.size()).isEqualTo(10);
    }
}
