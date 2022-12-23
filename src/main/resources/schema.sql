DROP SCHEMA IF EXISTS db CASCADE;

CREATE SCHEMA IF NOT EXISTS db AUTHORIZATION sa;

CREATE TABLE db.member
(
    member_id BIGINT NOT NULL AUTO_INCREMENT COMMENT '회원 아이디',
    regist_date DATE NOT NULL COMMENT '등록일시',
    update_date DATE NOT NULL COMMENT '수정일시',
    PRIMARY KEY (member_id)
);

CREATE TABLE db.point
(
    seq BIGINT NOT NULL AUTO_INCREMENT COMMENT '포인트 시퀀스',
    member_id BIGINT NOT NULL COMMENT '회원 아이디',
    action_type VARCHAR2 NOT NULL COMMENT '행위 유형',
    point DECIMAL(19, 2) NOT NULL COMMENT '포인트',
    remain_point DECIMAL(19, 2) NULL COMMENT '잔여 포인트',
    parent_seq BIGINT NULL COMMENT '사용된 포인트 시퀀스',
    regist_date DATE NOT NULL COMMENT '등록일시',
    update_date DATE NOT NULL COMMENT '수정일시',
    expire_date DATE NULL COMMENT '포인트 아이디',
    PRIMARY KEY (seq)
);