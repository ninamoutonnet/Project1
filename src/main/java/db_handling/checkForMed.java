package db_handling;

import java.util.ArrayList;
import java.util.Vector;

/* This class checks if a medicine is already in the list of products sold at one particular branch */

public class checkForMed {

    public  checkForMed(){
    }

    public Vector<Integer> isTheMedicineIn(String selectedName, String selectedAmount, String selectedSalesP, String selectedPurchP, String selectedDescription, String selectedCategory, Integer branch){
        Vector<Integer> id = new Vector<>();

        GetDB_medicine info = new GetDB_medicine(branch);//get info from DB
        ArrayList<String> str1 = info.getBrand(); //GET FROM DB
        ArrayList<String> str2 = info.getAmount(); //GET FROM DB
        ArrayList<String> str3 = info.getSPrice(); //GET FROM DB
        ArrayList<String> str4 = info.getPPrice(); //GET FROM DB
        ArrayList<String> str5 = info.getDescription(); //GET FROM DB
        ArrayList<String> str6 = info.getCategory(); //GET FROM DB

        boolean itIsNotIn = false; //assume that it is in the db

        //get indexes of name

        for(int i=0; i<str1.size(); i++){
            if( selectedName.equalsIgnoreCase(str1.get(i)) ) { //use the .equals operator as it compares the content of the string and not if they are the same object.
                id.add(i+1); //add one because index starts at 0 and id at 1
                //at this point id contains the id of all the medicines in the db with that same name as the one given in the input of the function
            }
        }

        //verify those indexes match between brand name and amount
        Vector<Integer> temp = new Vector<>();
        for(int i=0; i<id.size();i++){
            int index = id.get(i);
            if(selectedAmount.equalsIgnoreCase(str2.get(index-1))) {
                //refine the index vector with only the medicine that we care about
                temp.add(index);
            }
        }
        id.clear();
        for(int i=0; i<temp.size();i++){
            id.add(temp.get(i)); //update the id vector
        }
        temp.clear();
        // at this point the vector id only contains the id of the medicines in the db that have both the same name AND amount per box

        //verify brand and sprice match
        for(int i=0; i<id.size();i++){
            int index = id.get(i);
            if(selectedSalesP.equalsIgnoreCase(str3.get(index-1))) {
                //refine the index vector with only the medicine that we care about
                temp.add(index);
            }
        }
        id.clear();
        for(int i=0; i<temp.size();i++){
            id.add(temp.get(i)); //update the id vector
        }
        temp.clear();

        //verify brand and purchprice match
        for(int i=0; i<id.size();i++){
            int index = id.get(i);
            if(selectedPurchP.equalsIgnoreCase(str4.get(index-1))) {
                //refine the index vector with only the medicine that we care about
                temp.add(index);
            }
        }
        id.clear();
        for(int i=0; i<temp.size();i++){
            id.add(temp.get(i)); //update the id vector
        }
        temp.clear();

        //verify brand and description match
        for(int i=0; i<id.size();i++){
            int index = id.get(i);
            if(selectedDescription.equalsIgnoreCase(str5.get(index-1))) {
                //refine the index vector with only the medicine that we care about
                temp.add(index);
            }
        }
        id.clear();
        for(int i=0; i<temp.size();i++){
            id.add(temp.get(i)); //update the id vector
        }
        temp.clear();


        //verify brand and category match
        for(int i=0; i<id.size();i++){
            int index = id.get(i);
            if(selectedCategory.equalsIgnoreCase(str6.get(index-1))) {
                //refine the index vector with only the medicine that we care about
                temp.add(index);
            }
        }
        id.clear();
        for(int i=0; i<temp.size();i++){
            id.add(temp.get(i)); //update the id vector
        }
        temp.clear();

        return id;
    }


}
