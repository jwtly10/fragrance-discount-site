# discount-finder-api

https://fragrancefinder.uk/products/mens

REST Api based web app for parsing Fragrance Stores APIs, finding products with the best discounts. Deploy on Heroku (Springboot Server) and Netlify (React App).

Some features:

-   Calling the products endpoint

```
api/v1/stores/theperfumeshop/mens?sort=max_discount
```

Returns a list of discounted fragrance products from the [ThePerfumeShop's](https://www.theperfumeshop.com) Mens range, sorted by Most to Least discounted.

Behind the scenes, this calls the stores' internal APIs (where possible), and parses each potential page concurrently, checking 1000's of products for discounts - returning a list of only the on sale products on the site.

Built to serve the need of a Perfume Discount Finder Facebook group, this site finds the best deals on the web, and is a great way to find a bargain.

## Notes

Due to security on the internal store APIs, a proxy was needed to be able to access this from the deployed Herokyu dyno, which slows down fetching performance. Some changes were made to increase responsiveness despite this:

-   Server side caching to reduce proxy api calls and increase responsiveness of the site.
-   Client side sorting was also added to speed up responsiveness of the site. Server side sorting is still available via the API but the client side sorting increases responsiveness considerably.
-   This has the side benefit of also rate limiting the number of calls to the paid proxy as well as the internal store APIs. Rate limiting has also been setup seperately as a fall back.

## Requirements

For building and running the application you need:

-   [JDK 17](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
-   [Maven 3](https://maven.apache.org)

## Running the application locally

Shell command:

```shell
mvn spring-boot:run
```
