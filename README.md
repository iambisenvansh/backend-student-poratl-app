# Student Portal API — Backend (Spring Boot + JWT)

This is the **backend** for the _Student Portal System_.  
It exposes REST APIs for **authentication**, **role-based access**, **courses**, and **rank list / points**.

Frontend repo (Expo / React Native) consumes these APIs.

---

## 🧱 Tech Stack

- **Java 21**
- **Spring Boot 3**
- **Spring Web**
- **Spring Data JPA**
- **Spring Security + JWT**
- **H2 Database** (in-memory; can be swapped with MySQL/PostgreSQL)

---

## 📁 Project Structure (main files)

```bash
src/main/java/com/assignment/finalproject/
  config/
    SecurityConfig.java      # Spring Security + JWT filter configuration
    WebConfig.java           # CORS config (if any)

  controller/
    AuthController.java      # /api/auth/login, /api/auth/signup
    UserController.java      # /api/user/... (rank list, profile, etc.)
    AdminController.java     # /api/admin/... (course management)
    TeacherController.java   # /api/teacher/... (update student points)

  jwt/
    JwtUtils.java            # Generate & validate JWT
    AuthTokenFilter.java     # Reads JWT from Authorization header

  model/
    User.java                # User entity (student/teacher/admin)
    UserType.java            # Enum: STUDENT, TEACHER, ADMIN
    Course.java              # Course entity
    StudentPoints.java       # (or field in User) points for ranking

  payload/
    request/
      LoginRequest.java
      SignupRequest.java
    response/
      JwtResponse.java       # Token + user info

  repository/
    UserRepository.java
    CourseRepository.java

  service/
    UserService.java         # Implements UserDetailsService, signup logic
    CourseService.java       # Course CRUD



⚙️ Configuration
All main config is in src/main/resources/application.properties:
# H2 in-memory DB
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true

# JWT
app.jwtSecret=ThisIsAVerySecretKeyForJwtDemo1234567890
app.jwtExpirationMs=86400000   # 1 day in milliseconds

# (Optional) show Spring Security debug logs
logging.level.org.springframework.security=DEBUG

H2 Console


URL: http://localhost:8080/h2-console


JDBC URL: jdbc:h2:mem:testdb


User: sa


Password: (empty)



🔐 Security & JWT Flow


User calls POST /api/auth/login with email & password.


Spring Security authenticates using UserService.loadUserByUsername().


On success:


JwtUtils.generateJwtToken(user) returns a JWT.


AuthController responds with JwtResponse:
{
  "token": "<JWT>",
  "id": 1,
  "name": "Vansh Bisen",
  "email": "vansh@example.com",
  "type": "STUDENT"
}





Frontend sends this token in Authorization header for protected APIs:
Authorization: Bearer <JWT>



AuthTokenFilter reads and validates the token on each request and sets the SecurityContext.


Roles use UserType:


ROLE_STUDENT


ROLE_TEACHER


ROLE_ADMIN



🌐 REST API Endpoints
Base path for all APIs:
/api

AuthController (/api/auth)


POST /auth/signup


Request body: SignupRequest


Creates new user (default type: STUDENT, or as provided)


Password is hashed using PasswordEncoder before saving.




POST /auth/login


Request body: LoginRequest


Returns JwtResponse with token + user info.





UserController (/api/user)


GET /user/ranklist


Role: STUDENT / TEACHER / ADMIN


Returns a list of students with points sorted descending for rank list.


Example response:
[
  { "id": 2, "name": "Radha Krishna", "email": "radha@example.com", "points": 50 },
  { "id": 3, "name": "Ranveer Singh", "email": "ranveer@example.com", "points": 30 }
]





GET /user/me


Returns current logged in user details.





AdminController (/api/admin)


GET /admin/courses


Role: ADMIN


Returns all courses.




POST /admin/courses


Role: ADMIN


Creates a new course.


Request body:
{
  "code": "CSE101",
  "name": "Data Structures",
  "description": "Intro to DS"
}






TeacherController (/api/teacher)


POST /teacher/students/{id}/points?points=10


Role: TEACHER


Adds/updates points for a student.


Returns updated student info with new point total.





Courses for Students (/api/courses)


GET /courses


Role: any authenticated user


Returns list of all available courses (used by Student dashboard).





🛠️ Running the Project
1. Prerequisites


Java 21


Maven


2. Build & Run
# from backend project root
mvn clean install

# run
mvn spring-boot:run

Server will start at:
http://localhost:8080


🔐 Password Security


We never store plain-text passwords.


UserService.registerNewUser() encodes passwords using PasswordEncoder:


user.setPasswordHash(passwordEncoder.encode(signUpRequest.getPassword()));



On login, Spring Security compares provided password with the stored hash.



✅ Validation & Checks
During signup, backend performs:


Valid email format (via @Email / custom validations on SignupRequest)


Valid mobile format (basic pattern / length)


Duplicate email check using UserRepository.existsByEmail(...)


Duplicate mobile check using UserRepository.existsByMobile(...)


If any validation fails, backend returns 400 Bad Request with an explanatory message, which frontend displays.

📌 Summary


This backend secures all important operations using JWT + Spring Security.


Exposes clean REST APIs for auth, courses, rank list, and points.


Designed to work seamlessly with the React Native + Expo frontend:


/auth/login + /auth/signup for authentication


/user/ranklist, /courses for student dashboard


/admin/courses for course management


/teacher/students/{id}/points for teacher actions




You can extend this further with:


Profile update endpoints (email/mobile/department)


Course enrollment APIs


Pagination, search, audit logs, etc.



If you tell me your exact package name or any extra controllers you added, I can tweak the endpoint list to match them 100%.
