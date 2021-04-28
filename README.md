# Client demo
## Introduction

Small test for a basic user + client demo. Built with Maven, Java 8, Spring Boot, Spring MVC, Thymeleaf, H2 Database Engine and Liquibase.

<img src="https://github.com/ssilver113/client-test-app/blob/main/app_image1.PNG?raw=true" width="500" alt="app_image1">
<img src="https://github.com/ssilver113/client-test-app/blob/main/app_image2.PNG?raw=true" width="500" alt="app_image2">

## Overview & requirements

* Database is populated with 3 users on startup
* User has to log in as one of the users (basic authentication)
* User is displayed their list of clients (can't see other clients)
* User can add and edit clients (mandatory fields are validated)
* Values of country select box are populated with data from db

## Setup

* Build and run Spring Boot app with Maven
* Visit localhost:8080

App uses embedded H2 database and populates DB with data on start-up using Liquibase which runs the relevant sql scripts