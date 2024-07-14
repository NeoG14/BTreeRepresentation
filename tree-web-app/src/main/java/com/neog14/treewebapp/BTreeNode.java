package com.neog14.treewebapp;

import java.util.Arrays;

public class BTreeNode {
    private boolean leaf;
    private Integer[] keys;
    private BTreeNode[] children;
    private int num_node;
    private int num_keys;
    private BTreeNode parent;

    public BTreeNode(boolean leaf, int order, int num_node, BTreeNode parent){
        this.leaf = leaf;
        this.keys = new Integer[order-1];
        this.children = new BTreeNode[order];
        this.num_keys = 0;
        this.num_node = num_node;
        this.parent = parent;
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
                while (i<keys.length && keys[i] != null && key > keys[i]){
                    i++;
                }
                return children[i].search_to_insert(key);
            }
        }
    }

    public void print(){
        System.out.println("Node: "+num_node+" Keys: "+Arrays.toString(keys));
        System.out.println("Node: "+num_node+" NumKeys: "+ num_keys);
        System.out.println("Node: "+num_node+" childrens: "+ printChildrens());
        System.out.println("###################################################");
        System.out.println();
        //System.out.println("Node: "+num_node+" Childrens: "+Arrays.toString(children));
        for (BTreeNode c : children){
            if(c!=null){
                c.print();
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

    public String printChildrens() {
        String childString = "";
        for (BTreeNode c : children){
            if(c != null)
                childString +=  " " + c.getNum_node();
        }
        return childString;
    }

    public void insert_non_full(Integer key){
        int i = 0;
        while(keys[i] != null && key>keys[i]){
            i++;
        }
        for (int j=num_keys; j>i; j--)
            keys[j] = keys[j-1];
        keys[i]=key;
        num_keys++;
        System.out.println("Key " + key +" inserted in node: "+num_node);
    }


    public int split_node(BTreeNode right_node, int key,int order){
        Integer[] split = Arrays.copyOf(this.getKeys(),order);
        this.num_keys=0;
        right_node.num_keys=0;
        Integer[] left_array = new Integer[order-1];
        Integer[] right_array = new Integer[order-1];
        int promoted;
        split[order-1]=key;
        split = Arrays.stream(split)
                .sorted().
                toArray(Integer[]::new);
        int i=0;

        while(i<split.length/2) {
            left_array[i] = split[i];
            i++;
            this.num_keys++;
        }
        promoted = split[i];
        i++;
        int j=0;
        while (i<split.length){
            right_array[j]=split[i];
            i++;
            j++;
            right_node.num_keys++;
        }
        this.setKeys(left_array);
        right_node.setKeys(right_array);
        return promoted;
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

    public BTreeNode getParent() {
        return parent;
    }

    public void setParent(BTreeNode parent) {
        this.parent = parent;
    }
}
