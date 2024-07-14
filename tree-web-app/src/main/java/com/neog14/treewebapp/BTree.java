package com.neog14.treewebapp;

import java.lang.reflect.Array;
import java.util.Arrays;

public class BTree {
    int order;
    BTreeNode root;
    int t;//minimun grade of intermediate nodes
    int max_keys;
    int min_keys;//min keys of a leaf
    int num_nodes;

    public BTree(int order){
        this.order = order;
        this.root = new BTreeNode(true,order,0);
        this.t = (order/2);
        this.max_keys = order-1;
        this.min_keys = t-1;
        num_nodes = 1;
    }

    public void traverse(){
        if (root != null){
            root.traverse(0);
        }
    }



    public void insert(int key) {
        BTreeNode node_to_insert = root.search_to_insert(key);
        if(node_to_insert != null && !node_to_insert.full(max_keys)){
            node_to_insert.insert_non_full(key);
        }else {
            if(node_to_insert != null && node_to_insert.full(max_keys)){
                BTreeNode new_node = new BTreeNode(true;order,num_nodes);
                num_nodes++;
                int promoted = split_node(node_to_insert,new_node,key);
            }
        }

    }

    public int split_node(BTreeNode left_node, BTreeNode right_node, int key){
        Integer[] split = Arrays.copyOf(left_node.getKeys(),order);
        Integer[] left_array = new Integer[max_keys];
        Integer[] right_array = new Integer[max_keys];
        int promoted;
        split[order-1]=key;
        split = Arrays.stream(split)
                .sorted().
                toArray(Integer[]::new);
        int i=0;
        if((split.length % 2) == 0){

        }else {
            while(i<split.length/2){
                left_array[i]=split[i];
                i++;
            }
            promoted = split[i];
            i++;
            while (i<split.length){
                right_array[i]=split[i];
                i++;
            }

        }
        return 4;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public BTreeNode getRoot() {
        return root;
    }

    public void setRoot(BTreeNode root) {
        this.root = root;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public int getMax_keys() {
        return max_keys;
    }

    public void setMax_keys(int max_keys) {
        this.max_keys = max_keys;
    }

    public int getMin_keys() {
        return min_keys;
    }

    public void setMin_keys(int min_keys) {
        this.min_keys = min_keys;
    }

    public int getNum_nodes() {
        return num_nodes;
    }

    public void setNum_nodes(int num_nodes) {
        this.num_nodes = num_nodes;
    }
}
