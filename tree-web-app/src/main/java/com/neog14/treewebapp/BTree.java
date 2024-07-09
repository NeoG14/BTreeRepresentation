package com.neog14.treewebapp;

public class BTree {
    int order;
    BTreeNode root;
    int t;//minimun grade

    public BTree(int order){
        this.order = order;
        this.root = new BTreeNode(true,order);
        this.t = (order/2) - 1;
    }

    public void insert(int key) {
        if (root == null) {
            root = new BTreeNode(true, order);
            root.keys[0] = key;
            root.num_keys = 1;
        } else {
            if (root.num_keys == 2 * t - 1) {
                BTreeNode s = new BTreeNode(false, order);
                s.children[0] = root;
                s.split_child(0, root);
                int i = 0;
                if (s.keys[0] < key) {
                    i++;
                }
                s.children[i].insert_non_full(key);
                root = s;
            } else {
                root.insert_non_full(key);
            }
        }
    }


}
