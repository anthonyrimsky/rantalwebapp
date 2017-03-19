package servlet;

import model.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Murdock on 11/9/2016.
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet
{
    private Model model;

    @Override
    public void init() throws ServletException
    {
        model = (Model) getServletContext().getAttribute("model");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // Invalidate user session
        HttpSession session = request.getSession();
        session.invalidate();

        // And go back to homepage
        String redirectURL = "/index.html";
        RequestDispatcher dispatcher = request.getRequestDispatcher(redirectURL);
        dispatcher.forward(request, response);
    }
}
