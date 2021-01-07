package stock_handling;

import java.util.ArrayList;
import db_handling.GetDB;


public class ViewStock {

    public ViewStock() {

        GetDB info = new GetDB();

        ArrayList<Double> percentage = new ArrayList<>(); // to stock the percentages for each med

        int size = info.getCurrentStock().size();

        for (int i = 0; i < size; i++) {
            String current = info.getCurrentStock().get(i);
            String original = info.getFullStock().get(i);
            // converting the strings to numbers
            int number1 = Integer.parseInt(current);
            int number2 = Integer.parseInt(original);
            double percent = (number1/number2)*100;
            percentage.add(percent);
        }

        System.out.println(percentage);

    }
}
