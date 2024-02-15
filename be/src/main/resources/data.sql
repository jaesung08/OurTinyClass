-- 멤버
insert into member (id, member_id, password, name, email, birthday, point, role)
values (1, 'park', 'lF7`Oeb?e', 'Keogh', 'mkeogh0@topsy.com', '2023-03-30', 27, 'ROLE_ADMIN');
insert into member (id, member_id, password, name, email, birthday, point, role)
values (2, 'kim', 'cH8\1,y`D|z', 'Nabbs', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role)
values (3, 'lee', 'kH8}osxJQ#p\6', 'Fyers', 'lfyers3@wsj.com', '2023-06-04', 95, 'ROLE_MENTOR');
insert into member (id, member_id, password, name, email, birthday, point, role)
values (4, 'a', 'kH8}osxJQ#p\6', 'Fyers', 'lfyers3@wsj.com', '2023-06-04', 95, 'ROLE_MENTOR');
insert into member (id, member_id, password, name, email, birthday, point, role)
values (5, 'b', 'kH8}osxJQ#p\6', 'Fyers', 'lfyers3@wsj.com', '2023-06-04', 95, 'ROLE_STUDENT');
insert into member (id, member_id, password, name, email, birthday, point, role)
values (6, 'c', 'kH8}osxJQ#p\6', 'Fyers', 'lfyers3@wsj.com', '2023-06-04', 95, 'ROLE_STUDENT');
insert into member (id, member_id, password, name, email, birthday, point, role)
values (7, 'd', 'kH8}osxJQ#p\6', 'Fyers', 'lfyers3@wsj.com', '2023-06-04', 95, 'ROLE_STUDENT');
insert into member (id, member_id, password, name, email, birthday, point, role)
values (8, 'e', 'kH8}osxJQ#p\6', 'Fyers', 'lfyers3@wsj.com', '2023-06-04', 95, 'ROLE_STUDENT');
insert into member (id, member_id, password, name, email, birthday, point, role)
values (9, 'f', 'kH8}osxJQ#p\6', 'Fyers', 'lfyers3@wsj.com', '2023-06-04', 95, 'ROLE_USER');
insert into member (id, member_id, password, name, email, birthday, point, role)
values (10, 'g', 'kH8}osxJQ#p\6', 'Fyers', 'lfyers3@wsj.com', '2023-06-04', 95, 'ROLE_USER');

-- 개발 더미 데이터
insert into member (id, member_id, password, name, email, birthday, point, role, profile_url)
values (11, 'ssafy1234', '$2a$10$a6OcTpLzJ4Yq9vSWKVeJSOtVUSASArGKv5RcAtWHDHdIJsTg2cpN.', 'Fyers', 'chh9450@naver.com', '2023-06-04', 95, 'ROLE_STUDENT',
        "https://otc-image-bucket.s3.ap-northeast-2.amazonaws.com/5c699caa-d131-42bd-beac-fa5322c75f43.jpg");
insert into member (id, member_id, password, name, email, birthday, point, role, profile_url)
values (12, 'ssafy12345', '$2a$10$a6OcTpLzJ4Yq9vSWKVeJSOtVUSASArGKv5RcAtWHDHdIJsTg2cpN.', 'Fyers', 'chh9450@naver.com', '2023-06-04', 95, 'ROLE_MENTOR',
        "https://otc-image-bucket.s3.ap-northeast-2.amazonaws.com/5c699caa-d131-42bd-beac-fa5322c75f43.jpg");

-- 조원 데이터
insert into member (id, member_id, password, name, email, birthday, point, role, profile_url)
values (13, 'tjgml1 ', '$2a$10$a6OcTpLzJ4Yq9vSWKVeJSOtVUSASArGKv5RcAtWHDHdIJsTg2cpN.', '정서희', 'tjgml1@naver.com', '2023-06-04', 128, 'ROLE_STUDENT',
        "https://otc-image-bucket.s3.ap-northeast-2.amazonaws.com/5c699caa-d131-42bd-beac-fa5322c75f43.jpg");
insert into member (id, member_id, password, name, email, birthday, point, role, profile_url)
values (14, 'rmsdud1', '$2a$10$a6OcTpLzJ4Yq9vSWKVeJSOtVUSASArGKv5RcAtWHDHdIJsTg2cpN.', '최근영', 'rmsdud1@naver.com', '2023-06-04', 65, 'ROLE_USER',
        "https://otc-image-bucket.s3.ap-northeast-2.amazonaws.com/5c699caa-d131-42bd-beac-fa5322c75f43.jpg");
