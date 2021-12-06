package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dto.Board;
import dto.Page;
import service.BoardService;
import service.BoardServiceImpl;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		System.out.println(uri);
		BoardService boardService = new BoardServiceImpl();
		String path = request.getContextPath();
		if(uri.contains("list")) {
			String findkey = request.getParameter("findkey");
			String findvalue = request.getParameter("findvalue");
			String curpage_s = request.getParameter("curpage");
			int curpage = 1;
			if(curpage_s!=null) {
				curpage = Integer.parseInt(curpage_s);
			}
			Page page = new Page();
			page.setFindkey(findkey);
			page.setFindvalue(findvalue);
			page.setCurpage(curpage);
			System.out.println("controller:"+curpage);
			List<Board> blist = boardService.selectList(page);
			System.out.println("blist:"+blist);
			request.setAttribute("blist", blist);
			request.setAttribute("page", page);
			request.getRequestDispatcher("/views/board/list.jsp").forward(request, response);
		}else if(uri.contains("add")) {
			String saveDirectory = getServletContext().getInitParameter("savedir");
			int size = 1024*1024*10;
			MultipartRequest multi = new MultipartRequest(request, saveDirectory, size, "utf-8", new DefaultFileRenamePolicy());
			String email = multi.getParameter("email");
			String subject = multi.getParameter("subject");
			String content = multi.getParameter("content");
			
			Board board = new Board();
			board.setEmail(email);
			board.setSubject(subject);
			board.setContent(content);
			board.setIp(request.getRemoteAddr());
			System.out.println(board);
			
			List<String>fileNames = new ArrayList<>();
			//파일의 이름 모음
			Enumeration files = multi.getFileNames();
			while(files.hasMoreElements()) {//다음 자료가 있으면 true
				String name = (String)files.nextElement(); //file1,file2,fil3
				System.out.println(name);
				String fileName = multi.getFilesystemName(name);
				fileNames.add(fileName);
			}

			String msg = boardService.insert(board,fileNames);
			response.sendRedirect(path+"/board/list?msg="+URLEncoder.encode(msg, "utf-8"));	
		}else if(uri.contains("detail")) {
			int bnum = Integer.parseInt(request.getParameter("bnum"));
			//조회수+1
			String cntPlus = request.getParameter("cntPlus");
			if(cntPlus!=null) {
				boardService.cntPlus(bnum);
			}
			Map<String, Object>map = boardService.selectOne(bnum);
			
			request.setAttribute("map", map);
			request.getRequestDispatcher("/views/board/detail.jsp").forward(request, response);;
		}else if(uri.contains("remove")) {
			int bnum = Integer.parseInt(request.getParameter("bnum"));
			String msg = boardService.delete(bnum);
			response.sendRedirect(path+"/board/list?msg="+URLEncoder.encode(msg, "utf-8"));
		}else if(uri.contains("modiForm")) {
			int bnum = Integer.parseInt(request.getParameter("bnum"));
			Map<String, Object>map = boardService.selectOne(bnum);
			request.setAttribute("map", map);
			request.getRequestDispatcher("/views/board/modify.jsp").forward(request, response);
		}else if(uri.contains("modify")) {
			//수정
			String saveDirectory = getServletContext().getInitParameter("savedir");
			int size = 1024*1024*10;
			MultipartRequest multi = new MultipartRequest(request, saveDirectory, size, "utf-8", new DefaultFileRenamePolicy());
			int bnum = Integer.parseInt(multi.getParameter("bnum"));
			String email = multi.getParameter("email");
			String subject = multi.getParameter("subject");
			String content = multi.getParameter("content");
			Board board = new Board();
			board.setBnum(bnum);
			board.setEmail(email);
			board.setSubject(subject);
			board.setContent(content);
			board.setIp(request.getRemoteAddr());
			//파일 개별삭제
			String[] filedels = multi.getParameterValues("filedel");
			//파일 추가			
			List<String>fileNames = new ArrayList<>();
			//파일의 이름 모음
			Enumeration<String> files = multi.getFileNames();
			while(files.hasMoreElements()) {//다음 자료가 있으면 true
				String name = (String)files.nextElement(); //file1,file2,fil3
				System.out.println("name:"+name);
				String fileName = multi.getFilesystemName(name);
				if(fileName!=null) {					
					fileNames.add(fileName);
				}
			}
			String msg = boardService.update(board,fileNames,filedels);
			response.sendRedirect(path+"/board/detail?bnum="+bnum+"&msg="+URLEncoder.encode(msg, "utf-8"));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
