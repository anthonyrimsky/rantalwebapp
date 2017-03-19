package servlet;

import model.Apartment;
import model.Model;
import model.Owner;
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

@WebServlet("/searchRoom")
public class SearchRoomServlet extends HttpServlet
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
        // Check if user is logged in and is a Customer
        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null)
        {
            response.sendRedirect("index.html");
            return;
        }
        else if (model.getUser((String) session.getAttribute("username")) instanceof Owner)
        {
            response.sendRedirect("index.html");
            return;
        }

        // What do we need
        ArrayList<Apartment> apartments;
        RoomFilter roomFilter = new RoomFilter();

        String location;
        double priceMax = 0, sizeMin = 0, sizeMax = 0;
        boolean isValid = true;
        String reasonForFailure = "";

        // The response
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        // Attempt to extract query string parameters
        try {
            if (request.getParameter("maxprice").length() > 0){
                priceMax = Double.parseDouble(request.getParameter("maxprice"));
            }   else{
                priceMax = -1.0;
            }
        } catch (Exception n) {
            reasonForFailure = "pricemax \n";
            n.printStackTrace();
            isValid = false;
        }

        try {
            if (request.getParameter("roomsizeMin").length() > 0){
                sizeMin = Double.parseDouble(request.getParameter("roomsizeMin"));
            }   else{
                sizeMin = -1.0;
            }
        } catch (Exception n) {
            reasonForFailure = "roomsizemin \n";
            isValid = false;
        }

        try {
            if (request.getParameter("roomsizeMax").length() > 0){
                sizeMax = Double.parseDouble(request.getParameter("roomsizeMax"));
            }   else{
                sizeMax = -1.0;
            }
        } catch (Exception n) {
            reasonForFailure = "roomsizemax \n";
            isValid = false;
        }

        location = request.getParameter("location");

        if (!isValid)
        {
            out.print(reasonForFailure);
            out.print("your query is not valid .<br /><a href=\"login\">Please try again</a>");
            return;
        }
        else
        {
            // Set up our room filter
            roomFilter.location(location).priceMax(priceMax).sizeMax(sizeMax).sizeMin(sizeMin);
            apartments = model.getApartments(roomFilter);

            // Show what we got
            out.print("<a href=\"login\">Back to search</a> // <a href=\"logout\">Logout</a><br /><br />");
            out.print(model.apartmentsToHTML(apartments));
        }
    }
}
