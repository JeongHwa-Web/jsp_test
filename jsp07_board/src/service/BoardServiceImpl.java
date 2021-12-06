package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.BoardDAO;
import dao.BoardDAOImpl;
import dao.BoardFileDAO;
import dao.BoardFileDAOImpl;
import dto.Board;
import dto.BoardFile;
import dto.Page;

public class BoardServiceImpl implements BoardService{
	private BoardDAO bdao = new BoardDAOImpl();
	private BoardFileDAO bfdao = new BoardFileDAOImpl();
	@Override
	public String insert(Board board, List<String>fileNames) {
		int cnt = bdao.insert(board);
		for(String fileName:fileNames) {
			if(fileName==null) {
				continue;
			}
			BoardFile boardFile = new BoardFile();
			boardFile.setBnum(board.getBnum());
			boardFile.setFileName(fileName);
			bfdao.insert(boardFile);
		}
		if(cnt>0) {
			return "추가되었습니다.";
		}else {
			return "추가실패되었습니다.";
		}
		
	}

	@Override
	public String update(Board board, List<String>fileNames, String[] filedels) {
		int cnt = bdao.update(board);
		if(filedels!=null) {				
			for (String filedel : filedels) {
				bfdao.delete(Integer.parseInt(filedel));
			}
		}
		for(String fileName:fileNames) {
			if(fileName==null) {
				continue;
			}
			BoardFile boardFile = new BoardFile();
			boardFile.setBnum(board.getBnum());
			boardFile.setFileName(fileName);
			bfdao.insert(boardFile);
		}
		if(cnt>0) {
			return "수정되었습니다.";
		}else {
			return "수정실패되었습니다.";
		}
	}

	@Override
	public String delete(int bnum) {
		bfdao.delete_bnum(bnum);
		int cnt = bdao.delete(bnum);
		if(cnt>0) {
			return "삭제되었습니다.";
		}else {
			return "삭제실패되었습니다.";
		}
	}

	@Override
	public Map<String, Object> selectOne(int bnum) {
		Board board = bdao.selectOne(bnum);
		List<BoardFile> boardFile = bfdao.selectList(bnum);
		List<Board> rlist = bdao.select_reply(bnum);
		
		System.out.println(board);
		System.out.println(boardFile);
		Map<String, Object>map = new HashMap<>();
		map.put("board", board);
		map.put("boardFile",boardFile);
		map.put("rlist", rlist);
		return map;
	}

	@Override
	public List<Board> selectList(Page page) {
		//페이징처리
		int curpage = page.getCurpage();
		int perpage = page.getPerpage();
		int startnum = (curpage-1)*perpage+1; //시작번호
		int endnum = startnum+perpage-1; //끝번호
		page.setStartnum(startnum);
		page.setEndnum(endnum);
		
		//전체페이지수 구하기
		int totcnt = bdao.select_totcnt(page);
		int totpage = totcnt/perpage;
		if(totcnt%perpage>0) {
			totpage++;
		}
		page.setTotpage(totpage);
		System.out.println(totpage);
		
		//페이지블럭 구하기
		int perblock = page.getPerblock();
		int startpage = curpage-((curpage-1)%perblock);
		int endpage = startpage+perblock-1;
		if(totpage<endpage) {
			endpage = totpage;
		}

		page.setStartpage(startpage);
		page.setEndpage(endpage);
		System.out.println(startpage);
		System.out.println(endpage);
		return bdao.selectList(page);
	}

	@Override
	public void cntPlus(int bnum) {
		bdao.cntPlus(bnum);
	}

}
