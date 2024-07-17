package com.neog14.treewebapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BtreeTest {
    private BTree bTreeOddOrder;

    @BeforeEach
    public void setUp(){
        bTreeOddOrder = new BTree(3);//I reference order as num of pointer of the tree if order=3 then keys on every node are 2  M=Order min_key_per_node= [M/2]-1
    }

    @Test
    public void testInsert(){
        bTreeOddOrder.insert(5);
        bTreeOddOrder.insert(90);
        bTreeOddOrder.insert(13);
        bTreeOddOrder.insert(29);
        bTreeOddOrder.insert(44);
        bTreeOddOrder.insert(26);
        bTreeOddOrder.insert(123);
        bTreeOddOrder.insert(1);
        bTreeOddOrder.insert(33);
        bTreeOddOrder.insert(17);
        bTreeOddOrder.insert(38);
        bTreeOddOrder.insert(-6);
        bTreeOddOrder.insert(-2);
        bTreeOddOrder.insert(3);
        bTreeOddOrder.insert(500);
        bTreeOddOrder.insert(450);
        bTreeOddOrder.insert(76);
        bTreeOddOrder.insert(2);
        bTreeOddOrder.insert(37);
        bTreeOddOrder.insert(700);
        bTreeOddOrder.insert(750);
        bTreeOddOrder.insert(600);
        bTreeOddOrder.insert(800);
        bTreeOddOrder.insert(900);
        bTreeOddOrder.insert(920);
        bTreeOddOrder.insert(850);


        bTreeOddOrder.print_tree();


    }
}
