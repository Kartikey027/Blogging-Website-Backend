# 📝 Blogify Backend - A Blogging Platform REST API

This is a fully functional **Blogging Platform Backend** built with **Java Spring Boot**, featuring secure **JWT-based authentication**, **post management**, **comments**, **likes**, and more. It serves as a strong backend foundation for a full-stack blog application.

---

## 🚀 Features

✅ User Registration & Login  
✅ JWT Authentication & Authorization  
✅ Create / Read / Update / Delete (CRUD) Posts  
✅ Comment on Posts  
✅ Like/Unlike Posts  
✅ View Like Count & Like Details  
✅ Update User Profile (bio, profile image URL)  
✅ Secure endpoints using Spring Security  
✅ Clean architecture using DTOs & MapStruct  
✅ Unique constraints on likes (1 user ↔ 1 post)  
✅ RESTful APIs with proper status codes  
✅ Timestamp tracking on posts, comments, likes

---

## 📦 Tech Stack

| Layer       | Technology                     |
|------------|---------------------------------|
| Language    | Java 21                        |
| Framework   | Spring Boot 3.2+               |
| Security    | Spring Security + JWT          |
| ORM         | Hibernate (JPA)                |
| Database    | H2 / MySQL (dev mode)          |
| Build Tool  | Maven                          |
| Mapping     | MapStruct                      |
| Validation  | Jakarta Validation (BeanValidation) |
| Dev Tools   | Spring Boot DevTools           |

---

## 📂 Project Structure
com.kartikey.blog
│
├── controller # REST Controllers (Post, Auth, Comment, Like)
├── service # Business Logic Interfaces and Implementations
├── model # JPA Entities (User, Post, Comment, Like, Tag)
├── repository # Spring Data JPA Repositories
├── dto # Data Transfer Objects (PostRequest, PostResponse, etc.)
├── mapper # MapStruct mappers for DTO <-> Entity
├── config # Spring Security & App Config
├── security # JWT Filter, Util, UserDetails classes
└── BlogApplication # Main class


---

## 🔐 Authentication

All secured endpoints require a **JWT Bearer Token**.

### 🔐 Auth Flow
1. `POST /api/auth/register` – Create a user
2. `POST /api/auth/login` – Returns JWT token
3. Set `Authorization: Bearer <token>` in headers

---

## 🔧 Setup & Run

### ✅ Prerequisites
- Java 21
- Maven
- MySQL

### 🛠 Build & Run

# Clone the repository
git clone https://github.com/your-username/blogify-backend.git
cd blogify-backend

# Build the project
mvn clean install

# Run the project
mvn spring-boot:run
