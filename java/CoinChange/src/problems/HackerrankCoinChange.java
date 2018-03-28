package problems;

import java.io.*;
import java.util.StringTokenizer;

/**]
 *
 * https://www.hackerrank.com/challenges/coin-change/problem
 * */

public class HackerrankCoinChange {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out), true);

    public static long solver(int [] coins, int target) {
        long table [] = new long[target+1];
        table[0] = 1;
        for (int i = 0; i <coins.length ; i++) {
            for (int j = coins[i]; j<=target ; j++) {
                int ithSolution = j - coins[i];
                table[j] += table[ithSolution];
            }
        }
        return table[target];
    }

    public static void main(String[] args) {
        try {
            StringTokenizer tk = new StringTokenizer(reader.readLine(), " ");
            int k = Integer.parseInt(tk.nextToken());
            int s = Integer.parseInt(tk.nextToken());
            int [] set = new int[s];
            tk = new StringTokenizer(reader.readLine(), " ");
            for (int i = 0; tk.hasMoreTokens(); i++) {
                set[i] = Integer.parseInt(tk.nextToken());
            }
            writer.printf("%d\n", solver(set, k));
        } catch (IOException ieox) {}
    }
}
