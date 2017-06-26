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
        // Init 
        BTree tree = new BTree(3);
        System.out.println("-----------Insert 1----------");
        tree.insert(tree.getRoot(), new Key(1, 1));
        System.out.println(tree.getRoot().toString());
        System.out.println("-----------Insert 2----------");
        tree.insert(tree.getRoot(), new Key(2, 2));
        System.out.println(tree.getRoot().toString());
        System.out.println("-----------Insert 0----------");
        tree.insert(tree.getRoot(), new Key(0, 3));
        System.out.println(tree.getRoot().toString());
        System.out.println(tree.getRoot().getChildren().toString());
        tree.insert(tree.getRoot(), new Key(6, 4));
        System.out.println("-----------Insert 6----------");
        System.out.println(tree.getRoot().toString());
        System.out.println(tree.getRoot().getChildren().toString());
        System.out.println("-----------Insert 3----------");
        tree.insert(tree.getRoot(), new Key(3, 5));
        System.out.println(tree.getRoot().toString());
        System.out.println(tree.getRoot().getChildren().toString());
        System.out.println("-----------Insert 11----------");
        tree.insert(tree.getRoot(), new Key(11, 6));
        System.out.println(tree.getRoot().toString());
        System.out.println(tree.getRoot().getChildren().toString());
        System.out.println("-----------Insert 8----------");
        tree.insert(tree.getRoot(), new Key(8, 7));
        System.out.println(tree.getRoot().toString());
        System.out.println(tree.getRoot().getChildren().toString());
        System.out.print(tree.getRoot().getChildren().get(0).getChildren().toString() + " ");
        System.out.println(tree.getRoot().getChildren().get(1).getChildren().toString() + " ");
        System.out.println("-----------Insert 14----------");
        tree.insert(tree.getRoot(), new Key(14, 8));
        System.out.println(tree.getRoot().toString());
        System.out.println(tree.getRoot().getChildren().toString());
        System.out.print(tree.getRoot().getChildren().get(0).getChildren().toString() + " ");
        System.out.println(tree.getRoot().getChildren().get(1).getChildren().toString() + " ");
        System.out.println("-----------Insert 7----------");
        tree.insert(tree.getRoot(), new Key(7, 9));
        System.out.println(tree.getRoot().toString());
        System.out.println(tree.getRoot().getChildren().toString());
        System.out.print(tree.getRoot().getChildren().get(0).getChildren().toString() + " ");
        System.out.println(tree.getRoot().getChildren().get(1).getChildren().toString() + " ");
        System.out.println("-----------Insert 15----------");
        tree.insert(tree.getRoot(), new Key(15, 10));
        System.out.println(tree.getRoot().toString());
        System.out.println(tree.getRoot().getChildren().toString());
        System.out.print(tree.getRoot().getChildren().get(0).getChildren().toString() + " ");
        System.out.println(tree.getRoot().getChildren().get(1).getChildren().toString() + " ");
        System.out.println("-----------Insert 20----------");
        tree.insert(tree.getRoot(), new Key(20, 11));
        System.out.println(tree.getRoot().toString());
        System.out.println(tree.getRoot().getChildren().toString());
        System.out.print(tree.getRoot().getChildren().get(0).getChildren().toString() + " ");
        System.out.println(tree.getRoot().getChildren().get(1).getChildren().toString() + " ");
        System.out.println("-----------Insert 21----------");
        tree.insert(tree.getRoot(), new Key(21, 12));
        System.out.println(tree.getRoot().toString());
        System.out.println(tree.getRoot().getChildren().toString());
        System.out.print(tree.getRoot().getChildren().get(0).getChildren().toString() + " ");
        System.out.print(tree.getRoot().getChildren().get(1).getChildren().toString() + " ");
        System.out.println(tree.getRoot().getChildren().get(2).getChildren().toString() + " ");

        System.out.println("Todas las llaves: " + tree.getAllkeys());
        System.out.println("------------COMPARISONS-------------");
        System.out.println("Todas las llaves: " + tree.getAllkeys());

        /*  El delete funciona de la siguiente manera:
            El int delete es el ID especificio de la persona, que se encuentra en el índice
            Key returnKey se inicia en null, entonces por cada llave que se encuentra en el índice getAllKeys()
            busca si kay un índice que tiene ese el id == delete y lo asigna a returnKey sino siempre se queda en nulo.
            El índice siempre está ordenado en el árbol.
         */
        int delete = 21;
        Key returnKey = null;
        for (Comparable key : tree.getAllkeys()) {
            if (key instanceof Key) {
                if (((Key) key).getKey() == delete) {
                    returnKey = (Key) key;
                    break;
                } else if (((Key) key).getKey() > delete) {
                    delete = -1;
                    break;
                }
            }
        }
        /*Sí cumple las dos condiciones para borrar, primero busca el nodo donde se encuentra la llave{search(tree.getRoot(),delete) regresa el nodo donde se encuentra la llave}
        Con el método delete, se pone el nodo encontrado en el search yy el índice(returnKey) que se va a borrar*/
        if (delete != -1 && returnKey != null) {
            Node node = tree.search(tree.getRoot(), delete);
            tree.delete(node, returnKey);
            System.out.println(tree.getRoot().toString());
            System.out.println(tree.getRoot().getChildren().toString());
            System.out.print(tree.getRoot().getChildren().get(0).getChildren().toString() + " ");
            System.out.print(tree.getRoot().getChildren().get(1).getChildren().toString() + " ");
        }
//        System.out.print(tree.getRoot().getChildren().get(2).getChildren().toString() + " ");

//        node = tree.search(tree.getRoot(), 0);
//        tree.delete(node, 0);
//        System.out.println(tree.getRoot().toString());
//        System.out.println(tree.getRoot().getChildren().toString());
//        System.out.print(tree.getRoot().getChildren().get(0).getChildren().toString() + " ");
//        System.out.print(tree.getRoot().getChildren().get(1).getChildren().toString() + " ");
//        System.out.println("");
    }

}
