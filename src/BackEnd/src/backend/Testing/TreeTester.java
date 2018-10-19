package backend.Testing;

import backend.Interpreter;
import backend.ModelController;
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
            //System.out.println(root.getCommandName());
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
            //System.out.println("-------------"); //end of tree
            break;

        }


    }


    public static void main(String[] args) throws Exception {
        //TESTING
        var cnt = new ModelController();
        // note, this simple "algorithm" will not handle SLogo comments
        String userInput = "ifelse lessp 10 5 [ fd 50 fd 50 ] [ fd 40 fd 40 ]";
        String userInput2 = "fd sum sum 20 20 20 ";
        String userInput3 = "bk sum ( 20 30 )";
        cnt.parseCommand(userInput2);

        //var tester = new TreeTester();
        //tester.execute(myRoots);

    }





}
