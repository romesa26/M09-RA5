import java.security.MessageDigest;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
 
public class AES {
 
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
 
    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "LaClauSecretaQueVulguis";
 
    public static byte[] xifraAES(String msg, String clau) throws Exception {
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
 
        return resultat;
    }
 
    public static String desxifraAES(byte[] bIvMsgXifrat, String clau) throws Exception {
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
    }
 
    public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet",
                "Hola Andrés cómo está tu cuñado",
                "Àgora ïlla Ôtto"};
 
        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];
 
            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: "
                        + e.getLocalizedMessage());
            }
            System.out.println("--------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
    }
}