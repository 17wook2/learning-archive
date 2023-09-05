package boj;

import java.util.Scanner;

public class boj6987 {
    static int ans;
    static int[] win, draw, lose;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i < 4; i++){
            win = new int[6]; draw = new int[6]; lose = new int[6];
            ans = 0;
            for(int j = 0; j < 6; j++){
                win[j] = sc.nextInt();
                draw[j] = sc.nextInt();
                lose[j] = sc.nextInt();
            }
            go(0,1);
            for (int j = 0; j < 6; j++) {
                int s = win[j] + draw[j] + lose[j];
                if(s != 5) ans = 0;
            }
            System.out.print(ans+" ");
        }
    }

    private static void go(int left, int right) {
        if (left == 5) {
            ans = 1;
            return;
        }
        // left가 이긴경우
        if(win[left] > 0 && lose[right] > 0){
            win[left] -= 1;
            lose[right] -= 1;
            if(right == 5) go(left+1, left+2);
            else go(left, right+1);
            win[left] += 1;
            lose[right] += 1;
        }
        // right가 이긴경우
        if(lose[left] > 0 && win[right] > 0){
            lose[left] -= 1;
            win[right] -= 1;
            if(right == 5) go(left+1, left+2);
            else go(left, right+1);
            lose[left] += 1;
            win[right] += 1;
        }
        // 비긴 경우
        if (draw[left] > 0 && draw[right] > 0) {
            draw[left] -= 1;
            draw[right] -= 1;
            if(right == 5) go(left + 1, left + 2);
            else go(left,right+1);
            draw[left] += 1;
            draw[right] += 1;
        }

    }

}
