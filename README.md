# **Movie API (Spring Boot)**

This project provides a **RESTful API** for managing movies in a **Movie Dashboard** application. The backend is built with **Spring Boot**, supporting **user authentication, movie management, and OMDb API integration**.

🔗 **Frontend Repo**: [Movie Dashboard (Angular)](https://github.com/salma-4/Movie_Dashboard)


## **Setup & Installation**
### **1️⃣ Prerequisites**
Ensure you have:
- **Java 17+**
- **Maven**
- **PostgreSQL/MySQL**

### **2️⃣ Clone the Repository**
```bash
git clone https://github.com/salma-4/Movie_API

```

### **3️⃣ Configure the Database**
Update `application.properties` or `application.yml` with your database credentials:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/moviedb
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### **4️⃣ Install Dependencies & Run**
```bash
mvn clean install
mvn spring-boot:run
```

## **Tech Stack**
- **Spring Boot 3** (Backend)
- **Spring Security** (JWT & Role-based Access)
- **Spring Data JPA** (Database Interaction)
- **MySQL** (Database)
- **JWT (JSON Web Token)** (Authentication)
- **OMDb API** (Fetching Movie Data)

[//]: # (- **Swagger/OpenAPI** &#40;API Documentation&#41;)


## **Features**
### **🎬 Movie Management**
- **Fetch Movies**: Retrieve movies from OMDb API or the database.
- **Add Movies**: Admins can add movies to the database.
- **Delete Movies**: Admins can delete movies.
- **Pagination**: Supports paginated movie listings.

### **🛡️ Authentication & Authorization**
- **User Registration & Login** using JWT.
- **Role-based Access Control**:
    - `ADMIN`: Can **add, delete, and manage** movies.
    - `USER`: Can **search and view** movies.

---
/dashboard-api/v1/user/movies/movie?title=home
## **API Endpoints**
| HTTP Method | Endpoint                                                                 | Description                                                             | Access |
|-------------|--------------------------------------------------------------------------|-------------------------------------------------------------------------|--------|
| `POST`      | `/dashboard-api/v1/auth/register`                                        | Register a new user                                                     | Public |
| `POST`      | `/dashboard-api/v1/auth/login`                                           | Authenticate user & return JWT                                          | Public |
| `GET`       | `dashboard-api/v1/admin/movies"`                                         | Get all movies (paginated)                                              | Admin  |
| `GET`       | `/dashboard-api/v1/admin/movies/{id}`                                    | Get movie details                                                       | Admin  |
| `POST`      | `/dashboard-api/v1/admin/movies`                                         | Add a new movie                                                         | Admin  |
| `DELETE`    | `/dashboard-api/v1/admin/movies/{id}`                                    | Delete a movie                                                          | Admin  |
| `GET`       | `/dashboard-api/v1/user/movies/movie?tilte={}`                           | Get movies in DB by title (pagination)                                  | USER   |
| `GET`       | `/dashboard-api/v1/user/movies`                                          | Get all movies in DB                                                    | USER   |
| `GET`       | `/dashboard-api/v1/user/movies/{id}`                                     | Get Movie details                                                       | USER   |
| `GET`       | `/dashboard-api/v1/admin/omdb/movies?imdbId=&title=&imdbId=&type=&year=` | Get movies (params can be null but entering imdbId or title(Pagination) | Admin  |

--