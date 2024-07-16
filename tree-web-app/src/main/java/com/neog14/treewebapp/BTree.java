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
        this.root = new BTreeNode(true,order,0,null);
        this.t = (order/2);
        this.max_keys = order-1;
        this.min_keys = t-1;
        num_nodes = 1;
    }

    public void print_tree(){
        if (root != null){
            root.print();
        }
    }

    public void insert_on_root(int promoted, BTreeNode node_to_insert, BTreeNode new_node){
        BTreeNode new_root = new BTreeNode(false,order,num_nodes,null);//creo la nueva raiz
        root = new_root;//seteo la nueva raiz
        num_nodes++;
        new_root.insert_non_full(promoted);//agrego el elemento promocionado a la nueva raiz
       // node_to_insert.setLeaf(true);//la vieja raiz pasa a ser hoja
        //hago los enlaces en la nueva raiz y seteo al padre
        node_to_insert.setParent(new_root);
        new_node.setParent(new_root);
        new_root.getChildren()[0]=node_to_insert;
        new_root.getChildren()[1]=new_node;

    }

    private void realocate_parent_childs(BTreeNode parent, BTreeNode new_parent, BTreeNode node_to_insert, BTreeNode new_node){
        BTreeNode[] child_to_split = new BTreeNode[order+1];
        BTreeNode[] childs_parent = new BTreeNode[order];
        BTreeNode[] childs_new_parent = new BTreeNode[order];
        int i = 0;
        while (node_to_insert.getNum_node() != parent.getChildren()[i].getNum_node()){
            i++;
        }
        int j = 0;
        while(j<child_to_split.length){
            if(j<=i)
                child_to_split[j]=parent.getChildren()[j];
            else if (j==i+1)
                child_to_split[j]=new_node;
            else
                child_to_split[j]=parent.getChildren()[j-1];
            j++;
        }

        i=0;
        j=0;
        while(i<child_to_split.length){

            if(i<child_to_split.length/2){
                childs_parent[i]=child_to_split[i];
            }else {
                if(i==child_to_split.length/2 && child_to_split.length%2==0){
                    childs_new_parent[j]=child_to_split[i];
                    j++;
                } else if (i==child_to_split.length/2 && child_to_split.length%2!=0) {
                    childs_parent[i]=child_to_split[i];
                }else {
                    childs_new_parent[j]=child_to_split[i];
                    j++;
                }
            }
            i++;
        }
        parent.setChildren(childs_parent);
        new_parent.setChildren(childs_new_parent);


//        for (int j=0; j<=i; j++){
//            child_to_split[j]=parent.getChildren()[j];
//        }


//        BTreeNode[] parent_child_copy = parent.getChildren().clone();
//        BTreeNode[] parent_childs = new BTreeNode[order];
//        for (int j=0;j<i;j++){
//            parent_childs[j]=parent_child_copy[j];
//        }
//        if(parent_child_copy[i].getKeys()[0]>new_node.getKeys()[0]){
//            if(parent.getKeys()[i-1]<new_node.getKeys()[0]){
//                parent_childs[i]=new_node;
//            }
//        }
//
//        for (int j=i;j<parent_child_copy.length;j++){
//            new_parent.getChildren()[j]=parent_child_copy[j];
//        }
//        parent.setChildren(parent_childs);
    }

    public void insert_on_parent(int key, BTreeNode node_to_insert, BTreeNode new_node){
        BTreeNode parent = node_to_insert.getParent();
        BTreeNode aux = null;
        if (parent == root){
            if (!parent.full(max_keys)){
                parent.insert_non_full(key);
                int i = 0;
                while(i<parent.getNum_keys()){
                    if (parent.getKeys()[i]>new_node.getKeys()[0]){
                        aux = parent.getChildren()[i];
                        parent.getChildren()[i] = new_node;
                        new_node.setParent(parent);
                    }
                    i++;
                }
                if(aux!=null){
                    parent.getChildren()[i]=aux;
                    aux.setParent(parent);
                } else{
                    parent.getChildren()[i]=new_node;
                    new_node.setParent(parent);
                }

            }else {
                BTreeNode new_parent = new BTreeNode(false,order,num_nodes,null);
                num_nodes++;
                int promoted= parent.split_node(new_parent,key,order);
                realocate_parent_childs(parent,new_parent,node_to_insert,new_node);
                insert_on_root(promoted,parent,new_parent);
                }

                //insert_on_root(promoted,parent,new_parent);
        }

    }


    public void insert(int key) {
        BTreeNode node_to_insert = root.search_to_insert(key);
        System.out.println("Key: "+key+"node to insert: "+node_to_insert.getNum_node()+"leaf: "+node_to_insert.isLeaf());
        if (node_to_insert != null) {
            if(!node_to_insert.full(max_keys)){ //no esta lleno
                node_to_insert.insert_non_full(key);
            }else {
                BTreeNode new_node = new BTreeNode(true,order,num_nodes,null);
                num_nodes++;
                int promoted = node_to_insert.split_node(new_node,key,order);
                if (node_to_insert == root){// si el nodo que se dividio era la raiz
                    insert_on_root(promoted,node_to_insert,new_node);
                }else {
                    insert_on_parent(promoted,node_to_insert,new_node);
                }
            }
        }
    }


//    public int split_node(BTreeNode left_node, BTreeNode right_node, int key){
//        Integer[] split = Arrays.copyOf(left_node.getKeys(),order);
//        Integer[] left_array = new Integer[max_keys];
//        Integer[] right_array = new Integer[max_keys];
//        int promoted;
//        split[order-1]=key;
//        split = Arrays.stream(split)
//                .sorted().
//                toArray(Integer[]::new);
//        int i=0;
//
//        while(i<split.length/2) {
//            left_array[i] = split[i];
//            i++;
//        }
//        promoted = split[i];
//        i++;
//        int j=0;
//        while (i<split.length){
//            right_array[j]=split[i];
//            i++;
//            j++;
//        }
//        left_node.setKeys(left_array);
//        right_node.setKeys(right_array);
//        return promoted;
//    }

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

