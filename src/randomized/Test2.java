package randomized;

import java.util.Random;

public class Test2 {
    public static void main(String[] args) {
        int[] sizeLeft = {1,10,100,1000,10000};
        int[] sizeRight = {1,1,1,1,1,
                            10,10,10,10,10,
                            100,100,100,100,100,
                            1000,1000,1000,1000,1000,
                            10000,10000,10000,10000,10000};
        int repetitions = 1000;

        long begin;
        long end;
        long[][][][] times = new long[sizeLeft.length][sizeRight.length][repetitions][3];
        Random rand = new Random();

        for(int l=0;l<sizeLeft.length;l++) {
            int sizeL = sizeLeft[l];
            for(int r=0;r<sizeRight.length;r++) {
                int sizeR = sizeRight[r];
                for(int rep=0;rep<repetitions;rep++) {
                    Treap left = new Treap();
                    Treap right = new Treap();
                    for (int i = 0; i < sizeL; i++) {
                        left.insertNode(i, rand.nextLong());
                    }
                    for (int i = 0; i < sizeR; i++) {
                        right.insertNode(sizeL + 1 + i, rand.nextLong());
                    }
                    begin = System.nanoTime();
                    left.join((long) sizeL, right);
                    end = System.nanoTime();
                    times[l][r][rep][0] = end - begin;

                    begin = System.nanoTime();
                    Treap[] treapList = left.split(sizeL);
                    end = System.nanoTime();
                    times[l][r][rep][1] = end - begin;

                    left = treapList[0];
                    right = treapList[1];
                    begin = System.nanoTime();
                    left.paste(right);
                    end = System.nanoTime();
                    times[l][r][rep][2] = end - begin;
                }
            }
        }


        System.out.println("Left, Right, Join, Split, Paste");
        //calculate avg results:
        for(int m = 0;m<sizeLeft.length;m++) {
            for(int n =0;n<sizeRight.length;n++) {
                long currSumJoin = 0;
                long currSumSplit= 0;
                long currSumPaste= 0;
                for(int r=0;r<repetitions;r++) {
                    currSumJoin += times[m][n][r][0];
                    currSumSplit+= times[m][n][r][1];
                    currSumPaste+= times[m][n][r][2];
                }
                System.out.println(sizeLeft[m] + "," + sizeRight[n] + "," + currSumJoin/repetitions +
                        "," + currSumSplit/repetitions +
                        "," + currSumPaste/repetitions);
            }
        }
    }
}
