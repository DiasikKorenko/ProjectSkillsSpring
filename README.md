# ProjectSkillsSpring
# Cargo Transportation Support application
<hr>
This application is created for organizations that are engaged in cargo transportation.The application is based on the placement and monitoring of available vehicles and the actual cargo to be transported, which facilitates the process of searching for cargo or finding a transport to transport cargo from one point to another.

## Database
Application use PostgreSQL database. For start the application you need Postgres server (jdbc:postgresql://localhost:5432/Black_projectSkills) with created database 'Black_projectSkills'. The database contains six tables.
* Table _users_ - contains information about application users;
* Table _l_user_transport_ - сontains information about posted transports;
* Table _l_user_cargo_ - сontains information about posted cargos;
* Table _favorite_transport_ - contains information about transports that have been added to favorites;
* Table _favorite_cargo_ - contains information about cargos that have been added to favorites.
* Table _reviews_ - contains user reviews of the application about the companies that provided their transport services (transport or cargo)..

## Available endpoints for users
* http://localhost:8080/user - GET method, show all user information for the user
* http://localhost:8080/user/{id} - GET method, show user information for the user
* http://localhost:8080/user - POST method, user creation
* http://localhost:8080/user - PUT method, user change
* http://localhost:8080/user/changePassword - PUT method, change user's password
* http://localhost:8080/user - DELETE method, delete current user's account
* http://localhost:8080/transport - GET method, show all information about transport for the user
* http://localhost:8080/transport/{id} - GET method, show transport information for the user
* http://localhost:8080/transport - POST method, creation of transport
* http://localhost:8080/transport - PUT method, transport change for the user
* http://localhost:8080/transport - DELETE method, deleting a transport for the user
* http://localhost:8080/transport/fromUser/{userId} - GET method, show all transport owned by one user
* http://localhost:8080/cargo - GET method, show all information about cargos for the user
* http://localhost:8080/cargo/{id} - GET method, show cargo information for the user
* http://localhost:8080/cargo - POST method, creation of cargo
* http://localhost:8080/cargo - PUT method, cargo change for the user
* http://localhost:8080/cargo - DELETE method, deleting a cargo for the user
* http://localhost:8080/cargo/fromUser/{userId} - GET method, show all cargo owned by one user
* http://localhost:8080/favorite/transport/addFav - POST method, add transport to user's favourite
* http://localhost:8080/favorite/cargo/addFav - POST method, add cargo to user's favourite
* http://localhost:8080/favorite/transport/removeFav - DELETE method, remove transport from user's favourite
*  http://localhost:8080/favorite/cargo/removeFav - DELETE method, remove cargo from user's favourite
* http://localhost:8080/reviews/{toWhichCompanyId} - GET method, show information about all reviews for one company for the user
* http://localhost:8080/reviews - POST method, creation reviews for company
* http://localhost:8080/reviews - PUT method, update review's for the user
* http://localhost:8080/reviews - DELETE method, deleting review's for the user


## Available endpoints for admins
* http://localhost:8080/user - POST method, user creation
* http://localhost:8080/user - GET method, show all user information 
* http://localhost:8080/user/{id} - GET method, show user information 
* http://localhost:8080/user/admin/changePasswordAdmin - PUT method, changing the user's password
* http://localhost:8080/user/admin - PUT method, user change
* http://localhost:8080/user/admin - GET method, show all users information
* http://localhost:8080/user/admin/{id}" - GET method, show all information from one user
* http://localhost:8080/user/admin - DELETE method, delete user's account
* http://localhost:8080/transport - POST method, creation of transport
* http://localhost:8080/transport - GET method, show all information about transport 
* http://localhost:8080/transport/{id} - GET method, show transport information 
* http://localhost:8080/transport/admin/{id} - GET method, show transport information
* http://localhost:8080/transport/admin - GET method, show all information about transports
* http://localhost:8080/transport/admin/fromUser/{userId} - GET method, show all transports owned by one user
* http://localhost:8080/transport/admin - PUT method, transport change
* http://localhost:8080/transport/admin - DELETE method, deleting a transport
* http://localhost:8080/cargo - POST method, creation of cargo
* http://localhost:8080/cargo - GET method, show all information about cargo
* http://localhost:8080/cargo/{id} - GET method, show cargo information
* http://localhost:8080/cargo/admin/{id} - GET method, show cargo information
* http://localhost:8080/cargo/admin - GET method, show all information about cargos
* http://localhost:8080/cargo/admin/fromUser/{userId} - GET method, show all cargos owned by one user
* http://localhost:8080/cargo/admin - PUT method, cargo change
* http://localhost:8080/cargo/admin - DELETE method, deleting a cargo
* http://localhost:8080/favorite/transport/addFav - POST method, add transport to user's favourite
* http://localhost:8080/favorite/transport/admin/{userId} - GET method, show all transport's information about favourite for one user
* http://localhost:8080/favorite/cargo/admin/{userId}} - GET method, show all cargo's information favourite for one user
* http://localhost:8080/reviews - POST method, creation reviews for company
* http://localhost:8080/reviews/admin/{toWhichCompanyId} - GET method, show information about all reviews for one company for the user
* http://localhost:8080/reviews/admin - DELETE method, deleting review's 

<hr>
The technology stack of this project: Java, PostgreSQL,  Hibernate, Spring (Boot, Security, Data),
Lombok, AOP, Swagger, Maven, Mockito.

<br>