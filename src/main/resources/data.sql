insert into school(school_name, school_location, school_tell, school_fax, school_url_home, school_url_study_room, school_url_shuttle,
                   school_url_notice, school_url_calendar, school_url_library)
values ('동의대학교', '부산광역시 부산진구 엄광로 176 (가야동)', '051-890-1114', '051-890-1234', 'https://www.deu.ac.kr/www', 'https://lib.deu.ac.kr/lib/SlimaPlus.csp',
        'https://www.deu.ac.kr/www/content/13', 'https://www.deu.ac.kr/www/board/3', 'https://www.deu.ac.kr/www/academic_calendar',
        'https://lib.deu.ac.kr/lib/SlimaPlus.csp');


/*
User Data
id: test_id_@ (1 ~ 10)
password : test_password
*/
insert into users (is_deleted, created_at, updated_at, email, id, name, nickname, password)
values (false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'test@test.com', 'test_id', 'test_name', 'test_nickname',
        '$2a$10$1EZ2ZmciW/XsVs/wbmR45us2SOnLlsrALEZEGiscwWttxUlsnaMnq'),
       (false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'test1@test.com', 'test_id_1', 'test_name_1', 'test_nickname_1',
        '$2a$10$1EZ2ZmciW/XsVs/wbmR45us2SOnLlsrALEZEGiscwWttxUlsnaMnq'),
       (FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'test2@test.com', 'test_id_2', 'test_name_2', 'test_nickname_2',
        '$2a$10$1EZ2ZmciW/XsVs/wbmR45us3SOnLlsrALEZEGiscwWttxUlsnaMnq'),
       (FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'test3@test.com', 'test_id_3', 'test_name_3', 'test_nickname_3',
        '$2a$10$1EZ2ZmciW/XsVs/wbmR45us4SOnLlsrALEZEGiscwWttxUlsnaMnq'),
       (FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'test4@test.com', 'test_id_4', 'test_name_4', 'test_nickname_4',
        '$2a$10$1EZ2ZmciW/XsVs/wbmR45us5SOnLlsrALEZEGiscwWttxUlsnaMnq'),
       (FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'test5@test.com', 'test_id_5', 'test_name_5', 'test_nickname_5',
        '$2a$10$1EZ2ZmciW/XsVs/wbmR45us6SOnLlsrALEZEGiscwWttxUlsnaMnq'),
       (FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'test6@test.com', 'test_id_6', 'test_name_6', 'test_nickname_6',
        '$2a$10$1EZ2ZmciW/XsVs/wbmR45us7SOnLlsrALEZEGiscwWttxUlsnaMnq'),
       (FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'test7@test.com', 'test_id_7', 'test_name_7', 'test_nickname_7',
        '$2a$10$1EZ2ZmciW/XsVs/wbmR45us8SOnLlsrALEZEGiscwWttxUlsnaMnq'),
       (FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'test8@test.com', 'test_id_8', 'test_name_8', 'test_nickname_8',
        '$2a$10$1EZ2ZmciW/XsVs/wbmR45us9SOnLlsrALEZEGiscwWttxUlsnaMnq'),
       (FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'test9@test.com', 'test_id_9', 'test_name_9', 'test_nickname_9',
        '$2a$10$1EZ2ZmciW/XsVs/wbmR45us10SOnLlsrALEZEGiscwWttxUlsnaMnq'),
       (FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'test10@test.com', 'test_id_10', 'test_name_10', 'test_nickname_10',
        '$2a$10$1EZ2ZmciW/XsVs/wbmR45us11SOnLlsrALEZEGiscwWttxUlsnaMnq');

insert into user_authorities (user_id, authorities)
values ('test_id', 'USER'),
       ('test_id', 'MANAGER'),
       ('test_id', 'ADMIN'),
       ('test_id_1', 'USER'),
       ('test_id_1', 'MANAGER'),
       ('test_id_2', 'USER'),
       ('test_id_2', 'MANAGER'),
       ('test_id_3', 'USER'),
       ('test_id_3', 'MANAGER'),
       ('test_id_4', 'USER'),
       ('test_id_4', 'MANAGER'),
       ('test_id_5', 'USER'),
       ('test_id_5', 'MANAGER'),
       ('test_id_6', 'USER'),
       ('test_id_6', 'MANAGER'),
       ('test_id_7', 'USER'),
       ('test_id_7', 'MANAGER'),
       ('test_id_8', 'USER'),
       ('test_id_8', 'MANAGER'),
       ('test_id_9', 'USER'),
       ('test_id_9', 'MANAGER'),
       ('test_id_10', 'USER'),
       ('test_id_10', 'MANAGER');
