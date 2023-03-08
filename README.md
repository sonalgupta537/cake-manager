
![Logo](https://raw.githubusercontent.com/sonalgupta537/cake-manager/master/CakeManager.png)



## Introduction

Cake Manager is a microservice built as a Spring Boot Application in Java provides simple APIs and JUnit Test cases to work with cakes.

 ->   Add User to get authorization to the Application

 ->   Fetch a list of all cakes.

 ->   Create a new cake.

 ->   Update an existing cake.

 ->   Delete a cake.

The project is deployed in AWS Elastic Beanstalk using AWS Code Pipeline as well as available to clone the repository and run accordingly.

**About Spring Boot:**
Spring Boot helps you to create Spring-powered, production-grade applications and services with absolute minimum fuss. It takes an opinionated view of the Spring platform so that new and existing users can quickly get to the bits they need.

You can use Spring Boot to create stand-alone Java applications that can be started using java -jar or more traditional WAR deployments. We also provide a command-line tool that runs Spring scripts.

**About AWS Elastic Beanstalk:**

AWS Elastic Beanstalk is an orchestration service offered by Amazon Web Services for deploying applications which orchestrates various AWS services.

**Benefits of AWS Elastic Beanstalk:**

AWS Elastic Beanstalk makes it even easier for developers to quickly deploy and manage applications in the AWS Cloud. Developers simply upload their application, and Elastic Beanstalk automatically handles the deployment details of capacity provisioning, load balancing, auto-scaling, and application health monitoring.

**About AWS CodePipeline:**

AWS CodePipeline is a continuous delivery service that enables you to model, visualize, and automate the steps required to release your software.

**Benefits of AWS CodePipeline:**

Automates your software release process, allowing you to rapidly release new features.

Immediately begin to model your software release process.

Model different stages of your software release process.

Easily extended to adapt to specific needs.
## Prerequisites
Java - version 11 or above.

Gradle - version 6 or above.

SpringBoot - version 3
## Run Locally

1. Local Run:

    Download the project from github repository:
    https://github.com/sonalgupta537/cake-manager.git

    Open any IDE(Spring Tool Suite is preferred or IntelliJ Idea), load the project.
    Right click on the project and run as "Spring Boot App".

2. Cloud

    Link: http://cake-manager.us-east-1.elasticbeanstalk.com/

    You do not need to install anything to run the project in AWS Elastic Beanstalk.

    Initially you will get Access Denied error as you do not have authentication for the project.
## API Documentation

The first most thing required to run the endpoints is creating a new User so that later authentication can be performed.
Authentication and Authorization is done for the endpoints using JWT Token with Spring Security.

#### Add new User

```http
  POST 
  Local run: http://localhost:8080/cakes/newUser
  Cloud run: http://cake-manager.us-east-1.elasticbeanstalk.com/cakes/newUser

```

| Request Type| RequestBody | Type     | Description    | Response                 |
| :--------   | :---------- | :--------|:-------------  |:-------------            |
| POST        | userInfo    | `String` | **Required**   |user added to the   system|

Here UserInfo class object is required to authenticate the new user by giving values as json(example):

RequestBody:
{

    "name":"sonal",

    "password":"Pwd2",

    "roles":"ROLE_USER"

}

Here role can be 'ROLE_User' or 'ROLE_ADMIN'

Basic rule is: 
User is allowed to do all the actions except delete.
Admin gets the administrator power to do all the actions.


#### Get a JWT token for the user to get authentication

```http
  POST 
  Local run: http://localhost:8080/cakes/authenticate
  Cloud run: http://cake-manager.us-east-1.elasticbeanstalk.com/cakes/authenticate

```

| Request Type| RequestBody | Type     | Description    | Returns       |
| :--------   | :---------- | :--------|:-------------  |:------------- |
| POST        | AuthRequest | `String` | **Required**   |JWT token      |

Here AuthRequest class object is required to authenticate the new user by giving values as json(example):

RequestBody:
{

    "name":"sonal",

    "password":"Pwd2"

}

After you get the JWT Token, make sure to insert Authorisation  Bearer token with every request ahead.

#### Get all cakes

```http
  GET 
  Local run: http://localhost:8080/cakes/all
  Cloud run: http://cake-manager.us-east-1.elasticbeanstalk.com/cakes/all
  Include JWT Bearer token

```

| Request Type| Response                   |
| :--------   | :----------                | 
| GET         | JSON with all cake details |

Here how it looks as the cakes has already been added from JSON thru post Construct:
[

    {        
        "id": 1,
        "title": "Lemon cheesecake",
        "desc": "A cheesecake made of lemon",
        "image": "https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg"
    },
    {
        "id": 2,
        "title": "victoria sponge",
        "desc": "sponge with jam",
        "image": "http://www.bbcgoodfood.com/sites/bbcgoodfood.com/files/recipe_images/recipe-image-legacy-id--1001468_10.jpg"
    },
    ......
    {
        "id": 20,
        "title": "Birthday cake",
        "desc": "a yearly treat",
        "image": "http://cornandco.com/wp-content/uploads/2014/05/birthday-cake-popcorn.jpg"
    }
]
 
#### Get cake by id

```http
  GET 
  Local run: http://localhost:8080/cakes/getCake/{id}
  Cloud run: http://cake-manager.us-east-1.elasticbeanstalk.com/cakes/getCake/{id}
  Include JWT Bearer token
```

| Request Type| Parameter   | Type     | Description    | Response      |
| :--------   | :---------- | :--------|:-------------  |:------------- |
| GET         | id          | `String` | **Required**   |JSON           |

Here how it looks as the cakes has already been added from JSON thru post Construct:

[
 
    {        
        "id": 1,
        "title": "Lemon cheesecake",
        "desc": "A cheesecake made of lemon",
        "image": "https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg"
    }]


#### Add a new Cake

```http
  POST 
  Local run: http://localhost:8080/cakes/addCake
  Cloud run: http://cake-manager.us-east-1.elasticbeanstalk.com/cakes/addCake
  Include JWT Bearer token
```

| Request Type| RequestBody | Type     | Description    | Returns       |
| :--------   | :---------- | :--------|:-------------  |:------------- |
| POST        | Cake JSON   | 'Cake'   | **Required**   |JSON           |

Here Cake class object in the form of JSON is required to add a new cake and returns the json details of the added cake in the database(example):

RequestBody JSON:

{

        "title": "Cheesecake",
        "desc": "A cheesecake made of lemon",
        "image": "https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg"
}


Response JSON:

{

    "id": 21,
    "title": "Cheesecake",
    "desc": "A cheesecake made of lemon",
    "image": "https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg"
}


#### Update an existing Cake

```http
  PUT 
  Local run: http://localhost:8080/cakes/updateCake
  Cloud run: http://cake-manager.us-east-1.elasticbeanstalk.com/cakes/updateCake
  Include JWT Bearer token
```

| Request Type| RequestBody | Type     | Description    | Returns       |
| :--------   | :---------- | :--------|:-------------  |:------------- |
| PUT         | Cake JSON   | 'Cake'   | **Required**   |JSON           |

Here Cake class object in the form of JSON is required to update an existing cake and returns the json details of the updated cake in the database(example):

RequestBody JSON:

{
    
        "id": 21,
        "title": "Cheesecake",
        "desc": "A cheesecake made of Cheese",
        "image": "https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg"
}


Response JSON:

{

    "id": 21,
    "title": "Cheesecake",
    "desc": "A cheesecake made of Cheese",
    "image": "https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg"
}


#### Delete Cake by id

```http
  DELETE 
  Local run: http://localhost:8080/cakes/deleteCake/{id}
  Cloud run: http://cake-manager.us-east-1.elasticbeanstalk.com/cakes/deleteCake/{id}
  Include JWT Bearer token
```
Point to consider: User should be having ROLE_ADMIN to delete the cake.

| Request Type| Parameter | Type     | Description    | Returns       |
| :--------   | :-------- | :--------|:-------------  |:------------- |
| DELETE      | id        | 'Integer'| **Required**   |void           |

You will get a 403 Access Denied error if you try to delete the cake without being an admin.
## Running Tests

Junit Tests are written to test the APIs in CakeManagerApplicationTests.java file.

To run tests, run the following:
Right click on the application in your IDE and Run as JUnit Tests.



## Deployment

To deploy this locally its already explained in the installation process.

To deploy on cloud steps(Hoping you are already having an account on AWS):

    1. Go to AWS Console, services-> AWS Elastic Beanstalk and create an environment.
       It takes sometime to create the environment, let the health be OK.
    2. Go to AWS Codepipeline, create a new pipeline and fill in all the details required 
       such as Source Provider - GitHub(Authenticate thru your credentials).
    3. You enter into CodeBuild stage and create a project by specifying the image and 
       linux 2 as the system. Here buildspec.yml file is required which is already present
       in the github repository and complete the further steps.
    4. You enter into Code Deploy stage where you mention Elastic Beanstalk with details
       of your created environment.
    5. Hit 'Create Pipeline' button and it starts building. You are good to go your
       environment with the application deployed in it.

Congratulations you have successfully deployed the project.

Now whenever you make changes in github, pipeline will process and update the changes accordingly, there is no need to hit the pipeline run again after change.


