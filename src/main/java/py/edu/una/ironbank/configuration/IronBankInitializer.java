package py.edu.una.ironbank.configuration;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
//import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import py.edu.una.ironbank.IronbankFeApplication;

public class IronBankInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(IronbankFeApplication.class);
	}

	public void onStartup(ServletContext servletContext) throws ServletException {

		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.setServletContext(servletContext);
		servletContext.setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE));
		SessionCookieConfig sessionCookieConfig = servletContext.getSessionCookieConfig();

		EnumSet<DispatcherType> securityDispatcher = EnumSet.of(DispatcherType.REQUEST, DispatcherType.ERROR);
		sessionCookieConfig.setHttpOnly(true);

		servletContext.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class)
				.addMappingForUrlPatterns(securityDispatcher, true, "/*");

		servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
		super.onStartup(servletContext);
	}

}
