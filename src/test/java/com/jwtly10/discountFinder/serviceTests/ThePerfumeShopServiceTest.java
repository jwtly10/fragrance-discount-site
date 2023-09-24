package com.jwtly10.discountFinder.serviceTests;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.jwtly10.discountFinder.models.Product;
import com.jwtly10.discountFinder.service.ThePerfumeShopService;

@SpringBootTest
@WireMockTest(httpPort = 8089)
public class ThePerfumeShopServiceTest {

    @Autowired
    private ThePerfumeShopService thePerfumeShopService;


    @Test
    void Should_ParseJsonFromAPIAndGetFirstDiscountedProduct() throws JsonProcessingException {
        stubFor(get(urlMatching("api/v2/*")).willReturn(aResponse().withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBodyFile("theperfumeshop.json")));


        List<Product> products = thePerfumeShopService.getDiscountedProducts("M");
        assertThat(products.get(0).getBrand()).isEqualTo("Calvin Klein");
        assertThat(products.get(0).getOldPrice()).isEqualTo(90);
        assertThat(products.get(0).getOldPrice()).isEqualTo(90);
        assertThat(products.get(0).getDiscount()).isEqualTo(55.57);
        assertThat(products.size()).isEqualTo(204);
    }







    
}
