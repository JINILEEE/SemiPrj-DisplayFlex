package displayFlex.movie.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import displayFlex.movie.dto.MovieListDto;
import displayFlex.movie.service.MovieService;
import displayFlex.movie.vo.GenreCategoryVo;
import displayFlex.movie.vo.ScreenGradeVo;
import displayFlex.util.page.vo.PageVo;

/**
 * Servlet implementation class MovieSearchController
 */
@WebServlet("/movie/list/search")
public class MovieSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final MovieService movieService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieSearchController() {
        super();
        movieService = new MovieService();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.service(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//영화 전체 카테고리 가져오기
			List<GenreCategoryVo> genreList =  movieService.getAllGenreCategory();
			
			//관람 등급 가져오기
			List<ScreenGradeVo> screenGradeList = movieService.getAllScreenGrade();
			String[] genres = request.getParameterValues("genres");
			String grade = request.getParameter("grade") == "" ? null : request.getParameter("grade");
			
			//영화 리스트 가져오기(페이징 처리 5개씩)
			int pno = request.getParameter("pno") == null ? 1 : Integer.parseInt(request.getParameter("pno"));
			System.out.println("pno = " + pno);
			int movieCount  = movieService.getAllMovieCountByCondition(genres, grade);
			System.out.println("movieCount = " + movieCount);
			PageVo page = setPage(movieCount , pno, 5, 5);
			List<MovieListDto> movieList = movieService.findMoiveListByCondition(genres, grade, page);
			
			String selectedQuery = request.getQueryString();
			//쿼리 스트링 중복 생성 방지
			if(selectedQuery.contains("&pno")) {
				selectedQuery = request.getQueryString().substring(0, selectedQuery.lastIndexOf("&"));				
			}

			request.setAttribute("genreList", genreList);
			request.setAttribute("selectGenre", genres);	
			request.setAttribute("selectGrade", grade);
			
			Gson gson = new Gson();
			request.setAttribute("a", gson.toJson(genreList));
			request.setAttribute("b", gson.toJson(genres));

			request.setAttribute("screenGradeList", screenGradeList);
			request.setAttribute("pageVo", page);
			request.setAttribute("movieList", movieList);
			request.setAttribute("queryString", selectedQuery);
			request.getRequestDispatcher("/WEB-INF/views/movie/search-list.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private PageVo setPage(int listCount, int currentPage, int pageLimit, int boardLimit) {
		return new PageVo(listCount, currentPage, pageLimit, boardLimit);
	}
}
