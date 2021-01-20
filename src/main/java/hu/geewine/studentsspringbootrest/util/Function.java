package hu.geewine.studentsspringbootrest.util;

public class Function {

    public static String first_name(String text) {
        int ind = text.lastIndexOf(" ");
        return text.substring(0, ind);
    }

    public static String last_name(String text) {
        int ind = text.lastIndexOf(" ") + 1;
        return text.substring(ind);
    }

}
