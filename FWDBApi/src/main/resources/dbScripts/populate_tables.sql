
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

insert into options (enunciation, correctness, questions_question_id) values ('Home Tool Markup Language', 0, 3);
insert into options (enunciation, correctness, questions_question_id) values ('Hyper Text Markup Language', 1, 3);
insert into options (enunciation, correctness, questions_question_id) values ('Hyperlinks and Text Markup Language', 0, 3);


insert into options (enunciation, correctness, questions_question_id) values (' <break>', 0, 4);
insert into options (enunciation, correctness, questions_question_id) values ('<br>', 1, 4);
insert into options (enunciation, correctness, questions_question_id) values ('<lb>', 0, 4);


insert into options (enunciation, correctness, questions_question_id) values ('<a href="url" target="new">', 0, 5);
insert into options (enunciation, correctness, questions_question_id) values ('<a href="url" target="_blank>', 1, 5);
insert into options (enunciation, correctness, questions_question_id) values ('<a href="url" target="new">', 0, 5);


insert into options (enunciation, correctness, questions_question_id) values ('<input type="textfield">', 0, 6);
insert into options (enunciation, correctness, questions_question_id) values ('<input type="text">', 1, 6);
insert into options (enunciation, correctness, questions_question_id) values ('<textfield>', 0, 6);


--- QUESTIONS ---
insert into questions(enunciation, reward, chapter_id, characters_id) values ('Ce este html?', 10, 2, 1);
insert into questions(enunciation, reward, chapter_id, characters_id) values ('De ce te afli aici?', 10, 1, 2);
insert into questions(enunciation, reward, chapter_id, characters_id) values ('Care este acronimul pentru HTML?', 10, 2, 3);

insert into questions(enunciation, reward, chapter_id, characters_id) values ('Care este elementul HTML corect pentru a trece pe rândul urm?tor (line break)?', 10, 2, 4);
insert into questions(enunciation, reward, chapter_id, characters_id) values ('Cum poti deschide un link într-o fereastra tab nou?', 10, 1, 5);
insert into questions(enunciation, reward, chapter_id, characters_id) values ('Care este codul HTML corect pentru a crea o caset? de text?', 10, 2, 6);


--- USERS ---
insert into users(name, user_role, email, login_type, hints_left, gold_left, avatar_path, user_level) values ('Bogdan', 'user', 'bb@yahoo.com', 'custom', 10, 10, '/home', 10);
insert into users(name, user_role, email, login_type, hints_left, gold_left, avatar_path, user_level) values ('Sefu', 'user', 'sefulinu@yahoo.com', 'social', 10, 10, '/home', 10);

--- LOGGED_USERS ---
insert into LOGGED_USERS(USER_NAME) values ('Bogdan');
insert into LOGGED_USERS(USER_NAME) values ('Cristi');

--- TRAINING ----
insert into TRAINING values (1, 'Bogdan', 7);
insert into TRAINING values (2, 'Bogdan', 6);
insert into TRAINING values (3, 'Cristi', 8);
insert into TRAINING values (4, 'Cristi', 7);

commit;