package com.isaura.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LinkedList {
    //utility function to insert node at the end of the list
    static Node insertNode(Node head, String val) {
        Node newNode = new Node(val);
        if (head == null) {
            head = newNode;
            return head;
        }
        Node temp = head;
        while (temp.next != null) temp = temp.next;

        temp.next = newNode;
        return head;
    }
    //utility function to rotate list by k times
    static Node rotateRight(Node head, int k) {
        if (head == null || head.next == null) return head;
        for (int i = 0; i < k; i++) {
            Node temp = head;
            while (temp.next.next != null) temp = temp.next;
            Node end = temp.next;
            temp.next = null;
            end.next = head;
            head = end;
        }
        return head;
    }
    //utility function to print list
    static String printList(Node head) {
        String print = "";
        while (head.next != null) {
            print += head.num + "->";
            head = head.next;
        }

        //System.out.println(head.num.getName());
        print += head.num;
        return print;
    }

    public static List<String> schecule(List<String> memberList) {

        Node head = null;
        List<Member> membersDistrubuted = new ArrayList<>();

        System.out.println("Array size: " + memberList.size());
        //inserting Node
        for (int i=0; i<memberList.size(); i++){
            head = insertNode(head, memberList.get(i));
        }

        System.out.println(printList(head));

        int k = 1;
        Node newHead = rotateRight(head, k); //calling function for rotating
        System.out.println("After " + k + " iterations: ");

        List<String> membersEmails = Arrays.asList(printList(newHead).split("->", memberList.size()));

        return membersEmails; //list after rotating nodes

    }
}
