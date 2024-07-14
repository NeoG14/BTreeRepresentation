package com.neog14.treewebapp;

import java.util.Arrays;

public class BTreeNode {
    private boolean leaf;
    private Integer[] keys;
    private BTreeNode[] children;
    private int num_node;
    private int num_keys;

    public BTreeNode(boolean leaf, int order, int num_node){
        this.leaf = leaf;
        this.keys = new Integer[order-1];
        this.children = new BTreeNode[order];
        this.num_keys = 0;
        this.num_node = num_node;
    }

    public BTreeNode search_to_insert(Integer key){
        boolean contains = Arrays.stream(keys).anyMatch(key::equals);
        if (leaf && !contains){
            return this;
        } else{
            if(contains)
                return null;
            else {
                int i = 0;
                while (keys[i]!=null && key > keys[i]){
                    i++;
                }
                return children[i].search_to_insert(key);
            }
        }
    }

    public boolean full(int max_keys){
        return (max_keys==num_keys);
    }

    @Override
    public String toString() {
        return "BTreeNode{" + "\n"+
                "leaf=" + leaf + "\n"+
                "keys=" + Arrays.toString(keys) + "\n"+
                "children=" + printChildrens() + "\n"+
                "num_keys=" + num_keys + "\n"+
                '}'+ "\n";
    }

    private String printChildrens() {
        String childString = "";
        for (BTreeNode c : children){
            if(c != null)
                childString +=  " " + num_node;
        }
        return childString;
    }

    public void insert_non_full(Integer key){
        int i = 0;
        while(keys[i] != null && key>keys[i]){
            i++;
        }
        for (int j=num_keys; i>j; j--)
            keys[j] = keys[j-1];
        keys[i]=key;
        num_keys++;
        System.out.println("Key " + key +"inserted in node: "+num_node);
    }


    public void split_child(BTreeNode left_child, int key){
        Integer[] aux = Arrays.copyOf(keys,num_keys+1);//copio de keys en aux con +1 de DimF

        //agrego ordernado el nuevo elemento en aux
        int i=0;
        while(aux[i] != null && key>aux[i]){
            i++;
        }
        for (int j=num_keys; i>j; j--)
            aux[j] = aux[j-1];
        aux[i]=key;

        //Logica para dividir el arreglo y promocionar la clave

    }

    public void traverse(int level) {
//        if(!leaf){
//            for(BTreeNode child : children){
//                if (child != null){
//                    child.traverse(level+1);
//                    System.out.println(this);
//                }
//            }
//        }




        int i;
        for (i = 0; i < num_keys; i++) {
            if (!leaf) {
                children[i].traverse(level+1);
            }
            System.out.print(this);
            System.out.println("###############");
        }

        if (!leaf) {
            children[i].traverse(level+1);
        }

    }


    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public Integer[] getKeys() {
        return keys;
    }

    public void setKeys(Integer[] keys) {
        this.keys = keys;
    }

    public BTreeNode[] getChildren() {
        return children;
    }

    public void setChildren(BTreeNode[] children) {
        this.children = children;
    }

    public int getNum_node() {
        return num_node;
    }

    public void setNum_node(int num_node) {
        this.num_node = num_node;
    }

    public int getNum_keys() {
        return num_keys;
    }

    public void setNum_keys(int num_keys) {
        this.num_keys = num_keys;
    }
}
