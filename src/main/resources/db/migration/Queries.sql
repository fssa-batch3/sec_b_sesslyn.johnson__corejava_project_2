create database if not exists minimal_java;

USE minimal_java;
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    is_designer BOOLEAN DEFAULT TRUE,
    phone_number VARCHAR(50) NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Add indexes to the referenced columns in the 'users' table
ALTER TABLE users ADD INDEX idx_users_id (id);


CREATE TABLE appointment (
    id INT AUTO_INCREMENT PRIMARY KEY,
    from_user INT NOT NULL,
    to_user INT NOT NULL,
    email VARCHAR(50) NOT NULL,
    phone_number VARCHAR(50) NOT NULL,
    status VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    time TIME NOT NULL,
    address VARCHAR(255),
    FOREIGN KEY (from_user) REFERENCES users (id),
    FOREIGN KEY (to_user) REFERENCES users (id),
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create User
INSERT INTO users (name, email, password, is_designer, phone_number)
VALUES
       ('Sesslyn', 'sesslyn@gmail.com', 'Sesslyn@12', 0, '6381040916'),
       ('Jerusheya', 'jerusheya@gmail.com', 'Jerusheya@12', 1, '9323255969'),
         ('Ruby', 'ruby@gmail.com', 'Ruby@27', 0, '8778061351'),
       ('Johnson', 'johnson@gmail.com', 'Johnson@23', 1, '8754797667'),
       ('Ashely', 'ash@gmail.com', 'Ash@06', 0, '9854678989');

-- User details
SELECT * 
FROM users
WHERE is_active = 1;

-- Update user
UPDATE users
SET password = 'Sesslyn@15'
WHERE id = 1 AND is_active = 1;

-- DELETE user
UPDATE users
SET is_active = false
WHERE id = 1 AND is_active = 1;

-- List all designer profile
SELECT * 
FROM users
WHERE is_active = 1 AND is_designer = 1;

-- Designer profile details
SELECT * 
FROM users
WHERE is_active = 1 AND is_designer = 1 AND id = 2;

SELECT *
FROM users;

-- Booked appointment
INSERT INTO appointment (from_user, to_user, email, phone_number, status, date, time)
VALUES
       (3, 2, 'ruby@gmail.com', '8778061351' , 'waiting_list', '2023-08-26', '10:00:00'),
       (5, 4, 'ash@gmail.com', '9854678989' ,'approved', '2023-08-30', '16:00:00'),
       (3, 4, 'ruby@gmail.com', '8778061351' ,'rejected', '2023-08-15', '13:00:00');
       
-- list all designer appointment request
SELECT u.id, u.name, u.email, u.phone_number, a.email,a.phone_number,a.status, a.date, a.time, a.address
FROM users u
JOIN appointment a ON u.id = a.to_user
WHERE u.is_designer = 1;

-- approved appointment
SELECT u.id, u.name, u.email, u.phone_number, a.email,a.phone_number,a.status, a.date, a.time, a.address
FROM users u
JOIN appointment a ON u.id = a.to_user
WHERE u.is_designer = 1 AND a.status = 'approved';

-- rejected appointment
SELECT u.id, u.name, u.email, u.phone_number, a.email,a.phone_number,a.status, a.date, a.time, a.address
FROM users u
JOIN appointment a ON u.id = a.to_user
WHERE u.is_designer = 1 AND a.status = 'rejected';

-- waiting_list appointment
SELECT u.id, u.name, u.email, u.phone_number, a.email,a.phone_number,a.status, a.date, a.time, a.address
FROM users u
JOIN appointment a ON u.id = a.to_user
WHERE u.is_designer = 1 AND a.status = 'waiting_list';

SELECT *
FROM appointment;