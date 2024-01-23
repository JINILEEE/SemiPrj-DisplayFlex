

-- 스토어 리스트
SELECT 
    P.PRODUCT_NO 
    , C.CATE_NAME CATEGORY 
    , P.MEMBER_NO MEMBER_NO 
    , P.IMAGE 
    , P.TITLE
    , P.PRICE 
    , P.PRODUCT_ELEMENT 
    , P.ENROLL_DATE 
    , P.DEL_YN 
    , P.SHORT_DESCRIPTION 
FROM PRODUCT P 
JOIN PRODUCT_CATEGORY C ON C.PRODUCT_CATE_NO = P.PRODUCT_CATE_NO 
JOIN MEMBER M ON M.MEMBER_NO = P.MEMBER_NO
WHERE C.CATE_NAME LIKE '베스트'
;


-- 스토어 메뉴 리스트
SELECT 
    P.PRODUCT_NO 
    , C.CATE_NAME CATEGORY 
    , P.MEMBER_NO MEMBER_NO 
    , P.IMAGE 
    , P.TITLE
    , P.PRICE 
    , P.PRODUCT_ELEMENT 
    , P.ENROLL_DATE 
    , P.DEL_YN 
    , P.SHORT_DESCRIPTION 
FROM PRODUCT P 
JOIN PRODUCT_CATEGORY C ON C.PRODUCT_CATE_NO = P.PRODUCT_CATE_NO 
JOIN MEMBER M ON M.MEMBER_NO = P.MEMBER_NO
WHERE C.CATE_NAME LIKE '베스트'
;


-- 스토어 메뉴 상세조회
SELECT 
    P.PRODUCT_NO NO
    , C.CATE_NAME CATEGORY 
    , P.MEMBER_NO MEMBER_NO 
    , P.IMAGE 
    , P.TITLE
    , P.PRICE 
    , P.PRODUCT_ELEMENT 
    , P.ENROLL_DATE 
    , P.DEL_YN 
    , P.SHORT_DESCRIPTION 
FROM PRODUCT P 
JOIN PRODUCT_CATEGORY C ON C.PRODUCT_CATE_NO = P.PRODUCT_CATE_NO 
JOIN MEMBER M ON M.MEMBER_NO = P.MEMBER_NO
WHERE P.PRODUCT_NO = 2
;
commit;



-- 스토어 제품 등록
INSERT INTO PRODUCT (PRODUCT_NO, PRODUCT_CATE_NO, IMAGE, TITLE, PRICE, PRODUCT_ELEMENT, SHORT_DESCRIPTION) 
VALUES (SEQ_PRODUCT.NEXTVAL, ?, ?, ?, ?, ?, ?);
COMMIT;
ROLLBACK;


-- 스토어 제품 수정
UPDATE PRODUCT 
SET 
    PRODUCT_CATE_NO=?
    , IMAGE= ?
    , TITLE=?
    , PRICE=?
    , PRODUCT_ELEMENT=?
    , SHORT_DESCRIPTION=?
    , DEL_YN=?
WHERE PRODUCT_NO=?
;

ROLLBACK;


-- 스토어 제품 삭제
UPDATE PRODUCT 
SET 
    DEL_YN='Y'
WHERE PRODUCT_NO=1
;



-- 관리자 리스트 페이지
SELECT 
    P.PRODUCT_NO 
    , C.CATE_NAME CATEGORY 
    , P.MEMBER_NO MEMBER_NO 
    , P.IMAGE 
    , P.TITLE , P.PRICE , P.PRODUCT_ELEMENT , P.ENROLL_DATE , P.DEL_YN 
    , P.SHORT_DESCRIPTION 
FROM PRODUCT P 
JOIN PRODUCT_CATEGORY C ON C.PRODUCT_CATE_NO = P.PRODUCT_CATE_NO 
JOIN MEMBER M ON M.MEMBER_NO = P.MEMBER_NO 
WHERE C.CATE_NAME LIKE '스낵' AND M.MEMBER_NO = 1
;






SELECT P.PRODUCT_NO , C.CATE_NAME CATEGORY , P.MEMBER_NO MEMBER_NO , P.IMAGE , P.TITLE , P.PRICE , P.PRODUCT_ELEMENT , P.ENROLL_DATE , P.DEL_YN , P.SHORT_DESCRIPTION FROM PRODUCT P JOIN PRODUCT_CATEGORY C ON C.PRODUCT_CATE_NO = P.PRODUCT_CATE_NO JOIN MEMBER M ON M.MEMBER_NO = P.MEMBER_NO WHERE C.CATE_NAME LIKE '팝콘' AND P.DEL_YN = 'N';



