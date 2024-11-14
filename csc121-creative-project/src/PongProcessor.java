import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class PongProcessor {

	public static void main(String[] args) {

		System.out.println ("Processing Background"); 

		System.out.println ("Enter File Name"); 

		// get user's input from "standard input" --- ususally the keyboard
		Scanner kb = new Scanner (System.in); 
		String filename = kb.nextLine(); 
		
		System.out.println("Reading from" + filename + "..."); 
	
		// open the data file 
		Scanner sc = new Scanner (new File(filename));
		
		// open an output file 
		PrintWriter pw = new PrintWriter (new File ("output txt")); 
		
		
		
	}

}
