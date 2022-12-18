insert into pointdb.member_entity(member_id, registration_date, update_date) values (1 ,CURRENT_DATE() ,CURRENT_DATE());
insert into pointdb.member_entity(member_id, registration_date, update_date) values (2 ,CURRENT_DATE() ,CURRENT_DATE());

insert into pointdb.point_entity(member_id, point, status, registration_date, update_date) values (2, 1000, 10 ,CURRENT_DATE() ,CURRENT_DATE());
insert into pointdb.point_entity(member_id, point, status, registration_date, update_date) values (2, -500, 20 ,CURRENT_DATE() ,CURRENT_DATE());
