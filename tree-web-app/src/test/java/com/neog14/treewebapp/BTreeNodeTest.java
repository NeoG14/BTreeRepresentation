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

//    @Test
//    public void testDivideArrayEvenOrder(){
//        nodeEvenOrder.insert(1);
//        nodeEvenOrder.insert(4);
//        nodeEvenOrder.insert(9);
//
//        BTreeNode actualNode = nodeEvenOrder;
//        BTreeNode newNode = new BTreeNode(nodeEvenOrder.getOrder(),true,nodeEvenOrder.getRoot());
//
//        int[] splittedArray = Arrays.copyOf(actualNode.getKeys(),nodeEvenOrder.getOrder());//creo el arreglo con +1 de espacio
//        // estado actual del arreglo de claves (lleno) 1,4,9
//        nodeEvenOrder.insertAndKeepSorted(splittedArray,5); //Supongo que inserto el numero 5
//
//        int promoted = nodeEvenOrder.divideArray(splittedArray,actualNode,newNode);
//        assertEquals(5,promoted);
//
//        assertEquals(1,actualNode.getKeys()[0]);
//        assertEquals(4,actualNode.getKeys()[1]);
//
//        assertEquals(9,newNode.getKeys()[0]);
//    }

//    @Test //En caso de ser de orden impar el nodo de la izquierda queda siemprecon un elemento mas que el nuevo nodo
//    public void testDivideArrayOddOrder(){
//        nodeOddOrder.insert(1);
//        nodeOddOrder.insert(2);
//        nodeOddOrder.insert(7);
//        nodeOddOrder.insert(9);
//
//        BTreeNode actualNode = nodeOddOrder;
//        BTreeNode newNode = new BTreeNode(nodeOddOrder.getOrder(),true,nodeOddOrder.getRoot());
//
//        int[] splittedArray = Arrays.copyOf(actualNode.getKeys(),nodeOddOrder.getOrder());//creo el arreglo con +1 de espacio
//        // estado actual del arreglo de claves (lleno) 1,2,7,9
//        nodeOddOrder.insertAndKeepSorted(splittedArray,5); //Supongo que inserto el numero 5
//
//
//        int promoted = nodeOddOrder.divideArray(splittedArray,actualNode,newNode);
//        assertEquals(5,promoted);
//
//        assertEquals(1,actualNode.getKeys()[0]);
//        assertEquals(2,actualNode.getKeys()[1]);
//
//        assertEquals(7,newNode.getKeys()[0]);
//        assertEquals(9,newNode.getKeys()[1]);
//    }

    @Test
    public void testInsertOnFullRoot(){
        nodeOddOrder.insert(1);
        nodeOddOrder.insert(2);
        nodeOddOrder.insert(7);
        nodeOddOrder.insert(9);

        nodeOddOrder.insert(5);
        nodeOddOrder.printTree();



    }
}
