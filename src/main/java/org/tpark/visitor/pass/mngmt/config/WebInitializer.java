package org.tpark.visitor.pass.mngmt.config; 

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
  
public class WebInitializer implements WebApplicationInitializer  {

	public void onStartup(ServletContext servletContext) throws ServletException {  
          
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        
        ctx.register(Config.class);  
        ctx.setServletContext(servletContext);    
        
        Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));  
        servlet.addMapping("/api/*");  
        servlet.setLoadOnStartup(1);  
          
    }

	 

	/*@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			onStartup(arg0.getServletContext());
		} catch (ServletException e) {
			e.printStackTrace();
		}
		
	}  */

}
