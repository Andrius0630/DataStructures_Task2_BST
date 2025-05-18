import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {
    private Node root;

    public void insert(Book book) {
        root = insertHelper(root, book);
    }

    private Node insertHelper(Node root, Book book) {
        if (search(book.getId()))
            throw new IllegalArgumentException("ID \'" + book.getId() + "\' of the book is already in use!");
        if (search(book.getTitle()))
            throw new IllegalArgumentException("Name \"" + book.getTitle() + "\" of the book is already in use!");
        if (root == null)
            return new Node(book);
        else if(book.compareTo(root.getBook()) < 0)
            root.setLeft(insertHelper(root.getLeft(), book));
        else
            root.setRight(insertHelper(root.getRight(), book));

        return root;
    }

    public void printInOrder() {
        printInOrderHelper(root);
    }

    private void printInOrderHelper(Node root) {
        if(root != null) {
            printInOrderHelper(root.getLeft());
            System.out.println(root.getBook().toString());
            printInOrderHelper(root.getRight());
        }
    }

    public boolean search(int key) {
        return searchHelper(root, key);
    }

    public boolean search(String key) {
        return searchHelper(root, key);
    }

    private boolean searchHelper(Node root, int key) {
        if (root == null)
            return false;
        if (root.getBook().getId() == key)
            return true;
        if (root.getBook().getId() < key)
            return searchHelper(root.getRight(), key);
        return searchHelper(root.getLeft(), key);
    }

    private boolean searchHelper(Node root, String key) {
        if (root == null)
            return false;
        if (root.getBook().getTitle().equals(key))
            return true;
        if (searchHelper(root.getLeft(), key))
            return true;
        return searchHelper(root.getRight(), key);
    }

    public List<Book> inOrderTraversal() {
        List<Book> newList = new ArrayList<>();
        inOrderTraversalHelper(root, newList);
        return newList;
    }

    private void inOrderTraversalHelper(Node root, List<Book> newList) {
        if(root != null) {
            inOrderTraversalHelper(root.getLeft(), newList);
            newList.add(root.getBook());
            inOrderTraversalHelper(root.getRight(), newList);
        }
    }

    public static BinarySearchTree treeFromList(List<Book> books) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.root = buildBalancedBST(books, 0, books.size() - 1);
        return bst;
    }

    private static Node buildBalancedBST(List<Book> books, int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        Node node = new Node(books.get(mid));
        node.setLeft(buildBalancedBST(books, start, mid - 1));
        node.setRight(buildBalancedBST(books, mid + 1, end));
        return node;
    }
}