package tw.com.ispan.cma;

import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;

@SpringBootTest
public class MessageSourceTests {
	@Test
	void contextLoads() {
	}
	
	@Value("${password.length}")
	private Integer passwordLength;
	
	@Autowired
	private Environment env;
	
	@Test
	public void testCustomSettings() {
		System.out.println("passwordLength="+passwordLength);
		
		Integer envPasswordLength = env.getProperty("password.length", Integer.class);
		System.out.println("envPasswordLength="+envPasswordLength);
	}
	
	@Autowired
	private MessageSource messageSource;
	
	@Test
	public void testI18n() {
		String test1 = messageSource.getMessage("login.failed", null, Locale.TAIWAN);
		System.out.println("test1="+test1);
		
		String test2 = messageSource.getMessage(
				"product.id.required", new String[] {"hehehe"}, Locale.US);
		System.out.println("test2="+test2);
	}
}
