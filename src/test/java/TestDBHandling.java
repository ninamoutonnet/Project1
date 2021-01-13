import com.google.gson.Gson;
import db_handling.GetDB_clients;
import db_handling.GetDB_medicine;
import db_handling.checkForMed;
import org.junit.Test;
import org.junit.Assert;
import static org.hamcrest.CoreMatchers.hasItems;

import java.util.ArrayList;
import java.util.Vector;

public class TestDBHandling {
    @Test
    public void TestGetDB_medicine(){ //testing if GetDB methods work
        GetDB_medicine testDB = new GetDB_medicine();
        Assert.assertThat(testDB.getBrand(),hasItems("Benadryl","Dettol","Nurofen","E45"));
        Assert.assertThat(testDB.getAmount(),hasItems("24 caps","7 tablets","100 sprays"));
        Assert.assertThat(testDB.getSPrice(),hasItems("18","6","12","6.4"));
        Assert.assertThat(testDB.getPPrice(),hasItems("7.1","2.3","4","3"));
        Assert.assertThat(testDB.getFullStock(),hasItems("20","50","30","25"));
        Assert.assertThat(testDB.getLimit(),hasItems("f","t"));
        Assert.assertThat(testDB.getDescription(),hasItems("Relief","tabs","Liquid","Antiseptic"));
        Assert.assertThat(testDB.getCategory(),hasItems("Allergy","First aid", "Cold and Flu","Digestion"));
        Assert.assertThat(testDB.getID(),hasItems("1","14","35","48"));
    }
    @Test
    public void TestGetDB_clients(){ //testing if GetDB clients work
        GetDB_clients testDBc = new GetDB_clients();
        Assert.assertThat(testDBc.getLastName(),hasItems("Moutonnet","Baron","Jolie","Beckham"));
        Assert.assertThat(testDBc.getFirstName(),hasItems("Nina","Louise","Napoleon","David"));
        Assert.assertThat(testDBc.getCardNumber(),hasItems("1234567891234567","8979000033334444","1387240913687829","9999999922229999"));
        Assert.assertThat(testDBc.getCCV(),hasItems("123","963","470","133"));
        Assert.assertThat(testDBc.getExpDate(),hasItems("23/05/25","23/10/21","05/06/22","09/12/22"));
        Assert.assertThat(testDBc.getPassword(),hasItems("nina13","dudu3298","PoTC","footBall199321"));
        Assert.assertThat(testDBc.getID(),hasItems("16","21","22","24"));
    }
    @Test
    public void TestCheckForMed(){ //testing if GetDB clients work
        checkForMed testCFM= new checkForMed();
        Vector<Integer> testid1;
        Vector<Integer> testid27;
        Vector<Integer> testwrongid;
        testid1=testCFM.isTheMedicineIn("Benadryl","24 caps","18","7.1","Relief","Allergy");
        testid27 = testCFM.isTheMedicineIn("E45","50 mL","12","16","Psoriasis cream", "Skincare");
        testwrongid = testCFM.isTheMedicineIn("Adeiz","0","0", "Hfzei","Kofjzi","Kdijiz");
        Assert.assertEquals(1, testid1.size());
        Assert.assertEquals(1, testid27.size());
        Assert.assertEquals(0, testwrongid.size());
    }


}
