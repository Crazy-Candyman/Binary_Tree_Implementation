import java.util.Scanner;


/** Structure for the Node Class. */
class Node {
    int data;
    Node left;
    Node right;

    /** Creates a new Node object and instantiates two
     *  Nodes (left and right). Both nodes will be assigned
     *  to null when new object is created via the constructor.
     *
     * @param data an integer passed as the data for the Node.
     * @return New node object
     */
    public Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

/** Structure for the Binary Tree Class. */
class BinaryTree {
    static Node root;
    static BinaryTree tree = null;

    // Private constructor for singleton class of tree instance
    private BinaryTree() {
        root = null;
    }


    /** This method allows only one instance of BinaryTree object
     * by retrieving the current object or creating a new object if
     * none exist at the time.
     *
     * @param numArray Array of integers you want to add to tree at implementation.
     * @return
     */
    public static BinaryTree getTreeInstance(int[] numArray){
        if (tree == null) {
            tree = new BinaryTree();
            for (int i = 0; i < numArray.length; i++) {
                tree.add(numArray[i]);
            }
        }
        return tree;
    }


    /** Adds a node to the tree by calling the addNode function
     * with the root as the starting Node during the iteration of
     * the addNode function.
     *
     * @param data The integer you would like to add to the tree.
     */
    public void add(int data) {
        root = addNode(root, data);
    }


    /** This method adds a new node by looking for a null object and then
     * instantiating the new node to that null object. The null is object
     * is found by comparing the "data" to the current "node".
     * If data <= current node than recursively call addNode with the
     * node left of current node. If data >= current node than recursively
     * call addNode with node right of the current node. This repetition
     * continues until a null object is found and then node is instantiated.
     *
     * @param node The node is the starting point of this method.
     * @param data The integer you want to add to the tree.
     * @return New node with integer as the data.
     */
    public Node addNode(Node node, int data) {
        if (node == null) {
            node = new Node(data);
        }else if (data <= node.data) {
            node.left = addNode(node.left, data);
        }else if (data >= node.data) {
            node.right = addNode(node.right, data);
        }
        return node;
    }


    /** This method deletes the node that holds the "data" value in
     * the parameter. The method recursively locates the node by checking
     * if the data is less than or greater than the current iterations node.
     * The node is swapped with the next lowest number in the subtree and
     * then that node is deleted from the list.
     *
     * @param node The starting point of iteration process.
     * @param data The node with the assigned data you want deleted.
     * @return An updated subtree of nodes within the tree.
     */
    public Node deleteNode(Node node, int data) {
        // Check if node is null
        if (node == null) {
            System.out.println("\nNode does not exist!");
            return node;
        // Check if node is to the left
        }else if (data < node.data) {
            node.left = deleteNode(node.left, data);
        // Check if node is the right
        }else if (data > node.data) {
            node.right = deleteNode(node.right, data);
        // If this is the null we want to delete, lets check ....
        }else if (data == node.data){
            // If both children are null
            if (node.left == null && node.right == null) {
                node = null;
            // If only right child exist
            }else if (node.left == null && node.right != null) {
                node = node.right;
            // If only left child exist
            }else if (node.left != null && node.right == null) {;
                node = node.left;
            // If both children exist left and right
            }else if (node.left != null && node.right != null) {
                // Use nextMinimum to check for next lowest number to swap with
                Node nextMinimum = node.right;
                while (nextMinimum.left != null){
                    nextMinimum = nextMinimum.left;
                }
                // Swap data with node
                node.data = nextMinimum.data;
                // Recursive method to delete the lowest number
                node.right = deleteNode(node.right, nextMinimum.data);
            }
        }
        return node;
    }


    /** This method prints the tree in order from least
     * to greatest.
     *
     * @param node Tree node should be used as a starting node.
     */
    public void inOrderPrint(Node node) {
        if (node == null) {
            return;
        }
        // Recur on the left subtree
        inOrderPrint(node.left);
        // Visit the current node
        System.out.print(node.data + ", ");
        // Recur on the right subtree
        inOrderPrint(node.right);
    }

    /** This method prints the tree in pre-order.
     *
     * @param node Tree node should be used as a starting node.
     */
    public void preOrderPrint(Node node) {
        if (node == null) {
            return;
        }
        // Visit the current node
        System.out.print(node.data + ", ");
        // Recur on the left subtree
        preOrderPrint(node.left);
        // Recur on the right subtree
        preOrderPrint(node.right);
    }

    /** This method prints the tree in post-order.
     *
     * @param node Tree node should be used as a starting node.
     */
    public void postOrderPrint(Node node) {
        if (node == null) {
            return;
        }
        // Recur on the left subtree
        postOrderPrint(node.left);
        // Recur on the right subtree
        postOrderPrint(node.right);
        // Visit the current node
        System.out.print(node.data + ", ");
    }

} // End BinaryTree Class


// Main class
public class Main {

    /** This method prints the menu for the application. */
    public static void displayMenu() {
        System.out.println("\n\tMy Binary Tree");
        System.out.println("---------------------------------");
        System.out.println("[1] Create a binary search tree");
        System.out.println("[2] Add a node");
        System.out.println("[3] Delete a node");
        System.out.println("[4] Print nodes in order");
        System.out.println("[5] Print nodes in pre-order");
        System.out.println("[6] Print nodes in post-order");
        System.out.println("[7] Quit application");
        System.out.print("Enter a menu selection: ");
    }


    // Driver
    public static void main(String []args) {
        Scanner userInput = new Scanner(System.in);
        BinaryTree tree = null;
        String menuSelection;
        int data;
        int[] numArray = {1009, 1007, 1011, 1005, 1013, 1003, 1015, 1001, 1017, 1019};

        do {
            displayMenu();
            menuSelection = userInput.nextLine();
            switch (menuSelection) {
                case "1": // Initialize binary tree with array
                    tree = BinaryTree.getTreeInstance(numArray);
                    break;
                case "2": // Add a node
                    System.out.println("");
                    System.out.print("---> Enter the value data for the Node: ");
                    data = userInput.nextInt();
                    userInput.nextLine();
                    tree.add(data);
                    break;
                case "3": // Delete node
                    if (tree != null) {
                        System.out.println("");
                        System.out.print("---> Enter the value data for the Node you want to delete: ");
                        data = userInput.nextInt();
                        userInput.nextLine();
                        tree.root = tree.deleteNode(tree.root, data);
                    } else {
                        System.out.println("\nTree is empty!");
                    }
                    break;
                case "4": // Print nodes in order
                    if (tree != null) {
                        System.out.println("\nNodes in order");
                        tree.inOrderPrint(tree.root);
                        System.out.println("");
                    }else {
                        System.out.println("\nTree is empty!");
                    }
                    break;
                case "5": // Print nodes in pre-order
                    if (tree != null) {
                        System.out.println("\nNodes in pre-order");
                        tree.preOrderPrint(tree.root);
                        System.out.println("");
                    }else {
                        System.out.println("\nTree is empty!");
                    }
                    break;
                case "6": // Print nodes in post-order
                    if (tree != null) {
                        System.out.println("\nNodes in post-order");
                        tree.postOrderPrint(tree.root);
                        System.out.println("");
                    }else {
                        System.out.println("\nTree is empty!");
                    }
                    break;
                case "7": // Quit application
                    break;
                default:
                    System.out.println("\nPlease enter a valid selection.");
            }
        } while (!menuSelection.equals("7"));

        // Ending Application
        System.out.println("End of application");
        System.exit(0);

    } // End Driver

} // End Main