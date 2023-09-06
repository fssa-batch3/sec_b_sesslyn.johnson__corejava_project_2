
USE sesslyn_johnson_corejava_project;

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    is_designer BOOLEAN DEFAULT false,
    phone_number VARCHAR(50) NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE appointments(
    id INT AUTO_INCREMENT PRIMARY KEY,
    from_user INT NOT NULL,
    to_user INT NOT NULL,
    email VARCHAR(50) NOT NULL,
    phone_number VARCHAR(10) NOT NULL,
    status VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    time TIME NOT NULL,
    address VARCHAR(255),
    FOREIGN KEY (from_user) REFERENCES users (id),
    FOREIGN KEY (to_user) REFERENCES users (id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS styles (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

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

CREATE TABLE IF NOT EXISTS assets (
  id INT AUTO_INCREMENT PRIMARY KEY,
  asset_url VARCHAR(500) NOT NULL,
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS design_assets (
   id INT AUTO_INCREMENT PRIMARY KEY,
   design_id INT NOT NULL,
   assets_id INT NOT NULL,
    is_active boolean DEFAULT true,
   FOREIGN KEY (design_id) REFERENCES designs (id),
   FOREIGN KEY (assets_id) REFERENCES assets (id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

--  User
INSERT INTO users (name, email, password, is_designer, phone_number)
VALUES
       ('Sesslyn', 'sesslyn@gmail.com', 'Sesslyn@12', 0, '6381040916'),
       ('Jerusheya', 'jerusheya@gmail.com', 'Jerusheya@12', 1, '9323255969'),  -- designer
	   ('Ruby', 'ruby@gmail.com', 'Ruby@2703', 0, '8778061351'),
       ('Johnson', 'johnson@gmail.com', 'Johnson@23', 1, '8754797667'), -- designer
       ('Ashely', 'ash@gmail.com', 'Ashely@06', 0, '9854678989'),
       ('Sam Victor', 'sam@gmail.com', 'Sam@2312', 1, '6789598765'), -- designer
       ('Gauri Khan', 'gaurikhan@gmail.com', 'GauriKhan@13', 1, '9952344564'), -- designer
       ('Sunita Kohli', 'sunitakohli@gmail.com', 'SunithaKohli@13', 1, '8778096547'), -- designer
       ('Manit Rastogi', 'manitrastogi@gmail.com', 'ManitRastogi@13', 1, '7899872344'), -- designer
       ('Rajiv Saini', 'rajivsaini@gmail.com', 'RajivSaini@13', 1, '8778061324'),  -- designer
		('Anjum Jung', 'anjumjung@gmail.com', 'AnjumJung@13', 1, '9873456782'), -- designer
       ('Sussanne Khan', 'sussannekhann@gmail.com', 'SussanneKhan@13', 1, '6787685439'), -- designer
       ('Sarah Sham', 'sarahsham@gmail.com', 'SarahSham@13', 1, '8678543456'), -- designer
        ('Deepika', 'deepika@gmail.com', 'Deepika@02', 0, '8678543456'),
        ('Jelsiha', 'jelisha@gmail.com', 'Jelisha@20', 0, '9787896543'); 
       
--  Appointment
INSERT INTO appointments (from_user, to_user, email, phone_number, status, date, time)
VALUES
       (3, 2, 'ruby@gmail.com', '8778061351' , 'approved', '2023-09-10', '10:00:00'),
       (5, 4, 'ash@gmail.com', '8754678989' ,'approved', '2023-08-30', '16:00:00'),
	   (1, 7, 'sesslyn@gmail.com', '6381040916' , 'approved', '2023-09-16', '08:30:00'),
       (14, 8, 'sanjay@gmail.com', '8678543456' ,'approved', '2023-08-15', '11:00:00'), 
       (3, 4, 'ruby@gmail.com', '9934456578' , 'rejected', '2023-07-17', '01:20:00'),
       (5, 8, 'ash@gmail.com', '9854678989' ,'rejected', '2023-07-20', '04:00:00'),
	   (1, 2, 'jenusha@gmail.com', '6381040916' , 'rejected', '2023-08-10', '06:45:00'),
       (14, 7, 'deepika@gmail.com', '8678543456' ,'rejected', '2023-08-05', '19:00:00'),
       (3, 8, 'ruby@gmail.com', '9934456578' , 'waiting_list', '2023-09-11', '01:20:00'),
       (5, 7, 'ash@gmail.com', '9854678989' ,'waiting_list', '2023-09-15', '04:00:00'),
	   (1, 4, 'jenusha@gmail.com', '6381040916' , 'waiting_list', '2023-09-20', '06:45:00'),
       (14, 2, 'deepika@gmail.com', '8678543456' ,'waiting_list', '2023-09-25', '19:00:00');
    
-- Style
INSERT INTO styles ( name ) 
VALUES ("Shabby Chic"),("Minimalism"),("Art Déco style");

 --  Designs    
INSERT INTO designs (name, description, location, style_id, created_by)
VALUES 
(
    'Traditional Family Home',
    'Customer Name: Sonam & Ashish Gupta
    Apartment Size: 1200 sqft.
    Project Value: 10.8 lac
    Project Manager: Irfan Khan
    This traditional home interior by Bonito Designs features a beautifully adorned foyer area, graced with traditional artifacts .
    The living room exudes coziness with traditional masks, clay pottery, and green plants. The dining room boasts a charming yellow wall,
    while the kitchen showcases a vibrant blue-floral printed walls. In the master bedroom, Indian cloth prints adorn the walls,
    complementing golden wall lights, and thoughtful décor. The guest bedroom features ample natural light, a blue slider wardrobe, 
    creating a harmonious traditional ambiance.',
    'Chennai',
    3,
    2
),
(
    'Minimalistic Homes',
    'Customer Name: Johnson & Ruby
    Apartment Size: 2500 sqft.
    Project Value: 30.8 lac
    Project Manager: Robin
    Minimalist homes are characterized by simplicity, clean lines, and uncluttered spaces. 
    Emphasizing "less is more," they focus on essential elements, creating a calming and functional environment. 
    Neutral color palettes, sleek furniture, and smart storage solutions are common. Minimalism promotes a sense of 
    mindfulness, reducing distractions and allowing residents to appreciate the beauty of each carefully curated item. 
    These homes offer a sanctuary of tranquility, promoting a balanced and harmonious lifestyle.',
    'Bangalore',
    2,
    4
);

-- Assets
INSERT INTO assets ( asset_url ) 
VALUES ("https://youtu.be/OzUkvzyBttA"),
("https://youtu.be/DFgL3URDOr4") ,
("https://youtu.be/Hu-TLhYu1wY?si=3PDuCbvGFFNfYTDR"),
("https://youtu.be/edJOyB8cHYw?si=W4ijrYsYzOFabcFm");


-- Design Assets
INSERT INTO design_assets (design_id, assets_id)
VALUES (1, 3), (2, 4);

-- Select query
SELECT *
FROM users;

SELECT *
FROM appointments;

SELECT *
FROM styles;

SELECT *
FROM designs;

SELECT *
FROM assets;

SELECT *
FROM design_assets;

-- Truncate table query
TRUNCATE TABLE design_assets;
TRUNCATE TABLE designs;
TRUNCATE TABLE assets;
TRUNCATE TABLE styles;
TRUNCATE TABLE appointments;
TRUNCATE TABLE users;

-- Drop table query
DROP TABLE design_assets;
DROP TABLE designs;
DROP TABLE assets;
DROP TABLE styles;
DROP TABLE appointments;
DROP TABLE users;