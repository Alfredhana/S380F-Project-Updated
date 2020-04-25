CREATE TABLE reply (
    reply_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,
    INCREMENT BY 1),
    reply_content VARCHAR(250) NOT NULL,
    reply_author VARCHAR(50) NOT NULL,
    ticket_id INTEGER NOT NULL,
    PRIMARY KEY (reply_id),
    FOREIGN KEY (ticket_id) REFERENCES ticket(id)
);