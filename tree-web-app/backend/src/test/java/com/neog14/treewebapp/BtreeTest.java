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
        int enteros[] = {23,56,12,4,8,9,22,65,98,45,15,48,94,3,64,58,68,689,65,9489,2215,645,2162,21,547,44,55,31,54,89,47,67,52,53,90,92,87,88};
        for(int n : enteros){
            bTreeOddOrder.insert(n);
        }

        bTreeOddOrder.print_tree();
    }
}