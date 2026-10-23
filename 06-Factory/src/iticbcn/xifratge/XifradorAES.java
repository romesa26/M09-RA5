package iticbcn.xifratge;

import java.security.MessageDigest;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class XifradorAES implements Xifrador {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private byte[] iv = new byte[MIDA_IV];

    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            byte[] bMsg = msg.getBytes("UTF-8");

            SecureRandom random = new SecureRandom();
            random.nextBytes(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH);
            byte[] hash = md.digest(clau.getBytes("UTF-8"));
            SecretKeySpec key = new SecretKeySpec(hash, ALGORISME_XIFRAT);

            Cipher cipher = Cipher.getInstance(FORMAT_AES);
            cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
            byte[] bXifrat = cipher.doFinal(bMsg);

            byte[] resultat = new byte[MIDA_IV + bXifrat.length];
            for (int i = 0; i < MIDA_IV; i++) {
                resultat[i] = iv[i];
            }
            for (int i = 0; i < bXifrat.length; i++) {
                resultat[MIDA_IV + i] = bXifrat[i];
            }

            return new TextXifrat(resultat);
        } catch (Exception e) {
            System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            System.exit(1);
        }
        return null;
    }

    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            byte[] bIvMsgXifrat = xifrat.getBytes();

            byte[] ivExtret = new byte[MIDA_IV];
            for (int i = 0; i < MIDA_IV; i++) {
                ivExtret[i] = bIvMsgXifrat[i];
            }

            byte[] bXifrat = new byte[bIvMsgXifrat.length - MIDA_IV];
            for (int i = 0; i < bXifrat.length; i++) {
                bXifrat[i] = bIvMsgXifrat[MIDA_IV + i];
            }

            IvParameterSpec ivSpec = new IvParameterSpec(ivExtret);

            MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH);
            byte[] hash = md.digest(clau.getBytes("UTF-8"));
            SecretKeySpec key = new SecretKeySpec(hash, ALGORISME_XIFRAT);

            Cipher cipher = Cipher.getInstance(FORMAT_AES);
            cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
            byte[] bDesxifrat = cipher.doFinal(bXifrat);

            return new String(bDesxifrat, "UTF-8");
        } catch (Exception e) {
            System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            System.exit(1);
        }
        return null;
    }
}
