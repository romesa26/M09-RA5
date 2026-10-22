import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Polialfabetic {

    private static final char[] majuscules =
        "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();

    private static char[] permutat = new char[majuscules.length];

    private static Random random;

    private static final long clauSecreta = 123456;

    public static void initRandom(long clau) {
        random = new Random(clau);
    }

    public static void permutaAlfabet() {
        ArrayList<Character> lletres = new ArrayList<>();
        for (char c : majuscules) {
            lletres.add(c);
        }
        Collections.shuffle(lletres, random);

        for (int i = 0; i < lletres.size(); i++) {
            permutat[i] = lletres.get(i);
        }
    }

    private static int indexOf(char[] array, char c) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == c) {
                return i;
            }
        }
        return -1;
    }

    public static String xifraPoliAlfa(String msg) {
        String resultat = "";
        for (int i = 0; i < msg.length(); i++) {
            char c = msg.charAt(i);
            boolean minuscula = Character.isLowerCase(c);
            permutaAlfabet();
            int pos = indexOf(majuscules, Character.toUpperCase(c));

            if (pos == -1) {
                resultat = resultat + c;
            } else {
                char nova = permutat[pos];
                if (minuscula) {
                    nova = Character.toLowerCase(nova);
                }
                resultat = resultat + nova;
            }
        }
        return resultat;
    }

    public static String desxifraPoliAlfa(String msgXifrat) {
        String resultat = "";
        for (int i = 0; i < msgXifrat.length(); i++) {
            char c = msgXifrat.charAt(i);
            boolean minuscula = Character.isLowerCase(c);
            permutaAlfabet();
            int pos = indexOf(permutat, Character.toUpperCase(c));

            if (pos == -1) {
                resultat = resultat + c;
            } else {
                char nova = majuscules[pos];
                if (minuscula) {
                    nova = Character.toLowerCase(nova);
                }
                resultat = resultat + nova;
            }
        }
        return resultat;
    }

    public static void main(String[] args) {
        String msgs[] = {"Test 01 àrbitre, coixí, Perímetre",
                "Test 02 Taüll, DÍA, año",
                "Test 03 Peça, Òrrius, Bòvila"};
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("Xifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.println(msgs[i] + " -> " + msgsXifrats[i]);
        }

        System.out.println("Desxifratge:\n-----------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.println(msgsXifrats[i] + " -> " + msg);
        }
    }
}