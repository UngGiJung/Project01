SELECT * FROM MEMBER;

CREATE TABLE MEMBER (
ID varchar2(20) PRIMARY KEY,
password varchar2(20) NOT NULL
)

INSERT INTO MEMBER (id, password)
VALUES('신우철','1234')

INSERT INTO MEMBER (id, password)
VALUES('강준형','4321')

SELECT *
FROM MEMBER
WHERE id = trim('  신우철   ')

INSERT INTO MEMBER VALUES (asdasd,123123)

DELETE FROM MEMBER
WHERE ID = 'qweqweqwe'