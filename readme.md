testing copy-pasta & examples

``` 
###
POST http://localhost:8080/jpa/users/10001/posts
Content-Type: application/json

{
"description": "testtttttttt "
}

###

POST http://localhost:8080/jpa/users
Content-Type: application/json

{
"id": 1,
"name": "Tony",
"birthDate": "2000-04-28"
}

###

POST http://localhost:8080/jpa/users
Content-Type: application/json

{
"id": 2,
"name": "Tony",
"birthDate": "2000-04-28"
}

###

DELETE http://localhost:8080/jpa/users/2

###

POST http://localhost:8080/jpa/users/1/posts
Content-Type: application/json

{
"description": "testtttttttt "
}

###
    spring.datasource.url=jdbc:mysql://localhost:3306/social-media-database
    spring.datasource.username=social-media-user
    spring.datasource.password=dummypassword
```
