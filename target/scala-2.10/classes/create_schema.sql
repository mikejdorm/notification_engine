USE notification_engine; 

DROP TABLE IF EXISTS messages;
DROP TABLE IF EXISTS contacts;

CREATE TABLE contacts(
phone_number VARCHAR(15) NOT NULL, 
city VARCHAR(150), 
state VARCHAR(19), 
zip VARCHAR(15), 
country VARCHAR(50), 
PRIMARY KEY(phone_number)
); 

CREATE TABLE messages(
id VARCHAR(150) NOT NULL, 
from_number VARCHAR(150) NOT NULL, 
account_id VARCHAR(50) NOT NULL, 
phone_number VARCHAR(15) NOT NULL, 
created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
changed_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    ON UPDATE CURRENT_TIMESTAMP, 
content VARCHAR(150),
message_type VARCHAR(14) NOT NULL, 
status VARCHAR(25) DEFAULT "initialized",  
FOREIGN KEY(phone_number) REFERENCES contacts(phone_number), 
PRIMARY KEY(id) 
);