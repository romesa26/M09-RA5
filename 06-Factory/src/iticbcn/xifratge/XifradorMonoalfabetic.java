package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;

public class XifradorMonoalfabetic implements Xifrador {

    private static final char[] majuscules =
        "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();

    private char[] permutat;

    public XifradorMonoalfabetic() {
        permutat = permutaAlfabet(majuscules);
    }

    public char[] permutaAlfabet(char[] alfabet) {
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

    private int indexOf(char[] array, char c) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == c) {
                return i;
            }
        }
        return -1;
    }

    private String transforma(String cadena, char[] origen, char[] desti) {
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

    public String xifraMonoAlfa(String cadena) {
        return transforma(cadena, majuscules, permutat);
    }

    public String desxifraMonoAlfa(String cadena) {
        return transforma(cadena, permutat, majuscules);
    }

    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        return new TextXifrat(xifraMonoAlfa(msg).getBytes());
    }

    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        return desxifraMonoAlfa(xifrat.toString());
    }
}
