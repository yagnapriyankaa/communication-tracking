# Email Tracking Service

## Overview
The Email Tracking Service provides a user-friendly simulation for tracking email request statuses in both an email service and **Salesforce Marketing Cloud (SFMC)**. This tool was developed as part of an internship project using **mock data** to create a functional model.

### Purpose
This project demonstrates a simplified tracking interface for email request statuses, focusing on testing and visualization, without relying on live API connections or databases.

### Problem Statement
Without an effective way to monitor the SFMC email service statuses, users would have difficulty determining whether messages have been delivered or understanding their current status.

### Solution
This working model demonstrates a **submission interface** for tracking email statuses and a **visual table** to display each request’s details.

---

## Features

- **Submission Interface**: Users submit requests using mock fields like `Batch ID` and `Account ID`.
- **Visual Status Table**: Displays mock request details such as Batch ID, Scheduled Date, Email Status, and SFMC Status.
- **Error Handling**: Shows mock error messages for failed requests.

---

## Tech Stack

- **Frontend**: Angular, Angular Material
- **Backend**: Spring Boot, Gradle

---

## Project Structure

- **Frontend Folder**: Contains Angular code for the UI and client-side logic.
- **Backend Folder**: Contains Spring Boot code for managing mock data and REST API simulation.

---

## Installation and Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/communication-tracking.git
   ```
2. Follow the README files in the **backend** and **frontend** folders for specific setup instructions
