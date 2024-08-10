import java.util.Scanner;

// Node class
class Node {
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

// Tree Class
class BinaryTree {
    static Node root;
    static BinaryTree tree = null;

    // Private Constructor for singleton class of tree instance
    private BinaryTree() {
        root = null;
    }

    // Get tree instance by only allowing one at a time
    public static BinaryTree getTreeInstance(int[] numArray){
        if (tree == null) {
            tree = new BinaryTree();
            for (int i = 0; i < numArray.length; i++) {
                tree.add(numArray[i]);
            }
        }
        return tree;
    }

    // Adds a node to tree
    public void add(int data) {
        // Calls the addNode method using root as initial start point
        root = addNode(root, data);
    }

    public Node addNode(Node node, int data) {
            /*If root node is null, create new Node
              If data is less than root, re-run this method with roots left child as node
              If data is greater than root, re-run this method with roots right child as node
              Keep re-running method until empty node is available then create new Node */

        if (node == null) {
            node = new Node(data);
        }else if (data <= node.data) {
            node.left = addNode(node.left, data);
        }else if (data >= node.data) {
            node.right = addNode(node.right, data);
        }
        return node;
    }

    // Deletes a node
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

    // This method prints the menu options
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