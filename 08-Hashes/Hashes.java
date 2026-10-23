import java.security.MessageDigest;

package 08-Hashes;

public class Hashes {
    public String getSHA512AmbSalt(String pw, String salt) throws Exception {
    MessageDigest md = MessageDigest.getInstance("SHA-512");

    String text = pw + salt;
    byte[] bytes = md.digest(text.getBytes());

    HexFormat hex = HexFormat.of();
    String hash = hex.formatHex(bytes);

    return hash;

    }

    

   public String getPBKDF2AmbSalt(String pw, String salt) throws Exception {
    PBEKeySpec spec = new PBEKeySpec(
        pw.toCharArray(),
        salt.getBytes(),
        65536,
        256
    );

    SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
    byte[] bytes = skf.generateSecret(spec).getEncoded();

    HexFormat hex = HexFormat.of();
    String hash = hex.formatHex(bytes);

    return hash;
    }

    public String forcaBruta(String alg, String hash, String salt){

    }
    public String getIntervañ(long t1, long t2){

    }
    public static void main(String[] args){

    }

}
