drop table pd_board;
drop table pd_imgpath;
drop table pd_user;
drop table pd_product;

DROP SEQUENCE pd_user_seq;
DROP SEQUENCE pd_product_seq;
DROP SEQUENCE pd_board_seq;
DROP SEQUENCE pd_imgpath_seq;

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
    modelname VARCHAR2(100) NOT NULL,
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
    title varchar2(100) not null,
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

-- 사용자 데이터 (pd_user)
INSERT INTO pd_user (userid, nickname, pwd, email)
VALUES (pd_user_seq.NEXTVAL, 'alice', 'password123', 'alice@example.com');

INSERT INTO pd_user (userid, nickname, pwd, email)
VALUES (pd_user_seq.NEXTVAL, 'bob', 'secure456', 'bob@example.com');

-- 제품 데이터 (pd_product)
INSERT INTO pd_product (productid, modelname, description, price, condition, brand)
VALUES (pd_product_seq.NEXTVAL, 'iPhone 13', 'Apple smartphone in good condition', 900, 'used', 'Apple');

INSERT INTO pd_product (productid, modelname, description, price, condition, brand)
VALUES (pd_product_seq.NEXTVAL, 'Galaxy S21', 'Samsung flagship phone', 850, 'new', 'Samsung');

select *
from pd_user;

select *
from pd_product;

-- 예시: productid = 1, userid = 1 라고 가정하고 넣는 경우

-- 게시판 글 넣기
INSERT INTO pd_board (boardid, productid, userid, title, status, regdate)
VALUES (pd_board_seq.NEXTVAL, 1, 1, '판매 글 제목 예시', 'available', SYSDATE);

INSERT INTO pd_board (boardid, productid, userid, title, status, regdate)
VALUES (pd_board_seq.NEXTVAL, 2, 2, 'Brand new Galaxy S21 for sale', 'available', SYSDATE);

-- 이미지 경로 넣기
INSERT INTO pd_imgpath (imgid, productid, imgpath)
VALUES (pd_imgpath_seq.NEXTVAL, 1, '/images/sample_image.jpg');

INSERT INTO pd_imgpath (imgid, productid, imgpath)
VALUES (pd_imgpath_seq.NEXTVAL, 2, '/images/galaxy_s21_1.jpg');

select *
from pd_board;

select *
from pd_imgpath;

commit;