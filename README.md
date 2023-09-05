# dev-samurai-test
## Task Management System
Building a microservices-based task management system that allows users to create, view, update, and delete tasks. The communication
between microservices will be facilitated using Spring Cloud and Kafka.

## Requirements:
1. Set up a new Spring Boot project for each microservice.
2. Create microservices for Task Creation, Task Management, and Task Notification.
3. Use Kafka for communication between microservices for task creation and updates.
4. Implement a front-end using Spring MVC to interact with the Task Management microservice.
5. Use a simple in-memory database for storing task information.

## Microservices include
### Task Creation Microservice
- Implement a RESTful endpoint to create tasks in the Task Creation microservice. ✔
- Publish a Kafka message containing the task information when a new task is created. ✔
### Task Management Microservice
- Consume Kafka messages from the Task Creation microservice. ✔
- Create RESTful endpoints to manage tasks (read, update, delete) in the Task Management microservice. ✔
- Store and retrieve task information using a database via Spring Data JPA. ✔

## Bonus
- Implement user authentication and authorization using Spring Security. - ❌
- Add retry mechanisms for Kafka consumers to handle communication failures. - ✔
- Utilize Docker and Docker Compose to containerize and orchestrate microservices and Kafka (optional) - ✔

# My Work
On Main Branch, I have finish 2 microservices:
- Task Creation: create new Task
- Task Management: save new Task to H2-Database, view with MVC (thymeleaf), update, delete.
![image](https://github.com/thang-39/dev-samurai-test/assets/91838559/a584bb9b-31b9-4792-a122-034ab49e5e18)

# For Running with docker
- install docker
- download docker-compose.yaml file in main branch
- open terminal in the folder contained docker-compose.yaml file and run: ```docker-compose up```
- go to ```http://localhost:5001/create``` to create task or ```http://localhost:5001/list``` to see all tasks

# My Limitation
- Security for microservice is hard, and I am finding way to do,
- Especial in MVC view, I implement this on auth branch but I can not run MVC on TaskManagement service.
- I think it should be run on Api-gateway and do security on this also

# Learning from this challenge
https://thang93.notion.site/dev-samurai-test-df6ea18785d343b59765434438366efb?pvs=4
