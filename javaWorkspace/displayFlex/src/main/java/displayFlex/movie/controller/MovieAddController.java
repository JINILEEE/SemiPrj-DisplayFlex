package displayFlex.movie.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import displayFlex.movie.service.MovieService;
import displayFlex.movie.vo.MovieVo;
import displayFlex.movie.vo.ScreenGradeVo;

@WebServlet("/admin/movie/add")
@MultipartConfig(
		maxFileSize = 1024 * 1024/*1MB*/ * 10
		, maxRequestSize = 1024 * 1024 * 50
	)
public class MovieAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final MovieService movieService;

    public MovieAddController() {
    	movieService = new MovieService();
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.service(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//관람 등급 가져오기
			List<ScreenGradeVo> screenGradeList = movieService.getAllScreenGrade();
 			request.setAttribute("screenGrade", screenGradeList);
			request.getRequestDispatcher("/WEB-INF/views/movie/add.jsp").forward(request, response);			
		} catch (Exception e) {
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String movieCd = request.getParameter("movieCd");				//영화 코드
			String title = request.getParameter("title");								//영화 제목
			String director = request.getParameter("director");					//감독
			String poster = request.getParameter("poster");						//포스터
			String genre = request.getParameter("genre");						//장르
			String releaseDate = request.getParameter("releaseDate");			//개봉일
			String screenGrade = request.getParameter("screenGrade");		//관람 등급 번호
			String runningTime = request.getParameter("runningTime");		//상영 시간
			String actor = request.getParameter("actor");							//배우
			String rate = request.getParameter("rate");								//평점
			String nation = request.getParameter("nation");						//국가
			
			String story = request.getParameter("story");							//줄거리
			String mainImage = null; 													//메인 페이지용 이미지 경로
			List<Part> parts = (request.getParts().stream().filter(element -> element.getName().equals("mainImage") || element.getName().equals("stillImage") )).toList();
			List<String> stillImageUrl = Arrays.stream(request.getParameterValues("stillImageUrl")).filter(el -> !el.equals("")).toList(); //스틸 이미지 파일 url 경로
			
			System.out.println(stillImageUrl);
			String sep = File.separator;
			for(Part p : parts) {
	            String name = p.getName();            
	            String fileName = UUID.randomUUID().toString() +"_"+getFileName(p);
        

				//메인 페이지용 사진일 경우
				if(name.equals("mainImage")) {
					Path filePath = Paths.get(request.getServletContext().getRealPath(sep+ "resources"+sep+"image"+sep+"movie"+sep+"main"), fileName);
					String mainImagePath = String.valueOf(filePath);

					 try (InputStream input = p.getInputStream()) {
			                Files.copy(input, filePath , StandardCopyOption.REPLACE_EXISTING);
			            }
					 mainImage = mainImagePath.substring(mainImagePath.indexOf("resources"));
				} 
				// 스틸이미지용일 경우
				else {
					Path filePath = Paths.get(request.getServletContext().getRealPath(sep+ "resources"+sep+"image"+sep+"movie"+sep+"stills"), fileName);
					String stillImagePath = String.valueOf(filePath);


	                try (InputStream input = p.getInputStream()) {
	                         Files.copy(input, filePath , StandardCopyOption.REPLACE_EXISTING);
	                     }
	                stillImageUrl.add(stillImagePath.substring(stillImagePath.indexOf("resources")));
	            }
	         }
			
			MovieVo newMovie = new MovieVo(null, title, actor, story, rate, director, screenGrade, poster, runningTime, releaseDate, null, null, genre, nation, mainImage);
			int result = movieService.addMovie(newMovie, stillImageUrl);	
			
			if(result == 1) {
				request.getSession().setAttribute("alertMsg", "영화가 등록 되었습니다.");
				response.sendRedirect(request.getContextPath()+"/movie/list?pno=1");
			} else 
				throw new Exception();
		} catch (Exception e) {
			request.getSession().setAttribute("alertMsg", "영화 등록에 실패하셨습니다.");
			response.sendRedirect(request.getContextPath()+"/admin/movie/add");
			e.printStackTrace();
		}
	}

	 private String getFileName(final Part part) {
	        final String partHeader = part.getHeader("content-disposition");
	        for (String content : partHeader.split(";")) {
	            if (content.trim().startsWith("filename")) {
	                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
	            }
	        }
	        return null;
	    }
}
