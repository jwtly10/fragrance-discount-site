
# discount-finder-api

https://fragrancefinder.uk/products/mens

REST Api based web app for parsing Fragrance Stores APIs, finding products with the best discounts. Deploy on Heroku (Springboot Server) and Netlify (React App).

Some features:
- Calling the products endpoint

```
api/v1/stores/theperfumeshop/mens?sort=max_discount
```

Returns a list of discounted fragrance products from the [ThePerfumeShop's](https://www.theperfumeshop.com) Mens range, sorted by Most to Least discounted.

Behind the scenes, this calls the stores' internal APIs (where possible), and parses each potential page concurrently, checking 1000's of products for discounts - returning a list of only the on sale products on the site. 

Frontend React Site reads from these APIs and produces a storefront allowing for easy purchasing of discounted perfumes.

## Requirements

For building and running the application you need:

- [JDK 17](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.jwtly10.discountFinder.DiscountFinderApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
