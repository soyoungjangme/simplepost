create table post (
	post_no bigint auto_increment primary key, -- 자동갱신구문
	post_title varchar(255),
    post_content text,
    post_created_date date,
    post_hit bigint
);

