package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution1228 {

    static StringBuilder sb = new StringBuilder();
    static List<String> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int tc = 1; tc <= 10; tc++) {
            int n = Integer.parseInt(br.readLine());
            list = Arrays.stream(br.readLine().split(" ")).collect(Collectors.toList());
            int num = Integer.parseInt(br.readLine());
            String[] commands = br.readLine().split("I");
            for(int i = 1; i <= num; i++){
                commands[i] = commands[i].trim();
                String[] splited = commands[i].split(" ");
                int x = Integer.parseInt(splited[0]);
                int y = Integer.parseInt(splited[1]);
                for (int j = 0; j < y; j++) {
                    list.add(x+j, splited[2+j]);
                }
            }
            sb.append("#"+tc+" ");
            for(int i = 0; i < 10; i++){
                sb.append(list.get(i) + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
