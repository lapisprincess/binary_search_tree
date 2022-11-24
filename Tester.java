public class Tester
{
    public static void main(String[] args) {
        BinarySearchTree<Integer> my_bst = new BinarySearchTree<>();
        my_bst.add(3);
        my_bst.add(3);  // should ignore
        my_bst.add(4);
        my_bst.add(1);
        my_bst.add(5);
        my_bst.add(2);
        System.out.println(my_bst.toString());

        System.out.println("Largest: " + my_bst.largest());

        System.out.println("Leaves: " + my_bst.numLeaves());

        System.out.println("Height: " + my_bst.height());
        
        System.out.print("Preorder: ");
        my_bst.preOrder();
        System.out.println();

        System.out.print("Postorder: ");
        my_bst.postOrder();
        System.out.println();

        System.out.print("Inorder: ");
        my_bst.inOrder();
        System.out.println();
        System.out.println();

        my_bst.remove(3);
        System.out.println(my_bst.toString());

        my_bst.remove(1);
        System.out.println(my_bst.toString());

        my_bst.remove(5);
        System.out.println(my_bst.toString());
    }
}
