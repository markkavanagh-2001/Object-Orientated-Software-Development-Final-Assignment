package ie.atu.sw;
import java.io.*;

public class fileHandler {
	
	//Method to read in a new text file, encrypt or decrypt the contents of the file line by line, and save the 
	//encrypted/decrypted text to a new file. If the boolean encrypt is set to true, the mothod will encrypt the input
	//file, and if the boolean is false it will decrypt the input file.
	public void OutputFileCreator(String inFile, String outFile, boolean encrypt) throws Exception {
		try {
			FourSquareCipher cipher = new FourSquareCipher();
			FileWriter out = new FileWriter(outFile);
			//Buffer each new line of the input file.
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inFile)));

			String line;
			if(encrypt) {
				//For every none empty line, use the encryption methods in FourSquareCipher to encrypt each line of the file.
				while ((line = br.readLine()) != null) {
					//Each line will be trimmed of whitespace and set to all upper case when passed to fourSquareCipher.
					out.write(cipher.fourSquareCipher(line.trim().toUpperCase(), true));
					out.write("\n");
				}
			}
			else {
				////For every none empty line, use the encryption methods in FourSquareCipher to decrypt each line of the file.
				while ((line = br.readLine()) != null) {
					out.write(cipher.fourSquareCipher(line.trim().toUpperCase(), false));
					out.write("\n");
				}
			}
			br.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

}

