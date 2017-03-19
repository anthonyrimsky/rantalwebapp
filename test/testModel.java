import model.Apartment;
import model.Model;
import model.Owner;
import model.User;
import org.junit.Test;
import util.RoomFilter;

import java.util.ArrayList;

import static org.junit.Assert.*;
/**
 * Created by abe23 on 19/03/17.
 */
public class testModel {

    @Test
    public final void testOwnerUser() {
        Model model = new Model();
        User user =  model.getUser("testowner");
        assertNotNull(user);

    }

    @Test
    public final void testAddUser() {
        Model model = new Model();
        model.addUser(new User("owner2", "testing"));
        User user = model.getUser("owner2");
        assertNotNull(user);
    }


    @Test
    public final void testAppartments() {
        Model model = new Model();
        RoomFilter filter = new RoomFilter();
        filter.ownerName("testowner");
        ArrayList<Apartment> appartments =  model.getApartments(filter);
        System.out.println(appartments.size());
        boolean test = appartments.size() == 3; //should be 3
        //test = appartments.size() == 2; //the test will fail now
        assertTrue(test);
    }


}
