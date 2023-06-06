package org.example;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        HashMap<Object, Object> testTable = new HashMap<>(6);

        for (int i = 0; i < 16; i++) {
            testTable.put(new Random().nextInt(16), new Random().nextInt(10));
//            testTable.put(i + 1, i);
        }
        testTable.printTable();

        System.out.println(testTable.get(5));
        System.out.println(testTable.get(35));

        testTable.remove(5);
        testTable.printTable();
        System.out.println();
        testTable.put(5, 1000);
        testTable.replace(5, 2000);
        testTable.printTable();

        System.out.println();
        System.out.println(testTable.size());
        System.out.println(testTable.get(5));
        System.out.println(testTable.containsKey(4));
        System.out.println(testTable.containsValue(7));
    }
}