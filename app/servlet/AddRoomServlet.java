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

@WebServlet("/addRoom")
public class AddRoomServlet extends HttpServlet
{
    private Model model;

    @Override
    public void init() throws ServletException
    {
        model = (Model) getServletContext().getAttribute("model");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
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
        Apartment apartment;
        String location;
        double price = 0.0, size = 0.0;
        boolean isValid = true;

        // The response
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        // Attempt to extract query string parameters
        try {
            price = Double.parseDouble(request.getParameter("price"));
        } catch (Exception nfe){
            isValid = false;
        }

        try {
            size = Double.parseDouble(request.getParameter("roomsize"));
        } catch (Exception nfe){
            isValid = false;
        }

        location = request.getParameter("location");
        if (location == null || location.length() < 1)
            isValid = false;

        if (!isValid)
        {
            out.print("Failed to add new apartment.<br /><a href=\"showRooms\">Please try again</a>");
            return;
        }

        // Set up our room filter
        apartment = new Apartment(username, size, price, location);
        model.getApartments().add(apartment);

        // Show what we got
        out.print("A new apartment has successfully been added.<br /><a href=\"showRooms\">Back to overview</a>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.sendRedirect("/index.html");
    }
}
