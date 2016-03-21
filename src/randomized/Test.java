package randomized;

import java.util.Random;

public class Test {

	public static void main(String[] args) {
		int[] nvals = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000};
		Random rand = new Random();
		long[][] data = new long[nvals.length][3];
		int nvalIndex = 0;
		for (int n : nvals) {
			long[][] trails = new long[16][3];
			int trailIndex = 0;
			for (int i = 0; i < 4; i++) {
				Treap treap = new Treap();
				for (int k = 0; k < n; k++)
					treap.insert(2 * k);
				for (int j = 0; j < 4; j++) {
					long current = System.nanoTime();
					treap.find(2 * rand.nextInt(n));
					trails[trailIndex][0] = System.nanoTime() - current;

					int insert = 2 * rand.nextInt(n + 1) - 1;
					current = System.nanoTime();
					treap.insert(insert);
					trails[trailIndex][1] = System.nanoTime() - current;
					treap.delete(insert);

					int delete = 2 * rand.nextInt(n);
					current = System.nanoTime();
					treap.delete(delete);
					trails[trailIndex][2] = System.nanoTime() - current;
					treap.insert(delete);

					trailIndex++;
				}
			}
			for (int i = 0; i < 16; i++) {
				data[nvalIndex][0] += trails[i][0];
				data[nvalIndex][1] += trails[i][1];
				data[nvalIndex][2] += trails[i][2];
			}
			data[nvalIndex][0] /= 16;
			data[nvalIndex][1] /= 16;
			data[nvalIndex][2] /= 16;
			nvalIndex++;
		}

		nvalIndex = 0;
		for (int n : nvals) {
			System.out.println(n+","+data[nvalIndex][0]+","+data[nvalIndex][1]+","+data[nvalIndex][2]);
			nvalIndex++;
		}
	}

}
