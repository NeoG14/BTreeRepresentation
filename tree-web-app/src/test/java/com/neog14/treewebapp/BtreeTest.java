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


    }
}
