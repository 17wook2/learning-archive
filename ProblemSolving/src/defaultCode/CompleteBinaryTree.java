package defaultCode;

import java.util.ArrayDeque;
import java.util.Queue;

public class CompleteBinaryTree<T> {

    private Object[] nodes;
    private int lastIndex; // 채워진 마지막 노드의 인덱스
    private final int SIZE; // 최대 노드의 개수

    public CompleteBinaryTree(int size) {
        this.SIZE = size;
        nodes = new Object[size+1];
    }


    public boolean isFull() {
        return lastIndex == SIZE;
    }

    public boolean isEmpty() {
        return lastIndex == 0;
    }
    public boolean add(T data) {
        if(isFull()) return false;
        nodes[++lastIndex] = data;
        return true;
    }
    public void bfs() {
        if(isEmpty()) return;
        // 탐색 순서를 관리할 대기열 자료구조 생성
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1); // 루트 노드의 인덱스

        while(!queue.isEmpty()) {
            int current = queue.poll(); // 탐색대상 큐에서 꺼내기
            System.out.println(nodes[current]);
            // 현재 탐색대상을 통해 탐색해야 할 새로운 대상을 큐에 넣기
            if(current * 2 <= lastIndex) queue.offer(current * 2);
            if(current * 2 + 1 <= lastIndex) queue.offer(current * 2 + 1);

        }
    }
    public void bfs2() {
        if(isEmpty()) return;
        // 탐색 순서를 관리할 대기열 자료구조 생성
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1); // 루트 노드의 인덱스

        int breath = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(--size >= 0) {
                int current = queue.poll(); // 탐색대상 큐에서 꺼내기
                System.out.print(nodes[current]+ "\t");
                // 현재 탐색대상을 통해 탐색해야 할 새로운 대상을 큐에 넣기
                if(current * 2 <= lastIndex) queue.offer(current * 2);
                if(current * 2 + 1 <= lastIndex) queue.offer(current * 2 + 1);
            }
            System.out.println();
            System.out.println("=========" + breath + "너비 탐색 완료");
            breath++;
        }
    }
    public void bfs3() {
        if(isEmpty()) return;
        // 탐색 순서를 관리할 대기열 자료구조 생성
        Queue<int[]> queue = new ArrayDeque<>(); // int[] : {탐색노드의 인덱스, 너비}
        queue.offer(new int[] {1,0}); // 루트 노드의 인덱스

        while(!queue.isEmpty()) {
            int[] info = queue.poll();
            int current = info[0]; // 탐색대상 큐에서 꺼내기
            System.out.println(nodes[current] + "//" + info[1]);
            // 현재 탐색대상을 통해 탐색해야 할 새로운 대상을 큐에 넣기
            if(current * 2 <= lastIndex) queue.offer(new int[] {current * 2, info[1] + 1});
            if(current * 2 + 1 <= lastIndex) queue.offer(new int[] {current * 2 + 1, info[1] + 1});
        }
    }

    public void dfsByPreOrder(int current) {
        System.out.println(nodes[current]);

        if(current * 2 <= lastIndex) dfsByPreOrder(current*2);
        if(current*2 + 1 <= lastIndex) dfsByPreOrder(current*2 + 1);

    }
    public void dfsByInOrder(int current) {
        if(current * 2 <= lastIndex) dfsByInOrder(current*2);
        System.out.println(nodes[current]);
        if(current*2 + 1 <= lastIndex) dfsByInOrder(current*2 + 1);
    }
    public void dfsByPostOrder(int current) {
        if(current * 2 <= lastIndex) dfsByPostOrder(current*2);
        if(current*2 + 1 <= lastIndex) dfsByPostOrder(current*2 + 1);
        System.out.println(nodes[current]);
    }


}
