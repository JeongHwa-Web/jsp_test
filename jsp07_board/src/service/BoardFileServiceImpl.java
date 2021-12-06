package service;

import java.util.List;

import dao.BoardFileDAO;
import dao.BoardFileDAOImpl;
import dto.BoardFile;

public class BoardFileServiceImpl implements BoardFileService{
	private BoardFileDAO bfdao = new BoardFileDAOImpl();
	@Override
	public String insert(BoardFile boardFile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(BoardFile boardFile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(int fnum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardFile selectOne(int fnum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardFile> selectList(int bnum) {
		// TODO Auto-generated method stub
		return null;
	}

}
