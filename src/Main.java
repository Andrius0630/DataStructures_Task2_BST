/*
* andrius.kolenda@mif.stud.vu.lt
*/

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        try {
            BinarySearchTree bst1 = new BinarySearchTree();
            BinarySearchTree bst2 = new BinarySearchTree();

            bst1.insert(new Book(10, "Java"));
            bst1.insert(new Book(5, "Python"));
            bst1.insert(new Book(8, "Swift"));
            bst1.insert(new Book(1, "C++"));
            bst1.insert(new Book(7, "Kotlin"));

            bst2.insert(new Book(8, "Swift"));
            bst2.insert(new Book(5, "Python"));
            bst2.insert(new Book(1, "C++"));
            bst2.insert(new Book(15, "Go"));
            bst2.insert(new Book(10, "Java"));


            System.out.println("BST1 printed in-order:");
            bst1.printInOrder();
            System.out.println();

            System.out.println("BST2 printed in-order:");
            bst2.printInOrder();
            System.out.println();


            List<Book> bstList1 = bst1.inOrderTraversal();
            List<Book> bstList2 = bst2.inOrderTraversal();

            // a
            List<Book> mergedList = mergeLists(bstList1, bstList2);
            System.out.println("Merged list: \n" + mergedList);

            BinarySearchTree mergedBST = BinarySearchTree.treeFromList(mergedList);
            System.out.println("Merged BST printed in-order:");
            mergedBST.printInOrder();
            System.out.println();

            // b
            List<Book> intersecteddList = intersectSortedLists(bstList1, bstList2);
            System.out.println("Intersected list: \n" + intersecteddList);

            BinarySearchTree intersectedBST = BinarySearchTree.treeFromList(intersecteddList);
            System.out.println("Intersected BST printed in-order:");
            intersectedBST.printInOrder();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    private static List<Book> mergeLists(List<Book> list1, List<Book> list2) {
        List<Book> merged = new ArrayList<>();
        int i = 0, j = 0;
        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i).compareTo(list2.get(j)) < 0)
                merged.add(list1.get(i++));
            else if (list1.get(i).compareTo(list2.get(j)) > 0)
                merged.add(list2.get(j++));
            else {
                merged.add(list1.get(i));
                i++; j++;
            }
        }
        while (i < list1.size()) merged.add(list1.get(i++));
        while (j < list2.size()) merged.add(list2.get(j++));
        return merged;
    }

    private static List<Book> intersectSortedLists(List<Book> list1, List<Book> list2) {
        List<Book> intersection = new ArrayList<>();
        int i = 0, j = 0;
        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i).compareTo(list2.get(j)) < 0)
                i++;
            else if (list1.get(i).compareTo(list2.get(j)) > 0)
                j++;
            else {
                intersection.add(list1.get(i));
                i++; j++;
            }
        }
        return intersection;
    }
}