insert into member (id, member_id, password, name, email, birthday, point, role, profile_url)
values (15, 'dudqls1', '$2a$10$a6OcTpLzJ4Yq9vSWKVeJSOtVUSASArGKv5RcAtWHDHdIJsTg2cpN.', '전영빈', 'dudqls1@naver.com', '2023-06-04', 888, 'ROLE_STUDENT',
        "https://otc-image-bucket.s3.ap-northeast-2.amazonaws.com/5c699caa-d131-42bd-beac-fa5322c75f43.jpg");
insert into member (id, member_id, password, name, email, birthday, point, role, profile_url)
values (16, 'wotjd1', '$2a$10$a6OcTpLzJ4Yq9vSWKVeJSOtVUSASArGKv5RcAtWHDHdIJsTg2cpN.', '장재성', 'wotjd1@naver.com', '2023-06-04', 751, 'ROLE_USER',
        "https://otc-image-bucket.s3.ap-northeast-2.amazonaws.com/5c699caa-d131-42bd-beac-fa5322c75f43.jpg");
insert into member (id, member_id, password, name, email, birthday, point, role, profile_url)
values (17, 'wotjs1', '$2a$10$a6OcTpLzJ4Yq9vSWKVeJSOtVUSASArGKv5RcAtWHDHdIJsTg2cpN.', '박재선', 'wotjs1@naver.com', '2023-06-04', 654, 'ROLE_STUDENT',
        "https://otc-image-bucket.s3.ap-northeast-2.amazonaws.com/5c699caa-d131-42bd-beac-fa5322c75f43.jpg");
insert into member (id, member_id, password, name, email, birthday, point, role, profile_url)
values (18, 'ckdgh1', '$2a$10$a6OcTpLzJ4Yq9vSWKVeJSOtVUSASArGKv5RcAtWHDHdIJsTg2cpN.', '최창호', 'ckdgh1@naver.com', '2023-06-04', 456, 'ROLE_USER',
        "https://otc-image-bucket.s3.ap-northeast-2.amazonaws.com/5c699caa-d131-42bd-beac-fa5322c75f43.jpg");
insert into member (id, member_id, password, name, email, birthday, point, role, profile_url)
values (83, 'ssafy123', '$2a$10$a6OcTpLzJ4Yq9vSWKVeJSOtVUSASArGKv5RcAtWHDHdIJsTg2cpN.', 'Fyers', 'chh9450@naver.com', '2023-06-04', 95, 'ROLE_ADMIN',
        "https://otc-image-bucket.s3.ap-northeast-2.amazonaws.com/5c699caa-d131-42bd-beac-fa5322c75f43.jpg");
-- 대기멤버
insert into member (id, member_id, password, name, email, birthday, point, role)
values (19, 'rtertfdg4', 'kH8}osxJQ#p\6', 'name1', 'assdf324@vvv.com', '2023-06-04', 95, 'ROLE_WAITING');
insert into member (id, member_id, password, name, email, birthday, point, role)
values (20, 'fgdfgcvb3', 'kH8}osxJQ#p\6', 'name2', 'adsfvr1@ccc.com', '2023-06-04', 95, 'ROLE_WAITING');
insert into member (id, member_id, password, name, email, birthday, point, role)
values (21, 'sdfgsfd2', 'kH8}osxJQ#p\6', 'name3', 'sdfadf234@ewr.com', '2023-06-04', 95, 'ROLE_WAITING');
insert into member (id, member_id, password, name, email, birthday, point, role)
values (22, 'asdf1df', 'kH8}osxJQ#p\6', 'name4', 'fgrad11@ytr.com', '2023-06-04', 95, 'ROLE_WAITING');

