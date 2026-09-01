package ie.atu.sw;

public class FourSquareCipher {
	
	//Method to either encrypt or decrypt text. If encyrpt is true, the encryption methods will run.
	//If false, the decryption methods will run.
	public String fourSquareCipher(String plain, boolean encrypt) {
		if(encrypt) {
		return plain = encrypt(plain);
		}else {
			return plain = decrypt(plain);
		}
	}
	
	//-------------------ENCRYPTION METHODS-------------------
	//Method to find the index of the first plain text character in the top left quadrant
	private int[] getTopLeft(char char1) {
		for (int row = 0; row < 5; row++) {
				for (int col = 0; col < 5; col++) {
					//if the plain text character is found, return the index of that character in the TABLE array
						if (TABLE[row][col] == char1) {
							return new int[] {row,col};
						}
						
				}
			} return new int[] {-1,-1};
	}
	
	//Method to find the index of the second plain text character in the bottom right quadrant
	private int[] getBottomRight(char char1) {
		for (int row = 5; row < TABLE.length; row++) {
				for (int col = 5; col < TABLE[0].length; col++) {
					//if the plain text character is found, return the index of that character in the TABLE array
						if (TABLE[row][col] == char1) {
							return new int[] {row,col};
						}
						
				}
			} return new int[] {-1,-1};
	}
	
	//Method to find the corresponding encrypted characters of the two plain text characters being worked on
	private char [] getEncryptedChars (char plain1, char plain2) {

		//Save index of first and second plain text characters to int arrays using getTopLeft and getBottomRight
		int[] plainIndex1 = getTopLeft(plain1);
		int[] plainIndex2 = getBottomRight(plain2);
		
		//index of the first cipher text character will be the row of the 1st plain text char
		//and the col of the 2nd plain text char.
		//Save the cipher text character at this index in the TABLE to cipherChar1
		char cipherChar1 = TABLE[plainIndex1[0]][plainIndex2[1]];
		
		//index of the second cipher text character will be the row of the 2nd plain text char
		//and the col of the 1nd plain text char.
		//Save the cipher text character at this index in the TABLE to cipherChar2
		char cipherChar2 = TABLE[plainIndex2[0]][plainIndex1[1]];
		
		//return the first and 2nd cipher text chars in a char array
		return new char[] {cipherChar1, cipherChar2};
	}
		
	
	//Method to encrypt the plain text
	private String encrypt(String s) {
		StringBuilder sb = new StringBuilder();
		StringBuilder filteredInput = new StringBuilder();
		
		//process the input of the plain text -> set all charcters to upper case and replace any J's with I's
		String processedInput = s.toUpperCase().replace('J', 'I');
		
		//Remove any characters that can't be encrypted such as numbers or @ symbols etc.
		//Convert processedInput to a char array and check each character for legality
		for (char c : processedInput.toCharArray()) {
			if( c >= 'A' && c <= 'Z') {
				//Save the string with the illegal characters removed to a new String 'filteredInput'
				filteredInput.append(c);
			}
		}
		
		//Check if new legal String is divisible by 2.
		if ((filteredInput.length()%2) != 0) {
			//If not append an X to the end of the string to make sure that every char has a pair
			filteredInput.append('X');
		}
		
		//Loop through every character pair of the string
		for (int i = 0; i < filteredInput.length(); i +=2) {
			if(i+1 < filteredInput.length()) {
				//Encrypt each character pair using the getEncrypted method.
				char c[] =  getEncryptedChars(filteredInput.charAt(i), filteredInput.charAt(i + 1));
				//Add each char to the end of the encrypted string.
				sb.append(c);
			}
		}
		//Return the encrypted string
		return sb.toString();
	}
	
	
	//-------------------DECRYPTION METHODS-------------------
	//Method to find the index of the first cipher text character in the top right quadrant
		private int[] getTopRight(char char1) {
			for (int row = 0; row < 5; row++) {
					for (int col = 5; col < TABLE[0].length; col++) {
							if (TABLE[row][col] == char1) {
								return new int[] {row,col};
							}
							
					}
				} return new int[] {-1,-1};
		}
		
		//Method to find the index of the second cipher text character in the bottom left quadrant
		private int[] getBottomLeft(char char1) {
			for (int row = 5; row < TABLE.length; row++) {
					for (int col = 0; col < 5; col++) {
							if (TABLE[row][col] == char1) {
								return new int[] {row,col};
							}
							
					}
				} return new int[] {-1,-1};
		}
		
		//Method to find the corresponding decrypted characters of the two cipher text characters being worked on
		private char [] getDecryptedChars (char cipher1, char cipher2) {

			//Save index of first and second cipher text characters to int arrays using getTopLeft and getBottomRight
			int[] cipherIndex1 = getTopRight(cipher1);
			int[] cipherIndex2 = getBottomLeft(cipher2);
			
			//index of the first plain text character will be the row of the 1st cipher text char
			//and the col of the 2nd cipher text char.
			//Save the plain text character at this index in the TABLE to plainChar1
			char plainChar1 = TABLE[cipherIndex1[0]][cipherIndex2[1]];
			
			//index of the second plain text character will be the row of the 2nd cipher text char
			//and the col of the 1st cipher text char.
			//Save the plain text character at this index in the TABLE to plainChar2
			char plainChar2 = TABLE[cipherIndex2[0]][cipherIndex1[1]];
			
			//return the first and 2nd plain text chars in a char array
			return new char[] {plainChar1, plainChar2};
		}
		
		//Method to decrypt the cipher text
		private String decrypt(String s) {
			//The working of this method is the same as the previous 'encrypt' method above, with the 
			//encrypted characters replaced with decrypted ones.
			StringBuilder sb = new StringBuilder();
			StringBuilder filteredInput = new StringBuilder();
			
			String processedInput = s.toUpperCase().replace('J', 'I');
			
			for (char c : processedInput.toCharArray()) {
				if( c >= 'A' && c <= 'Z') {
					filteredInput.append(c);
				}
			}
			
			if ((filteredInput.length()%2) != 0) {
				filteredInput.append('X');
			}
			
			
			for (int i = 0; i < filteredInput.length(); i +=2) {
				if(i+1 < filteredInput.length()) {
					char c[] =  getDecryptedChars(filteredInput.charAt(i), filteredInput.charAt(i + 1));
					sb.append(c);
				}
			}
			return sb.toString();
		}
	
	private static final char[][] TABLE = { 
			{'A','B','C','D','E','Z','G','P','T','F'},
			{'F','G','H','I','K','O','I','H','M','U'},
			{'L','M','N','O','P','W','D','R','C','N'},
			{'Q','R','S','T','U','Y','K','E','Q','A'},
			{'V','W','X','Y','Z','X','V','S','B','L'},
			{'M','F','N','B','D','A','B','C','D','E'},
			{'C','R','H','S','A','F','G','H','I','K'},
			{'X','Y','O','G','V','L','M','N','O','P'},
			{'I','T','U','E','W','Q','R','S','T','U'},
			{'L','Q','Z','K','P','V','W','X','Y','Z'}
		};
}
