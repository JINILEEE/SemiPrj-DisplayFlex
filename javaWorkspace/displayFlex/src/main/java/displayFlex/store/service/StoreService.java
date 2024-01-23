package displayFlex.store.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import displayFlex.store.dao.StoreDao;
import displayFlex.store.vo.StoreVo;
import test.JDBCTemplate;

public class StoreService {
	
	// 스토어 메뉴 리스트(위에 보이는 메뉴 JSTL사용해 반복문 돌리기용)  (유저용)
	public List<StoreVo> selectMenuList() throws Exception{
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao 
		StoreDao dao = new StoreDao();
		List<StoreVo> storeVoList2 = dao.selectMenuList(conn);
		
		//close
		JDBCTemplate.close(conn);
		return storeVoList2;
		
	}// selectMenuList

	// 스토어 리스트(유저용)
	public List<StoreVo> selectStoreList() throws Exception{

		
		//conn
		Connection conn = JDBCTemplate.getConnection();

		//dao
		StoreDao dao = new StoreDao();
		List<StoreVo> storeVoList = dao.selectStoreList(conn);
		
		
		//close
		JDBCTemplate.close(conn);
		return storeVoList;
	
	}
	
	//스토어 리스트 관리자용
	public List<StoreVo> selectStoreListAdmin() throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();

		//dao
		StoreDao dao = new StoreDao();
		List<StoreVo> list2 = dao.selectStoreListAdmin(conn);
		
		
		//close
		JDBCTemplate.close(conn);
		return list2;
		
	}

	// 스토어메뉴 리스트 (유저용)(fetch, gson 사용해서 클릭시 해당메뉴 데이터 보내는 데이터용)
	public List<StoreVo> storeMenuList(String cate) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		StoreDao dao = new StoreDao();
		List<StoreVo> list = dao.storeMenuList(conn, cate);
		
		//tx
		
		//close
		JDBCTemplate.close(conn);
		
		return list;
	}
	

	// 스토어 메뉴 리스트 (관리자용)(위에 보이는 메뉴 JSTL사용해 반복문 돌리기용)  (관리자용)
	public List<StoreVo> storeMenuListAdmin(String cate) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		StoreDao dao = new StoreDao();
		List<StoreVo> list = dao.storeMenuListAdmin(conn, cate);
		
		//tx
		
		//close
		JDBCTemplate.close(conn);
		
		return list;
	}
	

	//제품 디테일 화면 보여주기
	public Map<String, Object> selectMenuListByNo(String no) throws Exception{

		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		StoreDao dao = new StoreDao();
		StoreVo vo = dao.selectMenuListByNo(conn, no);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("vo", vo);
		
		//tx
		
		//close
		JDBCTemplate.close(conn);
		return map;
	
	}

	// 제품 등록글 작성 로직
	public int storeEnroll(StoreVo vo) throws Exception {

		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		StoreDao dao = new StoreDao();
		int result = dao.storeEnroll(conn, vo);
		
		//tx
		if(result ==1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		//close
		JDBCTemplate.close(conn);
		return result;
	
	}

	// 제품 수정 화면
	public Map<String, Object> edit(String no) throws Exception {
		
		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		StoreDao dao = new StoreDao();
		StoreVo vo = dao.selectMenuListByNo(conn, no);
		
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("vo", vo);
		
		//close
		JDBCTemplate.close(conn);
		return m;

	}

	// 제품 상세글 수정 로직
	public int edit(StoreVo vo) throws Exception {

		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		StoreDao dao = new StoreDao();
		int result = dao.updateMenuByNo(conn, vo);
		
		//tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		//close
		JDBCTemplate.close(conn);
		return result;
	
	}

	// 제품 삭제 화면
	public int delete(String no) throws Exception{

		//conn
		Connection conn = JDBCTemplate.getConnection();
		
		//dao
		StoreDao dao = new StoreDao();
		int result = dao.delete(conn, no);
		
		//tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		//close
		JDBCTemplate.close(conn);
		return result;
	
	
	}


}//class
