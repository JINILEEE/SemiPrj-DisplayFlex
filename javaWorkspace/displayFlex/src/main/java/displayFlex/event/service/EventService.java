package displayFlex.event.service;

import java.sql.Connection;
import java.util.List;

import displayFlex.admincoupon.dao.AdminCouponDao;
import displayFlex.coupon.vo.CouponVo;
import displayFlex.event.dao.EventDao;
import displayFlex.event.dto.EventDto;
import test.JDBCTemplate;

public class EventService {
	public int eventCreate(EventDto Dto) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();

		// dao
		EventDao dao = new EventDao();
		int result = dao.createEvent(conn, Dto);

		// tx
		if (result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		// close
		JDBCTemplate.close(conn);

		return result;
	}
	
	public List<EventDto> selectEventList() throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();

		// dao
		EventDao dao = new EventDao();
		List<EventDto> eventDtoList = dao.selectEventList(conn);

		// close
		JDBCTemplate.close(conn);

		return eventDtoList;

	}
	public EventDto EventDetail(String EventNo) throws Exception {

		// conn
		Connection conn = JDBCTemplate.getConnection();

		// dao
		EventDao dao = new EventDao();
		EventDto eventDtoList = dao.selectEvenDetail(conn, EventNo);

		// close
		JDBCTemplate.close(conn);

		return eventDtoList;

	}
	

}
