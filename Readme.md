# 📝 NoteKeeper – Kotlin + Spring Boot + MongoDB

A simple cloud-based note-taking backend application built with **Spring Boot (Kotlin)**, **MongoDB**, and **JWT-based email/password authentication**. Users can register, login, and perform CRUD operations on their personal notes.

---

## 🚀 Features

- 🔐 User Registration & Login with email & password
- 🛡️ JWT Authentication
- 📒 Create, Read, Update, Delete (CRUD) Notes
- 🧩 MongoDB for data persistence
- ⚡ RESTful API ready for Postman testing

---

## 🛠️ Tech Stack

- **Language:** Kotlin
- **Framework:** Spring Boot
- **Database:** MongoDB
- **Authentication:** JWT (JSON Web Tokens)
- **Build Tool:** Gradle

---

## 📦 Setup Instructions

### 1. 🧰 Prerequisites

- Java 17+
- Kotlin
- MongoDB
- Postman (for API testing)

### 2. 🛠️ Clone the repository

```bash
git clone https://github.com/rishi2339/SpringBoot_Note.git
cd SpringBoot_Note
```

---

## 📬 API Endpoints

### 🔐 Authentication

| Method | Endpoint       | Description             |
|--------|----------------|-------------------------|
| POST   | `/auth/register` | Register a new user     |
| POST   | `/auth/login`  | Login and get JWT token |
| POST   | `/auth/refresh`    | Refresh expired token   |

**📝 Sample Request Body (JSON):**
```json
{
  "email": "user@example.com",
  "password": "yourpassword"
}
```

**✅ Sample Response:**
```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2ODg5ZGU3OGY3MjE0NzY1ZDhiZTRhZTQiLCJ0eXBlIjoiYWNjZXNzIiwiaWF0IjoxNzUzODY2MDA1LCJleHAiOjE3NTM4NjY5MDV9._g4MwIBTy9d5a00tIbuPLT8X-55thRDwYtV_lywJpjg",
  "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2ODg5ZGU3OGY3MjE0NzY1ZDhiZTRhZTQiLCJ0eXBlIjoicmVmcmVzaCIsImlhdCI6MTc1Mzg2NjAwNSwiZXhwIjoxNzU2NDU4MDA1fQ.5ewOc4QmFpwL4480cqeoCQuV5ipa5wLEDOBnOn0ApCk"
}
```

**🔄 Refresh Token Request:**
```json
{
  "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2ODg5ZGU3OGY3MjE0NzY1ZDhiZTRhZTQiLCJ0eXBlIjoicmVmcmVzaCIsImlhdCI6MTc1Mzg2NjAwNSwiZXhwIjoxNzU2NDU4MDA1fQ.5ewOc4QmFpwL4480cqeoCQuV5ipa5wLEDOBnOn0ApCk"
}
```

**🔄 Refresh Token Response:**
```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2ODg5ZGU3OGY3MjE0NzY1ZDhiZTRhZTQiLCJ0eXBlIjoiYWNjZXNzIiwiaWF0IjoxNzUzODY2MDA1LCJleHAiOjE3NTM4NjY5MDV9._g4MwIBTy9d5a00tIbuPLT8X-55thRDwYtV_lywJpjg",
  "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2ODg5ZGU3OGY3MjE0NzY1ZDhiZTRhZTQiLCJ0eXBlIjoicmVmcmVzaCIsImlhdCI6MTc1Mzg2NjAwNSwiZXhwIjoxNzU2NDU4MDA1fQ.5ewOc4QmFpwL4480cqeoCQuV5ipa5wLEDOBnOn0ApCk"
}
```

---

### 🗒️ Notes (Authenticated Routes – JWT Required)

> All the endpoints below **require** a valid JWT token in the `Authorization` header:

**📎 Required Header Example:**
```
Authorization: Bearer <your.jwt.token>
Content-Type: application/json
```

| Method | Endpoint      | Description              |
|--------|---------------|--------------------------|
| GET    | `/notes`      | Get all  notes       |
| POST   | `/notes`      | Create a new note        |
| POST   | `/notes`      | Update an existing note  |
| DELETE | `/notes/{id}` | Delete a note            |

---

### 📝 Sample Note JSON

**Request Body for Creating a Note:**
```json
{
  "title": "Meeting Notes",
  "content": "Discussed the roadmap for Q3 deliverables."
}
```

**Response:**
```json
[
  {
    "id": "6889e052f7214765d8be4ae6",
    "title": "Meeting Notes",
    "content": "Discussed the roadmap for Q3 deliverables.",
    "color": 0,
    "createdAt": "2025-07-30T09:05:22.851Z"
  }
]
```

### 🔁 Note Update (via POST to /notes)
To update an existing note, send a POST request to /notes with the full note payload (including the existing note's id).

**✅ Required Body (JSON):**

```json
{
"id": "6889e15af7214765d8be4ae7",
"title": "Updated Note",
"content": "Content",
"color": 4
}
```
**🔔 If the id exists, the note will be updated. If not, it may create a new note or return an error depending on how your backend handles it.**

---

## 📁 Project Structure

```
src/
├── main/
│   ├── kotlin/
│   │   └── com/springBoot/note_app/
│   │       ├── controllers/            # REST controllers
│   │       ├── database/
│   │       │   ├── model/              # Data models (User, Note)
│   │       │   └── repository/         # MongoDB repositories
│   │       ├── security/               # JWT config & filters
│   │       ├── GlobalValidationHandler # Handles validation exceptions
│   │       └── NoteAppApplication.kt   # Main entry point
│   └── resources/
│       └── application.properties      # Spring Boot config
```


### 🙌 Contributing
Pull requests are welcome! For major changes, please open an issue first to discuss what you’d like to change.
