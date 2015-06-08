Programming Exercise - MyRetail
===============================

Overview
--------
This is a simple application which will return list of items based on search criteria.

    * Search by item by ID.
    * Get all items available in repository.
    * Search items by Catalog.
    
Pre-requisites
--------------

   * Java 5.0 or Higher
   * Apache maven 3.x for building
   * JBoss 4.2.2 or any other Application Server (Tomcat or Jetty).
   * mysql-5.1.x version installed on local.
  
Running the app
---------------

   * Run 'mvn clean install' to build and create 'myretail-1.0' file under the target directory.
   * Deploy the myretail-1.0 to your favourite servlet container.
   * You are ready. Bring up http://localhost:<port>/myretail on your favourite browser.	
   * In case of JBoss you can see logs appended in server.log
	
Sample curl commands
---------------
You can also run following curl commands in browser to check the output


* Search by item by ID URI - http://localhost:8080/myretail/rest/item/{id}
  	     Example - http://localhost:8080/myretail/rest/item/5555
  	  
* Get all items	URI - http://localhost:8080/myretail/rest/item/all
		 Example -  http://localhost:8080/myretail/rest/item/all	
		
* Search by item by ID URI - http://localhost:8080/myretail/catalog/{catalog_id}
  	     Example - http://localhost:8080/myretail/rest/catalog/Baby
  
Code Structure 
---------------

1) Database 
---------------

I have used myBatis as ORM. Related db.properties and configuration.xml are present under
src/main/resources. Mapping is present in ContactMapper.xml which contains relevant select
queries. configuration.properties and db.properties are also present in config. Schema 
creation sql is present in create_schema.sql

2) Routers 
---------------

2.1) There are 2 routers defined one for Item and other for Catalog search.
The classes are defined in CatalogRouter.java and ItemRouter.java. These classes use mapper
to get the details from database and return response back to the client

2.2) Error codes are defined in ErrorConstants.java. In case no item is found then error response
along with message is returned to client.


3) Frontend
---------------
3.1) index.html - Is the home page of the application. From here there are 3 buttons which 
call the service. Service call are present in myScript.js file

This script calls the underlying service through jQuery and displays results in tablear 
form.


4) Test Cases
---------------
Test cases are present in src/test/java folder. SearchInputTest.java class validates the 
response from database.


Questions?
---------------
I will be attending interview on 12th June 2015 (Friday). C u then. :)

Prepared by 
---------------
Anurag Bagga
  