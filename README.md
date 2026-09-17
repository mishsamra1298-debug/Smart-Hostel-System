# Smart-Hostel-System
Java-based hostel complaint and maintenance management system with student, worker, and admin modules, automated priority assignment, analytics, file storage, and multithreading.

The system provides separate interfaces for **Students, Maintenance Workers, and Administrators**, while demonstrating important Java programming concepts such as **Object-Oriented Programming, Collections, Exception Handling, File I/O, Enumerations, Multithreading, and Modular Package Design**.

---

## 📌 Project Overview

Managing hostel maintenance complaints manually can result in delayed responses, poor tracking, repeated complaints, and difficulty monitoring unresolved issues.

The **Smart Hostel Complaint & Maintenance Management System** provides a centralized console-based solution where:

- Students can register and submit complaints.
- Complaints are automatically assigned a priority.
- Maintenance workers can accept and update complaints.
- Administrators can monitor the complete complaint system.
- Complaint statistics can be analyzed.
- Complaint records can be saved to a file.
- A notification thread can monitor complaint statuses.

The system is designed with a modular architecture so that additional features can be integrated in the future.

---

## 🎯 Objectives

The main objectives of the project are:

1. Digitize hostel complaint registration and management.
2. Provide role-based access for Students, Workers, and Admins.
3. Automatically determine complaint priority.
4. Track complaints through different maintenance stages.
5. Provide complaint statistics and resolution rates.
6. Implement proper input validation and exception handling.
7. Demonstrate Java Collections and File Handling.
8. Demonstrate Multithreading through background notifications.
9. Maintain a clean and modular Java project architecture.
10. Provide a foundation that can later be extended to a database-backed application.

---

## 🚀 Key Features


### 👨‍🎓 Student Module

Students can:

- Register as a new student.
- Login securely using email and password.
- View their profile.
- Submit hostel complaints.
- Select complaint categories.
- Enter complaint descriptions.
- Specify complaint locations.
- Automatically receive complaint priority.
- Search complaints using Complaint ID.
- Cancel complaints.
- View complaint status.

---

### 🔧 Maintenance Worker Module

Maintenance workers can:

- Login using worker credentials.
- View available complaints.
- Accept/assign complaints.
- Update complaints to `IN_PROGRESS`.
- Mark completed complaints as `RESOLVED`.
- Track complaint status during maintenance operations.

---

### 👨‍💼 Admin Module

Administrators can:

- Login to the administration panel.
- View all registered users.
- View all complaints.
- Assign workers to complaints.
- Update complaint status.
- Search complaints.
- View complaint analytics.
- Monitor submitted, assigned, in-progress, resolved, and cancelled complaints.

---

## 🧠 Smart Priority Assignment

The system contains an automatic priority mechanism.

Complaint categories are mapped to priorities as follows:

| Complaint Category | Priority |
|-------------------|----------|
| Electrical | HIGH |
| Water Leakage | HIGH |
| AC | MEDIUM |
| Fan | MEDIUM |
| WiFi | MEDIUM |
| Other | LOW |

This allows urgent maintenance issues to be identified quickly.

### Example

If a student submits:

```text
Category: Electrical
Description: Electrical socket is sparking
Location: A-101

Technologies Used*
Technology	Usage
Java	Application development
OOP	Classes, inheritance, abstraction, polymorphism and encapsulation
ArrayList	In-memory data management
Exception Handling	Input validation and error handling
File I/O	Saving and reading complaint records
Multithreading	Background notification processing
Enum	Complaint status and priority management
VS Code	Development environment
Git/GitHub	Version control and project management

*Author*

[Sameeksha Mishra]-[25BAI10859]
