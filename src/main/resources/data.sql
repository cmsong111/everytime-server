insert into university (name, address, zip_code, tell, fax)
VALUES ('경성대학교', '부산광역시 남구 수영로 309 (대연동, 경성대학교)', '48434', '051-663-4114', '051-663-4089'),
       ('고신대학교', '부산광역시 영도구 와치로 194 (동삼동, 고신대학교)', '49104', '051-990-2114', '051-911-2525'),
       ('국립부경대학교', '부산광역시 남구 용소로 45 (대연동, 부경대학교대연캠퍼스)', '48513', '051-629-4114', '051-629-5089'),
       ('국립한국해양대학교', '부산광역시 영도구 태종로 727 (동삼동, 한국해양대학교)', '49112', '051-410-4114', '051-405-2475'),
       ('동명대학교', '부산광역시 남구 신선로 428 (용당동, 동명대학교)', '48520', '051-629-1000', '051-629-0619'),
       ('동서대학교', '부산광역시 사상구 주례로 47 (주례동, 동서대학교)', '47011', '051-313-2001', '051-313-1046'),
       ('동아대학교', '부산광역시 사하구 낙동대로550번길 37 (하단동, 동아대학교)', '49315', '051-200-6114', '051-205-5788'),
       ('동의대학교', '부산광역시 부산진구 엄광로 176 (가야동, 동의대학교)', '47340', '051-890-1114', '051-890-1234'),
       ('부산가톨릭대학교', '부산광역시 금정구 오륜대로 57 (부곡동, 부산가톨릭대학교)', '46252', '051-515-5811', '051-514-1576'),
       ('부산교육대학교', '부산광역시 연제구 교대로 24 (거제동, 부산교육대학교)', '47503', '051-500-7141', '051-505-4908'),
       ('부산대학교', '부산광역시 금정구 부산대학로63번길 2 (장전동, 부산대학교)', '46241', '051-512-0311', '051-512-3368'),
       ('부산외국어대학교', '부산광역시 금정구 금샘로485번길 65 (남산동, 부산외국어대학교)', '46234', '051-509-5000', '051-509-5005'),
       ('신라대학교', '부산광역시 사상구 백양대로700번길 140 (괘법동, 신라대학교)', '46958', '051-999-5000', '051-999-5800');


insert into university_pages (university_id, type, url)
values (1, 'HOME', 'https://www.ks.ac.kr/'),
       (2, 'HOME', 'https://www.kosin.ac.kr/kr/'),
       (3, 'HOME', 'https://www.pknu.ac.kr'),
       (4, 'HOME', 'https://www.kmou.ac.kr'),
       (5, 'HOME', 'https://www.tu.ac.kr'),
       (6, 'HOME', 'https://www.dongseo.ac.kr'),
       (7, 'HOME', 'https://www.donga.ac.kr'),
       (8, 'HOME', 'https://www.deu.ac.kr'),
       (9, 'HOME', 'https://www.cup.ac.kr'),
       (10, 'HOME', 'https://www.bnue.ac.kr'),
       (11, 'HOME', 'https://www.pusan.ac.kr'),
       (12, 'HOME', 'https://www.bufs.ac.kr'),
       (13, 'HOME', 'https://www.silla.ac.kr');

