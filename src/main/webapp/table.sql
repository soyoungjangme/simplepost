create table posts (
	post_no bigint auto_increment primary key, -- 자동갱신구문
    post_writer_no bigint,
	post_title varchar(255),
    post_content text,
    post_reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
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

CREATE TABLE post_hit_history (
    history_no bigint PRIMARY KEY AUTO_INCREMENT,
    user_no bigint NOT NULL,
    post_no bigint NOT NULL,
    hit_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (post_no) REFERENCES posts(post_no),
    foreign key (user_no) references users(user_no)
);

create table post_comments (
	comment_no bigint primary key auto_increment,
    user_no bigint not null,
    post_no bigint not null,
    comment_text text,
    comment_date timestamp default current_timestamp,
    foreign key (user_no) references users(user_no),
    foreign key (post_no) references posts(post_no)
);