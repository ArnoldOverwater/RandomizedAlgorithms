package randomized;

import java.util.Random;

public class Test {

	public static void main(String[] args) {
		long end;
		long current;

		int diffTrees = 20;
        int itOnTree = 500;
        int totalIts = diffTrees * itOnTree;

        int[] nvals = {1,10,100,100,1000,10000,100000,1000000/*,10000000*/};

        Random rand = new Random();

        long[][] timeData = new long[nvals.length][3];
        double[][] rotateData = new double[nvals.length][2];

        int nvalIndex = 0;
		for (int n : nvals) {
			long[][] timeTrails = new long[totalIts][3];
			int[][] rotateTrails = new int[totalIts][2];

			int trailIndex = 0;
			for (int i = 0; i < diffTrees; i++) {
				Treap treap = new Treap();
				for (int k = 0; k < n; k++)
					treap.insert(2 * k);
				treap.getRotateCountAndReset();
				for (int j = 0; j < itOnTree; j++) {
					int random = rand.nextInt(n);
					current = System.nanoTime();
					treap.find(2 * random);
					end = System.nanoTime();
					timeTrails[trailIndex][0] = end - current;

					int insert = 2 * rand.nextInt(n + 1) - 1;
					current = System.nanoTime();
					treap.insert(insert);
					end = System.nanoTime();
					timeTrails[trailIndex][1] = end - current;
					rotateTrails[trailIndex][0] = treap.getRotateCountAndReset();
					treap.delete(insert);
					treap.getRotateCountAndReset();

					int delete = 2 * rand.nextInt(n);
					current = System.nanoTime();
					treap.delete(delete);
					end = System.nanoTime();
					timeTrails[trailIndex][2] = end - current;
					rotateTrails[trailIndex][1] = treap.getRotateCountAndReset();
					treap.insert(delete);
					treap.getRotateCountAndReset();

					trailIndex++;
				}
			}
			for (int i = 0; i < totalIts; i++) {
				timeData[nvalIndex][0] += timeTrails[i][0];
				timeData[nvalIndex][1] += timeTrails[i][1];
				timeData[nvalIndex][2] += timeTrails[i][2];
				rotateData[nvalIndex][0] += rotateTrails[i][0];
				rotateData[nvalIndex][1] += rotateTrails[i][1];
			}
			timeData[nvalIndex][0] /= totalIts;
			timeData[nvalIndex][1] /= totalIts;
			timeData[nvalIndex][2] /= totalIts;
			rotateData[nvalIndex][0] /= (double)totalIts;
			rotateData[nvalIndex][1] /= (double)totalIts;
			nvalIndex++;
		}

		nvalIndex = 0;
        System.out.println("Size, Find, Insert, Delete, Rotate Insert, Rotate Delete");
		for (int n : nvals) {
			System.out.println(n+","+timeData[nvalIndex][0]+","+timeData[nvalIndex][1]+","+timeData[nvalIndex][2]+","+rotateData[nvalIndex][0]+","+rotateData[nvalIndex][1]);
			nvalIndex++;
		}
	}

}
