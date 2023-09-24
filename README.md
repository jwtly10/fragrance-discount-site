
# discount-finder-api

REST Api based application for parsing Fragrance Stores APIs, finding products with the best discounts.

Some features:
- Calling the products endpoint

```
api/v1/stores/theperfumeshop/mens
```

Returns a list of discounted fragrance products from the [ThePerfumeShop's](https://www.theperfumeshop.com) Mens range.

Behind the scenes, this calls the stores' internal APIs (where possible), and parses each potential page concurrently, checking 1000's of products for discounts in only a few seconds - returning a list of only the on sale products on the site. 

Frontend React Site reads from these APIs and produces a storefront allowing for easy purchasing of discounted perfumes.
- Calling the subscribe endpoint
```
api/v1/subscribe/{email}
```
Email list endpoint allows users to sign up for deals as SOON as they come in, a background job parses these sites regularly and sends out an email with the best deals daily.

Some other server side filters have also been implemented.
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
