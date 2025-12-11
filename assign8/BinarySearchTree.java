public class BinarySearchTree {
    private Node root;
    
    public BinarySearchTree() {
        root = null;
    }
    
    public void insert(int value) {
        root = insertNode(root, value);
    }
    
    private Node insertNode(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }
        
        if (value < node.getValue()) {
            node.setLeft(insertNode(node.getLeft(), value));
        } else if (value > node.getValue()) {
            node.setRight(insertNode(node.getRight(), value));
        }
        
        return node;
    }
    
    public boolean search(int value) {
        return searchNode(root, value);
    }
    
    private boolean searchNode(Node node, int value) {
        if (node == null) {
            return false;
        }
        
        if (value == node.getValue()) {
            return true;
        } else if (value < node.getValue()) {
            return searchNode(node.getLeft(), value);
        } else {
            return searchNode(node.getRight(), value);
        }
    }
}
