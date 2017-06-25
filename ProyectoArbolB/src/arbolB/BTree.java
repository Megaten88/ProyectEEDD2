/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolB;

import java.util.ArrayList;

/**
 *
 * @author Agile PC
 */
public class BTree {

    private Node root;
    private int order;
    private boolean promote;
    private ArrayList<Comparable> allkeys;
    private int level;

    public BTree(int order) {
        this.order = order;
        this.root = new Node(order);
        this.allkeys = new ArrayList();
        this.promote = false;
        this.level = 0;
    }

    public void splitAndPromote(Node node, Comparable key) {
        if (node.getParent() == null) {
            node.addKey(key);
            int median = node.getMedianPosition();
            Comparable keySplit = node.getKeys().get(median);
            Node temp = new Node(node.getOrder());
            temp.addKey(keySplit);
            temp.setLevel(temp.getLevel() + 1);
            Node right = node.split();
            right.setLevel(right.getLevel() + 1);
            right.setParent(temp);
            node.setParent(temp);
            temp.getChildren().add(node);
            temp.getChildren().add(right);
            root = temp;
        } else {
            this.promote = true;
            node.addKey(key);
            int median = node.getMedianPosition();
            Comparable keySplit = node.getKeys().get(median);
            Node right = node.split();
            right.setLevel(right.getLevel() + 1);
            right.setParent(node.getParent());
            ArrayList<Node> tempArray = new ArrayList();
            int index = 0;
            for (int i = 0; i < node.getParent().getChildren().size(); i++) {
                if (node.getParent().getChildren().get(i).equals(node)) {
                    tempArray.add(node.getParent().getChildren().get(i));
                    index = i;
                    break;
                } else {
                    tempArray.add(node.getParent().getChildren().get(i));
                }
            }
            tempArray.add(right);
            for (int i = index; i < node.getParent().getChildren().size(); i++) {
                tempArray.add(node.getParent().getChildren().get(i));
            } 
            tempArray.remove(tempArray.lastIndexOf(node));
            node.getParent().setChildren(tempArray);
            insert(node.getParent(), keySplit);
        }
    }

    public void insert(Node node, Comparable key) {
        if (node.getLevel() == -1) {
            node.setLevel(this.level);
            this.level++;
            node.addKey(key);
            this.allkeys.add(key);
        } else if (!node.getChildren().isEmpty() && !this.promote) {
            int index = 0;
            for (index = 0; index < node.getKeys().size(); index++) {
                if (key.compareTo(node.getKeys().get(index)) < 0) {
                    break;
                }
            }
            if(index >= node.getChildren().size()){
                index = node.getChildren().size()-1;
            }
            insert(node.getChildren().get(index), key);
        } else if (node.getChildren().isEmpty() || this.promote) {
            if (node.getKeys().size() < node.getOrder() - 1) {
                node.addKey(key);
                this.allkeys.add(key);
            } else {
                splitAndPromote(node, key);
            }
            this.promote = false;
        }
    }

    public Node getRoot() {
        return root;
    }

}
