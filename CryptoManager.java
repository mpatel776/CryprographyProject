package application;

/**
 * Class: CMSC203
 * 
 * Instructor: Gary Thai
 * 
 * Description: The purpose of this class is encryption and decryption of data using the Ceasar and Bellaso 
 * cipher algorithms
 * 
 * Due: 25/3/2022
 * 
 * Platform/Compiler: Eclipse(Java Compiler)
 * 
 * I pledge that I have completed the programming assignment independently
 * I have not copied my code from a student or any source 
 * I have not given my code to any student
 * @author Meet Patel
 * 
**/



public class CryptoManager {
	
	private static final char LOWER_BOUND = ' ';
	private static final char UPPER_BOUND = '_';
	private static final int RANGE = UPPER_BOUND - LOWER_BOUND + 1;

	/**
	 * This method determines if a string is within the allowable bounds of ASCII codes 
	 * according to the LOWER_BOUND and UPPER_BOUND characters
	 * @param plainText a string to be encrypted, if it is within the allowable bounds
	 * @return true if all characters are within the allowable bounds, false if any character is outside
	 */
	public static boolean stringInBounds (String plainText) {
//		throw new RuntimeException("method not implemented");
		for(int i = 0;i<plainText.length();i++) {
			if((int) plainText.charAt(i)>UPPER_BOUND) {
				return false;
			}else if((int) plainText.charAt(i)<LOWER_BOUND) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Encrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in plainText is replaced by the character \"offset\" away from it 
	 * @param plainText an uppercase string to be encrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the encrypted string
	 */
	public static String encryptCaesar(String plainText, int key) {
//		throw new RuntimeException("method not implemented");
		String allowedAlphas = getAllowedChars();
		int offset;
		String cipherText = "";
		char letter;
		for(int i = 0;i<plainText.length();i++) {
			int charPos = allowedAlphas.indexOf(plainText.charAt(i));
			offset = (key + charPos) % RANGE;
			letter = allowedAlphas.charAt(offset);
			cipherText += letter;
		}
		return cipherText;
	}
	
	/**
	 * Encrypts a string according the Bellaso Cipher.  Each character in plainText is offset 
	 * according to the ASCII value of the corresponding character in bellasoStr, which is repeated
	 * to correspond to the length of plainText
	 * @param plainText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the encrypted string
	 */
	public static String encryptBellaso(String plainText, String bellasoStr) {
//		throw new RuntimeException("method not implemented");
		String normalizedKey = normalizeKey(plainText,bellasoStr);
		String allowedAlphas = getAllowedChars();
		String cipherText = "";
		int offset = 0;
		char letter;
		
		for(int i=0;i<plainText.length();i++) {
			int charPos = allowedAlphas.indexOf(plainText.charAt(i));
			offset = (normalizedKey.charAt(i) + charPos) % RANGE;
			letter = allowedAlphas.charAt(offset);
			cipherText += letter;
		}
		return cipherText;
	}
	
	/**
	 * Decrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in encryptedText is replaced by the character \"offset\" characters before it.
	 * This is the inverse of the encryptCaesar method.
	 * @param encryptedText an encrypted string to be decrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the plain text string
	 */
	public static String decryptCaesar(String encryptedText, int key) {
//		throw new RuntimeException("method not implemented");
		String allowedAlphas = getAllowedChars();
		String decipheredText = "";
		int offset = 0;
		char letter;
		for(int i = 0;i<encryptedText.length();i++) {
			int charPos = allowedAlphas.indexOf(encryptedText.charAt(i));
			offset = (charPos - key) % RANGE ;
			if(offset<0) {
				offset += RANGE;
			}
			letter = allowedAlphas.charAt(offset);
			decipheredText += letter;
		}
		
		return decipheredText;
	}
	
	/**
	 * Decrypts a string according the Bellaso Cipher.  Each character in encryptedText is replaced by
	 * the character corresponding to the character in bellasoStr, which is repeated
	 * to correspond to the length of plainText.  This is the inverse of the encryptBellaso method.
	 * @param encryptedText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the decrypted string
	 */
	public static String decryptBellaso(String encryptedText, String bellasoStr) {
//		throw new RuntimeException("method not implemented");
		String normalizedKey = normalizeKey(encryptedText,bellasoStr);
		String allowedAlphas = getAllowedChars();
		String decipheredText = "";
		int offset = 0;
		char letter;
		for(int i = 0;i<encryptedText.length();i++) {
			int charPos = allowedAlphas.indexOf(encryptedText.charAt(i));
			offset = (charPos - normalizedKey.charAt(i)) % RANGE ;
			if(offset<0) {
				offset += RANGE;
			}
			letter = allowedAlphas.charAt(offset);
			decipheredText += letter;
		}
		
		return decipheredText;
	}
	
	/**
	 * Gives the allowed characters contained within the LOWER_BOUND and UPPER_BOUND
	 * @return the allowed character range in a string
	 */
	private static String getAllowedChars() {
		String str = "";
		for(int i = LOWER_BOUND;i<=UPPER_BOUND;i++) {
			char letter = (char) i;
			str += letter;
		}
		return str;
	}
	
	/**
	 * Coverts the given key based on the plainText/cipherText. The new key has the same length
	 * as the plainText/cipherText
	 * @param plainText an uppercase string to be encrypted.
	 * @param key an uppercase string that specifies the offsets, character by character.
	 * @return the normalized key string
	 */
	private static String normalizeKey(String plainText,String key) {
		String newKey = "";
		if(plainText.length() > key.length()) {
			int i = 0;
			while(newKey.length()!=plainText.length()) {
				if(i == key.length()) {
					i = 0;
				}
				newKey += key.charAt(i);
				i++;
			}
			return newKey;
		}else if(plainText.length() < key.length()) {
			for(int i = 0;i<plainText.length();i++) {
				newKey += key.charAt(i);
			}
			return newKey;
		}
		newKey += key;
		return newKey;
	}
}
