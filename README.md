# spring-microservices-eureka
This is a demo application for a restaurant that will be developed using domain driven design, clean architecture and microservices.
We will use rabbit mq for our asynchronous communication and leverage virtual threads for performance.

![diagram.svg](./docs/spring-microservices.png) 

## Modules
* **menu-service**:
  This services provides REST API for managing the menu of a restaurant.

  **TechStack:** Spring Boot, Spring Data JPA, PostgreSQL

* **order-service**:
  This service provides the REST API for ordering food and publishes order events to the message broker.

  **TechStack:** Spring Boot, Spring Data JPA, PostgreSQL, RabbitMQ

* **notification-service**:
  This service listens to the order events and sends notifications to the kitchen.

  **TechStack:** Spring Boot, RabbitMQ

* **api-gateway**:
  This service is an API Gateway to the internal backend services (menu-service, order-service).

  **TechStack:** Spring Boot, Spring Cloud Gateway

* **service-discovery**:
  This service will be the register of our microservices, will also serve a load balancer between ours instances.

  **TechStack:** Netflix eureka


