import java.util.Arrays;

public class Set_Associative {
	int HitR = 0; // for Hit Rate
	int MissR = 0; // for Miss Rate
	int NoOfsets;
	int[] Addresses; // for all the addresses needed to be accessed
	int[][] ReplacementTable; // to track which is what to replace
	int[][] mem; // this is the actual memory
	String Current; // to hold each line of the output
	int blocksPerSet;
	int FIFOint = 1; // if we are doing FIFO, we will use this

	Set_Associative(int[] memAdd, int numBlocks, int replace, int setAssociative) {
		Addresses = memAdd;
		blocksPerSet = setAssociative;
		ReplacementTable = new int[2][numBlocks];
		/*
		 * for(int i=0; i<2; i++) { Arrays.fill(ReplacementTable[2], 0); }
		 */
		Arrays.stream(ReplacementTable).forEach(a -> Arrays.fill(a, 0));
		/*
		 * System.out.println();
		 * System.out.println(Arrays.toString(ReplacementTable[0]));
		 * System.out.println(Arrays.toString(ReplacementTable[1]));
		 * System.out.println();
		 */

		mem = new int[2][numBlocks];
		/*
		 * for(int i=0; i<2; i++) { Arrays.fill(mem[2], -1); }
		 */
		Arrays.stream(mem).forEach(a -> Arrays.fill(a, -1));
		/*
		 * System.out.println(Arrays.toString(mem[0]));
		 * System.out.println(Arrays.toString(mem[1])); System.out.println();
		 */
		NoOfsets = numBlocks / setAssociative;
		/*
		 * System.out.println(blocksPerSet); System.out.println();
		 */

		int temp = 0;
		for (int i = 0; i < numBlocks; i += setAssociative) {
			temp = i / 2;
			for (int j = 0; j < setAssociative; j++) {
				mem[0][i + j] = temp;
				ReplacementTable[0][i + j] = temp;
				// System.out.print((i+j) + " ");
			}
		}
		// checker
		/*
		 * System.out.println(); System.out.println(Arrays.toString(mem[0]));
		 * System.out.println(Arrays.toString(mem[1])); System.out.println();
		 * System.out.println(Arrays.toString(ReplacementTable[0]));
		 * System.out.println(Arrays.toString(ReplacementTable[1]));
		 * System.out.println();
		 */
		// System.out.println(Arrays.toString(mem));

		// print: block # for label
		System.out.printf("%d Way Set Associtaive\n", setAssociative);
		String Sets = "SETS: ";
		for (int i = 0; i < NoOfsets; i++) {
			for (int j = 0; j < blocksPerSet; j++) {
				Sets += i + "  ";
			}
		}
		System.out.printf("%-20s %-10s %-10s  %n", "Address Accessed", "Hit/Miss", Sets);

		int loc = 0; // temporary hold the set location
		int[] holdAccess = new int[blocksPerSet];
		for (int i = 0; i < Addresses.length; i++) {
			int addHold = Addresses[i];

			Current = (addHold + "                    ");
			loc = addHold % NoOfsets;
			for (int j = 0; j < blocksPerSet; j++) {
				holdAccess[j] = mem[1][j + (loc * 2)];
			}
			// checker
			// System.out.println();
			// System.out.println(Arrays.toString(holdAccess));
			// System.out.println();
			if ((contains(holdAccess, addHold)) != -1) { // hit
				incHit();
				if (replace == 0) { // LRU
					LRU(loc, addHold, -1);
				} else {
					FIFO(loc, -1);
				}
			} else if ((contains(holdAccess, -1)) != -1) { // miss, but empty
				incMiss();
				// checker
				// System.out.println("Miss 1");
				if (replace == 0) { // LRU
					LRU(loc, addHold, 0);
				} else {
					FIFO(loc, addHold);
				}
			} else { // miss, replacement
				incMiss();
				// checker
				// System.out.println("Miss 2");
				if (replace == 0) { // LRU
					LRU(loc, addHold, 0);
				} else {
					FIFO(loc, addHold);
				}
			}
			Current += Arrays.toString(mem[1]);
			Current = Current.replace("-1", "-");

			System.out.println(Current);
		}
	}

	public void LRU(int set, int replace, int signal) {
		int index;
		int indexArr2;
		if (signal == -1) { // so don't replace, just update, for hit's
			index = contains(mem[1], replace);
			ReplacementTable[1][index]++;
			// checker
			// System.out.println(ReplacementTable[1][index]);
		} else { // replace the insert, for misses
			index = ReplacementTable[1][blocksPerSet * set];
			indexArr2 = blocksPerSet * set;
			for (int i = 1; i < blocksPerSet; i++) {
				if (index > ReplacementTable[1][(blocksPerSet * set) + i]) {
					index = ReplacementTable[1][(blocksPerSet * set) + i];
					indexArr2 = (blocksPerSet * set) + i;
					// checker
					// System.out.println(index + " " + indexArr2);
				}
			}
			mem[1][indexArr2] = replace;
			ReplacementTable[1][indexArr2]++;
		}

	}

	public void FIFO(int set, int replace) {
		int index;
		int indexArr2;
		if (replace == -1) {
			// do nothing
		} else {
			index = ReplacementTable[1][blocksPerSet * set];
			indexArr2 = blocksPerSet * set;
			for (int i = 1; i < blocksPerSet; i++) {
				if (index > ReplacementTable[1][(blocksPerSet * set) + i]) {
					index = ReplacementTable[1][(blocksPerSet * set) + i];
					indexArr2 = (blocksPerSet * set) + i;
				}
			}
			mem[1][indexArr2] = replace;
			ReplacementTable[1][indexArr2] = FIFOint;
			FIFOint++;
		}

	}

	public void incHit() {
		HitR++;
		Current += ("Hit             ");
	}

	public void incMiss() {
		MissR++;
		Current += ("Miss            ");
	}

	public int contains(int[] arr, int data) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == data) {
				return i;
			}
		}
		return -1;
	}

	public int getHitR() {
		return HitR;
	}

	public int getMissR() {
		return MissR;
	}
}
