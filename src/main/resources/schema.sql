DROP SCHEMA pointdb CASCADE;

CREATE SCHEMA IF NOT EXISTS pointdb AUTHORIZATION sa;

CREATE TABLE pointdb.member_entity
(
    member_id BIGINT NOT NULL,
    registration_date DATE NOT NULL,
    update_date DATE NOT NULL,
    PRIMARY KEY (member_id)
);

CREATE TABLE pointdb.point_entity
(
    point_id BIGINT NOT NULL AUTO_INCREMENT,
    member_id BIGINT NOT NULL,
    point BiGINT NOT NULL,
    status BIGINT NOT NULL,
    registration_date DATE NOT NULL,
    update_date DATE NOT NULL,
    PRIMARY KEY (point_id)
);