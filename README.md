# Hotel Management System

## Overview

This Hotel Management System is a web application designed to simplify the operations of managing a hotel. The system allows for handling room bookings, managing room statuses (available/unavailable), and supports operations like adding, editing, and removing rooms and hotels from the system. Built with Spring Boot and Thymeleaf, this application provides a user-friendly interface for both hotel staff and guests.

## Features

- **Room Management**: Add, edit, and remove rooms. View all rooms, available rooms, and unavailable rooms.
- **Hotel Management**: Add, edit, and remove hotels. Search hotels by name and city.
- **Booking Management**: Interface to manage bookings (Future Scope).
- **Dynamic Room Status**: Mark rooms as available or unavailable.

## Technologies Used

- **Spring Boot**: For creating the application framework.
- **Thymeleaf**: For server-side rendering of HTML templates.
- **Spring Data JPA**: For database operations.
- **H2 Database**: An in-memory database for development and testing.
- **Maven**: For dependency management and build process.

## Getting Started

### Prerequisites

- JDK 1.8 or later
- Maven 3.2+

### Running the Application

1. Clone the repository:
   ```
   git clone https://github.com/yourusername/hotel-management-system.git
   ```
2. Navigate to the project directory:
   ```
   cd hotel-management-system
   ```
3. Run the application using Maven:
   ```
   mvn spring-boot:run
   ```
4. Access the application in your web browser at `http://localhost:8080/`.

## Usage

- **Manage Rooms**: Navigate to `/room` to view, add, edit, or delete rooms.
- **Manage Hotels**: Navigate to `/hotel` to view, add, edit, or delete hotels.
- **Filter Rooms**: Use the `/room/available` and `/room/unavailable` endpoints to filter rooms by availability.

## Contributing

Contributions are welcome! Please feel free to submit pull requests with new features, bug fixes, or improvements to the documentation.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- This project is part of a learning journey to better understand Spring Boot and web application development.
- Thanks to all the open source libraries and tools that made this project possible.

