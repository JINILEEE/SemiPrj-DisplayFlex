package displayFlex.serviceCenter.faq.service;

import java.sql.Connection;
import java.util.List;

import displayFlex.serviceCenter.faq.dao.CategoryDao;
import displayFlex.serviceCenter.faq.vo.CategoryVo;
import test.JDBCTemplate;

public class CategoryService {

    // FAQ 카테고리 목록 조회
    public List<CategoryVo> selectFaqCategoryList() throws Exception {
    	
        Connection conn = JDBCTemplate.getConnection();
        
        List<CategoryVo> categoryList = new CategoryDao().selectFaqCategoryList(conn);
        
        JDBCTemplate.close(conn);
        
        return categoryList;
    }

    //카테고리 목록 조회
	public List<CategoryVo> selectAllCategoryList() throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		CategoryDao categoryDao = new CategoryDao();
		List<CategoryVo> categoryList = categoryDao.selectAllCategoryList(conn);

		JDBCTemplate.close(conn);
		
		return categoryList;
	}

}