-- 선생님
insert into member (id, member_id, password, name, email, birthday, point, role) values (23, 'kim1', 'cH8\1,y`D|z', '김서희', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (24, 'kim2', 'cH8\1,y`D|z', '김서희', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (25, 'kim3', 'cH8\1,y`D|z', '김서희', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (26, 'kim4', 'cH8\1,y`D|z', '김서희', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (27, 'kim5', 'cH8\1,y`D|z', '김서희', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');

insert into member (id, member_id, password, name, email, birthday, point, role) values (28, 'choi1', 'cH8\1,y`D|z', '최근호', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (29, 'choi2', 'cH8\1,y`D|z', '최근호', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (30, 'choi3', 'cH8\1,y`D|z', '최근호', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (31, 'choi4', 'cH8\1,y`D|z', '최근호', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (32, 'choi5', 'cH8\1,y`D|z', '최근호', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');

insert into member (id, member_id, password, name, email, birthday, point, role) values (33, 'Tae1', 'cH8\1,y`D|z', '태철희', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (34, 'Tae2', 'cH8\1,y`D|z', '태철희', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (35, 'Tae3', 'cH8\1,y`D|z', '태철희', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (36, 'Tae4', 'cH8\1,y`D|z', '태철희', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (37, 'Tae5', 'cH8\1,y`D|z', '태철희', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');

insert into member (id, member_id, password, name, email, birthday, point, role) values (38, 'Dae1', 'cH8\1,y`D|z', '김대성', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (39, 'Dae2', 'cH8\1,y`D|z', '김대성', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (40, 'teacher', '$2a$10$tJSm6EfLNxha1.8CrAnJIOeaeC7oUo518GYPJKZiC3rx3rjg41KJa', '김대성', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (41, 'Dae4', 'cH8\1,y`D|z', '김대성', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (42, 'Dae5', 'cH8\1,y`D|z', '김대성', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');

insert into member (id, member_id, password, name, email, birthday, point, role) values (43, 'park1', 'cH8\1,y`D|z', '박성희', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (44, 'park2', 'cH8\1,y`D|z', '박성희', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (45, 'park3', 'cH8\1,y`D|z', '박성희', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (46, 'park4', 'cH8\1,y`D|z', '박성희', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (47, 'park5', 'cH8\1,y`D|z', '박성희', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');

insert into member (id, member_id, password, name, email, birthday, point, role) values (48, 'jung1', 'cH8\1,y`D|z', '정민영', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (49, 'jung2', 'cH8\1,y`D|z', '정민영', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (50, 'jung3', 'cH8\1,y`D|z', '정민영', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (51, 'jung4', 'cH8\1,y`D|z', '정민영', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (52, 'jung5', 'cH8\1,y`D|z', '정민영', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');

insert into member (id, member_id, password, name, email, birthday, point, role) values (53, 'sung1', 'cH8\1,y`D|z', '성종현', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (54, 'sung2', 'cH8\1,y`D|z', '성종현', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (55, 'sung3', 'cH8\1,y`D|z', '성종현', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (56, 'sung4', 'cH8\1,y`D|z', '성종현', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (57, 'sung5', 'cH8\1,y`D|z', '성종현', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');

insert into member (id, member_id, password, name, email, birthday, point, role) values (58, 'heo1', 'cH8\1,y`D|z', '허숙희', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (59, 'heo2', 'cH8\1,y`D|z', '허숙희', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (60, 'heo3', 'cH8\1,y`D|z', '허숙희', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (61, 'heo4', 'cH8\1,y`D|z', '허숙희', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (62, 'heo5', 'cH8\1,y`D|z', '허숙희', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');

insert into member (id, member_id, password, name, email, birthday, point, role) values (63, 'joe1', 'cH8\1,y`D|z', '조건희', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (64, 'joe2', 'cH8\1,y`D|z', '조건희', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (65, 'joe3', 'cH8\1,y`D|z', '조건희', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (66, 'joe4', 'cH8\1,y`D|z', '조건희', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (67, 'joe5', 'cH8\1,y`D|z', '조건희', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');

insert into member (id, member_id, password, name, email, birthday, point, role) values (68, 'lee1', 'cH8\1,y`D|z', '이민성', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (69, 'lee2', 'cH8\1,y`D|z', '이민성', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (70, 'lee3', 'cH8\1,y`D|z', '이민성', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (71, 'lee4', 'cH8\1,y`D|z', '이민성', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (72, 'lee5', 'cH8\1,y`D|z', '이민성', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');

insert into member (id, member_id, password, name, email, birthday, point, role) values (73, 'jung11', 'cH8\1,y`D|z', '정몽규', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (74, 'jung12', 'cH8\1,y`D|z', '정몽규', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (75, 'jung13', 'cH8\1,y`D|z', '정몽규', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (76, 'jung14', 'cH8\1,y`D|z', '정몽규', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (77, 'jung15', 'cH8\1,y`D|z', '정몽규', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');

insert into member (id, member_id, password, name, email, birthday, point, role) values (78, 'son1', 'cH8\1,y`D|z', '손민흥', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (79, 'son2', 'cH8\1,y`D|z', '손민흥', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (80, 'son3', 'cH8\1,y`D|z', '손민흥', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (81, 'son4', 'cH8\1,y`D|z', '손민흥', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');
insert into member (id, member_id, password, name, email, birthday, point, role) values (82, 'son5', 'cH8\1,y`D|z', '손민흥', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_TEACHER');

-- 관리자
insert into member (id, member_id, password, name, email, birthday, point, role) values (84, 'admin', '$2a$10$tJSm6EfLNxha1.8CrAnJIOeaeC7oUo518GYPJKZiC3rx3rjg41KJa', '관리자', 'unabbs1@sfgate.com', '2023-09-08', 53, 'ROLE_ADMIN');

insert into attendance (status, check_in, check_out, id, member_id)
values (0, '2024-02-01 09:48:34', '2024-02-01 18:48:34', 1, 4);
insert into attendance (status, check_in, check_out, id, member_id)
values (1, '2024-02-02 09:48:34', '2024-02-02 18:48:34', 2, 4);
insert into attendance (status, check_in, check_out, id, member_id)
values (1, '2024-02-03 09:48:34', '2024-02-03 18:48:34', 3, 4);
insert into attendance (status, check_in, check_out, id, member_id)
values (2, '2024-02-04 09:48:34', '2024-02-04 18:48:34', 4, 4);
insert into attendance (status, check_in, check_out, id, member_id)
values (2, '2024-02-05 09:48:34', '2024-02-05 18:48:34', 5, 4);
insert into attendance (status, check_in, check_out, id, member_id)
values (0, '2024-02-06 09:48:34', '2024-02-06 18:48:34', 6, 4);
insert into attendance (status, check_in, check_out, id, member_id)
values (0, '2024-02-07 09:48:34', '2024-02-07 18:48:34', 7, 4);
insert into attendance (status, check_in, check_out, id, member_id)
values (0, '2024-02-08 09:48:34', '2024-02-08 18:48:34', 8, 4);
insert into attendance (status, check_in, check_out, id, member_id)
values (0, '2024-02-09 09:48:34', '2024-02-09 18:48:34', 9, 4);
insert into attendance (status, check_in, check_out, id, member_id)
values (3, '2024-02-10 09:48:34', '2024-02-10 18:48:34', 10, 4);
insert into attendance (status, check_in, check_out, id, member_id)
values (3, '2024-02-11 09:48:34', '2024-02-11 18:48:34', 11, 4);

insert into badge (id, image_url)
values (1, "https://otc-image-bucket.s3.ap-northeast-2.amazonaws.com/e1393253-70bb-43df-91c0-eb08cf940141.png");
insert into badge (id, image_url)
values (2, "https://otc-image-bucket.s3.ap-northeast-2.amazonaws.com/38ae118f-ca62-45a6-b591-376bbce43bdc.png");
insert into badge (id, image_url)
values (3, "https://otc-image-bucket.s3.ap-northeast-2.amazonaws.com/ec37c826-aece-46ff-8372-775b1104c4e2.png");
insert into badge (id, image_url)
values (4, "https://otc-image-bucket.s3.ap-northeast-2.amazonaws.com/484b859a-413a-42aa-98bb-50e7d51c4508.png");
insert into badge (id, image_url)
values (5, "https://otc-image-bucket.s3.ap-northeast-2.amazonaws.com/681b5e23-b73a-4441-8b40-2853d52dbede.png");


insert into member_badge(member_id, badge_id)
values (4, 1);
insert into member_badge(member_id, badge_id)
values (4, 2);

-- 반 정보
insert into classroom (id, year, grade, number) values (1, 2024, 1, 1);
insert into classroom (id, year, grade, number) values (2, 2024, 1, 2);
insert into classroom (id, year, grade, number) values (3, 2024, 1, 3);
insert into classroom (id, year, grade, number) values (4, 2024, 1, 4);
insert into classroom (id, year, grade, number) values (5, 2024, 1, 5);
insert into classroom (id, year, grade, number) values (6, 2024, 2, 1);
insert into classroom (id, year, grade, number) values (7, 2024, 2, 2);
insert into classroom (id, year, grade, number) values (8, 2024, 2, 3);
insert into classroom (id, year, grade, number) values (9, 2024, 2, 4);
insert into classroom (id, year, grade, number) values (10, 2024, 2, 5);
insert into classroom (id, year, grade, number) values (11, 2024, 3, 1);
insert into classroom (id, year, grade, number) values (12, 2024, 3, 2);
insert into classroom (id, year, grade, number) values (13, 2024, 3, 3);
insert into classroom (id, year, grade, number) values (14, 2024, 3, 4);
insert into classroom (id, year, grade, number) values (15, 2024, 3, 5);
insert into classroom (id, year, grade, number) values (16, 2024, 4, 1);
insert into classroom (id, year, grade, number) values (17, 2024, 4, 2);
insert into classroom (id, year, grade, number, room_url) values (18, 2024, 4, 3, "https://our-tiny-classroom.daily.co/2024-4-3");
insert into classroom (id, year, grade, number) values (19, 2024, 4, 4);
insert into classroom (id, year, grade, number) values (20, 2024, 4, 5);
insert into classroom (id, year, grade, number) values (21, 2024, 5, 1);
insert into classroom (id, year, grade, number) values (22, 2024, 5, 2);
insert into classroom (id, year, grade, number) values (23, 2024, 5, 3);
insert into classroom (id, year, grade, number) values (24, 2024, 5, 4);
insert into classroom (id, year, grade, number) values (25, 2024, 5, 5);
insert into classroom (id, year, grade, number) values (26, 2024, 6, 1);
insert into classroom (id, year, grade, number) values (27, 2024, 6, 2);
insert into classroom (id, year, grade, number) values (28, 2024, 6, 3);
insert into classroom (id, year, grade, number) values (29, 2024, 6, 4);
insert into classroom (id, year, grade, number) values (30, 2024, 6, 5);
insert into classroom (id, year, grade, number) values (31, 2024, 7, 1);
insert into classroom (id, year, grade, number) values (32, 2024, 7, 2);
insert into classroom (id, year, grade, number) values (33, 2024, 7, 3);
insert into classroom (id, year, grade, number) values (34, 2024, 7, 4);
insert into classroom (id, year, grade, number) values (35, 2024, 7, 5);
insert into classroom (id, year, grade, number) values (36, 2024, 8, 1);
insert into classroom (id, year, grade, number) values (37, 2024, 8, 2);
insert into classroom (id, year, grade, number) values (38, 2024, 8, 3);
insert into classroom (id, year, grade, number) values (39, 2024, 8, 4);
insert into classroom (id, year, grade, number) values (40, 2024, 8, 5);
insert into classroom (id, year, grade, number) values (41, 2024, 9, 1);
insert into classroom (id, year, grade, number) values (42, 2024, 9, 2);
insert into classroom (id, year, grade, number) values (43, 2024, 9, 3);
insert into classroom (id, year, grade, number) values (44, 2024, 9, 4);
insert into classroom (id, year, grade, number) values (45, 2024, 9, 5);
insert into classroom (id, year, grade, number) values (46, 2024, 10, 1);
insert into classroom (id, year, grade, number) values (47, 2024, 10, 2);
insert into classroom (id, year, grade, number) values (48, 2024, 10, 3);
insert into classroom (id, year, grade, number) values (49, 2024, 10, 4);
insert into classroom (id, year, grade, number) values (50, 2024, 10, 5);
insert into classroom (id, year, grade, number) values (51, 2024, 11, 1);
insert into classroom (id, year, grade, number) values (52, 2024, 11, 2);
insert into classroom (id, year, grade, number) values (53, 2024, 11, 3);
insert into classroom (id, year, grade, number) values (54, 2024, 11, 4);
insert into classroom (id, year, grade, number) values (55, 2024, 11, 5);
insert into classroom (id, year, grade, number) values (56, 2024, 12, 1);
insert into classroom (id, year, grade, number) values (57, 2024, 12, 2);
insert into classroom (id, year, grade, number) values (58, 2024, 12, 3);
insert into classroom (id, year, grade, number) values (59, 2024, 12, 4);
insert into classroom (id, year, grade, number) values (60, 2024, 12, 5);

insert into classroom (id, year, grade, number)
values (61, 2024, 1, 0);
insert into classroom (id, year, grade, number)
values (62, 2024, 2, 0);
insert into classroom (id, year, grade, number)
values (63, 2024, 3, 0);
insert into classroom (id, year, grade, number)
values (64, 2024, 4, 0);
insert into classroom (id, year, grade, number)
values (65, 2024, 5, 0);
insert into classroom (id, year, grade, number)
values (66, 2024, 6, 0);
insert into classroom (id, year, grade, number)
values (67, 2024, 7, 0);
insert into classroom (id, year, grade, number)
values (68, 2024, 8, 0);
insert into classroom (id, year, grade, number)
values (69, 2024, 9, 0);
insert into classroom (id, year, grade, number)
values (70, 2024, 10, 0);
insert into classroom (id, year, grade, number)
values (71, 2024, 11, 0);
insert into classroom (id, year, grade, number)
values (72, 2024, 12, 0);

-- 반 매핑
INSERT INTO member_class_room (`class_room_id`, `member_id`)
VALUES ('18', '2');
INSERT INTO member_class_room (`class_room_id`, `member_id`)
VALUES ('18', '3');
INSERT INTO member_class_room (`class_room_id`, `member_id`)
VALUES ('18', '4');
INSERT INTO member_class_room (`class_room_id`, `member_id`)
VALUES ('18', '5');
INSERT INTO member_class_room (`class_room_id`, `member_id`)
VALUES ('18', '6');
INSERT INTO member_class_room (`class_room_id`, `member_id`)
VALUES ('18', '7');
INSERT INTO member_class_room (`class_room_id`, `member_id`)
VALUES ('18', '8');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('18', '11');

-- 선생님 매핑
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('1', '23');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('2', '24');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('3', '25');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('4', '26');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('5', '27');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('6', '28');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('7', '29');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('8', '30');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('9', '31');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('10', '32');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('11', '33');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('12', '34');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('13', '35');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('14', '36');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('15', '37');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('16', '38');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('17', '39');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('18', '40');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('19', '41');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('20', '42');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('21', '43');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('22', '44');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('23', '45');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('24', '46');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('25', '47');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('26', '48');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('27', '49');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('28', '50');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('29', '51');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('30', '52');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('31', '53');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('32', '54');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('33', '55');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('34', '56');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('35', '57');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('36', '58');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('37', '59');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('38', '60');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('39', '61');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('40', '62');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('41', '63');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('42', '64');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('43', '65');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('44', '66');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('45', '67');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('46', '68');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('47', '69');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('48', '70');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('49', '71');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('50', '72');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('51', '73');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('52', '74');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('53', '75');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('54', '76');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('55', '77');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('56', '78');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('57', '79');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('58', '80');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('59', '81');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('60', '82');

--채팅을 위한 반 매핑
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('18', '13');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('18', '14');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('18', '15');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('18', '16');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('18', '17');
INSERT INTO member_class_room (`class_room_id`, `member_id`) VALUES ('18', '18');

-- 자유강의
INSERT INTO lecture (`day_of_week`, `time_table`, `member_id`, `description`, `title`, `lecture_category_type`, `lecture_type`, `approved`)
VALUES ('0', '0', '1', '모션 그래픽 공부', '모션 그래픽', 'ART', 'FREE_LECTURE', 'APPROVED');
INSERT INTO lecture (`day_of_week`, `time_table`, `member_id`, `description`, `title`, `lecture_category_type`, `lecture_type`, `approved`)
VALUES ('0', '0', '1', '한시간동안 기타 연습', '기타 연주', 'MUSIC', 'FREE_LECTURE', 'APPROVED');
INSERT INTO lecture (`day_of_week`, `time_table`, `member_id`, `description`, `title`, `lecture_category_type`, `lecture_type`, `approved`)
VALUES ('0', '0', '1', '백준에서 실버 한문제 풀기', '알고리즘', 'MATH', 'FREE_LECTURE', 'APPROVED');
INSERT INTO lecture (`day_of_week`, `time_table`, `member_id`, `description`, `title`, `lecture_category_type`, `lecture_type`, `approved`)
VALUES ('0', '0', '1', '체스를 같이 할 친구가 있으신가요?', '체스', 'HOBBY', 'FREE_LECTURE', 'APPROVED');
INSERT INTO lecture (`day_of_week`, `time_table`, `member_id`, `description`, `title`, `lecture_category_type`, `lecture_type`, `approved`)
VALUES ('0', '0', '1', '네일아트 공부', '네일아트', 'ART', 'FREE_LECTURE', 'APPROVED');
INSERT INTO lecture (`day_of_week`, `time_table`, `member_id`, `description`, `title`, `lecture_category_type`, `lecture_type`, `approved`)
VALUES ('0', '0', '1', '피아노피아노', '피아노', 'MUSIC', 'FREE_LECTURE', 'APPROVED');
INSERT INTO lecture (`day_of_week`, `time_table`, `member_id`, `description`, `title`, `lecture_category_type`, `lecture_type`, `approved`)
VALUES ('0', '0', '1', '꽃꽃꽃꽃', '꽃꽂이', 'ART', 'FREE_LECTURE', 'APPROVED');
INSERT INTO lecture (`day_of_week`, `time_table`, `member_id`, `description`, `title`, `lecture_category_type`, `lecture_type`, `approved`)
VALUES ('0', '0', '1', '진로탐색진로탐색', '진로탐색', 'CAREER', 'FREE_LECTURE', 'APPROVED');

-- 정규강의
INSERT INTO lecture (`day_of_week`, `time_table`, `member_id`, `description`, `title`, `lecture_category_type`, `lecture_type`, `approved`)
VALUES ('0', '0', '1', '수학수학', '수학', 'MATH', 'REGULAR_LECTURE', 'APPROVED');
INSERT INTO lecture (`day_of_week`, `time_table`, `member_id`, `description`, `title`, `lecture_category_type`, `lecture_type`, `approved`)
VALUES ('0', '0', '1', '영어영어', '영어', 'ENGLISH', 'REGULAR_LECTURE', 'APPROVED');
INSERT INTO lecture (`day_of_week`, `time_table`, `member_id`, `description`, `title`, `lecture_category_type`, `lecture_type`, `approved`)
VALUES ('0', '0', '1', '도덕도덕', '도덕', 'ETHICS', 'REGULAR_LECTURE', 'APPROVED');
INSERT INTO lecture (`day_of_week`, `time_table`, `member_id`, `description`, `title`, `lecture_category_type`, `lecture_type`, `approved`)
VALUES ('0', '0', '1', '국어', '진로탐색', 'KOREAN', 'REGULAR_LECTURE', 'APPROVED');

-- 특수강의
INSERT INTO lecture (`day_of_week`, `time_table`, `member_id`, `description`, `title`, `lecture_category_type`, `lecture_type`, `approved`, `date`)
VALUES ('0', '0', '3', '국어', '진로탐색', 'KOREAN', 'SPECIAL_LECTURE', 'APPROVED', '2024-02-12');
INSERT INTO lecture (`day_of_week`, `time_table`, `member_id`, `description`, `title`, `lecture_category_type`, `lecture_type`, `approved`, `date`)
VALUES ('0', '1', '4', '국어', '진로탐색', 'KOREAN', 'SPECIAL_LECTURE', 'APPROVED', '2024-02-13');

-- 스케줄
INSERT INTO schedule (`schedule_date`, `lecture_id`, `member_id`, `deletable`, `day_of_week`, `time_table`)
VALUES ('2024-02-14', '10', '2', false, '2', '3');
INSERT INTO schedule (`schedule_date`, `lecture_id`, `member_id`, `deletable`, `day_of_week`, `time_table`)
VALUES ('2024-02-14', '11', '2', false, '2', '4');
INSERT INTO schedule (`schedule_date`, `lecture_id`, `member_id`, `deletable`, `day_of_week`, `time_table`)
VALUES ('2024-02-13', '1', '4', false, '1', '3');
INSERT INTO schedule (`schedule_date`, `lecture_id`, `member_id`, `deletable`, `day_of_week`, `time_table`)
VALUES ('2024-02-13', '2', '4', false, '1', '4');
INSERT INTO schedule (`schedule_date`, `lecture_id`, `member_id`, `deletable`, `day_of_week`, `time_table`)
VALUES ('2024-02-13', '3', '4', false, '1', '5');
INSERT INTO schedule (`schedule_date`, `lecture_id`, `member_id`, `deletable`, `day_of_week`, `time_table`)
VALUES ('2024-02-13', '4', '4', false, '1', '6');
INSERT INTO schedule (`schedule_date`, `lecture_id`, `member_id`, `deletable`, `day_of_week`, `time_table`)
VALUES ('2024-02-13', '5', '4', false, '2', '1');
INSERT INTO schedule (`schedule_date`, `lecture_id`, `member_id`, `deletable`, `day_of_week`, `time_table`)
VALUES ('2024-02-13', '6', '4', false, '2', '2');
INSERT INTO schedule (`schedule_date`, `lecture_id`, `member_id`, `deletable`, `day_of_week`, `time_table`)
VALUES ('2024-02-13', '7', '4', false, '2', '5');
INSERT INTO schedule (`schedule_date`, `lecture_id`, `member_id`, `deletable`, `day_of_week`, `time_table`)
VALUES ('2024-02-13', '8', '4', false, '2', '6');
INSERT INTO schedule (`schedule_date`, `lecture_id`, `member_id`, `deletable`, `day_of_week`, `time_table`)
VALUES ('2024-02-13', '9', '4', false, '2', '7');

-- 멤버 스케줄
INSERT INTO member_schedule (`lecture_category_type`, `member_id`, `schedule_id`)
values ('ART', '4', '3');
INSERT INTO member_schedule (`lecture_category_type`, `member_id`, `schedule_id`)
values ('ART', '4', '4');
INSERT INTO member_schedule (`lecture_category_type`, `member_id`, `schedule_id`)
values ('ART', '4', '5');
INSERT INTO member_schedule (`lecture_category_type`, `member_id`, `schedule_id`)
values ('MATH', '4', '6');
INSERT INTO member_schedule (`lecture_category_type`, `member_id`, `schedule_id`)
values ('MATH', '4', '7');
INSERT INTO member_schedule (`lecture_category_type`, `member_id`, `schedule_id`)
values ('MATH', '4', '8');
INSERT INTO member_schedule (`lecture_category_type`, `member_id`, `schedule_id`)
values ('MATH', '4', '9');
INSERT INTO member_schedule (`lecture_category_type`, `member_id`, `schedule_id`)
values ('ENGLISH', '4', '10');
INSERT INTO member_schedule (`lecture_category_type`, `member_id`, `schedule_id`)
values ('ENGLISH', '4', '11');

-- 학생 등업
insert into student_role_update_request(member_id, before_school, original_file_names, quit_confirmation_paths, before_school_type, quit_reason)
values (2, '양재초', '["asdfqasdf.png"]', '["https://otc-image-bucket.s3.ap-northeast-2.amazonaws.com/e1393253-70bb-43df-91c0-eb08cf940141.png"]', 1, "그냥");
insert into student_role_update_request(member_id, before_school, original_file_names, quit_confirmation_paths, before_school_type, quit_reason)
values (5, '양재초', '["asdfqasdf.png"]', '["https://otc-image-bucket.s3.ap-northeast-2.amazonaws.com/e1393253-70bb-43df-91c0-eb08cf940141.png"]', 3, "힘들어요");
insert into student_role_update_request(member_id, before_school, original_file_names, quit_confirmation_paths, before_school_type, quit_reason)
values (19, '역삼초', '["asdfqasdf.png"]', '["https://otc-image-bucket.s3.ap-northeast-2.amazonaws.com/e1393253-70bb-43df-91c0-eb08cf940141.png"]', 2, "힘들어요");
insert into student_role_update_request(member_id, before_school, original_file_names, quit_confirmation_paths, before_school_type, quit_reason)
values (20, '언주초', '["asdfqasdf.png"]', '["https://otc-image-bucket.s3.ap-northeast-2.amazonaws.com/e1393253-70bb-43df-91c0-eb08cf940141.png"]', 3, "힘들어요");

-- 멘토 등업신청
insert into mentor_role_update_request(member_id, belong, belong_document_paths, original_file_names)
values (6, '한국대', '["https://otc-image-bucket.s3.ap-northeast-2.amazonaws.com/e1393253-70bb-43df-91c0-eb08cf940141.png"]', '["asdfqasdf.png"]');
insert into mentor_role_update_request(member_id, belong, belong_document_paths, original_file_names)
values (7, '싸피대', '["https://otc-image-bucket.s3.ap-northeast-2.amazonaws.com/e1393253-70bb-43df-91c0-eb08cf940141.png"]', '["asdfqasdf.png"]');
insert into mentor_role_update_request(member_id, belong, belong_document_paths, original_file_names)
values (21, '서희대', '["https://otc-image-bucket.s3.ap-northeast-2.amazonaws.com/e1393253-70bb-43df-91c0-eb08cf940141.png"]', '["asdfqasdf.png"]');
insert into mentor_role_update_request(member_id, belong, belong_document_paths, original_file_names)
values (22, '근희대', '["https://otc-image-bucket.s3.ap-northeast-2.amazonaws.com/e1393253-70bb-43df-91c0-eb08cf940141.png"]', '["asdfqasdf.png"]');

