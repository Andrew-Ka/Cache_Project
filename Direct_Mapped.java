import java.util.Arrays;

public class Direct_Mapped {
	int HitR = 0; // for Hit Rate
	int MissR = 0; // for Miss Rate
	int[] Addresses; // for all the addresses needed to be accessed
	int[] mem; // this is the actual memory
	String Current; // to hold each line of the output

	Direct_Mapped(int[] memAdd, int numBlocks) {
		Addresses = memAdd;
		mem = new int[numBlocks];
		Arrays.fill(mem, -1);
		// checker
		// System.out.println(Arrays.toString(mem));

		// print: block # for label
		System.out.println("Direct Mapped");
		String Blocks = "BLOCKS: ";
		for (int i = 0; i < numBlocks; i++) {
			Blocks += i + "  ";
		}
		System.out.printf("%-20s %-10s %-10s  %n", "Address Accessed", "Hit/Miss", Blocks);

		int loc = 0; // temporary hold the location
		for (int i = 0; i < Addresses.length; i++) {
			int addHold = Addresses[i];

			Current = (addHold + "                    ");
			loc = addHold % numBlocks;
			if (mem[loc] == addHold) { // hit
				incHit();
				repPol(loc, -1);
			} else if (mem[loc] == -1) { // miss, but empty
				incMiss();
				repPol(loc, addHold);
			} else { // miss, replacement
				incMiss();
				repPol(loc, addHold);
			}
			Current += Arrays.toString(mem);
			Current = Current.replace("-1", "-");

			System.out.println(Current);
		}
	}

	public void repPol(int block, int replace) { // for both LRU and FIFO
		if (replace == -1) { // so don't replace, just update
		} else { // replace the insert
			mem[block] = replace;
			// ReplacementTable[block]++;
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

	public int getHitR() {
		return HitR;
	}

	public int getMissR() {
		return MissR;
	}
}
