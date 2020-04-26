CREATE TABLE reply (
    reply_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,
    INCREMENT BY 1),
    reply_content VARCHAR(250) NOT NULL,
    reply_author VARCHAR(50) NOT NULL,
    ticket_id INTEGER NOT NULL,
    PRIMARY KEY (reply_id),
    FOREIGN KEY (ticket_id) REFERENCES ticket(id)
);

CREATE TABLE replyAttachment (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    filename VARCHAR(255) DEFAULT NULL,
    content_type VARCHAR(255) DEFAULT NULL,
    content BLOB DEFAULT NULL,
    reply_id INTEGER DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (reply_id) REFERENCES reply(reply_id)
);