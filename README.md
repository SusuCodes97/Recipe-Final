# My Cookbook

My cookbook is a programme which allows users to store recipes online. Users can also perform the basic CRUD methods as well as find recipes by name and by ingredient.
There are two versions of this application, the original trial, whereby the recipe is stored locally as a list (this can be found on the main branch), and the final backend product, where the recipes are stored in a PostgreSQL database through the use of JPA (found on nonautomatedID branch).
Currently, my cookbook works as a backend application, with a UI connected but not customised.


**Application requirements:**
- Java Version: 17
- PostgreSQL (this can be done through pgAdmin 4)
- IntelliJ Idea
- Visual Studio Code
- npm
- Extra: For ease of use, downloading Postman to run the API requests is recommended

**To run the backend**
- Clone this repository
- Open up the CBF-Final-Project on IntelliJ Idea
- Create a database on PostgreSQL (through pgAdmin 4) called recipe
- In the application.properties file, on line 6 and 7, input the required username and password
- Reload Maven dependencies in pom.xml
- Ensure port:8080 is available
- Run the application
- For ease of use, use postman for the API requests


**To run the frontend**
- Open the public-recipe-app-ui folder on Visual Studio Code
- Install the necessary dependencies using the Visual Studio Code terminal using the following command:
```shell
npm install
```
- Ensure post:3000 is available
- Run the application with the following command:
```shell
npm start
```
- To view the UI in browser, open [http://localhost:3000](http://localhost:3000)