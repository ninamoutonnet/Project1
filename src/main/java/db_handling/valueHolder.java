package db_handling;

public class valueHolder {
    //constructor
    private String strVH;

    public valueHolder(){
    }

    public String getStrVH(){
        return strVH;
    }

    public void setStr(String s){
        strVH = s;
        System.out.println("the string set is: "+ s);
    }

}
