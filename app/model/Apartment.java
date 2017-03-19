package model;

import java.util.Locale;

/**
 * Created by yketd on 1-9-2016.
 */
public class Apartment
{
    String ownerName;
    double size, price;
    String location;

    public Apartment(String ownerName, double size, double price, String location)
    {
        this.ownerName = ownerName;
        this.size = size;
        this.price = price;
        this.location = location;
    }

    public String getOwnerName()
    {
        return ownerName;
    }

    public void setOwnerName(String ownerName)
    {
        this.ownerName = ownerName;
    }

    public double getSize()
    {
        return size;
    }

    public void setSize(double size)
    {
        this.size = size;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    @Override
    public String toString()
    {
        return String.format(Locale.ENGLISH, "%s (%s)", location, ownerName);
    }
}
