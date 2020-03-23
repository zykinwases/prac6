package training_center;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// class that creates password hash using md5 to (md5 + salt)  
public class Encryption {
	
	static String salt = "opk24g8v";
	
	public static String md5Custom(String st) {
	    MessageDigest messageDigest = null;
	    byte[] digest = new byte[0];

	    try {
	        messageDigest = MessageDigest.getInstance("MD5");
	        messageDigest.reset();
	        messageDigest.update(st.getBytes());
	        digest = messageDigest.digest();
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }

	    BigInteger bigInt = new BigInteger(1, digest);
	    String md5Hex = bigInt.toString(16);

	    while( md5Hex.length() < 32 ){
	        md5Hex = "0" + md5Hex;
	    }

	    return md5Hex;
	}

	public static String encryptmd5(String st) {
		return md5Custom(md5Custom(st) + salt);
	}
}
