--drop table tbl_board;
--drop SEQUENCE seq_board;

create sequence seq_board;

create table tbl_board (
    bno number(10,0),
    title varchar2(200) not null,
    content varchar2(2000) not null,
    writer varchar2(50) not null,
    regdate date default sysdate,
    updatedate date default sysdate
);
--                    기본키 이름을 아래와 같이 지정하고 기본키 설정
alter table tbl_board add constraint pk_board primary key (bno);

-- 위와 같이 설정하여 기본키 이름이 설정된 이름으로 나옴.
SELECT constraint_name
FROM user_constraints
WHERE table_name = 'TBL_BOARD'  --대문자로 써야함
AND constraint_type = 'P';

-- 기본키 이름을 설정하지 않아서 오라클이 만든 이름으로 표시됨.
SELECT constraint_name
FROM user_constraints
WHERE table_name = 'BOARD'
AND constraint_type = 'P';