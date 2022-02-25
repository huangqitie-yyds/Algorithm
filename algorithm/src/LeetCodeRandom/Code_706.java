package LeetCodeRandom;

import java.util.Iterator;
import java.util.LinkedList;

public class Code_706 {
    //题目链接:https://leetcode-cn.com/problems/design-hashmap/
    //使用linkedlist来替代链表操作
    //链表存储key-value键值对
    public int BASE = 769;
    LinkedList[] myMap;

    public class Node {
        int key;
        int value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public Code_706() {
        myMap = new LinkedList[BASE];
        for (int i = 0; i < BASE; i++) {
            myMap[i] = new LinkedList<Node>();
        }
    }

    public void put(int key, int value) {
        int h = hash(key);
        Iterator<Node> iterator = myMap[h].iterator();
        while (iterator.hasNext()) {
            Node next = iterator.next();
            if (next.getKey() == key) {
                next.setValue(value);
                return;
            }
        }
        myMap[h].add(new Node(key, value));
    }

    public void remove(int key) {
        int h = hash(key);
        Iterator<Node> iterator = myMap[h].iterator();
        while (iterator.hasNext()) {
            Node next = iterator.next();
            if (next.getKey() == key) iterator.remove();
        }
        return;
    }

    public int get(int key) {
        int h = hash(key);
        Iterator<Node> iterator = myMap[h].iterator();
        while (iterator.hasNext()) {
            Node next = iterator.next();
            if (next.getKey() == key) {
                return next.getValue();
            }
        }
        return -1;
    }

    public int hash(int key) {
        return key % BASE;
    }
}
