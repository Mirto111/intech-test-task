DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS messages;
DROP TABLE IF EXISTS users;


CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(100),
    login    VARCHAR(100) UNIQUE,
    password VARCHAR(100)
);

CREATE TABLE authorities
(
    user_id   INT,
    authority VARCHAR(50) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);
CREATE UNIQUE INDEX ix_auth_username
    on authorities (user_id, authority);

CREATE TABLE messages
(
    id      SERIAL PRIMARY KEY,
    user_id INT,
    text    VARCHAR   NOT NULL,
    msg_date_time     TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

