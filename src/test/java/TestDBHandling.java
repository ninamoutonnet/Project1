import com.google.gson.Gson;
import db_handling.GetDB_medicine;
import org.junit.Test;
import org.junit.Assert;
import static org.hamcrest.CoreMatchers.hasItems;

import java.util.ArrayList;

public class TestDBHandling {
    @Test
    public void TestGetDB_medicine(){ //testing if GetDB methods work
        GetDB_medicine testDB = new GetDB_medicine();
        Assert.assertThat(testDB.getBrand(),hasItems("Benadryl","Piriteze","Beconase","Dettol"));
        Assert.assertThat(testDB.getAmount(),hasItems("24 caps","7 tablets","100 sprays"));
        Assert.assertThat(testDB.getSPrice(),hasItems("18","6","12","6.4"));
        Assert.assertThat(testDB.getPPrice(),hasItems("7.1","2.3","4","3"));
        Assert.assertThat(testDB.getFullStock(),hasItems("20","50","30","25"));
        Assert.assertThat(testDB.getLimit(),hasItems("f","t"));
        Assert.assertThat(testDB.getDescription(),hasItems("Relief","tabs","Liquid","Antiseptic"));
        Assert.assertThat(testDB.getCategory(),hasItems("Allergy","First aid", "Cold and Flu","Digestion"));
        Assert.assertThat(testDB.getID(),hasItems("1","14","35","48"));
    }
}
