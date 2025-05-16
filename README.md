TConnect - Backend System
Trainer Connect is a backend application built with Spring Boot and MySQL, designed to streamline interaction between Employers and Applicants. This platform supports job postings, applicant management, and profile handling with secure JWT-based authentication.

Whether you're posting jobs or applying for them, Trainer Connect provides a scalable API foundation for building robust job recruitment platforms.

🔧 Tech Stack
💻 Spring Boot (Java)

🛢️ MySQL

🔐 JWT (JSON Web Token) Authentication

☁️ RESTful APIs

📌 Modules & API Overview
1. 🧑‍💼 User Module
Handles authentication and account management:

POST /login - User login

POST /register - New user registration

POST /change-password - Change user password

POST /send-otp - Send OTP to registered contact

POST /verify-otp - Verify the OTP

2. 👤 Profile Module
Manage user profiles:

GET /profile - Fetch current user profile

PUT /profile - Update profile details

3. 🔔 Notification Module
User notifications handling:

GET /notifications/unread - Fetch unread notifications

POST /notifications/read - Mark notifications as read

4. 💼 Job Module
Job posting and application:

POST /jobs - Post a new job

GET /jobs - View all job listings

POST /jobs/{id}/apply - Apply for a job

GET /applications/history - View applicant's job history

PATCH /applications/{id}/status - Change application status

🛡️ Authentication & Security
All critical routes are protected via JWT authentication. Tokens must be included in request headers using the Bearer scheme:

Example:
Authorization: Bearer <your_token>

🚀 Getting Started
To run the application locally:

Clone the repository:
git clone [https://github.com/your-username/tconnect.git](https://github.com/ummed3062/tconnect.git)

Configure MySQL credentials in src/main/resources/application.properties:

spring.datasource.username=yourUsername
spring.datasource.password=yourPassword

Build and run the project:

mvn clean install
mvn spring-boot:run

The application will be available at:
http://localhost:8081

📬 Contribution
Feel free to fork this repository, raise issues, or submit PRs. All contributions that improve performance, design, or functionality are welcome!
