package servlet;

import model.Model;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@WebServlet("/listUsers")
public class ShowPersonsServlet extends HttpServlet
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
        // Check if user is logged in
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username == null)
        {
            response.sendRedirect("/index.html");
            return;
        }

        // Store how many times, and when the user last viewed the page in cookie
        Cookie[] cookies = request.getCookies();
        int cookiePageVisits = 0;
        String lastVisited;
        long oldTime = 0;
        long newTime = Instant.now().getEpochSecond();

        try {
            for (Cookie cookie : cookies)
            {
                if (cookie.getName().equals("usersPageCount"))
                    cookiePageVisits = Integer.parseInt(cookie.getValue());
                else if (cookie.getName().equals("usersPageTime"))
                    oldTime = Long.parseLong(cookie.getValue());
            }
        } catch(Exception ex) {
            cookiePageVisits = 0;
            oldTime = 0;
        }

        // Increase the page visits by 1
        response.addCookie(new Cookie("usersPageCount", String.format("%d", ++cookiePageVisits)));
        // Store the new time
        response.addCookie(new Cookie("usersPageTime", String.format("%d", newTime)));

        // Read date from timestamp
        if (oldTime < 1)
            lastVisited = "Never";
        else
        {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            lastVisited = sdf.format(new Date(oldTime * 1000));
        }

        // The response
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        // Show what we got
        out.print("<a href=\"login\">Back to home</a> // <a href=\"logout\">Logout</a><br />");
        out.print("You have visited this page " + cookiePageVisits + " times. " +
                "This page was last visited " + lastVisited + "<br /><br />");
        out.print(model.usersToHTML(model.getUsers()));
    }
}
