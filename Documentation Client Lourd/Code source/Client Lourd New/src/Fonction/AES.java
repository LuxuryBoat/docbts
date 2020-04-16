package Fonction;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class AES {

	private static String CIPHER_NAME = "AES/CBC/PKCS5PADDING";

	private static int CIPHER_KEY_LEN = 16; // 128 bits
	
	String key = "BESTGROUPEPPEGLR"; // 128 bit key
	String initVector = "1234567898765432";

	
	public static String encrypt(String key, String iv, String data) {

	    try {
	      IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes("UTF-8"));
	      SecretKeySpec secretKey = new SecretKeySpec(fixKey(key).getBytes("UTF-8"), "AES");
	      
	      Cipher cipher = Cipher.getInstance(AES.CIPHER_NAME);
	      cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

	      byte[] encryptedData = cipher.doFinal((data.getBytes()));

	      String encryptedDataInBase64 = Base64.getEncoder().encodeToString(encryptedData);
	      String ivInBase64 = Base64.getEncoder().encodeToString(iv.getBytes("UTF-8"));

	      return encryptedDataInBase64 + ":" + ivInBase64;

	    } catch (Exception ex) {
	      throw new RuntimeException(ex);
	    }
	  }

	  private static String fixKey(String key) {

	    if (key.length() < AES.CIPHER_KEY_LEN) {
	      int numPad = AES.CIPHER_KEY_LEN - key.length();

	      for (int i = 0; i < numPad; i++) {
	        key += "0"; //0 pad to len 16 bytes
	      }

	      return key;

	    }

	    if (key.length() > AES.CIPHER_KEY_LEN) {
	      return key.substring(0, CIPHER_KEY_LEN); //truncate to 16 bytes
	    }

	    return key;
	  }

	  /**
	   * Decrypt data using AES Cipher (CBC) with 128 bit key
	   *
	   * @param key  - key to use should be 16 bytes long (128 bits)
	   * @param data - encrypted data with iv at the end separate by :
	   * @return decrypted data string
	   */

	  public static String decrypt(String key, String data) {

	    try {
	      String[] parts = data.split(":");

	      IvParameterSpec iv = new IvParameterSpec(Base64.getDecoder().decode(parts[1]));
	      SecretKeySpec secretKey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

	      Cipher cipher = Cipher.getInstance(AES.CIPHER_NAME);
	      cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);

	      byte[] decodedEncryptedData = Base64.getDecoder().decode(parts[0]);

	      byte[] original = cipher.doFinal(decodedEncryptedData);

	      return new String(original);
	    } catch (Exception ex) {
	      throw new RuntimeException(ex);
	    }
	  }

}
