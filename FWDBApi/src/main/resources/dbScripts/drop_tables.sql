drop table Chapters;
/
drop table Hints;
/
drop table Characters;
/
drop table LoginDataCustom;
/
drop table LoginDataSocial;
/
drop table Options;
/
drop table Questions;
/
drop table Users;
/
drop table PendingPasswordReset;
/
drop table LOGGED_USERS;
/
drop table TRAINING;
/
drop sequence training_id_seq;
/
drop sequence logged_users_id_seq;
/
drop sequence chapters_id_seq;
/
drop sequence hints_id_seq;
/
drop sequence characters_id_seq;
/
drop sequence logindatacustom_id_seq;
/
drop sequence logindatasocial_id_seq;
/
drop sequence options_id_seq;
/
drop sequence questions_id_seq;
/
drop sequence user_id_seq;
/
drop trigger trg_chapters_id;
/
drop trigger trg_hints_id;
/
drop trigger trg_chapters_id;
/
drop trigger trg_logindatacustom_id;
/
drop trigger trg_logindatasocial_id;
/
drop trigger trg_options_id;
/
drop trigger trg_questions_id;
/
drop trigger trg_users_id;
/
drop sequence pending_password_reset_seq;
/
drop trigger trg_logged_id;
/
drop trigger trg_training_id;
/
commit;
/