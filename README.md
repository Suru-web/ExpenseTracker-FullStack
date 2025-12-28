# Expense Tracker — Full Stack Application

A full-stack expense tracking application built with **Spring Boot**, **PostgreSQL**, and **React**.
The app allows users to securely track their daily expenses, categorize them, and manage their data through a clean REST API and a simple web interface.

This project was built to demonstrate real-world backend and frontend integration, authentication, and clean API design.

---

## 🚀 Features

### Authentication & Security
* **JWT-based authentication:** Secure login and registration.
* **Stateless backend:** No server-side sessions; scalable architecture.
* **Password hashing:** Uses BCrypt for secure password storage.
* **Protected routes:** User-level authorization via Spring Security.
* **Secure ownership checks:** Users can strictly only access and modify their own data.

### Expense Management
* **CRUD Operations:** Create, view, update, and delete expenses.
* **User Scoping:** All expense data is scoped to the specifically logged-in user.
* **Categorization:** Each expense belongs to a category (Food, Fuel, Rent, etc.).
* **Clean API:** DTO-based API responses (prevents entity leakage).

### Categories
* **Database Driven:** Categories are stored in PostgreSQL.
* **Dynamic Fetching:** Categories are fetched dynamically from the backend.
* **UI Integration:** Dropdown selection available when adding expenses.

### Frontend (React SPA)
* **Login/Register:** dedicated screens with JWT handling.
* **Expense Dashboard:** List view with add & delete functionality.
* **Axios Interceptor:** Automatically attaches the JWT to all outgoing API requests.
* **Lightweight:** Simple, clean UI built with plain CSS (no heavy UI libraries).

---

## 🛠 Tech Stack

### Backend
* **Language:** Java
* **Framework:** Spring Boot
* **Security:** Spring Security, JWT (JSON Web Tokens), BCrypt
* **Database:** PostgreSQL, Spring Data JPA
* **Build Tool:** Maven

### Frontend
* **Library:** React.js
* **HTTP Client:** Axios
* **Styling:** Plain CSS

---

## 📂 Project Structure

```text
Expense-Tracker-FS/
├── Backend/
│   └── Expense-Tracker/
│       ├── controller   # API Endpoints
│       ├── service      # Business Logic
│       ├── repository   # Database Interactions
│       ├── model        # Entities & DTOs
│       ├── security     # JWT & Auth Config
│       └── config       # App Config
│
└── Frontend/
    └── expense-tracker-ui/
        ├── api          # Axios Setup
        ├── auth         # Auth Context/Logic
        ├── pages        # React Pages
        ├── components   # UI Components
        └── styles.css   # Global Styles
