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


}

