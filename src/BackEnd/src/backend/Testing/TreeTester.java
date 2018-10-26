package backend.Testing;

import backend.Commands.Node;

import java.util.List;

/**
 * @author Jose San Martin
 * Tester to test the tree and chekc that it's correctly implemented
 */
public class TreeTester {

    public TreeTester() {
    }

    //public static void main(String[] args) throws Exception {
    //    //TESTING
    //    var turtle = new Turtle();
//
    //    var cnt = new ModelController(turtle);
    //    // note, this simple "algorithm" will not handle SLogo comments
    //    String userInput = "ifelse lessp 10 5 [ fd 50 fd 50 ] [ fd 40 fd 40 ]";
    //    String userInput2 = "fd 50 fd 50 ";
    //    String userInput3 = "bk sum ( 20 30 )";
    //    cnt.parseCommand(userInput2);
//
    //    //var tester = new TreeTester();
    //    //tester.execute(myRoots);
//
    //}

    private void execute(List<Node> myRoots) {
        System.out.println(myRoots.size());
        for (Node root : myRoots) {
            //System.out.println(root.getCommandName());
            printTrees(root);
        }
    }

    //Preorder traversal
    private void printTrees(Node root) {
        System.out.println(root.getCommandName());
        while (root.getChildren() != null) {
            for (Node child : root.getChildren()) {
                printTrees(child);
            }
            //System.out.println("-------------"); //end of tree
            break;

        }


    }


}
