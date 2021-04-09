package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int N = oomages.size();
        int[] hashDistribute = new int[M];
        for (Oomage oomage : oomages) {
            int bucketNum = (oomage.hashCode() & 0x7FFFFFFF) % M;
            hashDistribute[bucketNum] += 1;
        }
        for (int i : hashDistribute) {
            if (i < N / 50 || i > N / 2.5) {
                return false;
            }
        }
        return true;
    }
}
