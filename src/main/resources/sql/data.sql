INSERT INTO users(name, login, password)
VALUES ('АлексейА','user', '{noop}user'),
       ('ВасилийВ','user1', '{noop}user1');

INSERT INTO authorities(user_id, authority)
VALUES ( 1, 'ROLE_USER'),
       (2, 'ROLE_USER');

INSERT INTO messages(text, msg_date_time, user_id)
VALUES ('Hello vsem', '2015-05-30 10:00:00', 1),
       ('Привет', '2015-05-30 10:01:00', 2)


