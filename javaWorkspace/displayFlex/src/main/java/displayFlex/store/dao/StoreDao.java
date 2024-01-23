package displayFlex.store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import displayFlex.store.vo.StoreVo;
import test.JDBCTemplate;

public class StoreDao {
	
	// 스토어 메뉴 리스트(JSTL사용해 반복문 돌리기용)  (유저용)
	public List<StoreVo> selectMenuList(Connection conn) throws Exception{
	
		//sql
		String sql = "SELECT CATE_NAME CATEGORY FROM PRODUCT_CATEGORY";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		List<StoreVo> storeVoList2 = new ArrayList<StoreVo>();
		
		while(rs.next()) {
			String category = rs.getString("CATEGORY");
			StoreVo storeVo = new StoreVo(category);
			
			storeVoList2.add(storeVo);
		}
		
		//close
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return storeVoList2;
		
	}//selectMenuList

	// 스토어 리스트(유저용)
	public List<StoreVo> selectStoreList(Connection conn) throws Exception {
		
		List<StoreVo> storeVoList = new ArrayList<StoreVo>();
		
		//sql
		String sql = "SELECT P.PRODUCT_NO , C.CATE_NAME CATEGORY , P.MEMBER_NO MEMBER_NO , P.IMAGE , P.TITLE , P.PRICE , P.PRODUCT_ELEMENT , P.ENROLL_DATE , P.DEL_YN , P.SHORT_DESCRIPTION FROM PRODUCT P JOIN PRODUCT_CATEGORY C ON C.PRODUCT_CATE_NO = P.PRODUCT_CATE_NO JOIN MEMBER M ON M.MEMBER_NO = P.MEMBER_NO WHERE C.CATE_NAME LIKE '추천메뉴' AND P.DEL_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		while(rs.next()) {
			
			String productNo = rs.getString("PRODUCT_NO");
			String category  = rs.getString("CATEGORY");
			String memberNo = rs.getString("MEMBER_NO");
			String image = rs.getString("IMAGE");
			String title = rs.getString("TITLE");
			String price = rs.getString("PRICE");
			String productElement = rs.getString("PRODUCT_ELEMENT");
			String enrollDate = rs.getString("ENROLL_DATE");
			String delYn = rs.getString("DEL_YN");
			String shortDescription = rs.getString("SHORT_DESCRIPTION");
			
			StoreVo storeVo = new StoreVo(productNo, category, memberNo, image, title,price,productElement,enrollDate,delYn,shortDescription);
			
			storeVoList.add(storeVo);
			
		}
		
