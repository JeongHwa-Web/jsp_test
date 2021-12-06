package dao;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MBConn {
	public static SqlSession getSession() {
		SqlSession session=null;
		String resource = "mybatis/mybatisConfig.xml";
		try {
			//���̹�Ƽ��ȯ�� xml����
			InputStream is = Resources.getResourceAsStream(resource);
			//���丮 �����(������ ���� �� �ִ� ��ü)
			SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(is);
			//���ǻ���
			session = sf.openSession();
			System.out.println("session생성 성공");
		} catch (IOException e) {
			System.out.println("mybatisȯ������ �б� ����");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return session;
	}
}
