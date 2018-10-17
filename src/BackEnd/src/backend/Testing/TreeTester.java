package backend.Testing;

import backend.Interpreter;
import backend.Nodes.BasicNode;

import java.util.List;

/**
 * @author Jose San Martin
 * Tester to test the tree and chekc that it's correctly implemented
 */
public class TreeTester {

    public TreeTester(){
    }

    private void execute(List<BasicNode> myRoots){
        System.out.println(myRoots.size());
        for(BasicNode root: myRoots){
            printTrees(root);
        }
    }

    //Preorder traversal
    private void printTrees(BasicNode root){
        System.out.println(root.getCommandName());
        while(root.getChildren() != null){
            for(BasicNode child: root.getChildren()){
                printTrees(child);
            }
            System.out.println("-------------"); //end of tree
            break;

        }


    }


    public static void main(String[] args) throws Exception {
        //TESTING
        var parser = new Interpreter();
        // note, this simple "algorithm" will not handle SLogo comments
        String userInput = "fd 50 rt 90 BACK cos 10";
        String userInput2 = "fd 1 + 3 + 4 + 5";
        var myRoots = parser.parse(userInput);

        var tester = new TreeTester();
        tester.execute(myRoots);

    }





}
