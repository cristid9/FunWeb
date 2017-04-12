set serveroutput on;

create or replace package user_package as 
  
  function existsUsername(nume Users.name%type)
    return int;
  function generateSuggestion(nume Users.name%type)
    return varchar2;
    
  function isWeak(pass varchar2)
    return int;
  
end user_package;

create or replace package body user_package as
  
  function existsUsername(nume Users.name%type)
    return int
  as
  
  v_exists int;
  v_count int := 0;
  
  begin
    
    select count(*) into v_count from Users where Users.name = nume;
    
    if v_count = 0 then
      v_exists := 0;
    else
      v_exists := 1;
    end if;
    
    return v_exists;
  end;  
  
  function generateSuggestion(nume Users.name%type)
    return varchar2
  as

  v_new_username varchar2(50);  
  v_name varchar2(50);
  begin
    
    v_name := nume;
    loop
      v_new_username := v_name;
      v_new_username := v_new_username||'_'||trunc(dbms_random.value(1, 10));
      
    exit when existsUsername(v_new_username) = 0;
    v_name := v_new_username;
    end loop;
    
    return v_new_username;
  end;
  
  function isWeak(pass varchar2)
   return int
  as
  v_conditions_met int := 0;  
  begin
  
  -- sa fie mai mare decat 8 
  
  if length(pass) >= 8 then
    v_conditions_met := v_conditions_met + 1;
  end if;
  
  --sa aiba un semn special
  
  if regexp_count(pass, '[!@#$%^&*]') != 0 then
    v_conditions_met := v_conditions_met + 1;
  end if;
  
  if regexp_count(pass, '[a-z]', 1, 'ic') != 0 and regexp_count(pass, '[A-Z]', 1, 'ic') != 0 then
    v_conditions_met := v_conditions_met + 1;
  end if;
  
  if regexp_count(pass, '[0-9]', 1, 'ic') != 0 then
    v_conditions_met := v_conditions_met + 1;
  end if;
  
  if v_conditions_met <= 1 then
    return 0;
  elsif v_conditions_met = 2 then
    return 1;
  else
    return 2;
  end if;
  end;
  
  
end user_package;

create or replace package questions_package as
  
  function isRelevant(p_id QUESTIONS.QUESTION_ID%type)
   return int;
  
  function weakestChapter(p_id USERS.id%type)
   return varchar2;
  
end questions_package;

create or replace package body questions_package as
  
  function isRelevant(p_id QUESTIONS.QUESTION_ID%type)
    return int
  as
  
  v_asked float := 0;
  v_solved float := 0;
  
  begin
    select asked into v_asked from QUESTIONS;
    select solved into v_solved from QUESTIONS;
    
    if v_asked < 3 then
      return 0;
    end if;
    
    if v_solved <= v_asked * 0.2 or v_solved >= v_asked * 0.8 then
      return 0;
    end if;
    
    return 1;
  end;
  
  function weakestChapter(p_id USERS.id%type) 
    return varchar2 as
  cursor c_chapter is select questions.chapter, count(id) from answers join questions on questions.question_id = answers.question_id where answers.solved = 1 and answers.user_id = p_id group by questions.chapter;
  v_chapter int;
  v_count int := 0;
  
  v_min int := 100;
  v_ret varchar2(50);
  
  begin
    open c_chapter;
      loop
        fetch c_chapter into v_chapter, v_count;
        exit when c_chapter%notfound;
        
        if v_count < v_min then 
          v_min := v_chapter; 
        end if;
      end loop;
    close c_chapter;
    
    if v_min = 100 then
      v_ret := 'Inca nu ai raspuns la nicio intrebare';
      return v_ret;
    end if;
    
    select chapters.chapter_name into v_ret from chapters where id = v_min;
    
    return v_ret;
  end;
  
end questions_package;

select questions_package.weakestChapter(10) from dual;

--select * from users where name = 'Bogdan';


