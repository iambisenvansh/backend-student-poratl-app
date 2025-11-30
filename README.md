# 🔐 Secure AI Gateway – Backend

A backend system built with **Spring Boot** that provides:
- JWT-based Authentication & Authorization
- AI-powered Threat Detection on each API request
- Adaptive Rate Limiting
- Dynamic Blacklisting using Redis + MongoDB
- Request Logging for Security Analytics

This backend will later be integrated with a monitoring dashboard (frontend).

---

## 🧠 Key Features

| Module | Description |
|--------|-------------|
| **Auth Service** | User register/login + JWT token |
| **Threat Analyzer** | Uses AI model to classify malicious traffic |
| **Rate Limiter** | Adjusts API limits dynamically based on risk score |
| **Dynamic Blacklist** | Temporary block via Redis & permanent block via MongoDB |
| **Request Logging** | Tracks all API calls with threat classification |

---

## 🏗️ Tech Stack

- **Java 17+**
- **Spring Boot 3**
- Spring Security, JWT
- Spring Data MongoDB
- Redis (for caching blacklist)
- Maven
- OpenAI API (or Local ML model later)
- Docker (optional deployment)

---

## 📂 Project Structure

```bash
secure-ai-gateway-backend/
│── src/
│   └── main/
│       └── java/com/vansh/secureaigateway/
│           ├── config/        # Security, CORS, etc.
│           ├── controller/    # REST Controllers
│           ├── dto/           # Request/Response models
│           ├── model/         # MongoDB Entities
│           ├── repository/    # Mongo Repositories
│           ├── security/      # JWT filters & utils
│           ├── service/       # Business logic
│           └── SecureAiGatewayApplication.java
└── pom.xml

⚙️ Setup & Installation
1️⃣ Clone the repository
git clone https://github.com/<your-username>/secure-ai-gateway-backend.git
cd secure-ai-gateway-backend

2️⃣ Configure MongoDB & Redis

Update application.properties:

spring.data.mongodb.uri=mongodb://localhost:27017/secure-gateway
spring.redis.host=localhost
spring.redis.port=6379
jwt.secret=YOUR_SECRET_KEY
openai.api.key=YOUR_OPENAI_API_KEY

3️⃣ Install dependencies & run
mvn clean install
mvn spring-boot:run

🔐 Authentication Flow
Endpoint	Method	Description
/auth/register	POST	Create new user
/auth/login	POST	Generate JWT token
Secured APIs	Any	Require valid JWT

JWT must be passed in headers:

Authorization: Bearer <token>

🧪 Testing with Postman

Register user

Login → get JWT token

Use token to access protected routes

Attack simulation → Check AI detection & blacklist

📊 Future Enhancements

Live Security Dashboard (React.js)

WebSocket alerts

Docker + AWS deployment (ECS / EC2 / Lambda)

Local ML Model support

API performance analytics

🧑‍💻 Author

Vansh Bisen
Backend Developer | Cybersecurity Enthusiast

⭐ Show Your Support!

If you like this project, please ⭐ the repository!


---

### After You Add This
We will next:

✔ Add **Architecture Diagram** in README  
✔ Add **Auth module** code  
✔ Add **Threat detection API logic**  
✔ Add **Logging + Rate limiting + Redis**  
✔ Add **Screenshots** once dashboard is ready

---

Would you like me to now send:

A) Full **Authentication Module** (User model, repository, DTOs, JWT security, APIs)  
or  
B) Architecture Diagram next?

Just reply: **A** or **B** 🚀

