Expense Tracker — Full Stack Application

A full-stack expense tracking application built with Spring Boot, PostgreSQL, and React.
The app allows users to securely track their daily expenses, categorize them, and manage their data through a clean REST API and a simple web interface.

This project was built to demonstrate real-world backend and frontend integration, authentication, and clean API design.

⸻

🚀 Features

Authentication & Security
    •    JWT-based authentication (login & register)
    •    Stateless backend (no server sessions)
    •    Password hashing using BCrypt
    •    Protected routes with user-level authorization
    •    Secure ownership checks (users can only access their own data)

Expense Management
    •    Create, view, update, and delete expenses
    •    Expenses are scoped to the logged-in user
    •    Each expense belongs to a category (Food, Fuel, Rent, etc.)
    •    Clean DTO-based API responses (no entity leakage)

Categories
    •    Categories stored in the database
    •    Categories fetched dynamically from backend
    •    Dropdown category selection while adding expenses
    •    User-scoped categories

Frontend (React SPA)
    •    Login screen with JWT handling
    •    Expense list with add & delete functionality
    •    Category dropdown while adding expenses
    •    Axios interceptor for automatic JWT attachment
    •    Simple, clean UI without heavy UI libraries

⸻

🛠 Tech Stack

Backend
    •    Java
    •    Spring Boot
    •    Spring Security
    •    Spring Data JPA
    •    PostgreSQL
    •    JWT (JSON Web Tokens)
    •    Maven

Frontend
    •    React
    •    Axios
    •    Plain CSS (no UI frameworks)

⸻

📂 Project Structure

Expense-Tracker-FS/
├── Backend/
│   └── Expense-Tracker/
│       ├── controller
│       ├── service
│       ├── repository
│       ├── model
│       ├── security
│       └── config
│
└── Frontend/
    └── expense-tracker-ui/
        ├── api
        ├── auth
        ├── pages
        ├── components
        └── styles.css


⸻

🔐 Authentication Flow
    1.    User registers or logs in
    2.    Backend generates a JWT on successful login
    3.    Frontend stores JWT in localStorage
    4.    Axios interceptor attaches JWT to all API requests
    5.    Backend validates JWT and authorizes requests

⸻

🧠 Key Design Decisions
    •    DTOs instead of entities are used for API responses to avoid serialization issues and keep the API stable.
    •    Authorization checks are enforced at the service layer to prevent horizontal privilege escalation.
    •    JWT authentication is stateless and scalable.
    •    CORS is explicitly configured in Spring Security to support frontend-backend communication.
    •    UI is intentionally kept simple to focus on functionality and maintainability.

⸻

▶️ Running the Project Locally

Backend
    1.    Create a PostgreSQL database:

CREATE DATABASE expense_tracker;


    2.    Update application.properties with DB credentials.
    3.    Run the backend:

./mvnw spring-boot:run



Backend runs on:
http://localhost:8080

⸻

Frontend
    1.    Navigate to frontend directory:

cd Frontend/expense-tracker-ui


    2.    Install dependencies:

npm install


    3.    Start the app:

npm start



Frontend runs on:
http://localhost:3000

⸻

📌 Sample API Endpoints

Method    Endpoint    Description
POST    /auth/register    Register a new user
POST    /auth/login    Login and receive JWT
GET    /expenses    Get user expenses
POST    /expenses    Add a new expense
PUT    /expenses/{id}    Update an expense
DELETE    /expenses/{id}    Delete an expense
GET    /categories    Get available categories


⸻

🧪 Testing
    •    Backend APIs tested using Postman
    •    Frontend tested manually through browser
    •    JWT authentication verified for all protected routes

⸻

📈 Possible Improvements
    •    Edit expense UI
    •    Monthly expense summary (grouped by category)
    •    Better error handling and validation
    •    Pagination for large expense lists
    •    Deployment using Docker / cloud services

⸻

👤 Author

Built by Suraj
(Full-stack project built for learning, practice, and interview preparation)
