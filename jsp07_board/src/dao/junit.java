package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import dto.Board;

class junit {
	BoardDAO bdao = new BoardDAOImpl();
	@Test
	void testMBConn() {
		MBConn.getSession();
	}

	@Test
	void testInsert() {
		Board board = new Board();
		board.setEmail("hong@naver.com");
		board.setSubject("제목");
		board.setContent("내용");
		board.setIp("192.168.0.1");
		int cnt = bdao.insert(board);
		System.out.println(cnt+"건 추가");
	}
	@Test
	void testupdate() {
		Board board = new Board();
		board.setBnum(4);
		board.setEmail("hong@naver.com");
		board.setSubject("제목");
		board.setContent("내용");
		board.setIp("192.168.0.1");
		int cnt = bdao.update(board);
		System.out.println(cnt+"건 수정");
	}
	
	@Test
	void testdelete() {
		int cnt = bdao.delete(4);
		System.out.println(cnt+"건 삭제");
	}
	@Test
	void testselectOne() {
		Board board = bdao.selectOne(3);
		System.out.println(board);
	}
	@Test
	void testselectList() {
		Map<String,String>map = new HashMap<>();
		map.put("findkey", "subcon");
		map.put("findvalue", "제목");
		List<Board> board = bdao.selectList(map);
		System.out.println(board);
	}
}
