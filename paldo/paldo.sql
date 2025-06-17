--drop table pd_board;
--drop table pd_imgpath;
--drop table pd_user;
--drop table pd_product;

--drop sequence pd_user_seq;
--drop sequence pd_imgpath_seq;
--drop sequence pd_board_seq;
--drop sequence pd_product_seq;


create user paldo identified by paldo;
grant connect, resource, dba to paldo;
commit;


CREATE TABLE pd_user (
    userid NUMBER,
    nickname VARCHAR2(50) NOT NULL,
    pwd VARCHAR2(100) NOT NULL,
    email VARCHAR2(100),
    CONSTRAINT pk_user primary key(userid)
);
create sequence pd_user_seq;

CREATE TABLE pd_product (
    productid NUMBER,
    modelname VARCHAR2(50) not null,
    description VARCHAR2(3000) NOT NULL,
    price NUMBER NOT NULL,
    condition VARCHAR2(20),
    brand VARCHAR2(10),
    CONSTRAINT pk_product primary key(productid)
);
create sequence pd_product_seq;

CREATE TABLE pd_board (
    boardid NUMBER,
    productid NUMBER NOT NULL,
    userid NUMBER NOT NULL,
    title VARCHAR2(100) NOT NULL,
    status VARCHAR2(20),
    regdate DATE DEFAULT SYSDATE,
    CONSTRAINT pk_board primary key(boardid),
    FOREIGN KEY (productid) REFERENCES pd_product(productid) ON DELETE CASCADE,
    FOREIGN KEY (userid) REFERENCES pd_user(userid) ON DELETE CASCADE
);
create sequence pd_board_seq;


CREATE TABLE pd_imgpath (
    imgid NUMBER,
    productid NUMBER NOT NULL,
    imgpath VARCHAR2(300) NOT NULL,
    CONSTRAINT pk_imgpath primary key(imgid),
    FOREIGN KEY (productid) REFERENCES pd_product(productid) ON DELETE CASCADE
);
create sequence pd_imgpath_seq;

SELECT constraint_name FROM user_constraints WHERE table_name = 'PD_BOARD' AND constraint_type = 'P';
SELECT constraint_name FROM user_constraints WHERE table_name = 'PD_IMGPATH' AND constraint_type = 'P';
SELECT constraint_name FROM user_constraints WHERE table_name = 'PD_PRODUCT' AND constraint_type = 'P';
SELECT constraint_name FROM user_constraints WHERE table_name = 'PD_USER' AND constraint_type = 'P';







commit;