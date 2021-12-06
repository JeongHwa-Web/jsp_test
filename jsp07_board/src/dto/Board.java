package dto;

import java.util.Date;

public class Board {
	private int bnum;
	private String email;
	private String subject;
	private String content;
	private int readcnt;
	private String ip;
	private Date regidate;
	private Date modidate;
	private int ref; //그룹번호
	private int restep;	//글순서
	private int relevel; //글레벨
	public Board() {
		super();
	}
	public Board(int bnum, String email, String subject, String content, int readcnt, String ip, Date regidate,
			Date modidate, int ref, int restep, int relevel) {
		super();
		this.bnum = bnum;
		this.email = email;
		this.subject = subject;
		this.content = content;
		this.readcnt = readcnt;
		this.ip = ip;
		this.regidate = regidate;
		this.modidate = modidate;
		this.ref = ref;
		this.restep = restep;
		this.relevel = relevel;
	}
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReadcnt() {
		return readcnt;
	}
	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getRegidate() {
		return regidate;
	}
	public void setRegidate(Date regidate) {
		this.regidate = regidate;
	}
	public Date getModidate() {
		return modidate;
	}
	public void setModidate(Date modidate) {
		this.modidate = modidate;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getRestep() {
		return restep;
	}
	public void setRestep(int restep) {
		this.restep = restep;
	}
	public int getRelevel() {
		return relevel;
	}
	public void setRelevel(int relevel) {
		this.relevel = relevel;
	}
	@Override
	public String toString() {
		return "Board [bnum=" + bnum + ", email=" + email + ", subject=" + subject + ", content=" + content
				+ ", readcnt=" + readcnt + ", ip=" + ip + ", regidate=" + regidate + ", modidate=" + modidate + ", ref="
				+ ref + ", restep=" + restep + ", relevel=" + relevel + "]";
	}
	
}
