package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dto.Member;
import service.MemberService;
import service.MemberServiceImpl;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService mservice = new MemberServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		System.out.println(uri);
		String path = request.getContextPath();
		if(uri.contains("add")) {
			//파일을 저장할 경로(서버)
//			String saveDirectory = "C:/Users/hjh54/OneDrive - 경기과학기술대학교/dropbox_hjh/java202102/savedir";
			//web.xml에서 경로 불러오기
			String saveDirectory = getServletContext().getInitParameter("savedir");
			int size = 1024*1024*10;//10m
//			new DefaultFileRenamePolicy(): 같은 이름의 파일이 있을때 파일 이름 변경
			MultipartRequest multi = new MultipartRequest(request, saveDirectory, size, "utf-8", new DefaultFileRenamePolicy());
			String email = multi.getParameter("email");
			String userpw = multi.getParameter("userpw");
			String zipcode = multi.getParameter("zipcode");
			String addr = multi.getParameter("addr");
			String addrdetail = multi.getParameter("addrdetail");
			String fileName = multi.getFilesystemName("fileName");
			if(fileName==null) {
				fileName="";
			}
			Member member = new Member();
			member.setEmail(email);
			member.setUserpw(userpw);
			member.setZipcode(zipcode);
			member.setAddr(addr);
			member.setAddrdetail(addrdetail);
			member.setFileName(fileName);
			String msg = mservice.insert(member);
			msg = URLEncoder.encode(msg, "utf-8");
			response.sendRedirect(path+"/views/home.jsp?msg="+msg);
		}else if(uri.contains("login")) {
			String email = request.getParameter("email");
			String userpw = request.getParameter("userpw");
			Map<String,String> map = mservice.login(email,userpw);
			String rcode = map.get("rcode");
			String msg = map.get("msg");
			if(rcode.equals("0")) {				
				HttpSession session = request.getSession();
				session.setAttribute("email", email);
				session.setMaxInactiveInterval(60*60*2);
				String idsave = request.getParameter("idsave");
				Cookie cookie = new Cookie("email",email);
				if(idsave!=null) {
					cookie.setPath(path);
					cookie.setMaxAge(60*60*2);
					response.addCookie(cookie);
				}else {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
				msg = URLEncoder.encode(msg, "utf-8");
				response.sendRedirect(path+"/views/home.jsp?msg="+msg);
			}else {
				msg = URLEncoder.encode(msg, "utf-8");
				response.sendRedirect(path+"/views/member/login.jsp?msg="+msg);				
			}
		}else if(uri.contains("logout")) {
			HttpSession session = request.getSession();
			session.invalidate();//모든 세션변수 삭제
			String msg = "로그아웃 되었습니다.";
			msg = URLEncoder.encode(msg, "utf-8");
			response.sendRedirect(path+"/views/home.jsp?msg="+msg);					
		}else if(uri.contains("myInfo")) {
			HttpSession session = request.getSession();
			String email = (String)session.getAttribute("email");
			Member member = mservice.selectOne(email);
			request.setAttribute("member", member);
			request.getRequestDispatcher("/views/member/myInfo.jsp").forward(request, response);
		}else if(uri.contains("modify")) {
			//파일을 저장할 경로(서버)
			String saveDirectory = getServletContext().getInitParameter("savedir");
			int size = 1024*1024*10;//10m
//			new DefaultFileRenamePolicy(): 같은 이름의 파일이 있을때 파일 이름 변경
			MultipartRequest multi = new MultipartRequest(request, saveDirectory, size, "utf-8", new DefaultFileRenamePolicy());
			String email = multi.getParameter("email");
			String userpw = multi.getParameter("userpw");
			String newpw = multi.getParameter("newpw");
			String zipcode = multi.getParameter("zipcode");
			String addr = multi.getParameter("addr");
			String addrdetail = multi.getParameter("addrdetail");
			String fileName = multi.getParameter("fileName");
			String newFileName = multi.getFilesystemName("file");
			String fileDelete = multi.getParameter("fileDelete");
			if(newFileName != null) {
				fileName=newFileName;
			}else if(fileDelete != null) {
				fileName="";
			}
			Member member = new Member();
			member.setEmail(email);
			member.setUserpw(userpw);
			member.setZipcode(zipcode);
			member.setAddr(addr);
			member.setAddrdetail(addrdetail);
			member.setFileName(fileName);
			String msg = mservice.update(member,newpw);
			msg = URLEncoder.encode(msg, "utf-8");
			response.sendRedirect(path+"/member/myInfo?msg="+msg);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
