public class BinarySearchTree<E extends Comparable<E>> 
  extends BinaryTree<E> 
  implements SearchTree<E> {

    /**
     * Creates an empty binary search tree
     */
    public BinarySearchTree() {
        super();
    }

    /**
     * Creates a binary tree with the given root
     * @param newRoot  reference to the root node
     */
    public BinarySearchTree(Node<E> newRoot) {
        super(newRoot);
    }

    /**
     * Inserts item where it belongs in the tree.
     * Ignores duplicates.
     * @param item  a reference to the data item to add
     */
    @Override
    public void add(E item) {
        this.root = this.addHelper(this.root, item);
    }

    /**
     * Helper method to recursively add an item to the BST that is rooted 
     * in the given node (local root).
     * 
     * @param localRoot  the local root node of the subtree in which to 
     *                   attempt to add the item
     * @param item  the item to be added
     * @return new root of the current subtree
     */
    private Node<E> addHelper(Node<E> localRoot, E item) {
        if (localRoot == null) {
            localRoot = new Node<>(item);
        }
        else if (item.compareTo(localRoot.data) < 0) {
            localRoot.left = addHelper(localRoot.left, item);
        }
        else if (item.compareTo(localRoot.data) > 0) {
            localRoot.right = addHelper(localRoot.right, item);
        }
        return localRoot;
    }

    /**
     * Recursively determines whether the tree contains the given item.
     * @param item  an item to search for
     * @return true if found, or false otherwise
     */
    @Override
    public boolean contains(E item) {
        return this.containsHelper(this.root, item);
    }

    /**
     * Helper method that searches for the item in the current given node.
     * If item is not found in the node, it recursively descends down 
     * either the left or right subtree.
     * 
     * @param item an item to search for
     * @param node the current tree node to search in
     * @return true if found, or false otherwise
     */
    private boolean containsHelper(Node<E> localRoot, E item) {
        if (localRoot == null) {
            //nothing left to search, item must not exist
            return false;
        }
        else if (item.compareTo(localRoot.data) == 0) {
            //found it in the current node!
            return true;
        }
        else if (item.compareTo(localRoot.data) < 0) {
            //try finding it in left subtree
            return containsHelper(localRoot.left, item);
        }
        else {
            //try finding it in right subtree
            return containsHelper(localRoot.right, item);
        }
    }

    /**
     * Returns the largest data element rooted by the given node. Basically,
     * traverse the right subtree recursively until a leaf is reached.
     * 
     * @param localRoot  local root of the subtree
     * @return the largest data element under the subtree rooted by the given node
     */
    public E largest() {
        return this.largest_helper(this.root);
    }

    private E largest_helper(Node<E> localRoot) {
        if (localRoot.right == null) {
            return localRoot.data;
        } else {
            return largest_helper(localRoot.right);
        }
    }

    /**
     * @returns the number of leaf nodes in this BST
     */
    public int numLeaves() {
        return this.numLeaves_helper(this.root);
    }

    public int numLeaves_helper(Node<E> localRoot) {
        Node<E> left = localRoot.left;
        Node<E> right = localRoot.right;
        if (left == null && right == null) {
            return 1;
        }
        if (left != null && right == null) {
            return numLeaves_helper(left);
        }
        if (left == null && right != null) {
            return numLeaves_helper(right);
        }
        if (left != null && right != null) {
            return numLeaves_helper(left) + numLeaves_helper(right);
        }
        return 0;
    }
    
    /**
     * The height of the tree is defined as the number of edges along the longest
     * path from root to any leaf.
     * 
     * @returns the height of the current tree
     */
    public int height() {
        return this.height_helper(this.root);
    }

    private int height_helper(Node<E> localRoot) {
        int left = 1;
        int right = 1;
        if (localRoot.left != null) {
            left += height_helper(localRoot.left);
        }
        if (localRoot.right != null) {
            right += height_helper(localRoot.right);
        }
        if (left > right) {
            return left;
        }
        return right;
    }

    /**
     * @return the current size of the tree
     */
    public int size() {
        return this.size_helper(this.root);
    }
    private int size_helper(Node<E> localRoot) {
        if (localRoot == null) {
            return 0;
        }
        return 1 + size_helper(localRoot.left) + size_helper(localRoot.right);
    }
    
    /**
     * Removes the specified item from the search tree
     * @param target The item to be removed
     */
    public void remove(E target) {
        this.root = this.removeHelper(this.root, target);
    }

    private Node<E> removeHelper(Node<E> localRoot, E target) {
        if (localRoot == null) {
            return null;
        } 

        if (localRoot.data.equals(target)) {
            Node<E> left = localRoot.left;
            Node<E> right = localRoot.right;
            if (left == null && right == null) {
                return null;
            }
            if (left != null && right == null) {
                return localRoot.left;
            }
            if (left == null && right != null) {
                return localRoot.right;
            }
            if (largest_helper(left).compareTo(largest_helper(right)) < 0) {
                localRoot.data = largest_helper(left);
                removeHelper(left, largest_helper(left));
                return localRoot;
            }
            // (largest_helper(right).compareTo(largest_helper(left)) > 0
            localRoot.data = largest_helper(right);
            removeHelper(right, largest_helper(right));
            return localRoot;
        }

        if (localRoot.data.compareTo(target) < 0) {
            localRoot.right = removeHelper(localRoot.right, target);
            return localRoot;
        }
        if (localRoot.data.compareTo(target) > 0) {
            localRoot.left = removeHelper(localRoot.left, target);
            return localRoot;
        }

        return localRoot;
    }
}
