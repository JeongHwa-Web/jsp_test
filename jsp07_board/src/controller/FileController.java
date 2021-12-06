package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/file/*")
public class FileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		System.out.println(uri);
		if(uri.contains("download")) {
			//파일다운로드
			//디렉토리,파일이름
			String saveDirectory = getServletContext().getInitParameter("savedir");
			String fileName = request.getParameter("fileName");
			//마임타입: 파일의 종류
			String mineType = getServletContext().getMimeType(fileName);
			if(mineType == null) {
				mineType = "application/octet-stream;charset=utf-8";
			}
			response.setContentType(mineType);
			//첨부파일로 파일을 보낼때
			response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(fileName,"utf-8"));
			//읽어올 파일 경로명
			String fileUrl = saveDirectory+'/'+fileName;
			System.out.println(fileUrl);
			//입력스트림
			FileInputStream fis = new FileInputStream(fileUrl);
			//출력스트림
			ServletOutputStream sos = response.getOutputStream();
			byte[] b = new byte[4096];
			int numRead = 0;
			while((numRead = fis.read(b,0,b.length)) != -1) {
				sos.write(b,0,numRead);
			}
			sos.flush();
			sos.close();
			fis.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
