# Spring data with MYSQL

### To use this app
1. Clone the repository to your computer if you have not already:
   
   `https://github.com/miraj00/Spring_data_MySQL-Cart_REST_API`
   
2. In Eclipse, select File > Import...
3. Select "Existing Gradle Project"
4. For the project root directory select this folder within the cloned repo.
5. Finish the import.
6. Add `src/main/resources/config/application.properties` and add connection info for your own MySQL schema.
7. You should initialize your MYSQL database with schema and replace cartapi_db ( line 12 ) in application.properties with your database (schema) name.  
8. Start the project: Select src/main/java >> RoomsApplication.java. Run as Java Application.
9. Browse to [http://localhost:8080](http://localhost:8080)



### Spring data MYSQL - Cart REST API


## Purpose
This project is built using Spring MVC framework ( website : start.spring.io ). It is setup using model, repository and controller. There will be no views in this project since its totally created using REST API. This webapp is built using : 

* Spring MVC framework ( start.spring.io )
* Links, Forms, URL Query Parameters, JSP Expression Language
* JAVA
* JSTL
* HTML
* CSS
* MySQL database
* CRUD ( Create(POST), Read(GET), Update(PUT), Delete and PATCH (adds to existing QTY) )

## Website

https://github.com/miraj00/Spring_data_MySQL-Cart_REST_API

## Screenshots

![](/src/main/resources/static/cart-items.JPG) "Homepage"
![](/src/main/resources/static/Express-tester-with-CrossOrigin.JPG) "Express Tester Page"
![](/src/main/resources/static/Express-tester-with-CrossOrigin-MaxPrice.JPG) "Express tester with MaxPrice Page"
![](/src/main/resources/static/Express-tester-with-CrossOrigin-Paging.JPG) "Express tester with Paging"