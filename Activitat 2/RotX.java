public class RotX {

    static char[] minuscules = {'a','á','à','b','c','ç','d','e','é','è','f','g','h','i','í','ï',
                                 'j','k','l','m','n','o','ó','ò','p','q','r','s','t','u','ù','ú',
                                 'ü','v','w','x','y','z'};

    static char[] majuscules = {'A','Á','À','B','C','Ç','D','E','É','È','F','G','H','I','Í','Ï',
                                 'J','K','L','M','N','O','Ó','Ò','P','Q','R','S','T','U','Ù','Ú',
                                 'Ü','V','W','X','Y','Z'};

    public static String xifraRotX(String cadena, int desplacament) {
        String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char lletra = cadena.charAt(i);
            boolean trobada = false;

            for (int j = 0; j < minuscules.length; j++) {
                if (lletra == minuscules[j]) {
                    resultat += minuscules[(j + desplacament) % minuscules.length];
                    trobada = true;
                    break;
                }
            }

            if (!trobada) {
                for (int j = 0; j < majuscules.length; j++) {
                    if (lletra == majuscules[j]) {
                        resultat += majuscules[(j + desplacament) % majuscules.length];
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

    public static String desxifraRotX(String cadena, int desplacament) {
        return xifraRotX(cadena, minuscules.length - (desplacament % minuscules.length));
    }

    public static void forcaBrutaRotX(String cadenaXifrada) {
        System.out.println("Missatge xifrat: " + cadenaXifrada);
        System.out.println("----------------");

        for (int desplacament = 1; desplacament <= minuscules.length; desplacament++) {
            String desxifrat = desxifraRotX(cadenaXifrada, desplacament);
            System.out.println("(" + desplacament + ")->" + desxifrat);
        }
    }

    
    }
}