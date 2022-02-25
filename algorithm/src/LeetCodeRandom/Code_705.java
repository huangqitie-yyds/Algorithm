package LeetCodeRandom;

import java.util.Iterator;
import java.util.LinkedList;

public class Code_705 {
    //题目链接:https://leetcode-cn.com/problems/design-hashset/
    //使用linkedlist来替代链表操作
    public int BASE = 769;
    LinkedList[] myMap;

    public Code_705() {
        myMap = new LinkedList[BASE];
        for (int i = 0; i < BASE; i++) {
            myMap[i] = new LinkedList<Integer>();
        }
    }

    public void add(int key) {
        int h=hash(key);
        Iterator<Integer> iterator = myMap[h].iterator();
        while (iterator.hasNext()){
            Integer next = iterator.next();
            if(next==key) return;
        }
        myMap[h].add(key);
    }

    public void remove(int key) {
        int h=hash(key);
        Iterator<Integer> iterator = myMap[h].iterator();
        while (iterator.hasNext()){
            Integer next = iterator.next();
            if(next==key) iterator.remove();
        }

        return;
    }

    public boolean contains(int key) {
        int h=hash(key);
        Iterator<Integer> iterator = myMap[h].iterator();
        while (iterator.hasNext()){
            Integer next = iterator.next();
            if(next==key) return true;
        }
        return false;
    }

    public int hash(int key) {
        return key % BASE;
    }
}
