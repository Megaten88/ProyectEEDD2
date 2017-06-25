/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolB;

/**
 *
 * @author Agile PC
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BTree tree = new BTree(3);
        System.out.println("-----------Insert 1----------");
        tree.insert(tree.getRoot(), 1);
        System.out.println(tree.getRoot().toString());
        System.out.println("-----------Insert 2----------");
        tree.insert(tree.getRoot(), 2);
        System.out.println(tree.getRoot().toString());
        System.out.println("-----------Insert 0----------");
        tree.insert(tree.getRoot(), 0);
        System.out.println(tree.getRoot().toString());
        System.out.println(tree.getRoot().getChildren().toString());
        tree.insert(tree.getRoot(), 6);
        System.out.println("-----------Insert 6----------");
        System.out.println(tree.getRoot().toString());
        System.out.println(tree.getRoot().getChildren().toString());
        System.out.println("-----------Insert 3----------");
        tree.insert(tree.getRoot(), 3);
        System.out.println(tree.getRoot().toString());
        System.out.println(tree.getRoot().getChildren().toString());
        System.out.println("-----------Insert 11----------");
        tree.insert(tree.getRoot(), 11);
        System.out.println(tree.getRoot().toString());
        System.out.println(tree.getRoot().getChildren().toString());
        System.out.println("-----------Insert 8----------");
        tree.insert(tree.getRoot(), 8);
        System.out.println(tree.getRoot().toString());
        System.out.println(tree.getRoot().getChildren().toString());
        System.out.print(tree.getRoot().getChildren().get(0).getChildren().toString() + " ");
        System.out.println(tree.getRoot().getChildren().get(1).getChildren().toString() + " ");
        System.out.println("-----------Insert 14----------");
        tree.insert(tree.getRoot(), 14);
        System.out.println(tree.getRoot().toString());
        System.out.println(tree.getRoot().getChildren().toString());
        System.out.print(tree.getRoot().getChildren().get(0).getChildren().toString() + " ");
        System.out.println(tree.getRoot().getChildren().get(1).getChildren().toString() + " ");
        System.out.println("-----------Insert 7----------");
        tree.insert(tree.getRoot(), 7);
        System.out.println(tree.getRoot().toString());
        System.out.println(tree.getRoot().getChildren().toString());
        System.out.print(tree.getRoot().getChildren().get(0).getChildren().toString() + " ");
        System.out.println(tree.getRoot().getChildren().get(1).getChildren().toString() + " ");
        System.out.println("-----------Insert 15----------");
        tree.insert(tree.getRoot(), 15);
        System.out.println(tree.getRoot().toString());
        System.out.println(tree.getRoot().getChildren().toString());
        System.out.print(tree.getRoot().getChildren().get(0).getChildren().toString() + " ");
        System.out.println(tree.getRoot().getChildren().get(1).getChildren().toString() + " ");
        System.out.println("-----------Insert Repeated----------");
        tree.insert(tree.getRoot(), 8);
        System.out.println(tree.getRoot().toString());
        System.out.println(tree.getRoot().getChildren().toString());
        System.out.print(tree.getRoot().getChildren().get(0).getChildren().toString() + " ");
        System.out.println(tree.getRoot().getChildren().get(1).getChildren().toString() + " ");
        System.out.println("-----------Insert Repeated----------");
        tree.insert(tree.getRoot(), 2);
        System.out.println(tree.getRoot().toString());
        System.out.println(tree.getRoot().getChildren().toString());
        System.out.print(tree.getRoot().getChildren().get(0).getChildren().toString() + " ");
        System.out.println(tree.getRoot().getChildren().get(1).getChildren().toString() + " ");
    }

}
