
/*
Todo:
Union and intersection of two Binary Search Trees (BST).

Create two binary search trees (BST) which store various Books in nodes. Each book object has unique id
and title. Use id of book as a key.

a) Merge these two BST‘s into one height balanced BST with the lowest possible height.
Hint: In-order traverse two initial BSTs and store all values in two sorted lists (ArrayList or LinkedList).
Merge these two lists into one sorted list (one for loop may be used).
Then construct a height-balanced BST from the merged sorted list. The idea is to take middle element from
the list and add it to final BST. Then take the middle element from the left side of the list and add it to final
BST. Take the middle element from the right side of the list and add it to final BST. Repeat recursively.
Print all trees and lists.

b) Intersect these two BST‘s (i.e. find those elements which are in both BSTs) and create height balanced
BST with the lowest possible height from elements of intersection .
Hint: In-order traverse two initial BSTs and store all values in two sorted lists (ArrayList or LinkedList).
Intersect these two lists into one sorted list (one for loop may be used).
Then construct a height-balanced BST from the intersected sorted list. The idea is to take middle element
from the list and add it to final BST. Then take the middle element from the left side of the list and add it to
final BST. Take the middle element from the right side of the list and add it to final BST. Repeat recursively.
Print all trees and lists.


 */


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
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


        System.out.println("In-order BST1:");
        bst1.printInOrder();
        System.out.println("In-order BST2:");
        bst2.printInOrder();
        System.out.println();

        List<Book> bstList1 = bst1.inOrderTraversal();
        List<Book> bstList2 = bst2.inOrderTraversal();

        // a
        List<Book> mergedList = mergeLists(bstList1, bstList2);
        System.out.println("Merged list: \n" + mergedList);

        BinarySearchTree balancedBST = BinarySearchTree.treeFromList(mergedList);
        balancedBST.printInOrder();

        // b
        List<Book> intersecteddList = intersectSortedLists(bstList1, bstList2);
        System.out.println("Intersected list: \n" + intersecteddList);

        BinarySearchTree intersectedBST = BinarySearchTree.treeFromList(intersecteddList);
        intersectedBST.printInOrder();
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
            if (list1.get(i).getId() < list2.get(j).getId())
                i++;
            else if (list1.get(i).getId() > list2.get(j).getId())
                j++;
            else {
                intersection.add(list1.get(i)); // Only add if ids match
                i++; j++;
            }
        }
        return intersection;
    }
}



