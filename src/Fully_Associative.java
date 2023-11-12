import java.util.Arrays;

public class Fully_Associative {
	int HitR = 0; // for Hit Rate
	int MissR = 0; // for Miss Rate
	int NoOfsets;
	int[] Addresses; // for all the addresses needed to be accessed
	int[] ReplacementTable; // to track which is what to replace
	int[] mem; // this is the actual memory
	String Current; // to hold each line of the output
	int FIFOint = 1; // if we are doing FIFO, we will use this

	public Fully_Associative(int[] memAdd, int numBlocks, int repPol) {
		ReplacementTable = new int[numBlocks];
		Arrays.fill(ReplacementTable, 0);
		mem = new int[numBlocks];
		Arrays.fill(mem, -1);
		// checker
		// System.out.println(Arrays.toString(ReplacementTable));
		// System.out.println(Arrays.toString(mem));

		// print: block # for label
		System.out.println("Direct Mapped");
		String Blocks = "BLOCKS: ";
		for (int i = 0; i < numBlocks; i++) {
			Blocks += i + "  ";
		}
		System.out.printf("%-20s %-10s %-10s  %n", "Address Accessed", "Hit/Miss", Blocks);

		int tempAdd;
		for (int i = 0; i < memAdd.length; i++) {
			tempAdd = memAdd[i];
			Current = tempAdd + "                    ";

			if (contains(mem, tempAdd) != -1) { // hit
				incHit();
				if (repPol == 0) {// LRU
					LRU(tempAdd, -1);
				} else {
					FIFO(tempAdd, -1);
				}
			} else { // miss
				incMiss();
				if (repPol == 0) {// LRU
					LRU(tempAdd, 0);
				} else {
					FIFO(tempAdd, 0);
				}
			}

			Current += Arrays.toString(mem);
			Current = Current.replace("-1", "-");

			System.out.println(Current);
		}
	}

	public void LRU(int replace, int signal) {
		int index;
		int indexArr2;
		if (signal == -1) { // so don't replace, just update, for hit's
			index = contains(mem, replace);
			ReplacementTable[index]++;
			// checker
			// System.out.println(ReplacementTable[index]);
		} else { // replace the insert, for misses
			index = ReplacementTable[0];
			indexArr2 = 0;
			for (int i = 1; i < mem.length; i++) {
				if (index > ReplacementTable[i]) {
					index = ReplacementTable[i];
					indexArr2 = i;
					// checker
					// System.out.println(index + " " + indexArr2);
				}
			}
			mem[indexArr2] = replace;
			ReplacementTable[indexArr2]++;
		}

	}

	public void FIFO(int block, int replace) {
		int index;
		int indexArr2;
		if (replace == -1) {
			// do nothing
		} else {
			index = ReplacementTable[0];
			indexArr2 = 0;
			for (int i = 1; i < mem.length; i++) {
				if (index > ReplacementTable[i]) {
					index = ReplacementTable[i];
					indexArr2 = i;
				}
			}
			mem[indexArr2] = replace;
			ReplacementTable[indexArr2] = FIFOint;
			FIFOint++;
		}

	}

	public void incHit() {
		HitR++;
		Current += ("Hit               ");
	}

	public void incMiss() {
		MissR++;
		Current += ("Miss              ");
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
