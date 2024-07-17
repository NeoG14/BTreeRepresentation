package com.neog14.treewebapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;

public class BTreeNodeTest {
    private BTreeNode nodeEvenOrder;
    private BTreeNode nodeOddOrder;

    @BeforeEach
    public void setUp(){
        //nodeEvenOrder = new BTreeNode(4);
        nodeOddOrder = new BTreeNode(true,5,0,null);
        nodeEvenOrder = new BTreeNode(true,6,0,null);
    }

//    @Test
//    public void  test_insert_not_fullOdd(){
//        nodeOddOrder.insert_and_sort(4);
//        nodeOddOrder.insert_and_sort(1);
//        nodeOddOrder.insert_and_sort(12);
//        nodeOddOrder.insert_and_sort(9);
//
//        BTreeNode right_node = new BTreeNode(true,5,1);
//
//        System.out.println(Arrays.toString(nodeOddOrder.getKeys()));
//
//        int promoted = nodeOddOrder.split_node(right_node,15,5);
//
//        System.out.println("############################");
//
//        System.out.println("Nodo 0: "+Arrays.toString(nodeOddOrder.getKeys()));
//        System.out.println("Nodo 1: "+Arrays.toString(right_node.getKeys()));
//        System.out.println("promoted: "+promoted);
//    }

//    @Test
//    public void  test_insert_not_fullEven(){
//        nodeEvenOrder.insert_and_sort(13);
//        nodeEvenOrder.insert_and_sort(4);
//        nodeEvenOrder.insert_and_sort(1);
//        nodeEvenOrder.insert_and_sort(12);
//        nodeEvenOrder.insert_and_sort(9);
//
//        BTreeNode right_node = new BTreeNode(true,6,1);
//
//        System.out.println(Arrays.toString(nodeEvenOrder.getKeys()));
//
//        int promoted = nodeEvenOrder.split_node(right_node,15,6);
//
//        System.out.println("############################");
//
//        System.out.println("Nodo 0: "+Arrays.toString(nodeEvenOrder.getKeys()));
//        System.out.println("Nodo 1: "+Arrays.toString(right_node.getKeys()));
//        System.out.println("promoted: "+promoted);
//    }

//    @Test
//    public void test_search_to_insert(){
//        BTreeNode node0 = new BTreeNode(true,4,0);
//        node0.insert_and_sort(4);
//        node0.insert_and_sort(5);
//
//        BTreeNode node1 = new BTreeNode(true,4,1);
//        node1.insert_and_sort(14);
//        node1.insert_and_sort(15);
//
//        BTreeNode node3 = new BTreeNode(true,4,3);
//        node3.insert_and_sort(30);
//        node3.insert_and_sort(35);
//
//        BTreeNode node4 = new BTreeNode(true,4,4);
//        node4.insert_and_sort(79);
//        node4.insert_and_sort(87);
//
//
//        BTreeNode root_node = new BTreeNode(false,4,2);
//        root_node.insert_and_sort(10);
//        root_node.insert_and_sort(20);
//        root_node.insert_and_sort(50);
//        root_node.getChildren()[0] = node0;
//        root_node.getChildren()[1] = node1;
//        root_node.getChildren()[2] = node3;
//        root_node.getChildren()[3] = node4;
//
//        System.out.println(root_node.search_to_insert(99).getNum_node());
//
//    }

}

