#ShortyUrl:

To start with project, dependecies need to be installed.

For that go to shortyUrl directory than do npm install (React UI)

For java go to demo dir and run "mvn clean install"

once the dependencies are resolved, we need to start the API first, for that run the DemoApplication main class.

API will be available at http://localhost:8080
while swagger ui will be available at http://localhost:8080/swagger-ui.html

once API is up we can go for UI 
go to shortyUrl dir than run "npm start"

once command is executed succesfully, go to http://localhost:3000

UI will be there if everything is fine.

First Sign Up with random email id, username and password.
Than you can create cards, groups and manage them.

In card creation every field is mendotory.

Clicking on title will lead you to the full url in new tab, while title is representing the shortUrl.
while hovering on description will show complete description related to that title.

After 60 mins of card generation short url will be expired and the card will be removed automatically.


Note: For database, no configuration is needed, firebase is running on google
