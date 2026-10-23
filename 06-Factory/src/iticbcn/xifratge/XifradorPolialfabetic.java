package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador {

    private static final char[] majuscules =
        "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();

    private char[] permutat = new char[majuscules.length];

    private Random random;

    public void initRandom(long clau) {
        random = new Random(clau);
    }

    public void permutaAlfabet() {
        ArrayList<Character> lletres = new ArrayList<>();
        for (char c : majuscules) {
            lletres.add(c);
        }
        Collections.shuffle(lletres, random);

        for (int i = 0; i < lletres.size(); i++) {
            permutat[i] = lletres.get(i);
        }
    }

    private int indexOf(char[] array, char c) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == c) {
                return i;
            }
        }
        return -1;
    }

    public String xifraPoliAlfa(String msg) {
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

    public String desxifraPoliAlfa(String msgXifrat) {
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

    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            initRandom(Long.parseLong(clau));
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }
        return new TextXifrat(xifraPoliAlfa(msg).getBytes());
    }

    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            initRandom(Long.parseLong(clau));
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un String convertible a long");
        }
        return desxifraPoliAlfa(xifrat.toString());
    }
}
