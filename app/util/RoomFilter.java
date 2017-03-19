package util;

/**
 * Created by Murdock on 11/9/2016.
 */
public class RoomFilter
{
    private String location, ownerName;
    private double priceMax, sizeMin, sizeMax;

    public RoomFilter()
    {
        location = null;
        ownerName = null;
        priceMax = -1.0;
        sizeMin = 0.0;
        sizeMax = -1.0;
    }

    public RoomFilter location(String location)
    {
        if (location != null && location.length() < 1)
            location = null;

        this.location = location;
        return this;
    }

    public RoomFilter ownerName(String ownerName)
    {
        if (ownerName != null && ownerName.length() < 1)
            ownerName = null;

        this.ownerName = ownerName;
        return this;
    }

    public RoomFilter priceMax(double priceMax)
    {
        this.priceMax = priceMax;
        return this;
    }

    public RoomFilter sizeMin(double sizeMin)
    {
        this.sizeMin = sizeMin;
        return this;
    }

    public RoomFilter sizeMax(double sizeMax)
    {
        this.sizeMax = sizeMax;
        return this;
    }

    public String getLocation()
    {
        return location;
    }

    public String getOwnerName()
    {
        return ownerName;
    }

    public double getPriceMax()
    {
        return priceMax;
    }

    public double getSizeMin()
    {
        return sizeMin;
    }

    public double getSizeMax()
    {
        return sizeMax;
    }
}
