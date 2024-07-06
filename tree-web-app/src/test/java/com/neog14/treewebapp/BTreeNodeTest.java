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
        nodeEvenOrder = new BTreeNode(4);
        nodeOddOrder = new BTreeNode(5);
    }

    //@Test // change method to public for testing
//    public void testInsertKey(){
//        assertEquals(0,nodeEvenOrder.getNunKeys());
//        assertEquals(3,nodeEvenOrder.getMaxKeys());
//
//        assertTrue(nodeEvenOrder.insertKey(7));
//        assertEquals(7,nodeEvenOrder.getKeys()[0]);
//        assertEquals(1,nodeEvenOrder.getNunKeys());
//
//        nodeEvenOrder.insertKey(3);
//        assertEquals(3,nodeEvenOrder.getKeys()[0]);
//        assertEquals(7,nodeEvenOrder.getKeys()[1]);
//        assertEquals(2,nodeEvenOrder.getNunKeys());
//
//        nodeEvenOrder.insertKey(5);
//        assertEquals(3,nodeEvenOrder.getKeys()[0]);
//        assertEquals(5,nodeEvenOrder.getKeys()[1]);
//        assertEquals(7,nodeEvenOrder.getKeys()[2]);
//        assertEquals(3,nodeEvenOrder.getNunKeys());
//
//        assertFalse(nodeEvenOrder.insertKey(9));//no more space
//        assertEquals(3,nodeEvenOrder.getKeys()[0]);
//        assertEquals(5,nodeEvenOrder.getKeys()[1]);
//        assertEquals(7,nodeEvenOrder.getKeys()[2]);
//        assertEquals(3,nodeEvenOrder.getNunKeys());
//    }

    @Test
    public void testDivideArray(){

    }
}
