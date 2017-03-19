package model;

import util.RoomFilter;

import java.util.ArrayList;

/**
 * Created by Murdock on 11/9/2016.
 */
public class Model
{
    private ArrayList<User> users;
    private ArrayList<Apartment> apartments;

    public Model()
    {
        users = new ArrayList<>();
        apartments = new ArrayList<>();

        // Pre-defined users
        users.add(new Customer("testuser", "testing"));
        users.add(new Owner("testowner", "testing"));

        // Pre-defined apartments
        apartments.add(new Apartment("testowner", 2, 3000, "Enschede"));
        apartments.add(new Apartment("testowner", 4, 2000, "Enschede"));
        apartments.add(new Apartment("testowner", 6, 8000, "Hengelo"));
    }

    /**
     * Returns the list of all registered users
     * @return
     */
    public ArrayList<User> getUsers()
    {
        return users;
    }

    /**
     * Returns the list of all registered apartments
     * @return
     */
    public ArrayList<Apartment> getApartments() {
        return apartments;
    }

    /**
     * Returns the list of all registered apartments matching a room filter
     * @param filter
     * @return
     */
    public ArrayList<Apartment> getApartments(RoomFilter filter)
    {
        ArrayList<Apartment> result = new ArrayList<>();

        for(Apartment a : apartments)
        {
            if (    (filter.getLocation() == null || a.getLocation().equalsIgnoreCase(filter.getLocation())) &&
                    (filter.getOwnerName() == null || a.getOwnerName().equalsIgnoreCase(filter.getOwnerName())) &&
                    (filter.getPriceMax() < 0.0 || a.getPrice() <= filter.getPriceMax()) &&
                    (filter.getSizeMax() < 0.0 || a.getSize() <= filter.getSizeMax()) &&
                    a.getSize() >= filter.getSizeMin())
            {
                result.add(a);
            }
        }

        return result;
    }

    /**
     * Converts an array with apartments into a printable HTML table
     * @param apartments
     * @return
     */
    public String apartmentsToHTML(ArrayList<Apartment> apartments)
    {
        StringBuilder output = new StringBuilder();
        output  .append("<table><tr>")
                .append("<th>Apartment</th>")
                .append("<th>Size</th>")
                .append("<th>Price</th>")
                .append("</tr>");

        for(Apartment a : apartments)
        {
            output  .append("<tr>")
                    .append("<td>" + a + "</td>")
                    .append("<td>" + a.getSize() + "</td>")
                    .append("<td>" + a.getPrice() + "</td>")
                    .append("</tr>");
        }

        output.append("</table>");
        return output.toString();
    }

    /**
     * Converts an array with users into a printable HTML table
     * @param users
     * @return
     */
    public String usersToHTML(ArrayList<User> users)
    {
        StringBuilder output = new StringBuilder();
        output  .append("<table><tr>")
                .append("<th>Username</th>")
                .append("<th>Password</th>")
                .append("<th>Type</th>")
                .append("</tr>");

        for(User u : users)
        {
            output  .append("<tr>")
                    .append("<td>" + u+ "</td>")
                    .append("<td>(Bet you would like to see this)</td>")
                    .append("<td>" + (u instanceof Customer ? "Customer" : "Owner") + "</td>")
                    .append("</tr>");
        }

        output.append("</table>");
        return output.toString();
    }

    /**
     * Get a user instance by username
     * Returns null if no match
     * @param username
     * @return User
     */
    public User getUser(String username)
    {
        for(User user : users)
            if (user.getUsername().equals(username))
                return user;

        return null;
    }

    /**
     * Get a user instance by username and password combination
     * Returns null if no match
     * @param username
     * @param password
     * @return User
     */
    public User getUser(String username, String password)
    {
        for(User user : users)
            if (user.getUsername().equals(username) && user.getPassword().equals(password))
                return user;

        return null;
    }

    /**
     * Adds a new user instance to the list of registered users
     * @param newUser
     */
    public void addUser(User newUser)
    {
        if (getUser(newUser.getUsername())!= null)
            users.remove(getUser(newUser.getUsername()));

        users.add(newUser);
    }
}
