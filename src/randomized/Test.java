package randomized;

import java.util.Random;

public class Test {

	public static void main(String[] args) {
		long end;
		long current;
        int diffTrees = 20;
        int itOnTree = 500;
        int totalIts = diffTrees * itOnTree;
		int[] nvals = {1,10,100,100,1000,10000,100000, 100000000, 100000000};
		Random rand = new Random();
		long[][] data = new long[nvals.length][3];
		int nvalIndex = 0;
		for (int n : nvals) {
			long[][] trails = new long[totalIts][3];
			int trailIndex = 0;
			for (int i = 0; i < diffTrees; i++) {
				Treap treap = new Treap();
				for (int k = 0; k < n; k++)
					treap.insert(2 * k);
				for (int j = 0; j < itOnTree; j++) {
					int random = rand.nextInt(n);
					current = System.nanoTime();
					treap.find(2 * random);
					end = System.nanoTime();
					trails[trailIndex][0] = end - current;

					int insert = 2 * rand.nextInt(n + 1) - 1;
					current = System.nanoTime();
					treap.insert(insert);
					end = System.nanoTime();
					trails[trailIndex][1] = end - current;
					treap.delete(insert);

					int delete = 2 * rand.nextInt(n);
					current = System.nanoTime();
					treap.delete(delete);
					end = System.nanoTime();
					trails[trailIndex][2] = end - current;
					treap.insert(delete);

					trailIndex++;
				}
			}
			for (int i = 0; i < totalIts; i++) {
				data[nvalIndex][0] += trails[i][0];
				data[nvalIndex][1] += trails[i][1];
				data[nvalIndex][2] += trails[i][2];
			}
			data[nvalIndex][0] /= totalIts;
			data[nvalIndex][1] /= totalIts;
			data[nvalIndex][2] /= totalIts;
			nvalIndex++;
		}

		nvalIndex = 0;
        System.out.println("Size, Find, Insert, Delete");
		for (int n : nvals) {
			System.out.println(n+","+data[nvalIndex][0]+","+data[nvalIndex][1]+","+data[nvalIndex][2]);
			nvalIndex++;
		}
	}

}
