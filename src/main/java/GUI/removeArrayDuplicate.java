package GUI;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

/*this class does the following*/
//takes an arraylist as input and returns the same arraylist without any duplicate values
//this works using the hash set properties.
//by converting the array to a hash set and then re-converting the hash set to an array list you get rid of duplicate values


public class removeArrayDuplicate {

    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) {

        // Create a new LinkedHashSet
        Set<T> set = new LinkedHashSet<>();
        set.addAll(list);
        list.clear();
        list.addAll(set);
        // return the list
        return list;
    }
}
