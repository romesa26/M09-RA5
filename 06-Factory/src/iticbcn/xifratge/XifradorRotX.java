package iticbcn.xifratge;

public class XifradorRotX implements Xifrador {

    private static final String abc = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    private static final char[] minuscules = abc.toCharArray();
    private static final char[] majuscules = abc.toUpperCase().toCharArray();

    public String xifraRotX(String cadena, int desplacament) {
        String resultat = "";
        for (int i = 0; i < cadena.length(); i++) {
            char lletra = cadena.charAt(i);
            boolean trobada = false;

            for (int j = 0; j < minuscules.length; j++) {
                if (lletra == minuscules[j]) {
                    resultat += minuscules[(j + desplacament + minuscules.length) % minuscules.length];
                    trobada = true;
                    break;
                }
            }

            if (!trobada) {
                for (int j = 0; j < majuscules.length; j++) {
                    if (lletra == majuscules[j]) {
                        resultat += majuscules[(j + desplacament + majuscules.length) % majuscules.length];
                        trobada = true;
                        break;
                    }
                }
            }

            if (!trobada) {
                resultat += lletra;
            }
        }
        return resultat;
    }

    public String desxifraRotX(String cadena, int desplacament) {
        return xifraRotX(cadena, -desplacament);
    }

    public void forcaBrutaRotX(String cadenaXifrada) {
        System.out.println("Missatge xifrat: " + cadenaXifrada);
        System.out.println("----------------");
        for (int desplacament = 0; desplacament < minuscules.length; desplacament++) {
            String desxifrat = desxifraRotX(cadenaXifrada, desplacament);
            System.out.println("(" + desplacament + ")->" + desxifrat);
        }
    }

    private int convertirClau(String clau) throws ClauNoSuportada {
        int desplacament;
        try {
            desplacament = Integer.parseInt(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
        if (desplacament < 0 || desplacament > 40) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
        return desplacament;
    }

    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        int desplacament = convertirClau(clau);
        return new TextXifrat(xifraRotX(msg, desplacament).getBytes());
    }

    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        int desplacament = convertirClau(clau);
        return desxifraRotX(xifrat.toString(), desplacament);
    }
}
