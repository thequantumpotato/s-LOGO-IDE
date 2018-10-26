package backend;

import backend.Nodes.ArgumentNode;
import backend.Nodes.BasicNode;
import backend.Nodes.LoopNode;

import java.util.List;
import java.util.Random;

/**
 * @author Harry Xie
 * Contains all the helper command that modelcontroller invokes on
 */
public class Command {

    private Reflector m;
    private Turtle t;

    public Command(Reflector m, Turtle t) {
        this.m = m;
        this.t = t;
    }

    //public BasicNode forward(List<BasicNode> values) {
    //    Double d = 0.0;
    //    d = Double.parseDouble(values.get(0).getCommandName());
    //    t.move(d);
    //    t.notifyObservers();
    //    return new ArgumentNode(d.toString());
    //}
//
    //public BasicNode backward(List<BasicNode> values) {
    //    Double d = 0.0;
    //    d = Double.parseDouble(values.get(0).getCommandName());
    //    t.move(-1 * d);
    //    t.notifyObservers();
    //    return new ArgumentNode(d.toString());
    //}
//
    //public BasicNode left(List<BasicNode> l) {
    //    Double d = Double.parseDouble(l.get(0).getCommandName());
    //    t.turn(d);
    //    t.notifyObservers();
    //    return new ArgumentNode(d.toString());
    //}
//
    //public BasicNode right(List<BasicNode> l) {
    //    Double d = Double.parseDouble(l.get(0).getCommandName());
    //    t.turn(-d);
    //    t.notifyObservers();
    //    return new ArgumentNode(d.toString());
    //}
//
    //public BasicNode setHeading(List<BasicNode> l) {
    //    Double d = Double.parseDouble(l.get(0).getCommandName());
    //    t.setHeading(d);
    //    t.notifyObservers();
    //    return new ArgumentNode(d.toString());
    //}
//
    //public BasicNode setTowards(List<BasicNode> l) {
    //    double x = Double.parseDouble(l.get(0).getCommandName());
    //    double y = Double.parseDouble(l.get(0).getCommandName());
    //    double newDir = angleWithXAxis(x, y);
    //    double oldDir = t.getDirection();
    //    t.setHeading(newDir);
    //    t.notifyObservers();
    //    return new ArgumentNode(((Double) Math.abs(newDir - oldDir)).toString());
    //}
//
    ///**
    // * return angle with x axis given x and y
    // *
    // * @param x
    // * @param y
    // * @return positive number between 0 and 2PI
    // */
    //public double angleWithXAxis(double x, double y) {
    //    double res = 0;
    //    if (x == 0) {
    //        if (y > 0) {
    //            res = Math.PI / 2;
    //        }
    //        if (y < 0) {
    //            res = 1.5 * Math.PI;
    //        }
    //        return res;
    //    }
    //    double tmp = Math.atan(y / x);
    //    if (x > 0) {
    //        res = tmp;
    //    }
    //    if (x < 0) {
    //        res = Math.PI + tmp;
    //    }
    //    if (res < 0) {
    //        res += 2 * Math.PI;
    //    }
    //    return res;
    //}
//
    //public BasicNode showTurtle(List<BasicNode> l) {
    //    t.show();
    //    return new ArgumentNode("1");
    //}
//
    //public BasicNode clearScreen(List<BasicNode> l) {
    //    m.clearScreen();
    //    return this.home(l);
    //}
//
    //private double calcDist(double x1, double y1, double x2, double y2) {
    //    return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    //}
//
    //public BasicNode penDown(List<BasicNode> l) {
    //    t.putPenDown();
    //    return new ArgumentNode("1");
    //}
//
    //public BasicNode penUp(List<BasicNode> l) {
    //    t.liftPenUp();
    //    return new ArgumentNode("0");
    //}
//
    //public BasicNode hideTurtle(List<BasicNode> l) {
    //    t.hide();
    //    return new ArgumentNode("0");
    //}
//
    //public BasicNode home(List<BasicNode> l) {
    //    double dist = calcDist(t.getX(), t.getY(), 0, 0);
    //    t.setPosition(0, 0);
    //    t.notifyObservers();
    //    return new ArgumentNode(((Double) dist).toString());
    //}
//
    //public BasicNode xCoordinate(List<BasicNode> l) {
    //    return new ArgumentNode(((Double) t.getX()).toString());
    //}
//
    //public BasicNode yCoordinate(List<BasicNode> l) {
    //    return new ArgumentNode(((Double) t.getY()).toString());
    //}
//
    //public BasicNode heading(List<BasicNode> l) {
    //    return new ArgumentNode(((Double) t.getY()).toString());
    //}
//
    //public BasicNode isPenDown(List<BasicNode> l) {
    //    return new ArgumentNode(t.getIsPenDown() ? "1" : "0");
    //}
//
    //public BasicNode isShowing(List<BasicNode> l) {
    //    return new ArgumentNode(t.getIsShowing() ? "1" : "0");
    //}
//
    //private int[] parseTwoIntegers(List<BasicNode> l) {
    //    Double tmp1 = Double.parseDouble(l.get(0).getCommandName());
    //    Double tmp2 = Double.parseDouble(l.get(1).getCommandName());
    //    int a = tmp1.intValue();
    //    int b = tmp2.intValue();
    //    int[] res = new int[2];
    //    res[0] = a;
    //    res[1] = b;
    //    return res;
    //}
//
    //private double[] parseTwoDoubles(List<BasicNode> l) {
    //    double a = Integer.parseInt(l.get(0).getCommandName());
    //    double b = Integer.parseInt(l.get(1).getCommandName());
    //    double[] res = new double[2];
    //    res[0] = a;
    //    res[1] = b;
    //    return res;
    //}
//
    //public BasicNode sum(List<BasicNode> nodes) {
    //    Double sum = 0.0;
    //    for (BasicNode node : nodes) {
    //        sum += Double.parseDouble(node.getCommandName());
    //    }
    //    return new ArgumentNode(sum.toString());
    //}
//
    //public BasicNode difference(List<BasicNode> l) {
    //    double[] args = parseTwoDoubles(l);
    //    Double res = args[0] - args[1];
    //    return new ArgumentNode(res.toString());
    //}
//
    //public BasicNode product(List<BasicNode> l) {
    //    double[] args = parseTwoDoubles(l);
    //    Double res = args[0] * args[1];
    //    return new ArgumentNode(res.toString());
    //}
//
    //public BasicNode quotient(List<BasicNode> l) {
    //    int[] args = parseTwoIntegers(l);
    //    Integer res = args[0] / args[1];
    //    return new ArgumentNode(res.toString());
    //}
//
    //public BasicNode remainder(List<BasicNode> l) {
    //    int[] args = parseTwoIntegers(l);
    //    Integer res = args[0] % args[1];
    //    return new ArgumentNode(res.toString());
    //}
//
    //public BasicNode minus(List<BasicNode> l) {
    //    Double res = Double.parseDouble(l.get(0).getCommandName());
    //    return new ArgumentNode(res.toString());
    //}
//
    //public BasicNode random(List<BasicNode> l) {
    //    Double tmp = Double.parseDouble(l.get(0).getCommandName());
    //    Integer max = tmp.intValue();
    //    Random random = new Random();
    //    Integer res = random.nextInt(max);
    //    return new ArgumentNode(res.toString());
    //}
//
    //public BasicNode sine(List<BasicNode> l) {
    //    Double res = Double.parseDouble(l.get(0).getCommandName());
    //    res = Math.sin(Math.toRadians(res));
    //    return new ArgumentNode(res.toString());
    //}
//
    //public BasicNode cosine(List<BasicNode> l) {
    //    Double res = Double.parseDouble(l.get(0).getCommandName());
    //    res = Math.cos(Math.toRadians(res));
    //    return new ArgumentNode(res.toString());
    //}
//
    //public BasicNode tangent(List<BasicNode> l) {
    //    Double res = Double.parseDouble(l.get(0).getCommandName());
    //    res = Math.tan(Math.toRadians(res));
    //    return new ArgumentNode(res.toString());
    //}
//
    //public BasicNode arcTangent(List<BasicNode> l) {
    //    Double res = Double.parseDouble(l.get(0).getCommandName());
    //    res = Math.atan(res);
    //    return new ArgumentNode(res.toString());
    //}
//
    //public BasicNode naturalLog(List<BasicNode> l) {
    //    Double res = Double.parseDouble(l.get(0).getCommandName());
    //    res = Math.log(res);
    //    return new ArgumentNode(res.toString());
    //}
//
    //public BasicNode power(List<BasicNode> l) {
    //    double[] args = parseTwoDoubles(l);
    //    Double res = Math.pow(args[0], args[1]);
    //    return new ArgumentNode(res.toString());
    //}
//
    //public BasicNode pi(List<BasicNode> l) {
    //    return new ArgumentNode(((Double) Math.PI).toString());
    //}
//        public BasicNode doTimes (List<BasicNode> l) {
//            //to-do
//        }

