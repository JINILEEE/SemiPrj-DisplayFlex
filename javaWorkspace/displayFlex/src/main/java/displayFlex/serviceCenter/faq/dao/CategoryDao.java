package displayFlex.serviceCenter.faq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import displayFlex.serviceCenter.faq.vo.CategoryVo;
import test.JDBCTemplate;

public class CategoryDao {
	
	// FAQ 카테고리 목록 조회
    public List<CategoryVo> selectFaqCategoryList(Connection conn) throws Exception {
    	
        String sql = "SELECT FAQ_CATEGORY_NO, DIVISION AS CATEGORY_NAME FROM FAQ_CATEGORY ORDER BY FAQ_CATEGORY_NO";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        List<CategoryVo> categoryList = new ArrayList<>();
        while (rs.next()) {
            String faqCategoryNo = rs.getString("FAQ_CATEGORY_NO");
            String categoryName = rs.getString("CATEGORY_NAME");

            CategoryVo vo = new CategoryVo();
            vo.setFaqCategoryNo(faqCategoryNo);
            vo.setCategoryName(categoryName);

            categoryList.add(vo);
        }

        JDBCTemplate.close(rs);
        JDBCTemplate.close(pstmt);

        return categoryList;
    }

    //전체 카테고리 목록 조회
	public List<CategoryVo> selectAllCategoryList(Connection conn) throws Exception {
		
		//sql
		String sql = "SELECT * FROM FAQ_CATEGORY ORDER BY FAQ_CATEGORY_NO";
		PreparedStatement pstmt = conn.prepareStatement(sql); 
		ResultSet rs = pstmt.executeQuery();
        
		List<CategoryVo> categoryList = new ArrayList<>();
        while (rs.next()) {
            CategoryVo categoryVo = new CategoryVo();
            categoryVo.setFaqCategoryNo(rs.getString("FAQ_CATEGORY_NO"));
            categoryVo.setCategoryName("CATEGORY_NAME");

            categoryList.add(categoryVo);
        }
        

        return categoryList;
		
	}

    

}
