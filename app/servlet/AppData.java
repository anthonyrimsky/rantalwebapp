package servlet;

import model.Model;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by yketd on 1-9-2016.
 */
@WebListener
public class AppData implements ServletContextListener
{
    private ServletContext ctx;

    @Override
    public void contextInitialized(ServletContextEvent event)
    {
        ctx = event.getServletContext();
        Model model = new Model();
        ctx.setAttribute("model", model);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event)
    {
    }
}
