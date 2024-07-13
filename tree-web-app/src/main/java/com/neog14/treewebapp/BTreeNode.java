package com.neog14.treewebapp;

import java.util.Arrays;

public class BTreeNode {
    boolean leaf;
    Integer[] keys;
    BTreeNode[] children;
    int num_node;
    int num_keys;

    public BTreeNode(boolean leaf, int order, int num_node){
        this.leaf = leaf;
        this.keys = new Integer[order-1];
        this.children = new BTreeNode[order];
        this.num_keys = 0;
        this.num_node = num_node;

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
        int i=0;
        boolean contains = Arrays.stream(keys).anyMatch(key::equals);
        if (!contains){
            while(keys[i] != null && key>keys[i]){
                i++;
            }
            for (int j=num_keys; i>j; j--)
                keys[j] = keys[j-1];
            keys[i]=key;
            num_keys++;
            System.out.println("Key " + key +"inserted in node: "+num_node);
        }else {
            System.out.println("Key "+ key +"already exist in tree");
        }
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



//
//    //si no se pudo insertar (false) es que hay que crear otro nodo
//    private boolean insertKey(BTreeNode node, int key){
//        if ((this.isRoot || this.isLeaf) && nunKeys<maxKeys) {
//            int i= 0;
//            while(node.getKeys()[i]!=0 && node.getKeys()[i]<key){
//                i++;
//            }
//            this.insertAndKeepSorted(node.getPointers()[i].getKeys(),key);
//            this.nunKeys++;
//            return true;
//        }
//        return false;
//    }
//
//    public void insertAndKeepSorted(int[] array, int key){
//        if(nunKeys>0){
//            int i = nunKeys-1;
//            while(i>=0 && array[i]>key && array[i] != key){
//                array[i+1] = array[i];//realizo un corrimiento de los elementos
//                i--;
//            }
//            array[i+1]=key;
//        }else{
//            array[0] = key; //Inserto el primer espacio
//        }
//    }
//
//    //este metodo crea los arreglos para la redistribucion y los asigna a los nodos correspondientes retorna el elemento a promocionar
//    //En caso de ser de orden impar el nodo de la izquierda queda siempre con un elemento mas que el nuevo nodo
//    private int divideArray(int[] splitArray, BTreeNode firstNode, BTreeNode secondNode){
//        int[] firstArray = new int[maxKeys];
//        int[] secondArray = new int[maxKeys];
//
//        int firstHalf = splitArray.length/2;
//        int secondHalf = splitArray.length;
//
//        //int [] array = firstNode.getKeys();
//        int value=0;
//
//        for (int i=0; i<firstHalf ;i++){
//            firstArray[i] = splitArray[i];
//        }
//
//        int promoted = splitArray[firstHalf];//para evitar tener que hacer un corrimiento nuevamente al realizar la promocion en arbol b+
//
//        for (int i=firstHalf+1; i<secondHalf ;i++){
//            secondArray[value] = splitArray[i];
//            value++;
//        }
//
//        firstNode.setKeys(firstArray);// seteo el arreglo distribuido
//        firstNode.setNunKeys(firstHalf); //seteo el numero de claves que quedan en el primer nodo
//
//        //System.out.println(firstNode);
//
//        secondNode.setKeys(secondArray);// seteo el arreglo distribuido
//        secondNode.setNunKeys((secondHalf-firstHalf)-1);//seteo el numero de claves que quedan en el segundo nodo
//
//        //System.out.println(secondNode);
//        return promoted;
//    }
//
//
//    public void printTree() {
//        String treeString = "";
//        treeString += "RootNode: "+ root.getNumNode()+":";
//        for (int i=0; i<this.maxKeys; i++){
//
//            if(root.getPointers()[i] != null)
//                treeString += root.getPointers()[i].getNumNode();
//
//            treeString += "(" + root.getKeys()[i] + ")";
//        }
//        System.out.println(treeString);
//        int i=0;
//        while(root.getPointers()[i] != null){
//            System.out.print(printNode(root.getPointers()[i]));
//            System.out.print("  ");
//            i++;
//        }
//    }
//
//    private String printNode(BTreeNode node){
//        String nodeString = "Node: " + node.numNode +": ";
//        if (!isLeaf){
//            int i=0;
//            while(this.getPointers()[i] != null){
//                printNode(this.getPointers()[i]);
//                i++;
//            }
//        }else {
//            for (int i=0; i<node.maxKeys; i++){
//                nodeString += "(" + node.getKeys()[i] + ")";
//            }
//        }
//        return nodeString;
//    }
//
//    // no se si funciona
//    private void addPointer(BTreeNode actualNode, BTreeNode newNode) {
//        this.pointers[this.nunKeys-1] = actualNode;
//        this.pointers[this.nunKeys] = newNode;
//        int s = 5;
//    }
//
//
//    public int getOrder() {
//        return order;
//    }
//
//    public void setOrder(int order) {
//        this.order = order;
//    }
//
//    public BTreeNode[] getPointers() {
//        return pointers;
//    }
//
//    public void setPointers(BTreeNode[] pointers) {
//        this.pointers = pointers;
//    }
//
//    public int[] getKeys() {
//        return keys;
//    }
//
//    public void setKeys(int[] keys) {
//        this.keys = keys;
//    }
//
//    public boolean isLeaf() {
//        return isLeaf;
//    }
//
//    public void setLeaf(boolean leaf) {
//        this.isLeaf = leaf;
//    }
//
//    public boolean isRoot() {
//        return isRoot;
//    }
//
//    public void setIsRoot(boolean root) {
//        this.isRoot = root;
//    }
//
//    public int getNunKeys() {
//        return nunKeys;
//    }
//
//    public void setNunKeys(int nunKeys) {
//        this.nunKeys = nunKeys;
//    }
//
//    public int getMinKeys() {
//        return minKeys;
//    }
//
//    public void setMinKeys(int minKeys) {
//        this.minKeys = minKeys;
//    }
//
//    public int getMaxKeys() {
//        return maxKeys;
//    }
//
//    public void setMaxKeys(int maxKeys) {
//        this.maxKeys = maxKeys;
//    }
//
//    public static int getTotalNodes() {
//        return totalNodes;
//    }
//
//    public static void setTotalNodes(int totalNodes) {
//        BTreeNode.totalNodes = totalNodes;
//    }
//
//    public int getNumNode() {
//        return numNode;
//    }
//
//    public void setNumNode(int numNode) {
//        this.numNode = numNode;
//    }
//
//    public BTreeNode getRoot() {
//        return root;
//    }
//
//    public void setRoot(BTreeNode root) {
//        this.root = root;
//    }

}
