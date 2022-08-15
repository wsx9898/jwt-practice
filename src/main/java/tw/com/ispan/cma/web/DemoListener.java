package tw.com.ispan.cma.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DemoListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.err.println("DemoListener.contextInitialized()");
	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.err.println("DemoListener.contextDestroyed()");
	}
}
