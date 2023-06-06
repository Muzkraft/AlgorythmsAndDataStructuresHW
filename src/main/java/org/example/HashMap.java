package org.example;

import java.util.ArrayList;

public class HashMap<K, V> {

    /* Класс узла связного списка . Используется только в хэш-таблице,
     * реализуется в виде двусвязного списка . */
    private static class LinkedListNode<K, V> {
        public LinkedListNode<K, V> next;
        public LinkedListNode<K, V> prev;
        public K key;
        public V value;
        public LinkedListNode(K k, V v) {
            key = k;
            value = v;
        }

        public String printForward() {
            String data = "(" + key + "," + value + ")";
            if (next != null) {
                return data + "->" + next.printForward();
            } else {
                return data;
            }
        }
    }

    private final ArrayList<LinkedListNode<K, V>> arr;
    public HashMap(int capacity) {
        /* Создание списка связных списков . Список заполняется значениями
         * пull (единственный способ создания массива заданного размера ). */
        arr = new ArrayList<>();
        arr.ensureCapacity(capacity);
        for (int i = 0; i < capacity; i++) {
            arr.add(null);
        }
    }
    /* Проверка на наличие заданного ключа в ноде*/
    public boolean containsKey(K key){
        LinkedListNode<K, V> node = getNodeForKey(key);
        if (node != null) {
            return node.key == key;
        }
        return false;
    }

    /* Проверка на наличие заданного значения в ноде*/
    public boolean containsValue(V value){
        for (LinkedListNode<K, V> node: arr) {
            if (node != null) {
                while (node.next != null) {
                    if (node.value == value) {
                        return true;
                    }
                    node = node.next;
                }
            }
        }
        return false;
    }

    /* Вставка ключа и значения в хэш-таблицу . */
    public void put(K key, V value) {
        LinkedListNode<K, V> node = getNodeForKey(key);
        if (node != null) {
            node.value = value; // Просто обновить значение.
        }

        node = new LinkedListNode<>(key, value);
        int index = getIndexForKey(key);
        if (arr.get(index) != null) {
            node.next = arr.get(index);
            node.next.prev = node;
        }
        arr.set(index, node);
    }

    /* Замена значения в таблице */
    public Object replace(K key, V value) {
        int index = getIndexForKey(key);
        if (arr.get(index) != null) {
            LinkedListNode<K, V> tempNode = arr.get(index);
            while (tempNode != null) {
                if (tempNode.key == key) {
                    tempNode.value = value;
                    return value;
                }
                tempNode = tempNode.next;
            }
        } return null;
    }

    /* Удаление ноды по ключу . */
    public V remove(K key) {
        LinkedListNode<K, V> node = getNodeForKey(key);
        if (node == null) {
            return null;
        }

        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            /* Удаление начальной ноды - обновление. */
            int hashKey = getIndexForKey(key);
            arr.set(hashKey, node.next);
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        }
        return node.value;
    }

    /* Получение значения для ключа . */
    public V get(K key) {
        if (key == null) return null;
        LinkedListNode<K, V> node = getNodeForKey(key);
        return node == null ? null : node.value;
    }

    /* Получение ноды связного списка для заданного ключа . */
    private LinkedListNode<K, V> getNodeForKey(K key) {
        int index = getIndexForKey(key);
        LinkedListNode<K, V> current = arr.get(index);
        while (current != null) {
            if (current.key == key) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    /* Функция для связывания ключа с индексом . */
    public int getIndexForKey(K key) {
        return Math.abs(key.hashCode() % arr.size());
//        return key.hashCode()%16;
    }

    public void printTable() {
        for (int i = 0; i < arr.size(); i++) {
            String s = arr.get(i) == null ? "" : arr.get(i).printForward();
            System.out.println(i + ": " + s);
        }
    }

    /* Количество элементов (ключ, значение) */
    public int size() {
        int size = 0;

        for (LinkedListNode<K, V> tempNode : arr) {
            while (tempNode != null) {
                size++;
                tempNode = tempNode.next;
            }
        }
        return size;
    }
}
