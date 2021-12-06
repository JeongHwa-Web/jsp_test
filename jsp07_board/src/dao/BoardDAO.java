package dao;

import java.util.List;
import java.util.Map;

import dto.Board;
import dto.Page;

public interface BoardDAO {
	int insert(Board board);
	int update(Board board);
	int delete(int bnum);
	Board selectOne(int bnum);
	List<Board> selectList(Page page);
	void cntPlus(int bnum);
	int select_totcnt(Page page);
	void update_restepplus(Board board);
	List<Board> select_reply(int ref);
}
