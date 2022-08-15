package tw.com.ispan.cma;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class DatabaseSettings {
	@Test
	void contextLoads() {
	}
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Test
	@Transactional
	public void testSessionFactory() {
		Session session = sessionFactory.openSession();
		List result = session.createNativeQuery("select * from dept").list();
		if(result!=null && !result.isEmpty()) {
			for(Object obj : result) {
				Object[] array = (Object[]) obj;
				System.out.println(array[0]+","+array[1]);				
			}
		}
		session.close();
		System.out.println("==========");
	}
	
	@Autowired
	private DataSource dataSource;
	
	@Test
	public void testDataSource() throws SQLException {
		Connection conn = dataSource.getConnection();
		PreparedStatement stmt = conn.prepareStatement("select * from emp");
		ResultSet rset = stmt.executeQuery();
		while(rset.next()) {
			String col1 = rset.getString(1);
			String col2 = rset.getString(2);
			System.out.println(col1+":"+col2);
		}
		rset.close();
		stmt.close();
		conn.close();
		System.out.println("==========");
	}	
}
