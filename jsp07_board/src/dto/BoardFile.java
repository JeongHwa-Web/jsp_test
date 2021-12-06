package dto;

public class BoardFile {
	private int fnum;
	private int bnum;
	private String fileName;
	public BoardFile() {
		super();
	}
	public BoardFile(int fnum, int bnum, String fileName) {
		super();
		this.fnum = fnum;
		this.bnum = bnum;
		this.fileName = fileName;
	}
	public int getFnum() {
		return fnum;
	}
	public void setFnum(int fnum) {
		this.fnum = fnum;
	}
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Override
	public String toString() {
		return "BoardFile [fnum=" + fnum + ", bnum=" + bnum + ", fileName=" + fileName + "]";
	}
	
}
