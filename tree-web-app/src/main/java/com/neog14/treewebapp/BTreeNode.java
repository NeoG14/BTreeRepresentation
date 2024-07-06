package com.neog14.treewebapp;

public class BTreeNode {
    private int order;
    private BTreeNode[] pointers; //Punteros a otros nodos
    private int[] keys; //Claves
    private boolean isLeaf; //True si es un nodo terminal
    private boolean isRoot; //True si es la raiz
    private int nunKeys; //numero actual de claves en el nodo (DimL)
    private int minKeys; //numero minimo de valores en el nodo
    private int maxKeys; //numero máximo de valores ne el nodo
    private static int totalNodes; //numero de nodos totales
    private int numNode; //numero de nodo individual
    private BTreeNode root;//puntero a la raiz del arbol

    //Creacion del primer nodo
    public BTreeNode(int order){
        this.order = order;
        this.maxKeys = order -1;
        this.minKeys = (order/2) - 1;
        this.keys = new int[maxKeys];
        this.pointers = new BTreeNode[order];
        this.isLeaf = true;
        this.isRoot = true;
        this.nunKeys = 0;
        totalNodes = 1;
        numNode = 0;
        this.root = this;
    }

    //creacion nuevo nodo
    public BTreeNode(int order, boolean isLeaf){
        this.order = order;
        this.maxKeys = order -1;
        this.minKeys = (order/2) - 1;
        this.keys = new int[maxKeys];
        this.pointers = new BTreeNode[order];
        this.isLeaf = isLeaf;
        this.isRoot = !isLeaf;
        totalNodes++;
        numNode = totalNodes-1;
    }


    // insertar principal recibe la clave a insertar y si no se puede insertar hay que dividir y redistribuir
    public void insert(int key){
        boolean insertOk = insertKey(key);
        if(insertOk){
            System.out.println("El elemento "+key+" se inserto correctamente en el nodo "+this.numNode);
        }else {
            BTreeNode actualNode = this;
            BTreeNode newNode = new BTreeNode(this.order,true);// creo el nuevo nodo para la redistribución
            int promoted = divideArray(this.nunKeys/2, this.nunKeys,actualNode,newNode);
            if(this.isRoot){
                BTreeNode newRootNode = new BTreeNode(this.order,false); //Si el nodo era una raiz tambien necesitare otro nodo para que sea la nueva raiz
                newRootNode.insert(promoted);
                //llamada a metodo para agregar elementos al vector de puntero
                newRootNode.addPointer(actualNode,newNode);
            }else {
                this.root.insert(promoted);
                this.root.addPointer(actualNode,newNode);
            }
        }
    }

    //si no se pudo insertar (false) es que hay que crear otro nodo
    private boolean insertKey(int key){
        if (this.isLeaf && nunKeys<maxKeys) {
            this.insertAndKeepSorted(this.keys,key);
            this.nunKeys++;
            return true;
        }
        return false;
    }

    private void insertAndKeepSorted(int[] array, int key){
        if(nunKeys>0){
            int i = nunKeys-1;
            while(i>=0 && array[i]>key){
                array[i+1] = array[i];//realizo un corrimiento de los elementos
                i--;
            }
            array[i+1]=key;
        }else{
            array[0] = key; //Inserto el primer espacio
        }
    }

    //este metodo crea los arreglos para la redistribucion y los asigna a los nodos correspondientes retorna el elemento a promocionar
    private int divideArray(int firstHalf, int secondHalf, BTreeNode firstNode, BTreeNode secondNode){
        int[] firstArray = new int[maxKeys];
        int[] secondArray = new int[maxKeys];
        int [] array = firstNode.getKeys();
        int value=0;

        for (int i=0; i<firstHalf ;i++){
            firstArray[i] = array[i];
        }

        int promoted = array[firstHalf];//para evitar tener que hacer un corrimiento nuevamente al realizar la promocion en arbol b

        for (int i=firstHalf+1; i<secondHalf ;i++){
            secondArray[value] = array[i];
            value++;
        }

        firstNode.setKeys(firstArray);// seteo el arreglo distribuido
        firstNode.setNunKeys(firstHalf); //seteo el numero de claves que quedan en el primer nodo

        secondNode.setKeys(secondArray);// seteo el arreglo distribuido
        secondNode.setNunKeys((secondHalf-firstHalf)-1);//seteo el numero de claves que quedan en el segundo nodo
        return promoted;
    }

    // no se si funciona
    private void addPointer(BTreeNode actualNode, BTreeNode newNode) {
        this.pointers[this.nunKeys-1] = actualNode;
        this.pointers[this.nunKeys] = newNode;
        int s = 5;
    }


    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public BTreeNode[] getPointers() {
        return pointers;
    }

    public void setPointers(BTreeNode[] pointers) {
        this.pointers = pointers;
    }

    public int[] getKeys() {
        return keys;
    }

    public void setKeys(int[] keys) {
        this.keys = keys;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        this.isLeaf = leaf;
    }

    public boolean isRoot() {
        return isRoot;
    }

    public void setIsRoot(boolean root) {
        this.isRoot = root;
    }

    public int getNunKeys() {
        return nunKeys;
    }

    public void setNunKeys(int nunKeys) {
        this.nunKeys = nunKeys;
    }

    public int getMinKeys() {
        return minKeys;
    }

    public void setMinKeys(int minKeys) {
        this.minKeys = minKeys;
    }

    public int getMaxKeys() {
        return maxKeys;
    }

    public void setMaxKeys(int maxKeys) {
        this.maxKeys = maxKeys;
    }

    public static int getTotalNodes() {
        return totalNodes;
    }

    public static void setTotalNodes(int totalNodes) {
        BTreeNode.totalNodes = totalNodes;
    }

    public int getNumNode() {
        return numNode;
    }

    public void setNumNode(int numNode) {
        this.numNode = numNode;
    }
}
