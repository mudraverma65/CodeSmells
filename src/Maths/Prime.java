package Maths;

public class Prime {
    public boolean isP(String str){
        String reversed = new StringBuilder(str).reverse().toString();
        return str.equals(reversed);
    }
}
