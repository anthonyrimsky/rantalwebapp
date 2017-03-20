package servlet;

import model.Apartment;
import model.Customer;
import model.Model;
import util.RoomFilter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/showRooms")
public class ShowRoomsServlet extends HttpServlet
{
    private Model model;

    @Override
    public void init() throws ServletException
    {
        model = (Model) getServletContext().getAttribute("model");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response); // Login sends a POST request, handle it as GET
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // Check if user is logged in and is an Owner
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username == null)
        {
            response.sendRedirect("/index.html");
            return;
        }
        else if (model.getUser(username) instanceof Customer)
        {
            response.sendRedirect("/index.html");
            return;
        }

        // What do we need
        ArrayList<Apartment> apartments;
        RoomFilter roomFilter = new RoomFilter();

        // The response
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        // Set up our room filter
        roomFilter.ownerName(username);
        apartments = model.getApartments(roomFilter);

        // Show what we got
        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Homepage</title>\n" +
                "</head>\n" +
                "<body style=\"padding:40px\">");
        out.print("<a href=\"addroom.html\">Add a new apartment</a> // " +
                "<a href=\"listUsers\">Beheer</a> // " +
                "<a href=\"logout\">Logout 2</a><br /><br />");
        out.print(model.apartmentsToHTML(apartments));
        out.println("</body></html>");
    }
}
