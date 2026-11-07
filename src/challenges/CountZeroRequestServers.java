package challenges;

import java.util.Arrays;
import java.util.Comparator;

public class CountZeroRequestServers implements Challenge{

    private int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int q = queries.length;
        int [] res = new int[q];
        Arrays.sort(logs, Comparator.comparingInt(a -> a[1]));

        Integer[] order = new Integer[q];
        for (int i = 0; i < q; i++) order[i] = i;
        Arrays.sort(order, Comparator.comparingInt(i -> queries[i]));

        int[] freq = new int[n + 1];
        int active = 0;
        int l = 0, r = 0;

        for (int idx : order) {
            int t0 = queries[idx] - x;
            int t1 = queries[idx];
            while (r < logs.length && logs[r][1] <= t1) {
                if (freq[logs[r][0]]++ == 0) active++;
                r++;
            }
            while (l < r && logs[l][1] < t0) {
                if (--freq[logs[l][0]] == 0) active--;
                l++;
            }
            res[idx] = n - active;
        }
        return res;
    }

    @Override
    public void getSolutions() {

    }

    static void main() {
        CountZeroRequestServers c = new CountZeroRequestServers();
        int n = 3;
        int[][] logs = {{1,3},{2,6},{1,5}};
        int x = 5;
        int[] queries = {10, 11};
        int[] res = c.countServers(n, logs, x, queries);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }
}
