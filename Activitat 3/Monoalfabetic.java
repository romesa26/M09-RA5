 

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class Monoalfabetic {
    private static String abc = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüwxyz";
    private static final char[] majuscules = abc.toUpperCase().toCharArray();


    public static char[] permutaAlfabet(char[] alfabet){
        ArrayList<Character> list = new ArrayList<>();

        for(char c : alfabet){
            list.add(c);
        }

        Collections.shuffle(list);

        char[] newabc = new char[alfabet.length];

        for(int i = 0; i < list.size(); i++){
            newabc[i] = list.get(i);
        }
        return newabc;
    }




    public static void main (String[] args){
        permutaAlfabet(majuscules);

    
    }

}
