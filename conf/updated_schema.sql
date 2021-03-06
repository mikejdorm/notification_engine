USE notification_engine;

DROP TABLE IF EXISTS messages;


CREATE TABLE messages(
id VARCHAR(150) NOT NULL,
from_number VARCHAR(50) NOT NULL,
to_number VARCHAR(50) NOT NULL,
account_id VARCHAR(50) NOT NULL,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    ON UPDATE CURRENT_TIMESTAMP,
content VARCHAR(1600),
message_type VARCHAR(14) NOT NULL,
status VARCHAR(25) DEFAULT "initialized",
PRIMARY KEY(id)
);