		//close
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		return storeVoList;
	
	}
	
	//스토어 리스트 관리자용
	public List<StoreVo> selectStoreListAdmin(Connection conn) throws Exception{
	
		List<StoreVo> storeVoList = new ArrayList<StoreVo>();
		
		//sql
		String sql = "SELECT P.PRODUCT_NO , C.CATE_NAME CATEGORY , P.MEMBER_NO MEMBER_NO , P.IMAGE , P.TITLE , P.PRICE , P.PRODUCT_ELEMENT , P.ENROLL_DATE , P.DEL_YN , P.SHORT_DESCRIPTION FROM PRODUCT P JOIN PRODUCT_CATEGORY C ON C.PRODUCT_CATE_NO = P.PRODUCT_CATE_NO JOIN MEMBER M ON M.MEMBER_NO = P.MEMBER_NO WHERE C.CATE_NAME LIKE '추천메뉴'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		while(rs.next()) {
					
					String productNo = rs.getString("PRODUCT_NO");
					String category  = rs.getString("CATEGORY");
					String memberNo = rs.getString("MEMBER_NO");
					String image = rs.getString("IMAGE");
					String title = rs.getString("TITLE");
					String price = rs.getString("PRICE");
					String productElement = rs.getString("PRODUCT_ELEMENT");
					String enrollDate = rs.getString("ENROLL_DATE");
					String delYn = rs.getString("DEL_YN");
					String shortDescription = rs.getString("SHORT_DESCRIPTION");
					
					StoreVo storeVo = new StoreVo(productNo, category, memberNo, image, title,price,productElement,enrollDate,delYn,shortDescription);
					
					storeVoList.add(storeVo);
					
				}
		
		//close
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return storeVoList;
		
	}


	// 스토어메뉴 리스트 (유저용)(fetch, gson 사용해서 클릭시 해당메뉴 데이터 보내는 데이터용)
	public List<StoreVo> storeMenuList(Connection conn, String cate) throws Exception{

		//SQL
		String sql = "SELECT P.PRODUCT_NO , C.CATE_NAME CATEGORY , P.MEMBER_NO MEMBER_NO , P.IMAGE , P.TITLE , P.PRICE , P.PRODUCT_ELEMENT , P.ENROLL_DATE , P.DEL_YN , P.SHORT_DESCRIPTION FROM PRODUCT P JOIN PRODUCT_CATEGORY C ON C.PRODUCT_CATE_NO = P.PRODUCT_CATE_NO JOIN MEMBER M ON M.MEMBER_NO = P.MEMBER_NO WHERE C.CATE_NAME LIKE ? AND P.DEL_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, cate);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		List<StoreVo> voList = new ArrayList<StoreVo>();
		
		while(rs.next()) {
			String productNo = rs.getString("PRODUCT_NO");
			String category = rs.getString("CATEGORY");
			String memberNo = rs.getString("MEMBER_NO");
			String image = rs.getString("IMAGE");
			String title = rs.getString("TITLE");
			String price = rs.getString("PRICE");
			String productElement = rs.getString("PRODUCT_ELEMENT");
			String enrollDate = rs.getString("ENROLL_DATE");
			String delYn = rs.getString("DEL_YN");
			String shortDescription = rs.getString("SHORT_DESCRIPTION");
			
			StoreVo vo = new StoreVo(productNo, category, memberNo, image, title, price, productElement, enrollDate, delYn, shortDescription);
			voList.add(vo);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;
	}

	
	// 스토어 메뉴 리스트 (관리자용)(위에 보이는 메뉴 JSTL사용해 반복문 돌리기용)  (관리자용)
	public List<StoreVo> storeMenuListAdmin(Connection conn, String cate) throws Exception {
		
		//SQL
		String sql = "SELECT P.PRODUCT_NO , C.CATE_NAME CATEGORY , P.MEMBER_NO MEMBER_NO , P.IMAGE , P.TITLE , P.PRICE , P.PRODUCT_ELEMENT , P.ENROLL_DATE , P.DEL_YN , P.SHORT_DESCRIPTION FROM PRODUCT P JOIN PRODUCT_CATEGORY C ON C.PRODUCT_CATE_NO = P.PRODUCT_CATE_NO JOIN MEMBER M ON M.MEMBER_NO = P.MEMBER_NO WHERE C.CATE_NAME LIKE ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, cate);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		List<StoreVo> voList = new ArrayList<StoreVo>();
		
		while(rs.next()) {
			String productNo = rs.getString("PRODUCT_NO");
			String category = rs.getString("CATEGORY");
			String memberNo = rs.getString("MEMBER_NO");
			String image = rs.getString("IMAGE");
			String title = rs.getString("TITLE");
			String price = rs.getString("PRICE");
			String productElement = rs.getString("PRODUCT_ELEMENT");
			String enrollDate = rs.getString("ENROLL_DATE");
			String delYn = rs.getString("DEL_YN");
			String shortDescription = rs.getString("SHORT_DESCRIPTION");
			
			StoreVo vo = new StoreVo(productNo, category, memberNo, image, title, price, productElement, enrollDate, delYn, shortDescription);
			voList.add(vo);
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;
	}
	
	//제품 디테일 화면 보여주기
	public StoreVo selectMenuListByNo(Connection conn, String no) throws Exception{

		// sql
		String sql = "SELECT P.PRODUCT_NO NO , C.CATE_NAME CATEGORY , P.MEMBER_NO MEMBER_NO , P.IMAGE , P.TITLE , P.PRICE , P.PRODUCT_ELEMENT , P.ENROLL_DATE , P.DEL_YN , P.SHORT_DESCRIPTION FROM PRODUCT P JOIN PRODUCT_CATEGORY C ON C.PRODUCT_CATE_NO = P.PRODUCT_CATE_NO JOIN MEMBER M ON M.MEMBER_NO = P.MEMBER_NO WHERE P.PRODUCT_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		ResultSet rs = pstmt.executeQuery();
		
		// rs
		StoreVo vo = null;
		if(rs.next()) {
			String productNo = rs.getString("NO");
			String category = rs.getString("CATEGORY");
			String memberNo = rs.getString("MEMBER_NO");
			String image = rs.getString("IMAGE");
			String title = rs.getString("TITLE");
			String price = rs.getString("PRICE");
			String productElement = rs.getString("PRODUCT_ELEMENT");
			String enrollDate = rs.getString("ENROLL_DATE");
			String delYn = rs.getString("DEL_YN");
			String shortDescription = rs.getString("SHORT_DESCRIPTION");
			
			vo = new StoreVo();
			vo.setProductNo(productNo);
			vo.setCategory(category);
			vo.setMemberNo(memberNo);
			vo.setImage(image);
			vo.setTitle(title);
			vo.setPrice(price);
			vo.setProductElement(productElement);
			vo.setEnrollDate(enrollDate);
			vo.setDelYn(delYn);
			vo.setShortDescription(shortDescription);
			
		}
		
		//close
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		return vo;
	
	}//selectMenuListByNo

	// 제품 등록글 작성 로직
	public int storeEnroll(Connection conn, StoreVo vo) throws Exception {
		
		System.out.println("dao : " + vo);

		//sql
		String sql = "INSERT INTO PRODUCT (PRODUCT_NO, PRODUCT_CATE_NO, IMAGE, TITLE, PRICE, PRODUCT_ELEMENT, SHORT_DESCRIPTION, DEL_YN) VALUES (SEQ_PRODUCT.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getCategory());
		pstmt.setString(2, vo.getImage());
		pstmt.setString(3, vo.getTitle());
		pstmt.setString(4, vo.getPrice());
		pstmt.setString(5, vo.getProductElement());
		pstmt.setString(6, vo.getShortDescription());
		pstmt.setString(7, vo.getDelYn());
		int result = pstmt.executeUpdate();
		
		//rs
		
		//close
		JDBCTemplate.close(pstmt);
		return result;
		
	}

	// 제품 상세글 수정 로직
	public int updateMenuByNo(Connection conn, StoreVo vo) throws Exception {
		
		//sql
		String sql = "UPDATE PRODUCT SET PRODUCT_CATE_NO=? , IMAGE= ? , TITLE=? , PRICE=? , PRODUCT_ELEMENT=? , SHORT_DESCRIPTION=? , DEL_YN=?,  ENROLL_DATE=LOCALTIMESTAMP WHERE PRODUCT_NO=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getCategory());
		pstmt.setString(2, vo.getImage());
		pstmt.setString(3, vo.getTitle());
		pstmt.setString(4, vo.getPrice());
		pstmt.setString(5, vo.getProductElement());
		pstmt.setString(6, vo.getShortDescription());
		pstmt.setString(7, vo.getDelYn());
		pstmt.setString(8, vo.getProductNo());
		int result = pstmt.executeUpdate();
		
		//close
		JDBCTemplate.close(pstmt);
		return result;
	
	}

	// 제품 삭제 화면
	public int delete(Connection conn, String no) throws Exception {

		//sql
		String sql = "UPDATE PRODUCT SET DEL_YN='Y' WHERE PRODUCT_NO=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		int result = pstmt.executeUpdate();
		
		//close
		JDBCTemplate.close(pstmt);
		return result;
	
	}

}//class






