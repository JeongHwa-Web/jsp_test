package controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Board;
import service.BoardService;
import service.BoardServiceImpl;
import service.ReplyService;
import service.ReplyServiceImpl;

@WebServlet("/reply/*")
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReplyService replyService = new ReplyServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		System.out.println(uri);
		request.setCharacterEncoding("utf-8");
		String path = request.getContextPath();
		if(uri.contains("add")) {
			String email = request.getParameter("email");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			int ref = Integer.parseInt(request.getParameter("ref"));
			int restep = Integer.parseInt(request.getParameter("restep"));
			int relevel = Integer.parseInt(request.getParameter("relevel"));
			Board board = new Board();
			board.setEmail(email);
			board.setSubject(subject);
			board.setContent(content);
			board.setIp(request.getRemoteAddr());
			board.setRef(ref);
			board.setRestep(restep);
			board.setRelevel(relevel);
			System.out.println(board);
			
			String msg = replyService.insert(board);
			response.sendRedirect(path+"/board/detail?bnum="+ref);
		}else if(uri.contains("remove")) {
			int rnum = Integer.parseInt(request.getParameter("rnum"));
			int bnum = Integer.parseInt(request.getParameter("bnum"));
			BoardService boardService = new BoardServiceImpl();
			String msg = boardService.delete(rnum);
			response.sendRedirect(path+"/board/detail?bnum="+bnum);
		}else if(uri.contains("modiform")) {
			int bnum = Integer.parseInt(request.getParameter("bnum"));
			int rnum = Integer.parseInt(request.getParameter("rnum"));
			Board board = replyService.selectOne(bnum);
			System.out.println(board);
			request.setAttribute("board", board);
			request.setAttribute("rnum", rnum);
			request.getRequestDispatcher("/views/board/replyModify.jsp").forward(request, response);;
		}else if(uri.contains("modify")) {
			int bnum = Integer.parseInt(request.getParameter("bnum"));
			int ref = Integer.parseInt(request.getParameter("ref"));
			String email = request.getParameter("email");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			Board board = new Board();
			board.setBnum(bnum);
			board.setEmail(email);
			board.setSubject(subject);
			board.setContent(content);
			board.setIp(request.getRemoteAddr());
			String msg = replyService.update(board);
			response.sendRedirect(path+"/board/detail?bnum="+ref+"&msg="+URLEncoder.encode(msg,"utf-8"));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
