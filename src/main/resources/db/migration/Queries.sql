
USE sesslyn_johnson_corejava_project;
DROP TABLE users;
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

DROP TABLE appointment;
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
       ('Ashely', 'ash@gmail.com', 'Ash@06', 0, '9854678989'),
       ('Sam', 'sam@gmail.com', 'Sam@2312', 0, '6381040916');
       
-- User details
SELECT * 
FROM users
WHERE is_active = 1;

-- Update user
UPDATE users
SET is_designer = true
WHERE id = 6;

-- DELETE user
UPDATE users
SET is_active = true
WHERE id = 4;

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
       (5, 4, 'ash@gmail.com', '9854678989' ,'approved', '2023-08-30', '16:00:00');
       
-- list all designer appointment request
SELECT u.id, u.name, u.email, u.phone_number, a.email,a.phone_number,a.status, a.date, a.time, a.address
FROM users u
JOIN appointment a ON u.id = a.to_user
WHERE u.is_designer = 1;
-- to_user user.email, users.phonenumber,user.name, from_user user.name and appointment full row
-- from_user = id

-- approved appointment
SELECT u.id, u.name, u.email, u.phone_number, a.email,a.phone_number,a.status, a.date, a.time, a.address
FROM users u
JOIN appointment a ON u.id = a.to_user
WHERE u.is_designer = 1 AND a.status = 'approved';
select * from (select * from users) a, (select * from appointment) b where a.id=b.from_user;

select * from (select a.id,a.name,a.email,a.password from (select * from users) a, (select * from appointment) b 
where a.id=b.from_user) c where c.id=3;

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


DROP TABLE styles;
CREATE TABLE IF NOT EXISTS styles (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

-- Create style
INSERT INTO styles ( name ) 
VALUES ("Shabby Chic"),("Minimalism");

SELECT *
FROM styles;

DROP TABLE assets;
CREATE TABLE IF NOT EXISTS assets (
  id INT AUTO_INCREMENT PRIMARY KEY,
  asset_url VARCHAR(500) NOT NULL
);
INSERT INTO assets ( asset_url ) 
VALUES ("https://youtu.be/DFgL3URDOr4");

SELECT *
FROM assets;
DROP TABLE designs;
CREATE TABLE IF NOT EXISTS designs (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  description VARCHAR(5000) NOT NULL,
  location VARCHAR(200) NOT NULL,
  style_id INT NOT NULL,
  created_by INT NOT NULL,
  FOREIGN KEY (style_id) REFERENCES styles (id),
  FOREIGN KEY (created_by) REFERENCES users (id),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO designs (name, description, location, style_id, created_by)
VALUES (
    'Modern 3.5 Bhk Home',
   'Welcome to Bonito Designs! Today, we will take you on a tour of Madhu & Naveen’s ',
    'Chennai',
    1,
    2
);

CREATE TABLE IF NOT EXISTS design_assets (
   id INT AUTO_INCREMENT PRIMARY KEY,
   design_id INT NOT NULL,
   assets_id INT NOT NULL,
    is_active boolean DEFAULT true,
   FOREIGN KEY (design_id) REFERENCES designs (id),
   FOREIGN KEY (assets_id) REFERENCES assets (id)
);

SELECT *
FROM assets;

SELECT *
FROM designs;

SELECT *
FROM design_assets;

INSERT INTO design_assets (design_id, assets_id)
VALUES (1, 1);


