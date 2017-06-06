----- tabele ------

create table Chapters (
  id NUMBER (10, 0),
  chapter_name varchar2(200)
);  
/

create table Hints(
  id NUMBER (10, 0),
  question_id NUMBER(10, 0),
  text varchar2(200)
);
/

create table Characters(
  id NUMBER (10, 0),
  name varchar2(200),
  picture_path varchar2(200),
  question_number NUMBER (10, 0)
);
/

create table logindatacustom(
  id NUMBER(10, 0),
  user_id NUMBER(10, 0),
  password varchar2(500)
);
/

create table logindatasocial(
  id number(10, 0),
  auto_hash varchar2(500),
  user_id number(10, 0)
);
/

create table Options(
  id number(10, 0),
  enunciation varchar2(1000),
  correctness number(10, 0),
  questions_question_id number(10, 0)
);
/

create table Questions(
  question_id number(10, 0),
  enunciation varchar2(500),
  reward number(10, 0),
  chapter_id number(10, 0),
  characters_id number(10, 0)
);
/

create table users(
  id number(10, 0),
  name varchar2(200),
  user_role varchar2(200),
  email varchar2(200),
  login_type varchar2(200),
  hints_left number(10, 0),
  gold_left number(10, 0),
  avatar_path varchar2(200),
  user_level number(10, 0)
);

/
create table PendingPasswordReset(
  id number(10, 0),
  token varchar(500),
  username varchar(200)
);

create table LOGGED_USERS(
	id number(10, 0),
	USER_NAME varchar2(200);
);

/
create sequence chapters_id_seq;
/
create sequence hints_id_seq;
/
create sequence characters_id_seq;
/
create sequence logindatacustom_id_seq;
/
create sequence logindatasocial_id_seq;
/
create sequence options_id_seq;
/
create sequence questions_id_seq;
/
create sequence user_id_seq;
/
create sequence pending_password_reset_seq;
/
create sequence logged_users_id_seq;
/


create or replace trigger trg_logged_id
before insert on LOGGED_USERS
for each row
begin
	select logged_users_id_seq.nextval into :new.id from dual;
end;
/
create or replace trigger trg_chapters_pass_reset
before insert on PendingPasswordReset
for each row
begin
  select pending_password_reset_seq.nextval into :new.id from dual;
end;
/
create or replace trigger trg_chapters_id
  before insert on chapters
  for each row
begin
  select chapters_id_seq.nextval into :new.id from dual;
end;
/
create or replace trigger trg_hints_id
  before insert on hints
  for each row
begin
  select hints_id_seq.nextval into :new.id from dual;
end;
/
create or replace trigger trg_characters_id
  before insert on characters
  for each row
begin
  select characters_id_seq.nextval into :new.id from dual;
end;
/
create or replace trigger trg_logindatacustom_id
  before insert on logindatacustom
  for each row
begin
  select logindatacustom_id_seq.nextval into :new.id from dual;
end;
/
create or replace trigger trg_logindatasocial_id
  before insert on logindatasocial
  for each row
begin
  select logindatasocial_id_seq.nextval into :new.id from dual;
end;
/
create or replace trigger trg_options_id
  before insert on options
  for each row
begin
  select options_id_seq.nextval into :new.id from dual;
end;
/
create or replace trigger trg_questions_id
  before insert on questions
  for each row
begin
  select questions_id_seq.nextval into :new.question_id from dual;
end;
/
create or replace trigger trg_users_id
  before insert on users
  for each row
begin
  select user_id_seq.nextval into :new.id from dual;
end;
/