-- 최고 관리자 계정 (id: admin, password: admin)
-- 일반 사용자 계정 (id: user + 숫자, password: user)
INSERT INTO users (id, name, nickname, email, password, entrance_year, university_id, profile_image, is_deleted, created_at, updated_at)
VALUES ('admin', '슈퍼 관리자', '슈퍼 관리자', 'admin@admin.com', '$2a$10$Q6C5yL2bm63Z9Y7bjisXVeXbVcVBrkwFtWy.uypxg17OcXFkcASji', 2019, 1,
        'https://picsum.photos/id/1/200/300', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('user1', '일반 사용자', '일반 사용자', 'user1@user.com', '$2a$10$LVWdE/9aFCjJlYLZdt735.pQ67duGC4danDyMiCVYFFD6eMbELwym', 2019, 1,
        'https://picsum.photos/id/2/200/300', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('user2', '일반 사용자', '일반 사용자', 'user2@user.com', '$2a$10$LVWdE/9aFCjJlYLZdt735.pQ67duGC4danDyMiCVYFFD6eMbELwym', 2019, 2,
        'https://picsum.photos/id/3/200/300', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('user3', '일반 사용자', '일반 사용자', 'user3@user.com', '$2a$10$LVWdE/9aFCjJlYLZdt735.pQ67duGC4danDyMiCVYFFD6eMbELwym', 2019, 3,
        'https://picsum.photos/id/4/200/300', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('user4', '일반 사용자', '일반 사용자', 'user4@user.com', '$2a$10$LVWdE/9aFCjJlYLZdt735.pQ67duGC4danDyMiCVYFFD6eMbELwym', 2019, 4,
        'https://picsum.photos/id/5/200/300', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('user5', '일반 사용자', '일반 사용자', 'user5@user.com', '$2a$10$LVWdE/9aFCjJlYLZdt735.pQ67duGC4danDyMiCVYFFD6eMbELwym', 2019, 5,
        'https://picsum.photos/id/6/200/300', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('user6', '일반 사용자', '일반 사용자', 'user6@user.com', '$2a$10$LVWdE/9aFCjJlYLZdt735.pQ67duGC4danDyMiCVYFFD6eMbELwym', 2019, 6,
        'https://picsum.photos/id/7/200/300', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('user7', '일반 사용자', '일반 사용자', 'user7@user.com', '$2a$10$LVWdE/9aFCjJlYLZdt735.pQ67duGC4danDyMiCVYFFD6eMbELwym', 2019, 7,
        'https://picsum.photos/id/8/200/300', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('user8', '일반 사용자', '일반 사용자', 'user8@user.com', '$2a$10$LVWdE/9aFCjJlYLZdt735.pQ67duGC4danDyMiCVYFFD6eMbELwym', 2019, 8,
        'https://picsum.photos/id/9/200/300', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


insert into user_role(user_id, role)
VALUES ('admin', 'ADMIN'),
       ('admin', 'USER'),
       ('user1', 'USER'),
       ('user2', 'USER'),
       ('user3', 'USER'),
       ('user4', 'USER'),
       ('user5', 'USER'),
       ('user6', 'USER'),
       ('user7', 'USER'),
       ('user8', 'USER');


insert into board (name, description, board_type, university_id, manager_id, is_deleted, created_at, updated_at)
values ('자유게시판', '자유게시판', 'DEFAULT', 8, 'admin', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('비밀게시판', '비밀게시판', 'DEFAULT', 8, 'admin', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('졸업생게시판', '졸업생게시판', 'DEFAULT', 8, 'admin', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('새내기게시판', '새내기게시판', 'DEFAULT', 8, 'admin', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('시사·이슈', '시사·이슈', 'DEFAULT', 8, 'admin', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('장터게시판', '장터게시판', 'DEFAULT', 8, 'admin', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('정보게시판', '정보게시판', 'DEFAULT', 8, 'admin', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('이벤트게시판', '이벤트게시판', 'DEFAULT', 8, 'admin', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('홍보게시판', '홍보게시판', 'DEFAULT', 8, 'admin', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('동아리·학회', '동아리·학회', 'DEFAULT', 8, 'admin', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('취업·진로', '취업·진로', 'CAREER', 8, 'admin', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('간호학과 게시판', '간신히 호흡하는 학과 이야기', 'MAJOR', 8, 'admin', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('한의학과 게시판', '한의학과 이야기 :)', 'MAJOR', 8, 'admin', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('게임 같이하실분~~', '게임 같이하실분~~', 'ETC', 8, 'admin', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('🙏소원 비는곳🙏', '🙏소원 비는곳🙏', 'ETC', 8, 'admin', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('❤️동의인 미팅 남자 소개 & 여자 소개💙', '❤️동의인 미팅 남자 소개 & 여자 소개💙', 'ETC', 8, 'admin', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('끝말잇기하는 게시판', '끝말잇기하는 게시판', 'ETC', 8, 'admin', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('불꽃의 화풀이방', '불꽃의 화풀이방', 'ETC', 8, 'admin', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('애니게시판', '애니게시판', 'ETC', 8, 'admin', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('덕질게시판', '덕질게시판', 'ETC', 8, 'admin', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('🏋🏻헬스/운동 게시판🏋🏻‍♂️', '🏋🏻헬스/운동 게시판🏋🏻‍♂️', 'ETC', 8, 'admin', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('게시판 찾기', '게시판 찾기', 'ETC', 8, 'admin', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

