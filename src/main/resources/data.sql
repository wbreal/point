insert into musinsadb.member(member_id, regist_date, update_date) values (1 ,CURRENT_DATE() ,CURRENT_DATE());
insert into musinsadb.member(member_id, regist_date, update_date) values (2 ,CURRENT_DATE() ,CURRENT_DATE());
insert into musinsadb.member(member_id, regist_date, update_date) values (3 ,CURRENT_DATE() ,CURRENT_DATE());
insert into musinsadb.member(member_id, regist_date, update_date) values (4 ,CURRENT_DATE() ,CURRENT_DATE());

-- insert into musinsadb.point(seq, member_id, action_type, point, remain_point, parent_seq, regist_date, update_date, expire_date)
-- values (1, 1, 'SAVE', 1000, 1000, null, CURRENT_DATE(), CURRENT_DATE(), DATEADD('YEAR', 1, NOW()));
--
-- insert into musinsadb.point(seq, member_id, action_type, point, remain_point, parent_seq, regist_date, update_date, expire_date)
-- values (2, 2, 'SAVE', 1000, 500, null, CURRENT_DATE(), CURRENT_DATE(), DATEADD('YEAR', 1, NOW()));
--
-- insert into musinsadb.point(seq, member_id, action_type, point, remain_point, parent_seq, regist_date, update_date, expire_date)
-- values (3, 2, 'USE', -500, null, 1, CURRENT_DATE() ,CURRENT_DATE(), null);