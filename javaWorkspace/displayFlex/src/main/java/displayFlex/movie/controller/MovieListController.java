package displayFlex.movie.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import displayFlex.movie.dto.MovieListDto;
import displayFlex.movie.service.MovieService;
import displayFlex.movie.vo.GenreCategoryVo;
import displayFlex.movie.vo.ScreenGradeVo;
import displayFlex.util.page.vo.PageVo;


@WebServlet("/movie/list")
public class MovieListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final MovieService movieService;

	public MovieListController() {
		super();
		movieService = new MovieService();
	}


	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.service(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//영화 전체 카테고리 가져오기
			List<GenreCategoryVo> genreList =  movieService.getAllGenreCategory();
			
			//관람 등급 가져오기
			List<ScreenGradeVo> screenGradeList = movieService.getAllScreenGrade();
			
			//영화 리스트 가져오기(페이징 처리 5개씩)
			int pno = request.getParameter("pno") == null ? 1 : Integer.parseInt(request.getParameter("pno"));
			System.out.println("pno = " + pno);
			int movieCount  = movieService.getAllMovieCount();
			PageVo page = setPage(movieCount , pno, 5, 5);
			
			List<MovieListDto> movieList = movieService.getMovieList(page);
			
			//값 넣어주기
			request.setAttribute("genreList", genreList);
			request.setAttribute("screenGradeList", screenGradeList);
			request.setAttribute("pageVo", page);
			request.setAttribute("movieList", movieList);
			
			request.getRequestDispatcher("/WEB-INF/views/movie/list.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private PageVo setPage(int listCount, int currentPage, int pageLimit, int boardLimit) {
		return new PageVo(listCount, currentPage, pageLimit, boardLimit);
	}
}
