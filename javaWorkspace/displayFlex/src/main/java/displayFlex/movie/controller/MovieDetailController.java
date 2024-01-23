package displayFlex.movie.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import displayFlex.movie.dto.MovieDetailDto;

import displayFlex.movie.service.MovieService;

import displayFlex.movie.vo.StillImageVo;

@WebServlet("/movie/detail")
public class MovieDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final MovieService movieService;

    public MovieDetailController() {
        super();
        movieService = new MovieService();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.service(request, response);
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//영화 데이터 가져오기
			String movieNo = request.getParameter("movieNo");
			MovieDetailDto movieDetailDto = movieService.getMovieInfoByNo(movieNo);
			
			//해당 영화가 상영 중인지 체크하기
			int isScreening = movieService.checkScreeningByNo(movieNo);
			if(isScreening != 0) {
				movieDetailDto.setIsScreening(true);
			}
			
			//스틸 이미지 가져오기
			List<StillImageVo> stillImageList = movieService.getStillImageByMovieNo(movieNo);
			movieDetailDto.setStillsList(stillImageList);
			request.setAttribute("movie", movieDetailDto);
			request.getRequestDispatcher("/WEB-INF/views/movie/detail.jsp").forward(request, response);
		} catch (Exception e) {
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

}
