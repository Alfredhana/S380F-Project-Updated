CREATE TABLE poll (
    pollid INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1,
    INCREMENT BY 1),
    topic VARCHAR(200) NOT NULL,
    optionone VARCHAR(50),
    optiontwo VARCHAR(50),
    optionthree VARCHAR(50),
    optionfour VARCHAR(50),
    PRIMARY KEY (pollid)
);

