package model;

import java.util.ArrayList;

/**
 * Created by Murdock on 10/9/2016.
 */
public class Owner extends User
{
    ArrayList<Apartment> ownedApartments;

    public Owner(String username, String password)
    {
        super(username, password);

        ownedApartments = new ArrayList<>();
    }

    public ArrayList<Apartment> getOwnedApartments()
    {
        return ownedApartments;
    }

    @Override
    public String toString()
    {
        return super.toString();
    }
}
