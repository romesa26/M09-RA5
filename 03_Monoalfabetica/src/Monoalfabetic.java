import java.util.ArrayList;
import java.util.Collections;

public class Monoalfabetic {

    private static final char[] majuscules =
        "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();

    private static final char[] permutat = permutaAlfabet(majuscules);

    public static char[] permutaAlfabet(char[] alfabet) {
        ArrayList<Character> lletres = new ArrayList<>();
        for (char c : alfabet) {
            lletres.add(c);
        }
        Collections.shuffle(lletres);

        char[] resultat = new char[alfabet.length];
        for (int i = 0; i < resultat.length; i++) {
            resultat[i] = lletres.get(i);
        }
        return resultat;
    }

    private static int indexOf(char[] array, char c) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == c) {
                return i;
            }
        }
        return -1;
    }

    private static String transforma(String cadena, char[] origen, char[] desti) {
        String resultat = "";
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            boolean minuscula = Character.isLowerCase(c);
            int pos = indexOf(origen, Character.toUpperCase(c));

            if (pos == -1) {
                resultat = resultat + c;
            } else {
                char nova = desti[pos];
                if (minuscula) {
                    nova = Character.toLowerCase(nova);
                }
                resultat = resultat + nova;
            }
        }
        return resultat;
    }

    public static String xifraMonoAlfa(String cadena) {
        return transforma(cadena, majuscules, permutat);
    }

    public static String desxifraMonoAlfa(String cadena) {
        return transforma(cadena, permutat, majuscules);
    }

    public static void main(String[] args) {
        for (int i = 0; i < majuscules.length; i++) {
            System.out.print(majuscules[i] + " ");
        }
        System.out.println();

        for (int i = 0; i < permutat.length; i++) {
            System.out.print(permutat[i] + " ");
        }
        System.out.println();

        String test1 = "Test 01 àrbitre, coixí, Perímetre";
        String test2 = "Test 02 Taüll, DÍA, año";
        String test3 = "Test 03 Peça, Òrrius, Bòvila";

        String xifrat1 = xifraMonoAlfa(test1);
        String xifrat2 = xifraMonoAlfa(test2);
        String xifrat3 = xifraMonoAlfa(test3);

        System.out.println("Xifratge:");
        System.out.println(test1 + " -> " + xifrat1);
        System.out.println(test2 + " -> " + xifrat2);
        System.out.println(test3 + " -> " + xifrat3);

        System.out.println("Desxifratge:");
        System.out.println(xifrat1 + " -> " + desxifraMonoAlfa(xifrat1));
        System.out.println(xifrat2 + " -> " + desxifraMonoAlfa(xifrat2));
        System.out.println(xifrat3 + " -> " + desxifraMonoAlfa(xifrat3));
    }
}