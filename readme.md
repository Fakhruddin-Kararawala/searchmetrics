Exchange Rates App
==

# The assignment
Develop a service, that constantly checks the currency exchange rate from Bitcoin(BTC) to USD (1 BTC = x USD).

## Requirements:
* The check period has to be configurable
* The service has an HTTP-Resource with the following endpoints (The protocol design is up to you):
    * Get the latest rate
    * Get historical rates from startDate to endDate
* Please describe in a few sentences and/or with a diagram how you planned the project and architecture.

# Stack

* Java 8
* Spring Boot 2
* Lombok
* Swagger 2
* Mysql
* Docker
* Docker Compose
* Travis

# Configuring

This project uses the [CoinMarketCap](https://coinmarketcap.com/) service to get the exchange rates.

To change the retrieve period of the exchange rate from the CoinMarketCap API, you have to change the property `coinmarketcap.cron` in the `application.yml`. By default, the project will retrieve the exchange rates from Monday to Friday, every minute from 8 AM to 18 PM. 

# Building
This project uses Maven for project management tool. To build and package the project, the following command must be executed:

```
$ mvn clean package
```

The following command will package the project, but skipping the tests

```
$ mvn clean package -DskipTests
```

# Running

This project provides a `Dockerfile` & `docker-compose.yaml` file within the required pre-configured external components, so to before to run the project you must run the following command:

```
$ docker-compose build
$ docker-compose up -d
```

The project can be directly run from below command:

```
java -jar exchange-rates-0.0.1-SNAPSHOT.jar
```

# Testing

hit the url - `http://localhost:8080`
This will open swagger UI with api listing.

