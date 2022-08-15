package tw.com.ispan.cma;

import java.util.Locale;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
@ServletComponentScan
@EnableTransactionManagement
public class SpringMvcJavaConfig implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("langctry");
		registry.addInterceptor(interceptor);
	}
	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setCookieName("langCookie");
		resolver.setCookieMaxAge(864000);
		resolver.setDefaultLocale(new Locale("es", "ES"));
		return resolver;
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource rbms = new ResourceBundleMessageSource();
		rbms.setBasename("error.Messages");
		rbms.setDefaultEncoding("UTF-8");
		rbms.setFallbackToSystemLocale(false);
		return rbms;
	}

//這長串是jspViewResolver

	@Bean
	public ViewResolver jspViewResolver() {
//		System.out.println("有成功呼叫到jspViewResolver");
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setContentType("text/html");

		// Make sure > Thymeleaf order & FreeMarker order.
		viewResolver.setOrder(1000);

//		System.out.println("viewResolver = " + viewResolver);

		return viewResolver;
	}
//這長串是jspViewResolver



//這長串是thymeleafViewResolver
	@Bean
	public ViewResolver thymeleafViewResolver() {

		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();

		viewResolver.setTemplateEngine(thymeleafTemplateEngine());
		viewResolver.setCharacterEncoding("UTF-8");
		viewResolver.setOrder(0);

		// Important!!
		// th_page1.html, th_page2.html, ...
		viewResolver.setViewNames(new String[] {"th_*"});

		return viewResolver;
	}

	// Thymeleaf template engine with Spring integration
	@Bean
	public SpringTemplateEngine thymeleafTemplateEngine() {

		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(thymeleafTemplateResolver());
		templateEngine.setEnableSpringELCompiler(true);

		return templateEngine;
	}

	@Bean
	public SpringResourceTemplateResolver springResourceTemplateResolver() {
		return new SpringResourceTemplateResolver();
	}

	// Thymeleaf template resolver serving HTML 5
	@Bean
	public ITemplateResolver thymeleafTemplateResolver() {

		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

		templateResolver.setPrefix("templates/");
		templateResolver.setCacheable(false);
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCharacterEncoding("UTF-8");

		return templateResolver;
	}

//這長串是thymeleafViewResolver


}
