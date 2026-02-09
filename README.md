# Media Management System ("Moes")

Moes is a Java-based media management system that allows students to access and manage media content through different account types. The system supports media playback, point-based access, and persistent storage using file I/O.

---

## Features
- Supports multiple **student account types** (À la carte and Unlimited)
- Allows administrators to **add media** and **enroll students**
- Enables students to **purchase points** and **play media**
- Uses **point-based access control** for media consumption
- Persists data using **file I/O** with BufferedReader and BufferedWriter

---

## How It Works
1. Media and student data are stored in memory using Java collections.
2. Each student is associated with an account type that determines how media is accessed.
3. Media playback requests are validated against the student’s account and available points.
4. System state (media and student data) can be **saved to a file** and **loaded back** to maintain consistency across sessions.

---

## Technologies Used
- Java
- Object-Oriented Programming (OOP)
- Inheritance and Polymorphism
- File I/O
- BufferedReader / BufferedWriter
- Java Collections (ArrayList)

---

## Design Overview
- Applied **object-oriented principles** such as classes, inheritance, and interfaces to create a modular and maintainable architecture.
- Used separate classes for media, students, and account types to ensure clean separation of concerns.
- Implemented a controller-style class to manage interactions between media and students.

---

## Testing
- Built an **interactive test class** to simulate user actions such as:
  - Adding media
  - Enrolling students
  - Purchasing points
  - Playing media
- Used test scenarios to validate system behavior and ensure data integrity.

---

## Author
Sunil Thapa Magar
