public class Rot13 {

    static char[] miniscules = {'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'é', 'è', 'f', 'g', 'h', 'i', 'í', 'ï', 'j', 'k', 'l', 'm', 'n', 'o', 'ó', 'ò', 'p', 'q', 'r', 's', 't', 'u', 'ù', 'ú', 'ü', 'v', 'w', 'x', 'y', 'z'};
    static char[] mayuscules = {'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'F', 'G', 'H', 'I', 'Í', 'Ï', 'J', 'K', 'L', 'M', 'N', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ù', 'Ú', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};

    public static String xifraRot13(String cadena) {
        String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            boolean trobat = false;

            for (int j = 0; j < miniscules.length; j++) {
                if (c == miniscules[j]) {
                    resultat += miniscules[(j + 13) % miniscules.length];
                    trobat = true;
                    break;
                }
            }

            if (!trobat) {
                for (int j = 0; j < mayuscules.length; j++) {
                    if (c == mayuscules[j]) {
                        resultat += mayuscules[(j + 13) % mayuscules.length];
                        trobat = true;
                        break;
                    }
                }
            }

            if (!trobat) {
                resultat += c;
            }
        }

        return resultat;
    }

    public static String desxifraRot13(String cadena) {
        String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            boolean trobat = false;

            for (int j = 0; j < miniscules.length; j++) {
                if (c == miniscules[j]) {
                    resultat += miniscules[(j - 13 + miniscules.length) % miniscules.length];
                    trobat = true;
                    break;
                }
            }

            if (!trobat) {
                for (int j = 0; j < mayuscules.length; j++) {
                    if (c == mayuscules[j]) {
                        resultat += mayuscules[(j - 13 + mayuscules.length) % mayuscules.length];
                        trobat = true;
                        break;
                    }
                }
            }

            if (!trobat) {
                resultat += c;
            }
        }

        return resultat;
    }

    public static void main(String[] args) {

        System.out.println("Xifrat");
        System.out.println("-----------------");

        String cadenaXifrada = xifraRot13("ABC");
        System.out.println("ABC" + " -> " + cadenaXifrada);

        cadenaXifrada = xifraRot13("XYZ");
        System.out.println("XYZ" + " -> " + cadenaXifrada);

        cadenaXifrada = xifraRot13("Hola, Mr. Calçot");
        System.out.println("Hola, Mr. Calçot" + " -> " + cadenaXifrada);

        cadenaXifrada = xifraRot13("Perdó, per tu què és?");
        System.out.println("Perdó, per tu què és?" + " -> " + cadenaXifrada);
 
        System.out.println();

        System.out.println("Desxifrat");
        System.out.println("-----------------");

        String cadenaDesxifrada = desxifraRot13("IÏJ");
        System.out.println("IÏJ" + " -> " + cadenaDesxifrada);

        cadenaDesxifrada = desxifraRot13("FGH");
        System.out.println("FGH" + " -> " + cadenaDesxifrada);

        cadenaDesxifrada = desxifraRot13("Òwúi, Ùá. juúkwb");
        System.out.println("Òwúi, Ùá. juúkwb" + " -> " + cadenaDesxifrada);

        cadenaDesxifrada = desxifraRot13("Zmálx, zmá bc acñ nà");
        System.out.println("Zmálx, zmá bc acñ nà" + " -> " + cadenaDesxifrada);
    }
}

