package boj;

import java.util.Scanner;

public class boj11053 {
    private static int n;
    private static int[] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = sc.nextInt();
        System.out.println(go(0));
    }

    // idx 위치에서 시작해서 만들 수 있는 가장 긴 증가수열
    // arr[idx] < arr[idx+1] 이면 go(idx+1) + 1
    // 그렇지 않으면 go(idx+1)
    private static int go(int idx){
        if(idx == n-1) return 1;
        int res = 1;
        for(int i = idx+1; i < n; i++){
            if(arr[idx] < arr[i]) {
                res = Math.max(1,go(i) + 1);
            }
        }
        return res;
    }

}
