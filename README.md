# Customer Order Portal

A web application for clients to view their orders and upcoming deliveries.

## Features

- Client authentication with store-specific data access
- Dashboard with upcoming deliveries (card layout)
- Orders table with filtering capabilities
- Order details with expandable order lines
- Responsive design with modern UI

## Tech Stack

- **Frontend**: Next.js 14 with TypeScript
- **Backend**: Java Spring Boot
- **Database**: PostgreSQL
- **Styling**: Tailwind CSS

## Project Structure

```
Viljandi/
├── frontend/          # Next.js application
├── backend/           # Spring Boot application
└── database/          # Database scripts
```

## Quick Start

1. Start the backend:
   ```bash
   cd backend
   ./mvnw spring-boot:run
   ```

2. Start the frontend:
   ```bash
   cd frontend
   npm install
   npm run dev
   ```

3. Access the application at `http://localhost:3000`
