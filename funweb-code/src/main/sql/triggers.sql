set serveroutput on;

create or replace trigger trg_delete_on_users 
after delete on users
for each row
begin
  execute immediate 'delete from BADGES where user_id = '|| :old.id;
  execute immediate 'delete from CHAT where user_id = '|| :old.id;
  execute immediate 'delete from CURRENT_SESSION where user_id = '|| :old.id;
  execute immediate 'delete from LAST_SESSION where user_id = '|| :old.id;
  execute immediate 'delete from LOGINDATACUSTOM where user_id = '|| :old.id;
  execute immediate 'delete from LOGINDATASOCIAL where user_id = '|| :old.id;
  execute immediate 'delete from ANSWERS where user_id = '|| :old.id;
end;
/
create or replace trigger trg_delete_on_questions
after delete on questions
for each row
begin
  execute immediate 'delete from OPTIONS where questions_question_id = '|| :old.id;
  execute immediate 'delete from HINTS where questions_question_id = '|| :old.id;
  execute immediate 'delete from ROUND where questions_question_id = '|| :old.id;
end;
/
create or replace trigger trg_delete_on_chapters
after delete on chapters
for each row
begin
  execute immediate 'delete from questions where chapter = '|| :old.id;
end;
/
create or replace trigger trg_delete_on_characters
after delete on characters
for each row
begin
  execute immediate 'delete from QUESTIONS where characters_id = '|| :old.id;
  execute immediate 'delete from LAST_SESSION where characters_id = '|| :old.id;
end;
/
create or replace trigger trg_delete_on_map
after delete on map
for each row
begin
  execute immediate 'delete from MAP_ELEMENTS where map_id = '||:old.id;
end;
/
create or replace trigger trg_delete_on_game
after delete on game
for each row
begin
  execute immediate 'delete from ROUND where game_id = '||:old.id;
end;


