SELECT *FROM member

CREATE TABLE MEMBER (
ID varchar2(20) PRIMARY KEY,
password varchar2(20) NOT NULL
)

INSERT INTO MEMBER (id, password)
VALUES('himedia','himedia1234')

purge recyclebin

SELECT *
FROM MEMBER
WHERE id = trim('  himedia   ')

DELETE FROM MEMBER
WHERE ID = '강준형멍청이'

SELECT *FROM DB

SELECT murder, robbery, theft, violence
FROM DB
WHERE city = '서울특별시'and village like '중구'

SELECT *FROM DB2

SELECT name, address1, address2
from DB2
WHERE (address1 like '%서울특별시%' and address1 LIKE '%강남구%' and address1 LIKE '%도곡동%' or address2 LIKE '%서울특별시%' and address2 LIKE '%강남구%'and address2 LIKE '%도곡동%')