/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Agile PC
 */
public class Node implements Serializable {

    // Each node has an array of keys and an array of children
    private int order;
    private int level;
    private Node parent;
    private ArrayList<Node> children;
    private ArrayList<Key> keys;

    public Node(int order) {
        this.order = order;
        this.level = -1;
        this.parent = null;
        this.keys = new ArrayList();
        this.children = new ArrayList();
    }

    // Getter and setters of attributes
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

    public ArrayList<Key> getKeys() {
        return keys;
    }

    public void setKeys(ArrayList<Key> keys) {
        this.keys = keys;
    }

    // Adds key and sorts
    public void addKey(Key key) {
        if (this.keys.indexOf(key) == -1) {
            this.keys.add(key);
            sortKeys();
        }
    }

    public int getPositionKey(Comparable key) {
        int position = 0;
        if (this.keys.size() > 0) {
            for (int i = 0; i < this.getKeys().size();i++) {
                if(this.keys.get(i).getKey().compareTo(key)==0){
                    position = i;
                    break;
                }else{
                    position = -1;
                }
            }
        }
        return position;
    }

    // Comparator made to work with Collections.sort(<lists>) makes nodes ordered
    public static Comparator<Node> comparator = new Comparator<Node>() {
        @Override
        public int compare(Node node1, Node node2) {
            return node1.getKeys().get(0).getKey().compareTo(node2.getKeys().get(0).getKey());
        }

    };

    // Gets the median position for the key split
    public int getMedianPosition() {
        int position = 0;
        if (keys.size() % 2 != 0) {
            position = (keys.size() - 1) / 2;
        } else {
            position = keys.size() / 2;
        }
        return position;
    }

    // Checks if the arraylist of Keys is full.
    public boolean isFull() {
        return this.keys.size() == order;
    }

    // Sorts all keys while the arraylist has elements.
    public void sortKeys() {
        if (!this.keys.isEmpty()) {
            Collections.sort(keys, comparatorKeys);
        }
    }
    public static Comparator<Key> comparatorKeys = new Comparator<Key>() {
        @Override
        public int compare(Key o1, Key o2) {
            return o1.getKey().compareTo(o2.getKey());
        }

    };

    // Sorts children implementing the comparator on the Node class.
    public void sortChildren() {
        if (!this.children.isEmpty()) {
            Collections.sort(children, comparator);
        }
    }

    // Deletes key while position is valid
    public Key deleteKey(int position) {
        Key returnKey = null;
        if (position >= 0 && this.keys.size() > 0 && position < this.keys.size()) {
            returnKey = this.keys.remove(position);
            sortKeys();
        }
        return returnKey;
    }

    // Deletes Child on position
    public void deleteChild(int position) {
        for (int i = 0; i < this.children.size(); i++) {
            if (this.children.get(i).getKeys().isEmpty()) {
                this.children.remove(i);
            }
        }
        if (this.children.indexOf(position) != -1) {
            this.children.remove(position);
            sortChildren();
        }
    }

    // Splits the node in two parts and returns de right part
    public Node split() {
        int median = getMedianPosition();
        Key keySplit = this.keys.get(median);
        Node right = new Node(this.order);
        ArrayList<Key> leftKeys = new ArrayList<Key>(this.getKeys().subList(0, median));
        ArrayList<Key> rightKeys = new ArrayList<Key>(this.getKeys().subList(median + 1, this.getKeys().size()));
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

    // Verifies if it can share to other node
    public boolean canShare() {
        if (this.order % 2 == 0) {
            return this.keys.size() > (this.order / 2) - 1;
        } else {
            return this.keys.size() > (this.order / 2);
        }
    }

    // Verifies it it does not have children
    public boolean isLeaf() {
        return this.children.isEmpty();
    }

    @Override
    public String toString() {
        return keys + "";
    }

}
