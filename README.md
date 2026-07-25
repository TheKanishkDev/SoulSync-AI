<div align="center">

# SoulSync AI

### AI-Powered Modern Matrimonial Platform

An intelligent full-stack matrimonial platform built with **Spring Boot**, **React**, **MySQL**, and **Google Gemini AI** to provide personalized matchmaking, seamless communication, and a modern user experience.

</div>

---

## Overview

SoulSync AI is a modern matrimonial platform that combines traditional matchmaking with Artificial Intelligence to deliver a smarter and more personalized experience.

The platform enables users to create detailed profiles, receive AI-powered partner recommendations, send and manage interest requests, communicate through an integrated chat system, and maintain complete control over their profiles.

The application is built using a scalable client-server architecture where the frontend communicates with the backend through REST APIs, while Google Gemini AI enhances matchmaking with intelligent recommendations.

---

## Key Features

### User Management

- Secure user registration and authentication
- Create, update, and delete matrimonial profiles
- Profile completion tracking
- Personalized dashboard

### AI Matchmaking

- AI-powered profile recommendations
- Personalized suggestions based on user preferences
- Intelligent profile analysis

### Communication

- Send interest requests
- Accept or decline requests
- Real-time chat between matched users

### User Experience

- Responsive interface
- Modern and clean design
- Smooth navigation
- Fast performance

---

## Tech Stack

| Layer | Technologies |
|--------|--------------|
| Frontend | React, Vite, JavaScript, HTML, CSS |
| Backend | Java, Spring Boot, Spring MVC, Spring Data JPA, Hibernate |
| Database | MySQL |
| AI | Google Gemini AI |
| Tools | Git, GitHub, Maven, Postman, IntelliJ IDEA, VS Code |

---

## System Architecture

```
                React Frontend
                       │
                REST API Requests
                       │
             Spring Boot Backend
                       │
              Business Logic Layer
                       │
              Spring Data JPA
                       │
                    MySQL
                       │
             Google Gemini AI
```

---

## Project Structure

```
SoulSync-AI
│
├── Backend
│   ├── src
│   ├── pom.xml
│   └── ...
│
├── Frontend
│   ├── src
│   ├── public
│   └── ...
│
└── README.md
```

---

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/TheKanishkDev/SoulSync-AI.git
```

### Backend

```bash
cd Backend
mvn clean install
mvn spring-boot:run
```

### Frontend

```bash
cd Frontend
npm install
npm run dev
```

---

## Environment Variables

Configure the following environment variable before starting the backend.

```env
GEMINI_API_KEY=YOUR_GEMINI_API_KEY
```

---

## Planned Enhancements

- AI Compatibility Score
- Video & Voice Calling
- Aadhaar Verification
- Push Notifications
- Advanced Search Filters
- Mobile Application
- Multi-language Support
- Payment Gateway Integration

---

## Screenshots

Project screenshots will be added after deployment.

---

## Author

**Kanishka Gupta**

B.Tech Computer Science & Engineering

GitHub: **https://github.com/TheKanishkDev**

---

## License

This project is intended for educational and portfolio purposes.

---

<div align="center">

### ⭐ If you found this project useful, consider giving it a star.

</div>
