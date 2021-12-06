package service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.MemberDAO;
import dao.MemberDAOImpl;
import dto.Member;

public class MemberServiceImpl implements MemberService{
	private MemberDAO mdao = new MemberDAOImpl();
	
	//salt를 랜덤하게 만들기
	public String saltMake() {
		String salt = null;
		try {
			//난수를 생성해줌
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
			byte[] bytes = new byte[16]; //빈 배열
			sr.nextBytes(bytes); //랜덤한 값을 byte에 만듦
			//byte데이터를 String형으로 변환
			salt = new String(Base64.getEncoder().encode(bytes));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return salt;
	}
	
	//평문을 암호문으로 변경
	public String sha256(String userpw,String salt){
		StringBuffer sb = new StringBuffer();
		try {
			//SHA-256 : 단방향 암호기법, 복호화불가능
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(userpw.getBytes()); //문자열을 바이트 배열로 변경해서 전달
			md.update(salt.getBytes()); //솔트를 추가
			byte[] data = md.digest(); //암호화된 바이트 배열(32byte)
			System.out.println(Arrays.toString(data));
			//16진수 문자열로 변경 sb변수에 추가
			for(byte b: data) {
				sb.append(String.format("%02x", b)) ;
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
	@Override
	public String insert(Member member) {
		//비밀번호를 암호화해서 저장
		String salt = saltMake();
		String securePw = sha256(member.getUserpw(),salt);
		member.setUserpw(securePw);
		member.setSalt(salt);
		int cnt = mdao.insert(member);
		if(cnt>0) {
			return "추가 성공";
		}else {
			return "추가 실패";
		}
	}

	@Override
	public String update(Member member, String newpw) {
		Member dbMember = mdao.selectOne(member.getEmail());
		String dbpw = dbMember.getUserpw();
		String dbsalt = dbMember.getSalt();
		String securepw = sha256(member.getUserpw(),dbsalt);
		String msg;
		if(dbpw.equals(securepw)) {
			if(!newpw.equals("")) {
				String salt = saltMake();
				securepw = sha256(newpw,salt);
				member.setSalt(salt);
				member.setUserpw(securepw);
			}else {
				member.setSalt(dbsalt);
				member.setUserpw(dbpw);
			}
			int cnt = mdao.update(member);
			if(cnt>0) {
				msg = "수정 성공";
			}else {
				msg = "수정 실패";
			}
		}else {
			msg = "일치하지 않습니다";
		}
		return msg;
	}

	@Override
	public String delete(String email) {
		int cnt = mdao.delete(email);
		if(cnt>0) {
			return "삭제 성공";
		}else {
			return "삭제 실패";
		}
	}

	@Override
	public Member selectOne(String email) {
		return mdao.selectOne(email);
	}

	@Override
	public List<Member> selectList(String findkey,String findvalue) {
		Map<String,String>map = new HashMap<>();
		map.put("findkey", findkey);
		map.put("findvalue", findvalue);
		return mdao.selectList(map);
	}

	@Override
	public Map<String,String> login(String email, String userpw) {
		Map<String,String> map = new HashMap<>();
		String msg = null;
		String rcode = null;
		Member member = mdao.selectOne(email);
		if(member==null) {
			msg = "존재하지 않는 이메일입니다.";
			rcode = "1";
		}else {		
			String dbpw = member.getUserpw();
			String salt = member.getSalt();
			String securepw = sha256(userpw,salt);
			if(dbpw.equals(securepw)) {
				msg = "로그인되었습니다.";
				rcode = "0";
			}else {
				msg = "비밀번호가 일치하지 않습니다.";
				rcode = "2";
			}
		}
		map.put("msg", msg);
		map.put("rcode", rcode);
		return map;
	}

}
