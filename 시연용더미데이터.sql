
-- 시연용 더미데이터 

-- 제품 등록
INSERT INTO PRODUCT (PRODUCT_NO, PRODUCT_CATE_NO, MEMBER_NO, IMAGE, TITLE, PRICE, PRODUCT_ELEMENT, ENROLL_DATE, DEL_YN, SHORT_DESCRIPTION) 
VALUES (SEQ_PRODUCT.NEXTVAL, 6, 1,'\cinema\resources\image\store\chestnut.png','맛밤', '3500', '맛밤', '2023-12-12', 'Y', '겨울에 더 맛있게 먹을 수 있는 맛있는 맛밤!');
COMMIT;
