package service;

import dao.BoardDAO;
import dao.BoardDAOImpl;
import dto.Board;

public class ReplyServiceImpl implements ReplyService{
	private BoardDAO bdao = new BoardDAOImpl();
	@Override
	public String insert(Board board) {
		//부모의 글 순서(restep)+1
		board.setRestep(board.getRestep()+1);
		//부모의 글레벨(relevel)+1
		board.setRelevel(board.getRelevel()+1);
		System.out.println("서비스:"+board);
		//기존 댓글의 순서를 변경
		bdao.update_restepplus(board);
		int cnt = bdao.insert(board);
		System.out.println(cnt);
		if(cnt>0) {
			return "추가 성공";
		}else {
			return "추가 실패";
		}
	}
	
	@Override
	public Board selectOne(int bnum) {
		return bdao.selectOne(bnum); 
	}
	
	@Override
	public String update(Board board) {
		int cnt = bdao.update(board);
		if(cnt>0) {
			return "수정성공";
		}else {
			return "수정실패";
		}
		
	}
	

}
