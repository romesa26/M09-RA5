package src;
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

        String[] paraulesXifrar = {"ABC", "XYZ", "Hola, Mr. Calçot", "Perdó, per tu què és?"};
        String[] paraulesDesxifrar = {"IÏJ", "FGH", "Òwúi, Ùá. juúkwb", "Zmálx, zmá bc acñ nà"};

        System.out.println("Xifrat");
        System.out.println("-----------------");

        for (int i = 0; i < paraulesXifrar.length; i++) {
            String cadenaXifrada = xifraRot13(paraulesXifrar[i]);
            System.out.println(paraulesXifrar[i] + " -> " + cadenaXifrada);
        }

        System.out.println();

        System.out.println("Desxifrat");
        System.out.println("-----------------");

        for (int i = 0; i < paraulesDesxifrar.length; i++) {
            String cadenaDesxifrada = desxifraRot13(paraulesDesxifrar[i]);
            System.out.println(paraulesDesxifrar[i] + " -> " + cadenaDesxifrada);
        }
    }
}