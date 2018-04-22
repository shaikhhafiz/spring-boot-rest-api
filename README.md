## Rest api using spring boot 
Rest api using spring boot 1.5.9, spring security, spring data jpa and mysql database
<br> Change application.properties file according to your settings such as database name, username, password and so on
<br> You can find details application settings in the following link
<br> https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html

# Resources URL

#Manager resource:

GET:  http://localhost:8080/restspring/managers/

GET:  http://localhost:8080/restspring/managers/{id}

POST: http://localhost:8080/restspring/managers/create

PUT:  http://localhost:8080/restspring/managers/manager/{id}

DELETE: http://localhost:8080/restspring/managers/manager/{id}


#Employee resource

GET: http://localhost:8080/restspring/managers/{managerId}/employees

GET: http://localhost:8080/restspring/managers/{managerId}/employees/{id}

POST: http://localhost:8080/restspring/managers/{managerId}/employees/create

PUT: http://localhost:8080/restspring/managers/{managerId}/employees/{id}

DELETE: http://localhost:8080/restspring/managers/{managerId}/employees/{id}

# Authentication
Basic Auth
```javascript
username: hafiz
password: 12345
```

# Content of body

Header parameter for both POST and PUT action is given below

{ Content-Type : application/json, Accept : application/json } 

Content body in json format for both POST and PUT action of Manager is given below
```javascript
{
    "name":"Shaikh Hafiz",
    "email":"hafiz@hafiz.hafiz",
    "department":"Software",
    "salary":"200000"
}
```

Content body in json format for both POST and PUT action of Employee is given below
```javascript
{
    "name":"Hafiz Ahmed",
    "email":"hafiz@hafiz.hafiz",
    "designation":"developer",
    "salary":"20000"
}
```
