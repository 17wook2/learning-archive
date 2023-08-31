package swea;

import java.util.Arrays;
import java.util.Scanner;

public class Solution1952 {
    static int[] prices, plan, dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            prices = new int[4];
            plan = new int[13];
            dp = new int[13];
            for(int i = 0; i < 4; i++) prices[i] = sc.nextInt();
            for(int i = 1; i <= 12; i++) plan[i] = sc.nextInt();
            Arrays.fill(dp,prices[3]);
            dp[0] = 0;
            for(int i = 1; i <= 12; i++){
                if(plan[i] == 0) dp[i] = dp[i-1];
                // 1일권 구매 경우
                dp[i] = Math.min(dp[i], dp[i-1] + prices[0] * plan[i]);

                // 1달권 구매 경우
                dp[i] = Math.min(dp[i], dp[i-1] + prices[1]);

                // 3달권 구매 경우
                if(i >= 3) dp[i] = Math.min(dp[i], dp[i-3] + prices[2]);
            }
//            System.out.println(Arrays.toString(dp));
            System.out.println("#" + tc + " " + dp[12]);
        }
    }
}
