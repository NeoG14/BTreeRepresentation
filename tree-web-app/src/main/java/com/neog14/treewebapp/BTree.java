package com.neog14.treewebapp;

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
        if (root.leaf){ //primer nodo
            if (root.num_keys<max_keys) {//si tengo espacio en al raiz
                root.insert_non_full(key);
            } else {//raiz llena
                    BTreeNode new_node = new BTreeNode(true,order,num_nodes);
                    num_nodes++;
                    BTreeNode new_root = new BTreeNode(false,order,num_nodes);
                    num_nodes++;
                    root.split_child(new_node,key);

                    new_root.children[0] = root;
                    new_root.children[1] = new_node;
                    root=new_root;

                }
            }
        }



//        if (root == null) {
//            root = new BTreeNode(true, order,0);
//            root.keys[0] = key;
//            root.num_keys = 1;
//        } else {
//            if (root.num_keys == root.max_keys) {
//                BTreeNode s = new BTreeNode(false, order,root.num_node+1);
//                s.children[0] = root;
//                s.split_child(0, root);
//                int i = 0;
//                if (s.keys[0] < key) {
//                    i++;
//                }
//                s.children[i].insert_non_full(key);
//                root = s;
//            } else {
//                root.insert_non_full(key);
//            }
//        }
    }


}
