package com.android.newgeneration.dandisnap.Main;

import android.app.Fragment;

/**
 * Created by ???? on 2015-07-17.
 */

class Node {
    Fragment fragment;
    Node next;

    public Node() {
        super();
    }

    public Node(Fragment fragment, Node next) {
        super();
        this.fragment = fragment;
        this.next = next;
    }
}


public class LinkedListFragment {
    Node node;
    Node first;

    public LinkedListFragment() {
        super();
        first = null;
    }

    public void add(Fragment fragment) {
        if (first == null) {
            first = new Node(fragment, null);
            node = first;
        } else {
            while (node.next != null) {
                node = node.next;
            }
            Node newNode = new Node(fragment, null);
            node.next = newNode;
        }// if-else
    }

    public void delete(Fragment fragment) {
        Node pre, temp;
        if (first == null) return;
        if (first.next == null && first.fragment == fragment) {
            first = null;
        } else {
            pre = first;
            temp = first.next;
            while (pre.next != null) {
                if (pre.fragment == fragment) {
                    first = temp;
                    break;
                } else if (temp.fragment == fragment) {
                    pre.next = temp.next;
                    break;
                } else {
                    pre = temp;
                    temp = temp.next;
                }
            }
        }
    }

    public void deleteLastNode() {
        Node pre, temp;
        if (first == null) return;
        if (first.next == null) {
            first = null;
        } else {
            pre = first;
            temp = first.next;
            while (temp.next != null) {
                pre = temp;
                temp = temp.next;
            }
            pre.next = null;
        }
    }

    public Fragment Lastfragment() {
        if (first == null) {
            return null;
        } else {
            node = first;
            while (node.next != null) {
                node = node.next;
            }
            return node.fragment;
        }
    }

    //??????¸? ??????? ???? ???
    public void printAll() {
        node = first;
        System.out.println("*print start*");
        while (node != null) {
            System.out.print(node.fragment);
            System.out.print(" ");
            if (node.next == null) {
                break;
            } else {
                node = node.next;
            }
        }
        System.out.println("\n*print end*");
    }
}
