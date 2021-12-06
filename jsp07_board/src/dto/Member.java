package dto;

import java.util.Date;

public class Member {
	private String email;
	private String userpw;
	private String salt;
	private String zipcode;
	private String addr;
	private String addrdetail;
	private String fileName;
	private Date regidate;
	public Member() {
		super();
	}
	public Member(String email, String userpw, String salt, String zipcode, String addr, String addrdetail,
			String fileName, Date regidate) {
		super();
		this.email = email;
		this.userpw = userpw;
		this.salt = salt;
		this.zipcode = zipcode;
		this.addr = addr;
		this.addrdetail = addrdetail;
		this.fileName = fileName;
		this.regidate = regidate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getAddrdetail() {
		return addrdetail;
	}
	public void setAddrdetail(String addrdetail) {
		this.addrdetail = addrdetail;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Date getRegidate() {
		return regidate;
	}
	public void setRegidate(Date regidate) {
		this.regidate = regidate;
	}
	@Override
	public String toString() {
		return "Member [email=" + email + ", userpw=" + userpw + ", salt=" + salt + ", zipcode=" + zipcode + ", addr="
				+ addr + ", addrdetail=" + addrdetail + ", fileName=" + fileName + ", regidate=" + regidate + "]";
	}
	
}