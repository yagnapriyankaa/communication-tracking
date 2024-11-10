# Email Tracking Service - Backend

The backend, developed as part of an internship project, uses **Spring Boot** and **Gradle** to simulate API responses with mock data, demonstrating an email tracking backend service.

## Features

- **Simulated API Endpoints** for email tracking and status checking using mock data.
- **Error Handling and Retry Mechanism** for failed requests, using mock responses.

---

## API Endpoints

1. **Email Request API**: Returns mock details of email requests.
   - **Endpoint**: `GET /api/email-request`
   - **Query Parameters**:
     - `batchId` (required) – The unique batch ID for the email request.
     - `accountId` (optional) – The user's account ID or GUID.
2. **Fetch Status API**: Returns a mock status from email service and SFMC.
   - **Endpoint**: `GET /api/fetch-status`
   - **Query Parameters**:
     - `batchId` (required)
     - `ateMailRecordId` (required)

---

## Development Setup

### Prerequisites

- Java 17 or higher
- Gradle

### Installation

1. **Navigate to the backend folder**:
   ```bash
   cd communication-tracking/backend
   ```
2. **Run the Application**:
   ```bash
   ./gradlew bootRun
   ```
   This runs the Spring Boot application, which serves mock data for testing.