    //    public BasicNode makeUserInstruction(List<BasicNode> l) {
//        String name = l.get(0).getCommandName();
//        CommandNode c = (CommandNode) l.get(1);
//        return new ArgumentNode(m.createInstruction(name, c) ? "1" : "0");
//    }
//
//    public BasicNode getInstruction(List<BasicNode> l) {
//        String name = l.get(0).getCommandName();
//        return m.getInstruction(name);
//    }
//    public BasicNode getUserInstruction(List<BasicNode> l) throws IllegalCommandException {
//        String name = l.get(0).getCommandName().substring(1);
//        BasicNode function = m.getInstruction(name);
//        if (function == null) {
//            throw new IllegalCommandException();
//        }
//        BasicNode loopNode = new LoopNode(1, "loop");
//        for (BasicNode n : function.getChildren()) {
//            loopNode.addChild(n); // move children from the listNode to the loopNode
//        }
//        return loopNode;
//    }


//        public BasicNode setBackground (List<BasicNode> l) {
//
    //public BasicNode lessThan(List<BasicNode> l) {
    //    double[] args = parseTwoDoubles(l);
    //    boolean res = args[0] < args[1];
    //    return new ArgumentNode(res ? "1" : "0");
    //}
//
    //public BasicNode greaterThan(List<BasicNode> l) {
    //    double[] args = parseTwoDoubles(l);
    //    boolean res = args[0] > args[1];
    //    return new ArgumentNode(res ? "1" : "0");
    //}
//
    //public BasicNode equal(List<BasicNode> l) {
    //    double[] args = parseTwoDoubles(l);
    //    double e = 0.0001;
    //    boolean res = Math.abs(args[0] - args[1]) <= e;
    //    return new ArgumentNode(res ? "1" : "0");
    //}
//
    //public BasicNode notEqual(List<BasicNode> l) {
    //    double[] args = parseTwoDoubles(l);
    //    double e = 0.0001;
    //    boolean res = Math.abs(args[0] - args[1]) > e;
    //    return new ArgumentNode(res ? "1" : "0");
    //}
//
    //public BasicNode and(List<BasicNode> l) {
    //    int[] args = parseTwoIntegers(l);
    //    boolean res = (args[0] * args[1]) == 1;
    //    return new ArgumentNode(res ? "1" : "0");
    //}
//
    //public BasicNode or(List<BasicNode> l) {
    //    int[] args = parseTwoIntegers(l);
    //    boolean res = (args[0] + args[1]) >= 1;
    //    return new ArgumentNode(res ? "1" : "0");
    //}
//
    //public BasicNode not(List<BasicNode> l) {
    //    Double tmp = Double.parseDouble(l.get(0).getCommandName());
    //    Integer res = tmp.intValue();
    //    return new ArgumentNode(res == 1 ? "1" : "0");
    //}
//
    //public BasicNode makeVariable(List<BasicNode> l) {
    //    //check if it already exists, and if so replace it. If not create it
    //    String name = l.get(0).getCommandName(); //key
    //    ArgumentNode value = (ArgumentNode) l.get(1); //value
    //    m.createSetVariable(name, value);
    //    return value;
    //}
//
    ////       public BasicNode makeVariable (List<BasicNode> l) {
    ////           String name = l.get(0).getCommandName();
    ////           return new ArgumentNode(m.createVariable(name) ? "1" : "0");
    ////       }
////
    ////       public BasicNode setVariable (List<BasicNode> l) {
    ////           String name = l.get(0).getCommandName();
    ////
    ////           return new ArgumentNode(m.setVariable(name, value) ? "1" : "0");
    ////       }
//
    //public BasicNode getVariable(List<BasicNode> l) throws IllegalCommandException {
    //    String name = l.get(0).getCommandName();
    //    System.out.println(name);
    //    return m.getVariable(name);
    //}
//
    //public BasicNode repeat(List<BasicNode> nodes) {
    //    //Input is a turtle and a list of BasicNodes. The first one is the repSize, and the second one
    //    //is the listNode
    //    BasicNode repNode = nodes.get(0);
    //    int reps = Integer.parseInt(repNode.getCommandName());
    //    BasicNode loopNode = new LoopNode(reps, "loop");
//
    //    BasicNode list = nodes.get(1);
    //    for (BasicNode n : list.getChildren()) {
    //        loopNode.addChild(n); // move children from the listNode to the loopNode
    //    }
    //    return loopNode;
    //}
//
////
//  //      public BasicNode doTimes (List<BasicNode> l) {
//  //          //to-do
//  //      }
//
//  //  public BasicNode makeUserInstruction(List<BasicNode> l) {
//  //      String name = l.get(0).getCommandName();
//  //      CommandNode c = (CommandNode) l.get(1);
//  //      return new ArgumentNode(m.createInstruction(name, c) ? "1" : "0");
//  //  }
////
//  //  public BasicNode getInstruction(List<BasicNode> l) {
//  //      String name = l.get(0).getCommandName();
//  //      return m.getInstruction(name);
//  //  }
//public BasicNode getUserInstruction(List<BasicNode> l) throws IllegalCommandException {
    //String name = l.get(0).getCommandName().substring(1);
    //BasicNode function = m.getInstruction(name);
    //if(function == null){
    //    throw new IllegalCommandException();
    //}
    //BasicNode loopNode = new LoopNode(1, "loop");
    //for (BasicNode n : function.getChildren()) {
    //    loopNode.addChild(n); // move children from the listNode to the loopNode
    //}
    //return loopNode;
//
//
//
//  //      public BasicNode setBackground (List<BasicNode> l) {
////
//  //      }
////
//  //      public BasicNode setPenColor (List<BasicNode> l) {
////
//  //      }
////
//  //      public BasicNode setPenSize (List<BasicNode> l) {
////
//  //      }
////
//  //      public BasicNode setShape (List<BasicNode> l) {
////
//  //      }
////
//  //      public BasicNode setPalette (List<BasicNode> l) {
////
//  //      }
////
//  //      public BasicNode getPenColor (List<BasicNode> l) {
////
//  //      }
////
//  //      public BasicNode getShape (List<BasicNode> l) {
////
//  //      }
////
//  //      public BasicNode stamp (List<BasicNode> l) {
////
//  //      }
////
//  //      public BasicNode clearStamps (List<BasicNode> l) {
////
//  //      }

}

