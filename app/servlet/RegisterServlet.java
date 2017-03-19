package servlet;

import model.Customer;
import model.Model;
import model.Owner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet
{
    private Model model;

    @Override
    public void init() throws ServletException
    {
        model = (Model) getServletContext().getAttribute("model");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String type = request.getParameter("type");

        // The response
        PrintWriter out = response.getWriter();
        boolean isValid = true;
        response.setContentType("text/html");

        // Input validation
        if (username == null || username.length() < 1)
        {
            out.print("Invalid username<br />");
            isValid = false;
        }

        if (password == null || password.length() < 1)
        {
            out.print("Invalid password<br />");
            isValid = false;
        }

        if (type == null || type.length() < 1)
        {
            out.print("Invalid user type<br />");
            isValid = false;
        }

        if (!isValid)
        {
            out.print("<a href=\"registreer.html\">Go back and try again</a>");
            return;
        }

        if (type.equals("o"))
        {
            Owner user = new Owner(username, password);
            model.addUser(user);
        }
        else
        {
            Customer user = new Customer(username, password);
            model.addUser(user);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }
}
