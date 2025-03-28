package util.mybatis;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil {

	private static SqlSessionFactory sqlSessionFactory;
	
	//첫 실행시에만 동작한다.
	static {
		//설정파일을 읽기 위해 InputStream 객체 생성
		//InputStream 객체는 사용 후 닫아야 함. 
	    try (InputStream inputStream = Resources.getResourceAsStream("mybatis/config/mybatis-config.xml")) {
	        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	       
	        System.out.println("잘 실행됨: " + sqlSessionFactory);
	    
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("펑");
	    }
	}

	
	//getter 메서드 : SqlSessionFactory 객체를 외부에서 사용 가능
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

}
