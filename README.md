# Secure Authentication & Biometric Analysis System

This project is a Java-based security framework that implements multi-layered user authentication. It combines traditional credential management with a biometric keystroke analysis engine to verify user identity through unique typing behaviors.

## System Architecture

The application utilizes a modular **Data Access Object (DAO)** pattern to separate database persistence from business logic.

### Core Modules
* **Database Management**: Centralized handling for MySQL connections using JDBC.
* **User Authentication**: Manages account registration, credential hashing, and 2FA secret key storage.
* **Biometric Engine**: Captures real-time keystroke timing dynamics, including dwell time and interval vectors.
* **Behavioral Analysis**: Compares current typing patterns against stored profiles using mean absolute difference calculations to verify legitimacy.
* **Audit Logging**: Tracks and retrieves user login history to monitor for success and failure patterns.

## Technical Stack
* **Language**: Java
* **Database**: MySQL
* **Analysis**: Biometric timing vector comparison

## Database Schema
The system operates on a schema named `secure_auth` with the following tables:
* **`users`**: Stores unique identifiers, usernames, password hashes, and 2FA keys.
* **`keystroke_profiles`**: Contains JSON-encoded timing vectors associated with specific user IDs.
* **`login_attempts`**: Records timestamps and success/failure status for every authentication request.

## Key Features
* **Biometric Verification**: Implements a "relaxed threshold" algorithm to account for natural variations in human typing.
* **Security Integrity**: Uses `PreparedStatement` across all DAO layers to protect against SQL injection.
* **Real-time Monitoring**: The `KeystrokeRecorder` captures nanosecond-precision data during user interaction.

## Getting Started
1. Ensure a MySQL instance is running on port **3306**.
2. Configure the connection parameters (URL, User, Password) within `DatabaseConnection.java`.
3. Initialize the database schema as defined in the models.
4. Run the `main` method in `DatabaseConnection` to verify the environment link.
