package displayFlex.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

import displayFlex.member.MemberVo;

/**
 * Servlet Filter implementation class AdminFilter
 */
@WebFilter("/admin/*")
public class AdminFilter extends HttpFilter implements Filter {
  

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");
		if(loginMember != null && loginMember.getAdminYn().equals("Y")) {
			chain.doFilter(request, response);			
		} else {
			req.setAttribute("errorMsg", "관리자만 이용가능합니다.");
			//경로 어디로 이동?
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(request, response);
		}
	}

}
