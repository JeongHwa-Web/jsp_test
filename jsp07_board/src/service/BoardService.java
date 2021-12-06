package service;

import java.util.List;
import java.util.Map;

import dto.Board;
import dto.Page;

public interface BoardService {
	String insert(Board board, List<String>fileNames);
	String update(Board board, List<String>fileNames, String[] filedels);
	String delete(int bnum);
	Map<String, Object> selectOne(int bnum);
	List<Board>selectList(Page page);
	void cntPlus(int bnum);
	
}
