package ie.atu.sw;

import java.util.Scanner;

public class Menu {
	fileHandler handler = new fileHandler();
	StringBuilder sb = new StringBuilder();
	FourSquareCipher cipher = new FourSquareCipher();
	private Scanner s;
	private boolean keepRunning = true;
	private String encryptionFile = "";
	private String decryptionFileName = "";
	private String outputFileName = "./output.txt";
	
	
	public Menu() {
		s = new Scanner(System.in);
	}
	
	//Method to display user menu options
	private void showOptions() {
		System.out.println(ConsoleColour.WHITE);
		System.out.println("************************************************************");
		System.out.println("*     ATU - Dept. of Computer Science & Applied Physics    *");
		System.out.println("*                                                          *");
		System.out.println("*       Encrypting Files with a Four Square Cipher         *");
		System.out.println("*                                                          *");
		System.out.println("************************************************************");
		System.out.println("(1) Specify Text File to Encrypt");
		System.out.println("(2) Specify Text File to Decrypt");
		System.out.println("(3) Specify Output File (default = output.txt)");
		System.out.println("(4) Encrypt Text File");
		System.out.println("(5) Decrypt Text File");
		System.out.println("(6) Quit");
		System.out.println("Encryption File Name: " + encryptionFile);
		System.out.println("Decryption File Name : " + decryptionFileName);
		System.out.println("Output File Name: " + outputFileName);
		
		//Output a menu of options and solicit text from the user
		System.out.print(ConsoleColour.BLACK_BOLD_BRIGHT);
		System.out.print("Select Option [1-?]>");
		System.out.println();
		
	}
	
	//Method to start the menu
	public void start() {
		try {
		while (keepRunning) {
			showOptions();
			//new int choice which reads the users choice from the keyboard
			int choice = Integer.parseInt(s.next());
			switch (choice) {
			case 1 -> nameEncryptionFile();
			case 2 -> nameDecryptionFile();
			case 3 -> outFile();
			case 4 -> encryptFile();
			case 5 -> decryptFile();
			case 6 -> {
				System.out.println("Goodbye!");
				keepRunning = false;
			}
			default -> showOptions();
			}
			
		}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	//Method to set the name of the text file that the user wants to encrypt
	private void nameEncryptionFile() {
		System.out.print("Please specify the text file to encrypt ");
		String inFile = s.next();
		this.encryptionFile = "./" + inFile;
		
	}
	
	//Method to set the name of the text file that the user wants to decrypt
	private void nameDecryptionFile() {
		System.out.println("Please specify the text file to decrypt: ");
		String decryptionFile = s.next();
		this.decryptionFileName = "./" + decryptionFile;
		}
	
	//Method to set the name of the file that will hold the output of the encryption/decryption
	private void outFile() {
		System.out.print("Please specify the output file name ");
		this.outputFileName = s.next();
	}

	//Method to encrypt the chosen text file
	private void encryptFile() throws Exception {
		handler.OutputFileCreator(encryptionFile, outputFileName, true);
	}
	
	//Method to decrypt the chosen text file
	private void decryptFile() throws Exception {
		handler.OutputFileCreator(decryptionFileName, outputFileName, false);
		
	}
	
}
