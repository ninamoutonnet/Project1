package db_handling;

import Requests.Get;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

// code to get the information from the database in the constructor
// store the info into arrays
// make functions that return the arrays of info when called in other classes

public class GetDB_medicine {
    //ArrayList of ArrayList that stores all info from the DB
    ArrayList<ArrayList> AllProducts = new ArrayList<ArrayList>();
    String jsonS = "";

    // Arrays into which the info will be moved to be used in the other classes:
    ArrayList<String> Brand = new ArrayList<>();
    ArrayList<String> Amount = new ArrayList<>();
    ArrayList<String> SPrice = new ArrayList<>();
    ArrayList<String> PPrice = new ArrayList<>();
    ArrayList<String> FullStock = new ArrayList<>();
    ArrayList<String> Limit = new ArrayList<>();
    ArrayList<String> Description = new ArrayList<>();
    ArrayList<String> Category = new ArrayList<>();
    ArrayList<String> ID = new ArrayList<>();
    ArrayList<String> CurrentStock = new ArrayList<>();

    public GetDB_medicine(int nb) {
        /*
        if (branchnb == 1) {
            try {
                URL myURL = new URL("https://projectservlet.herokuapp.com/access");
                // https://projectservlet.herokuapp.com/access?item=products&nb=1
                Get getRequest = new Get();
                AllProducts = getRequest.Response(myURL);
            } catch (Exception e) {
                System.out.println("Something went wrong");
            }
        }
        */
        try {
            if (nb == 1) {
                URL myURL = new URL("https://projectservlet.herokuapp.com/access?item=products");
                Get getRequest = new Get();
                AllProducts = getRequest.Response(myURL);
            }
            else if (nb == 2) {
                URL myURL = new URL("https://projectservlet.herokuapp.com/access?item=products2");
                Get getRequest = new Get();
                AllProducts = getRequest.Response(myURL);
            }
            else if (nb == 3) {
                URL myURL = new URL("https://projectservlet.herokuapp.com/access?item=products3");
                Get getRequest = new Get();
                AllProducts = getRequest.Response(myURL);
            }
        }
        catch (Exception e) {
                System.out.println("Something went wrong");
        }

       // Arrays that will store the info by type

        for(int i = 0; i<AllProducts.size(); i++){
            ArrayList<String> product = new ArrayList<String>();
            product = AllProducts.get(i); // getting all the info for 1 product
            // ordering from DB: brand, amount, salesprice, purchaseprice
            // fullstock, limit, decription, category, id, current stock
            Brand.add(product.get(0));
            Amount.add(product.get(1));
            SPrice.add(product.get(2));
            PPrice.add(product.get(3));
            FullStock.add(product.get(4));
            Limit.add(product.get(5));
            Description.add(product.get(6));
            Category.add(product.get(7));
            ID.add(product.get(8));
            CurrentStock.add(product.get(9));

            //System.out.println(product);
        }

    }

    public ArrayList<String> getBrand() { return Brand; }

    public ArrayList<String> getAmount() { return Amount; }

    public ArrayList<String> getSPrice() { return SPrice; }

    public ArrayList<String> getPPrice() { return PPrice; }

    public ArrayList<String> getFullStock() { return FullStock; }

    public ArrayList<String> getLimit() { return Limit; }

    public ArrayList<String> getDescription() { return Description; }

    public ArrayList<String> getCategory() { return Category; }

    public ArrayList<String> getID() { return ID; }

    public ArrayList<String> getCurrentStock(){ return CurrentStock; }
}
