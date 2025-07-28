# Inventory Management API (Java & Spring Boot)

This repository contains the backend source code for a complete Inventory Management API. The application is built using Java and the Spring Boot framework, providing a robust and secure set of RESTful endpoints to manage users and product inventory.

## Project Overview & Features

This project fulfills the core requirements of a small business inventory system, providing a secure and scalable backend solution.

* **Secure User Authentication**: Endpoints for user registration and login using **JSON Web Tokens (JWT)** for secure, stateless authentication. All sensitive product-related endpoints are protected.
* **Complete Product Management**: Full CRUD-like functionality for inventory items.
    * **Add Products**: Create new products with details like name, SKU, description, quantity, and price.
    * **Update Product Quantity**: Specifically update the stock level of any product by its ID.
    * **Retrieve Products**: Fetch a paginated list of all available products.
* **Database Integration**: Uses **Spring Data JPA** with an in-memory **H2 Database**, which requires no external database setup. The schema is created automatically on application startup.
* **API Documentation**: Integrated **Swagger/OpenAPI v3** documentation. Once the server is running, a full, interactive API specification is available to view and test the endpoints.
* **Input Validation**: Implements request data validation using `jakarta.validation` annotations to ensure data integrity and provide clear error messages.
* **Centralized Exception Handling**: Provides clear and consistent JSON error responses for different failure scenarios, such as `404 Not Found` or `409 Conflict`.

---

## Technology Stack

* **Framework**: Spring Boot 3.x
* **Language**: Java 17
* **Authentication**: Spring Security 6.x with JWT
* **Database**: H2 In-Memory Database
* **Data Access**: Spring Data JPA (with Hibernate as the provider)
* **API Documentation**: SpringDoc OpenAPI
* **Build Tool**: Apache Maven

---

## Setup and Installation

Follow these steps to get the application running on your local machine.

### Prerequisites

* **Java Development Kit (JDK)**: Version 17 or higher.
* **Apache Maven**: Version 3.6 or higher.
* **Git**: For cloning the repository.

### Step-by-Step Instructions

1.  **Clone the Repository**
    Open your terminal and clone the project from your GitHub repository.
    ```bash
    git clone [https://github.com/](https://github.com/)<your-username>/<your-repo-name>.git
    cd <your-repo-name>
    ```

2.  **Build the Project with Maven**
    This command will compile the source code, run any tests, and package the application into a single executable JAR file. This process may take a minute as it downloads all the required dependencies.
    ```bash
    mvn clean install
    ```
    This will create a `target` directory containing the file `inventory-0.0.1-SNAPSHOT.jar`.

3.  **Run the Application**
    Execute the JAR file to start the Spring Boot server.
    ```bash
    java -jar target/inventory-0.0.1-SNAPSHOT.jar
    ```

4.  **Verify the Server is Running**
    Once started, you will see the Spring Boot banner and log output in your terminal. The server will be running on `http://localhost:8080`.

---

## Accessing Important URLs

* **API Docs (Swagger UI)**: You can access the interactive Swagger UI documentation at:
    * [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

* **H2 Database Console**: You can view the in-memory database through a web console at:
    * [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
    * **JDBC URL**: `jdbc:h2:mem:inventorydb`
    * **User Name**: `sa`
    * **Password**: (leave blank)
    * Click `Connect` to access the database schema and run SQL queries.

---

## API Endpoint Documentation

All endpoints are available under the base URL `http://localhost:8080`.

### **Authentication**

#### `POST /register`

* **Description**: Registers a new user. The username must be unique.
* **Request Body**:
    ```json
    {
      "username": "puja",
      "password": "mypassword"
    }
    ```
* **Responses**:
    * `201 Created`: If the user is successfully created.
    * `409 Conflict`: If the username already exists.

#### `POST /login`

* **Description**: Authenticates a user and returns a JWT. This token must be used for all subsequent protected requests.
* **Request Body**:
    ```json
    {
      "username": "puja",
      "password": "mypassword"
    }
    ```
* **Responses**:
    * `200 OK`: Returns an `access_token`.
        ```json
        {
          "access_token": "eyJhbGciOiJIUzI1NiJ9..."
        }
        ```
    * `401 Unauthorized`: If credentials are invalid.

### **Products (Requires Authentication)**

To access these endpoints, you must include the JWT in the `Authorization` header.
**Header Format**: `Authorization: Bearer <your_access_token>`

#### `POST /products`

* **Description**: Adds a new product to the inventory.
* **Request Body**:
    ```json
    {
      "name": "Phone",
      "type": "Electronics",
      "sku": "PHN-001",
      "imageUrl": "[https://example.com/phone.jpg](https://example.com/phone.jpg)",
      "description": "Latest Phone",
      "quantity": 5,
      "price": 999.99
    }
    ```
* **Responses**:
    * `201 Created`: Returns the new product's ID.
        ```json
        {
          "product_id": 1
        }
        ```

#### `GET /products`

* **Description**: Retrieves a paginated list of all products.
* **Query Parameters**:
    * `page` (optional, default: `0`): The page number to retrieve.
    * `size` (optional, default: `10`): The number of items per page.
* **Example URL**: `http://localhost:8080/products?page=0&size=5`
* **Responses**:
    * `200 OK`: Returns an array of product objects.

#### `PUT /products/{id}/quantity`

* **Description**: Updates the quantity for a specific product.
* **Path Parameter**:
    * `id`: The unique ID of the product to update.
* **Request Body**:
    ```json
    {
      "quantity": 15
    }
    ```
* **Responses**:
    * `200 OK`: Returns the complete, updated product object.
    * `404 Not Found`: If the product with the given ID does not exist.

---

## Testing with the Provided Script

You can use the `test_api.py` script to validate the functionality of all endpoints.

1.  **Ensure Python is installed** and you have the `requests` library.
    ```bash
    pip install requests
    ```

2.  **Make sure the Java server is running.**

3.  **Configure the script**: Open `test_api.py` and ensure the `BASE_URL` is set correctly:
    ```python
    BASE_URL = "http://localhost:8080"
    ```

4.  **Run the script from your terminal**:
    ```bash
    python test_api.py
    ```

You should see `✅ PASSED` for all tests if the API is working correctly.
