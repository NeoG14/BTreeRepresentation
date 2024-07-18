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
        new_root.insert_and_sort(promoted);//agrego el elemento promocionado a la nueva raiz
        node_to_insert.setParent(new_root);
        new_node.setParent(new_root);
        new_root.getChildren()[0]=node_to_insert;
        new_root.getChildren()[1]=new_node;

    }

    private BTreeNode[] split_parent_child(BTreeNode parent, BTreeNode right_child, BTreeNode left_child){
        BTreeNode[] child_to_split = new BTreeNode[order+1];
        int i = 0;
        while (right_child.getNum_node() != parent.getChildren()[i].getNum_node()){
            i++;
        }
        int j = 0;
        while(j<child_to_split.length){
            if(j<=i)
                child_to_split[j]=parent.getChildren()[j];
            else if (j==i+1)
                child_to_split[j]=left_child;
            else
                child_to_split[j]=parent.getChildren()[j-1];
            j++;
        }
        return child_to_split;
    }

    private void realocate_children(BTreeNode[] child_to_split, BTreeNode parent, BTreeNode new_parent ){
        BTreeNode[] childs_parent = new BTreeNode[order];
        BTreeNode[] childs_new_parent = new BTreeNode[order];
        int i=0;
        int j=0;
        while(i<child_to_split.length){
            if(i<child_to_split.length/2){
                childs_parent[i]=child_to_split[i];
                child_to_split[i].setParent(parent);
            }else {
                if(i==child_to_split.length/2 && child_to_split.length%2==0){
                    childs_new_parent[j]=child_to_split[i];
                    child_to_split[i].setParent(new_parent);
                    j++;
                } else if (i==child_to_split.length/2 && child_to_split.length%2!=0) {
                    childs_parent[i]=child_to_split[i];
                    child_to_split[i].setParent(parent);
                }else {
                    childs_new_parent[j]=child_to_split[i];
                    child_to_split[i].setParent(new_parent);
                    j++;
                }
            }
            i++;
        }
        parent.setChildren(childs_parent);
        new_parent.setChildren(childs_new_parent);
    }


    // this executes when a parent_node is divided so the pointes has to be realocated
    private void realocate_parent_childs(BTreeNode parent, BTreeNode new_parent, BTreeNode node_to_insert, BTreeNode new_node){
        //BTreeNode[] child_to_split = new BTreeNode[order+1];
        //BTreeNode[] childs_parent = new BTreeNode[order];
        //BTreeNode[] childs_new_parent = new BTreeNode[order];
        BTreeNode[] child_to_split = split_parent_child(parent,node_to_insert,new_node);
        realocate_children(child_to_split,parent,new_parent);
//        int i = 0;
//        while (node_to_insert.getNum_node() != parent.getChildren()[i].getNum_node()){
//            i++;
//        }
//        int j = 0;
//        while(j<child_to_split.length){
//            if(j<=i)
//                child_to_split[j]=parent.getChildren()[j];
//            else if (j==i+1)
//                child_to_split[j]=new_node;
//            else
//                child_to_split[j]=parent.getChildren()[j-1];
//            j++;
//        }
//        i=0;
//        j=0;
//        while(i<child_to_split.length){
//            if(i<child_to_split.length/2){
//                childs_parent[i]=child_to_split[i];
//                child_to_split[i].setParent(parent);
//            }else {
//                if(i==child_to_split.length/2 && child_to_split.length%2==0){
//                    childs_new_parent[j]=child_to_split[i];
//                    child_to_split[i].setParent(new_parent);
//                    j++;
//                } else if (i==child_to_split.length/2 && child_to_split.length%2!=0) {
//                    childs_parent[i]=child_to_split[i];
//                    child_to_split[i].setParent(parent);
//                }else {
//                    childs_new_parent[j]=child_to_split[i];
//                    child_to_split[i].setParent(new_parent);
//                    j++;
//                }
//            }
//            i++;
//        }
//        parent.setChildren(childs_parent);
//        new_parent.setChildren(childs_new_parent);
    }

    // this executes when a parent add a new key but is no divided so the pointer has to be realocated
    public void realocate_pointers(BTreeNode parent, BTreeNode new_node){
        BTreeNode aux = null;
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
    }

    public void insert_on_parent(int key, BTreeNode node_to_insert, BTreeNode new_node){
        BTreeNode parent = node_to_insert.getParent();
        BTreeNode aux = null;
        if(parent.isFull(max_keys)){
            insert_non_terminal(key,parent,node_to_insert,new_node);
        }else{
            parent.insert_and_sort(key);
            realocate_pointers(parent,new_node);
//            int i = 0;
//            while(i<parent.getNum_keys()){
//                if (parent.getKeys()[i]>new_node.getKeys()[0]){
//                    aux = parent.getChildren()[i];
//                    parent.getChildren()[i] = new_node;
//                    new_node.setParent(parent);
//                }
//                i++;
//            }
//            if(aux!=null){
//                parent.getChildren()[i]=aux;
//                aux.setParent(parent);
//            } else{
//                parent.getChildren()[i]=new_node;
//                new_node.setParent(parent);
//            }
        }
    }


    public void insert(int key) {
        //node_to_insert es hoja
        BTreeNode node_to_insert = root.search_to_insert(key);
        //Si el nodo esta lleno -> redistribucion
        if(node_to_insert!= null){ // si es null significa que la key ya esta en el arbol
            if (node_to_insert.isFull(max_keys)){
                insert(key,node_to_insert);
            }else { //sino es lleno se inserta
                node_to_insert.insert_and_sort(key);
            }
        }
    }

    private void insert(int key, BTreeNode node){
        // redistribucion
        BTreeNode new_node = new BTreeNode(true,order,num_nodes,null);
        num_nodes++;
        int promoted = node.split_node(new_node,key,order);
        if (node == root){
            insert_on_root(promoted,node,new_node);
        }else {
            insert_on_parent(promoted,node,new_node);
        }
    }

    private void insert_non_terminal(int key, BTreeNode parent, BTreeNode node_child, BTreeNode new_child){
        BTreeNode new_parent = new BTreeNode(false,order,num_nodes,null);
        num_nodes++;
        int promoted = parent.split_node(new_parent,key,order);
        realocate_parent_childs(parent,new_parent,node_child,new_child);
        if(parent==root)
            insert_on_root(promoted,parent,new_parent);
        else
            insert_on_parent(promoted,parent,new_parent);
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

