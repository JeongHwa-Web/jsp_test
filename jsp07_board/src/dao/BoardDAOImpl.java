package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.Board;
import dto.Page;

public class BoardDAOImpl implements BoardDAO{

	@Override
	public int insert(Board board) {
		try(SqlSession session = MBConn.getSession()){
			return session.insert("BoardMapper.insert", board);
		}
	}

	@Override
	public int update(Board board) {
		try(SqlSession session = MBConn.getSession()){
			return session.update("BoardMapper.update", board);
		}
	}

	@Override
	public int delete(int bnum) {
		try(SqlSession session = MBConn.getSession()){
			return session.delete("BoardMapper.delete", bnum);
		}
	}

	@Override
	public Board selectOne(int bnum) {
		try(SqlSession session = MBConn.getSession()){
			return session.selectOne("BoardMapper.selectOne", bnum);
		}
	}

	@Override
	public List<Board> selectList(Page page) {
		try(SqlSession session = MBConn.getSession()){
			return session.selectList("BoardMapper.selectList", page);
		}
	}

	@Override
	public void cntPlus(int bnum) {
		try(SqlSession session = MBConn.getSession()){
			session.update("BoardMapper.cntPlus", bnum);
		}
	}
	//전체 게시물수
	@Override
	public int select_totcnt(Page page) {
		try(SqlSession session = MBConn.getSession()){
			return session.selectOne("BoardMapper.select_totcnt", page);
		}
	}

	@Override
	public void update_restepplus(Board board) {
		try(SqlSession session = MBConn.getSession()){
			session.update("BoardMapper.update_restepplus", board);
		}
	}

	@Override
	public List<Board> select_reply(int ref) {
		try(SqlSession session = MBConn.getSession()){
			return session.selectList("BoardMapper.select_reply", ref);
		}
	}

}
