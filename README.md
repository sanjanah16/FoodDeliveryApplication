# Food Delivery Application

## Java Full Stack Web Application

A web-based food delivery application developed using **Java, JDBC, Servlets, MySQL, HTML, and CSS**.

The application allows users to browse restaurants, view menus, add food items to their cart, make payments, and view their orders.

## Features

### User Features

* User Signup and Login
* Browse Restaurants
* View Restaurant Menus
* Add Food Items to Cart
* Update Cart Items
* View Cart Total
* Payment Page
* Order Placement
* Order History
* User Profile

### Restaurant & Menu

* Display restaurants
* Display menu items based on restaurant
* Restaurant ratings and details
* Food item prices and descriptions

## Technologies Used

* **Java**
* **JDBC**
* **Servlets**
* **MySQL**
* **HTML5**
* **CSS3**
* **Apache Tomcat**
* **Eclipse IDE**

## Architecture

The project follows the **DAO (Data Access Object) design pattern** to separate database operations from application logic.

### Project Structure

```text
FoodAppApplication
│
├── DAO
│   ├── UserDAO
│   ├── RestaurantDAO
│   ├── MenuDAO
│   └── OrderDAO
│
├── DAOImpl
│   ├── UserDAOImpl
│   ├── RestaurantDAOImpl
│   ├── MenuDAOImpl
│   └── OrderDAOImpl
│
├── model
│   ├── User
│   ├── Restaurant
│   ├── Menu
│   └── Order
│
├── servlet
│   ├── LoginServlet
│   ├── SignupServlet
│   ├── MenuServlet
│   ├── CartServlet
│   └── PaymentServlet
│
├── util
│   └── DBConnection
│
└── webapp
    ├── css
    ├── images
    ├── login
    ├── signup
    ├── home
    ├── cart
    ├── payment
    └── profile
```

## Database

The application uses **MySQL** for storing and managing application data.

Main database tables include:

* User
* Restaurant
* Menu
* Orders
* Order Item

## How to Run

1. Clone or download this repository.
2. Import the project into **Eclipse IDE**.
3. Configure **Apache Tomcat**.
4. Create the required MySQL database.
5. Configure the database connection in `DBConnection`.
6. Add the MySQL Connector/J library.
7. Start the application using Tomcat.
8. Open the application in a web browser.

## Key Highlights

* Implemented using Java Full Stack technologies.
* Uses JDBC for database connectivity.
* Uses Servlets for handling web requests.
* Follows the DAO design pattern.
* Uses MySQL for persistent data storage.
* Includes cart and order management.
* Responsive and user-friendly web interface.

## Future Enhancements

* Online payment gateway integration
* Restaurant owner dashboard
* Admin dashboard
* Order tracking
* Food search and filtering
* User reviews and ratings
* Email notifications

## Author

**Sanjana Hatture**

Java Full Stack Developer

GitHub: https://github.com/sanjanah16

LinkedIn: https://www.linkedin.com/in/sanjana-hatture-549a93302/
