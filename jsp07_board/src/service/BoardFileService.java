package service;

import java.util.List;

import dto.BoardFile;

public interface BoardFileService {
	String insert(BoardFile boardFile);
	String update(BoardFile boardFile);
	String delete(int fnum);
	BoardFile selectOne(int fnum);
	List<BoardFile> selectList(int bnum);
}
