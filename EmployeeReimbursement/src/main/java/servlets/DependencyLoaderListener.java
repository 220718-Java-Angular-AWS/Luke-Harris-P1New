package servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DependencyLoaderListener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Servlet context init()");
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        System.out.println("Servlet context destroy()");
    }

}
