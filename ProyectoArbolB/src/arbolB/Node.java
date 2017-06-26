/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Agile PC
 */
public class Node {

    private int order;
    private int level;
    private Node parent;
    private ArrayList<Node> children;
    private ArrayList<Comparable> keys;

    public Node(int order) {
        this.order = order;
        this.level = -1;
        this.parent = null;
        this.keys = new ArrayList();
        this.children = new ArrayList();
    }

    public Node(Node node) {
        this.order = node.order;
        this.level = node.level;
        this.parent = node.parent;
        this.keys = node.keys;
        this.children = node.children;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getLevel() {
        if (this.parent == null) {
            return 0;
        }
        return parent.level + 1;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Node> children) {
        this.children = children;
    }

    public ArrayList<Comparable> getKeys() {
        return keys;
    }

    public void setKeys(ArrayList<Comparable> keys) {
        this.keys = keys;
    }

    public void addKey(Comparable key) {
        if (this.keys.indexOf(key) == -1) {
            this.keys.add(key);
            sortKeys();
        }
    }
    public static Comparator<Node> comparator = new Comparator<Node>() {
        @Override
        public int compare(Node node1, Node node2) {
            int key1 = (int) node1.getKeys().get(0);
            int key2 = (int) node2.getKeys().get(0);
            return key1 - key2;
        }

    };

    public int getMedianPosition() {
        int position = 0;
        if (keys.size() % 2 != 0) {
            position = (keys.size() - 1) / 2;
        } else {
            position = keys.size() / 2;
        }
        return position;
    }

    public boolean isFull() {
        return this.keys.size() == order;
    }

    public void sortKeys() {
        Collections.sort(keys);
    }

    public void sortChildren() {
        Collections.sort(children, comparator);
    }

    public void deleteKey(int position) {
        if(position>=0 && this.keys.size()>0 && position < this.keys.size()){
            this.keys.remove(position);
        }
    }

    public void deleteChild(int position) {
        this.children.remove(position);
        sortChildren();
    }

    public void deleteChild(Node node) {
        if (this.children.indexOf(node) != -1) {
            this.children.remove(this.children.indexOf(node));
        }
        sortChildren();
    }

    public Node split() {
        int median = getMedianPosition();
        Comparable keySplit = this.keys.get(median);
        Node right = new Node(this.order);
        ArrayList<Comparable> leftKeys = new ArrayList<Comparable>(this.getKeys().subList(0, median));
        ArrayList<Comparable> rightKeys = new ArrayList<Comparable>(this.getKeys().subList(median + 1, this.getKeys().size()));
        right.setKeys(rightKeys);
        this.setKeys(leftKeys);
        if (this.children.size() > 0) {
            if (this.children.size() % 2 != 0) {
                median = (this.children.size() - 1) / 2;
            } else {
                median = this.children.size() / 2;
            }
            ArrayList<Node> leftNodes = new ArrayList<Node>(this.getChildren().subList(0, median));
            ArrayList<Node> rightNodes = new ArrayList<Node>(this.getChildren().subList(median, this.children.size()));
            right.setChildren(rightNodes);
            this.setChildren(leftNodes);
        }
        for (int i = 0; i < right.getChildren().size(); i++) {
            right.getChildren().get(i).setParent(right);
        }
        return right;
    }

    public boolean canShare() {
        if (this.order % 2 == 0) {
            return this.keys.size() > (this.order / 2) - 1;
        } else {
            return this.keys.size() > (this.order / 2);
        }
    }
    
    public boolean isLeaf(){
        return this.children.isEmpty();
    }
    
    
    @Override
    public String toString() {
        return keys+"";
    }

}
