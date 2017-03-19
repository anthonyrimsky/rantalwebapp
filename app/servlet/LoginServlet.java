package servlet;

import model.Model;
import model.Owner;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by yketd on 1-9-2016.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet
{
    private Model model;

    @Override
    public void init() throws ServletException
    {
        model = (Model) getServletContext().getAttribute("model");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String redirectURL = "/fouteinlog.html";
        User user;
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Find a user matching the given username and password
        if ((user = model.getUser(username, password)) != null)
        {
            request.getSession().setAttribute("username", username);

            if (user instanceof Owner)
                redirectURL = "/showRooms";
            else
                redirectURL = "/WEB-INF/huurder.html";
        }

        //response.sendRedirect(redirectURL);
        RequestDispatcher dispatcher = request.getRequestDispatcher(redirectURL);
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // Redirect to user associated page if already logged in
        HttpSession session = request.getSession();
        String redirectURL;

        if (session.getAttribute("username") != null)
        {
            User user = model.getUser((String) session.getAttribute("username"));

            if (user instanceof Owner)
                redirectURL = "/showRooms";
            else
                redirectURL = "/WEB-INF/huurder.html";
        }
        else
        {
            redirectURL = "/login.html";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(redirectURL);
        dispatcher.forward(request, response);
    }
}
