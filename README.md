# 📝 Blogify Backend — A Powerful Blogging Platform API

This is a robust and extensible **backend REST API** for a blogging platform built using **Java Spring Boot**. It includes secure **JWT-based authentication**, post/comment/like management, and follows clean architecture principles using DTOs and MapStruct.

---

## 🚀 Features

- ✅ **User Registration & Login**
- ✅ **JWT-based Authentication & Authorization**
- ✅ **Create / Read / Update / Delete (CRUD) for Posts**
- ✅ **Add & View Comments on Posts**
- ✅ **Like / Unlike Posts**
- ✅ **Like Count and User Like Details**
- ✅ **Edit User Profile (bio, profile image, etc.)**
- ✅ **Secure Endpoints with Spring Security**
- ✅ **Unique Like Constraint** (1 user ↔ 1 post)
- ✅ **Proper RESTful Status Codes**
- ✅ **Timestamps** for Posts, Comments, and Likes

---

## 🧰 Tech Stack

| Layer        | Tech                                      |
|--------------|--------------------------------------------|
| Language     | Java 21                                    |
| Framework    | Spring Boot 3.2+                           |
| Security     | Spring Security + JWT                      |
| ORM          | Hibernate (JPA)                            |
| DB           | H2 / MySQL (configurable)                  |
| Build Tool   | Maven                                      |
| DTO Mapping  | MapStruct                                  |
| Validation   | Jakarta Bean Validation                    |
| Dev Tools    | Spring Boot DevTools                       |

---

## 📂 Project Structure

```
com.kartikey.blog
│
├── controller     # REST Controllers (Post, Auth, Comment, Like)
├── service        # Service Interfaces & Business Logic
├── model          # JPA Entities (User, Post, Comment, Like)
├── repository     # Spring Data JPA Repositories
├── dto            # Data Transfer Objects (PostRequest, PostResponse, etc.)
├── mapper         # MapStruct mappers (Entity ↔ DTO)
├── config         # Security Config, JWT Config, Web Config
├── security       # JWT Filter, Token Utils, UserDetails Service
└── BlogApplication.java  # Main Application Entrypoint
```

---

## 🔐 Authentication Flow

All secure endpoints require a **JWT Token** passed in the header.

```http
Authorization: Bearer <your_jwt_token>
```

### 🔑 API Endpoints

| Method | Endpoint               | Description            |
|--------|------------------------|------------------------|
| POST   | `/api/auth/register`   | Register a new user    |
| POST   | `/api/auth/login`      | Authenticate & get JWT |
| GET    | `/api/posts`           | View all posts         |
| POST   | `/api/posts`           | Create a new post      |
| POST   | `/api/posts/{id}/like` | Like/unlike a post     |
| GET    | `/api/posts/{id}`      | Get single post        |
| POST   | `/api/posts/{id}/comment` | Comment on post     |
| PUT    | `/api/users/profile`   | Update profile         |

---

## ⚙️ Setup & Run

### ✅ Prerequisites

- Java 21
- Maven
- MySQL (or use H2 for dev)

### 🛠 Run Instructions

```bash
# Clone the project
git clone https://github.com/Kartikey027/blogify-backend.git
cd blogify-backend

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

---

## 📬 Contact

Feel free to reach out:

[![LinkedIn](https://img.shields.io/badge/LinkedIn-blue?style=for-the-badge&logo=linkedin)](https://www.linkedin.com/in/kartikey-saxena-b16193290)  
[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/Kartikey027)

---

_Contributions and feedback are welcome!_ ⭐
