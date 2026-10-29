
import java.security.MessageDigest;
import java.util.HexFormat;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {

    int nPass = 0;

    public String getSHA512AmbSalt(String pw, String salt) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-512");

        byte[] bytes = md.digest((pw + salt).getBytes());

        HexFormat hex = HexFormat.of();
        return hex.formatHex(bytes);
    }

    public String getPBKDF2AmbSalt(String pw, String salt) throws Exception {
        PBEKeySpec spec = new PBEKeySpec(
            pw.toCharArray(),
            salt.getBytes(),
            65536,
            128
        );

        SecretKeyFactory skf =
            SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

        byte[] bytes = skf.generateSecret(spec).getEncoded();
        spec.clearPassword();

        HexFormat hex = HexFormat.of();
        return hex.formatHex(bytes);
    }

    public boolean comprovar(String alg, String pw, String hash,
            String salt) throws Exception {

        nPass++;

        String hashProvat;

        if (alg.equals("SHA-512")) {
            hashProvat = getSHA512AmbSalt(pw, salt);
        } else {
            hashProvat = getPBKDF2AmbSalt(pw, salt);
        }

        return hashProvat.equals(hash);
    }

    public String forcaBruta(String alg, String hash, String salt)
            throws Exception {

        String charset = "abcdefABCDEF1234567890!";
        char[] password = new char[6];
        nPass = 0;

        for (int a = 0; a < charset.length(); a++) {
            password[0] = charset.charAt(a);
            String pw = new String(password, 0, 1);

            if (comprovar(alg, pw, hash, salt)) return pw;

            for (int b = 0; b < charset.length(); b++) {
                password[1] = charset.charAt(b);
                pw = new String(password, 0, 2);

                if (comprovar(alg, pw, hash, salt)) return pw;

                for (int c = 0; c < charset.length(); c++) {
                    password[2] = charset.charAt(c);
                    pw = new String(password, 0, 3);

                    if (comprovar(alg, pw, hash, salt)) return pw;

                    for (int d = 0; d < charset.length(); d++) {
                        password[3] = charset.charAt(d);
                        pw = new String(password, 0, 4);

                        if (comprovar(alg, pw, hash, salt)) return pw;

                        for (int e = 0; e < charset.length(); e++) {
                            password[4] = charset.charAt(e);
                            pw = new String(password, 0, 5);

                            if (comprovar(alg, pw, hash, salt)) return pw;

                            for (int f = 0; f < charset.length(); f++) {
                                password[5] = charset.charAt(f);
                                pw = new String(password);

                                if (comprovar(alg, pw, hash, salt)) {
                                    return pw;
                                }
                            }
                        }
                    }
                }
            }
        }

        return null;
    }

    public String getInterval(long t1, long t2) {
        long temps = t2 - t1;

        long dies = temps / 86400000;
        temps %= 86400000;

        long hores = temps / 3600000;
        temps %= 3600000;

        long minuts = temps / 60000;
        temps %= 60000;

        long segons = temps / 1000;
        long millis = temps % 1000;

        return dies + " dies / " + hores + " hores / "
            + minuts + " minuts / " + segons + " segons / "
            + millis + " millis";
    }

    public static void main(String[] args) throws Exception {
        String salt = "qpoweiuruañslkdfjz";
        String pw = "aaabF!";

        Hashes h = new Hashes();

        String[] aHashes = {
            h.getSHA512AmbSalt(pw, salt),
            h.getPBKDF2AmbSalt(pw, salt)
        };

        String[] algoritmes = {"SHA-512", "PBKDF2"};

        for (int i = 0; i < aHashes.length; i++) {
            System.out.println("==========================");
            System.out.println("Algorisme: " + algoritmes[i]);
            System.out.println("Hash: " + aHashes[i]);
            System.out.println("--------------------------");
            System.out.println("-- Inici de força bruta --");

            long t1 = System.currentTimeMillis();

            String pwTrobat = h.forcaBruta(
                algoritmes[i], aHashes[i], salt
            );

            long t2 = System.currentTimeMillis();

            System.out.println("Pass : " + pwTrobat);
            System.out.println("Provats: " + h.nPass);
            System.out.println("Temps : " + h.getInterval(t1, t2));
            System.out.println("--------------------------\n");
        }
    }
}
