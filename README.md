# tconnect

Trainer Connect API (Spring Boot + MySQL)
Trainer Connect is a backend REST API developed using Spring Boot and MySQL to facilitate smooth interaction between Employers and Applicants. It offers core functionality such as user authentication, job posting, profile management, notifications, and application tracking — all secured using JWT-based authentication.

🔐 Secure. ⚙️ Scalable. 📲 Developer-friendly.

🧰 Tech Stack

Spring Boot (Java)

MySQL Database

JWT (JSON Web Token) Authentication

REST API (Stateless)

📦 Available APIs

👤 User APIs

POST /login — Authenticate user and return JWT

POST /register — Register new user

POST /change-password — Update password

POST /send-otp — Send OTP to user

POST /verify-otp — Verify OTP

📄 Profile APIs

GET /profile — Retrieve user profile

PUT /profile — Update user profile

🔔 Notification APIs

GET /notifications/unread — Get all unread notifications

POST /notifications/read — Mark notifications as read

💼 Job APIs

POST /jobs — Post a new job

GET /jobs — Retrieve available job listings

POST /jobs/{id}/apply — Apply to a specific job

GET /applications/history — View applicant’s job application history

PATCH /applications/{id}/status — Update application status (e.g., accepted, rejected)

🔒 Authentication

All protected endpoints require JWT tokens passed via the Authorization header:

Authorization: Bearer your_token_here

🚀 Getting Started (Development Setup)

Clone the repository:
git clone [https://github.com/ummed3062/tconnect](https://github.com/ummed3062/tconnect)

Set up database credentials in:
src/main/resources/application.properties

Run the application:
mvn spring-boot:run

Application runs at: http://localhost:8081

🤝 Contributing

Open to contributions! Whether it’s bug fixes, enhancements, or documentation improvements — feel free to fork and submit a pull request.
If you have any questions or issues, please contact the developer at ummedsingh3062000@gmail.com.

