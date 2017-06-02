
--- CHAPTERS---
insert into chapters(chapter_name) values ('welcome');
insert into chapters(chapter_name) values ('html');
--- HINTS ----
insert into hints(question_id, text) values (1, 'acesta este un hint');
--- CHARACTERS ---
insert into characters(name, picture_path, question_number) values ('Giani', '/home', 10);
insert into characters(name, picture_path, question_number) values ('Morandi', '/home', 10);
--- LOGINDATACUSTOM ---
insert into logindatacustom(user_id, password) values (1, 'asddsa');
--- LOGINDATASOCIAL ---
insert into logindatasocial(auto_hash, user_id) values ('anaaremereautohash', 2);
--- OPTIONS ---
insert into options (enunciation, correctness, questions_question_id) values ('Ceva nasol', 1, 1);
insert into options (enunciation, correctness, questions_question_id) values ('Ceva smecher', 0, 1);
insert into options (enunciation, correctness, questions_question_id) values ('Nu stiu', 0, 1);

insert into options (enunciation, correctness, questions_question_id) values ('Ca sa invat TW!', 0, 2);
insert into options (enunciation, correctness, questions_question_id) values ('Ca sa ma distrez', 1, 2);
insert into options (enunciation, correctness, questions_question_id) values ('Ca sa descopar tehnologii noi', 0, 2);
--- QUESTIONS ---
insert into questions(enunciation, reward, chapter_id, characters_id) values ('Ce este html?', 10, 2, 1);
insert into questions(enunciation, reward, chapter_id, characters_id) values ('De ce te afli aici?', 10, 1, 2);
--- USERS ---
insert into users(name, user_role, email, login_type, hints_left, gold_left, avatar_path, user_level) values ('Bogdan', 'user', 'bb@yahoo.com', 'custom', 10, 10, '/home', 10);
insert into users(name, user_role, email, login_type, hints_left, gold_left, avatar_path, user_level) values ('Sefu', 'user', 'sefulinu@yahoo.com', 'social', 10, 10, '/home', 10);
