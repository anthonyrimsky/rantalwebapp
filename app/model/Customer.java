package model;

import java.util.ArrayList;

/**
 * Created by Murdock on 10/9/2016.
 */
public class Customer extends User
{
    ArrayList<Apartment> rentedApartments;

    public Customer(String username, String password)
    {
        super(username, password);

        rentedApartments = new ArrayList<>();
    }

    public ArrayList<Apartment> getRentedApartments()
    {
        return rentedApartments;
    }

    @Override
    public String toString()
    {
        return super.toString();
    }
}
