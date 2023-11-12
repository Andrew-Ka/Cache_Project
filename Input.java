
/*
 * Andrew Kalathra
 * 11/13/23
 * COSC 4310
 * This class will take the inputs necessary for this project
 */
import java.util.Scanner;

public class Input {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		// the main variables
		int NumbOfCacheBlocks;
		int setAssociative = 0;
		// initially assume as Direct Mapped
		int repPolOp;
		// choose between FIFO or LRU
		// int[] memAddress = new int[];
		// have to actually put this later, but this is important

		// formal intro
		System.out.println("Hello, this is a simple cache creator");
		System.out.println("In the lines below, enter the required information");
		System.out.println();
		// input time
		System.out.print("Number of Cache Blocks: ");
		NumbOfCacheBlocks = input.nextInt();
		System.out.println();

		System.out.printf("For Set Associative, enter \n 0 for Direct Mapped,\n");
		System.out.printf(" 1 for Fully Associative, \n or the number for Set Associative: \n");
		setAssociative = input.nextInt();
		System.out.println();

		System.out.print("Enter 0 for LRU or 1 for FIFO (replacement policies): ");
		repPolOp = input.nextInt();
		// False = 0 = LRU
		System.out.println();

		System.out.println("How many block address references will you be using?");
		int numbRef = input.nextInt();
		int[] memAddress = new int[numbRef];
		System.out.println("Enter " + numbRef + " block address references: ");
		for (int i = 0; i < numbRef; i++) {
			memAddress[i] = input.nextInt();
		}

		input.close();
		// checker
		/*
		 * System.out.println(); for(int i=0; i<numbRef; i++) {
		 * System.out.print(memAddress[i] + " "); }
		 */

		int HitRate;
		int MissRate;
		// Direct_Mapped test =
		if (setAssociative == 0) {// direct map
			Direct_Mapped test = new Direct_Mapped(memAddress, NumbOfCacheBlocks); // don't need replacement
			// policy bc it doesn't matter
			HitRate = test.getHitR();
			MissRate = test.getMissR();
		} else if (setAssociative == 1) { // fully associative
			Fully_Associative test = new Fully_Associative(memAddress, NumbOfCacheBlocks, repPolOp);
			HitRate = test.getHitR();
			MissRate = test.getMissR();
		} else {// set associative
			Set_Associative test = new Set_Associative(memAddress, NumbOfCacheBlocks, repPolOp, setAssociative);
			HitRate = test.getHitR();
			MissRate = test.getMissR();
		}
		System.out.println("Hit Rate was: " + HitRate);
		System.out.println("Miss Rate was: " + MissRate);

	}

}
