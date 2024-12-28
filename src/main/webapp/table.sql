create table post (
	post_no bigint auto_increment primary key, -- 자동갱신구문
	post_title varchar(255),
    post_content text,
    post_created_date date,
    post_hit bigint
);

create table users (
	user_no bigint auto_increment primary key, 
    user_id varchar(30),
    user_pw varchar(100),
    user_name varchar(100),
    user_nick varchar(100),
    user_reg_date date
);