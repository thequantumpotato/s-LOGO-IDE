package backend;

import backend.Nodes.ArgumentNode;
import backend.Commands.Node;
import backend.Nodes.CommandNode;
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

    public Node forward(List<Node> values) {
        Double d = 0.0;
        d = Double.parseDouble(values.get(0).getCommandName());
        t.move(d);
        t.notifyObservers();
        return new ArgumentNode(d.toString());
    }

    public Node backward(List<Node> values) {
        Double d = 0.0;
        d = Double.parseDouble(values.get(0).getCommandName());
        t.move(-1 * d);
        t.notifyObservers();
        return new ArgumentNode(d.toString());
    }

    public Node left(List<Node> l) {
        Double d = Double.parseDouble(l.get(0).getCommandName());
        t.turn(d);
        t.notifyObservers();
        return new ArgumentNode(d.toString());
    }

    public Node right(List<Node> l) {
        Double d = Double.parseDouble(l.get(0).getCommandName());
        t.turn(-d);
        t.notifyObservers();
        return new ArgumentNode(d.toString());
    }

    public Node setHeading(List<Node> l) {
        Double d = Double.parseDouble(l.get(0).getCommandName());
        t.setHeading(d);
        t.notifyObservers();
        return new ArgumentNode(d.toString());
    }

    public Node setTowards(List<Node> l) {
        double x = Double.parseDouble(l.get(0).getCommandName());
        double y = Double.parseDouble(l.get(0).getCommandName());
        double newDir = angleWithXAxis(x, y);
        double oldDir = t.getDirection();
        t.setHeading(newDir);
        t.notifyObservers();
        return new ArgumentNode(((Double) Math.abs(newDir - oldDir)).toString());
    }

    /**
     * return angle with x axis given x and y
     *
     * @param x
     * @param y
     * @return positive number between 0 and 2PI
     */
    public double angleWithXAxis(double x, double y) {
        double res = 0;
        if (x == 0) {
            if (y > 0) {
                res = Math.PI / 2;
            }
            if (y < 0) {
                res = 1.5 * Math.PI;
            }
            return res;
        }
        double tmp = Math.atan(y / x);
        if (x > 0) {
            res = tmp;
        }
        if (x < 0) {
            res = Math.PI + tmp;
        }
        if (res < 0) {
            res += 2 * Math.PI;
        }
        return res;
    }

    public Node showTurtle(List<Node> l) {
        t.show();
        return new ArgumentNode("1");
    }

    public Node clearScreen(List<Node> l) {
        m.clearScreen();
        return this.home(l);
    }

    private double calcDist(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }

    public Node penDown(List<Node> l) {
        t.putPenDown();
        return new ArgumentNode("1");
    }

    public Node penUp(List<Node> l) {
        t.liftPenUp();
        return new ArgumentNode("0");
    }

    public Node hideTurtle(List<Node> l) {
        t.hide();
        return new ArgumentNode("0");
    }

    public Node home(List<Node> l) {
        double dist = calcDist(t.getX(), t.getY(), 0, 0);
        t.setPosition(0, 0);
        t.notifyObservers();
        return new ArgumentNode(((Double) dist).toString());
    }

    public Node xCoordinate(List<Node> l) {
        return new ArgumentNode(((Double) t.getX()).toString());
    }

    public Node yCoordinate(List<Node> l) {
        return new ArgumentNode(((Double) t.getY()).toString());
    }

    public Node heading(List<Node> l) {
        return new ArgumentNode(((Double) t.getY()).toString());
    }

    public Node isPenDown(List<Node> l) {
        return new ArgumentNode(t.getIsPenDown() ? "1" : "0");
    }

    public Node isShowing(List<Node> l) {
        return new ArgumentNode(t.getIsShowing() ? "1" : "0");
    }

    private int[] parseTwoIntegers(List<Node> l) {
        Double tmp1 = Double.parseDouble(l.get(0).getCommandName());
        Double tmp2 = Double.parseDouble(l.get(1).getCommandName());
        int a = tmp1.intValue();
        int b = tmp2.intValue();
        int[] res = new int[2];
        res[0] = a;
        res[1] = b;
        return res;
    }

    private double[] parseTwoDoubles(List<Node> l) {
        double a = Integer.parseInt(l.get(0).getCommandName());
        double b = Integer.parseInt(l.get(1).getCommandName());
        double[] res = new double[2];
        res[0] = a;
        res[1] = b;
        return res;
    }

    public Node sum(List<Node> nodes) {
        Double sum = 0.0;
        for (Node node : nodes) {
            sum += Double.parseDouble(node.getCommandName());
        }
        return new ArgumentNode(sum.toString());
    }

    public Node difference(List<Node> l) {
        double[] args = parseTwoDoubles(l);
        Double res = args[0] - args[1];
        return new ArgumentNode(res.toString());
    }

    public Node product(List<Node> l) {
        double[] args = parseTwoDoubles(l);
        Double res = args[0] * args[1];
        return new ArgumentNode(res.toString());
    }

    public Node quotient(List<Node> l) {
        int[] args = parseTwoIntegers(l);
        Integer res = args[0] / args[1];
        return new ArgumentNode(res.toString());
    }

    public Node remainder(List<Node> l) {
        int[] args = parseTwoIntegers(l);
        Integer res = args[0] % args[1];
        return new ArgumentNode(res.toString());
    }

    public Node minus(List<Node> l) {
        Double res = Double.parseDouble(l.get(0).getCommandName());
        return new ArgumentNode(res.toString());
    }

    public Node random(List<Node> l) {
        Double tmp = Double.parseDouble(l.get(0).getCommandName());
        Integer max = tmp.intValue();
        Random random = new Random();
        Integer res = random.nextInt(max);
        return new ArgumentNode(res.toString());
    }

    public Node sine(List<Node> l) {
        Double res = Double.parseDouble(l.get(0).getCommandName());
        res = Math.sin(Math.toRadians(res));
        return new ArgumentNode(res.toString());
    }

    public Node cosine(List<Node> l) {
        Double res = Double.parseDouble(l.get(0).getCommandName());
        res = Math.cos(Math.toRadians(res));
        return new ArgumentNode(res.toString());
    }

    public Node tangent(List<Node> l) {
        Double res = Double.parseDouble(l.get(0).getCommandName());
        res = Math.tan(Math.toRadians(res));
        return new ArgumentNode(res.toString());
    }

    public Node arcTangent(List<Node> l) {
        Double res = Double.parseDouble(l.get(0).getCommandName());
        res = Math.atan(res);
        return new ArgumentNode(res.toString());
    }

    public Node naturalLog(List<Node> l) {
        Double res = Double.parseDouble(l.get(0).getCommandName());
        res = Math.log(res);
        return new ArgumentNode(res.toString());
    }

    public Node power(List<Node> l) {
        double[] args = parseTwoDoubles(l);
        Double res = Math.pow(args[0], args[1]);
        return new ArgumentNode(res.toString());
    }

    public Node pi(List<Node> l) {
        return new ArgumentNode(((Double) Math.PI).toString());
    }

    public Node lessThan(List<Node> l) {
        double[] args = parseTwoDoubles(l);
        boolean res = args[0] < args[1];
        return new ArgumentNode(res ? "1" : "0");
    }

    public Node greaterThan(List<Node> l) {
        double[] args = parseTwoDoubles(l);
        boolean res = args[0] > args[1];
        return new ArgumentNode(res ? "1" : "0");
    }

    public Node equal(List<Node> l) {
        double[] args = parseTwoDoubles(l);
        double e = 0.0001;
        boolean res = Math.abs(args[0] - args[1]) <= e;
        return new ArgumentNode(res ? "1" : "0");
    }

    public Node notEqual(List<Node> l) {
        double[] args = parseTwoDoubles(l);
        double e = 0.0001;
        boolean res = Math.abs(args[0] - args[1]) > e;
        return new ArgumentNode(res ? "1" : "0");
    }

    public Node and(List<Node> l) {
        int[] args = parseTwoIntegers(l);
        boolean res = (args[0] * args[1]) == 1;
        return new ArgumentNode(res ? "1" : "0");
    }

    public Node or(List<Node> l) {
        int[] args = parseTwoIntegers(l);
        boolean res = (args[0] + args[1]) >= 1;
        return new ArgumentNode(res ? "1" : "0");
    }

    public Node not(List<Node> l) {
        Double tmp = Double.parseDouble(l.get(0).getCommandName());
        Integer res = tmp.intValue();
        return new ArgumentNode(res == 1 ? "1" : "0");
    }

    public Node makeVariable(List<Node> l) {
        //check if it already exists, and if so replace it. If not create it
        String name = l.get(0).getCommandName(); //key
        ArgumentNode value = (ArgumentNode) l.get(1); //value
        m.createSetVariable(name, value);
        return value;
    }

    //       public Node makeVariable (List<Node> l) {
    //           String name = l.get(0).getCommandName();
    //           return new ArgumentNode(m.createVariable(name) ? "1" : "0");
    //       }
//
    //       public Node setVariable (List<Node> l) {
    //           String name = l.get(0).getCommandName();
    //
    //           return new ArgumentNode(m.setVariable(name, value) ? "1" : "0");
    //       }

    public Node getVariable(List<Node> l) throws IllegalCommandException {
        String name = l.get(0).getCommandName();
        System.out.println(name);
        return m.getVariable(name);
    }

    public Node repeat(List<Node> nodes) {
        //Input is a turtle and a list of BasicNodes. The first one is the repSize, and the second one
        //is the listNode
        Node repNode = nodes.get(0);
        int reps = Integer.parseInt(repNode.getCommandName());
        Node loopNode = new LoopNode(reps, "loop");

        Node list = nodes.get(1);
        for (Node n : list.getChildren()) {
            loopNode.addChild(n); // move children from the listNode to the loopNode
        }
        return loopNode;
    }

//
//        public Node doTimes (List<Node> l) {
//            //to-do
//        }

    public Node makeUserInstruction(List<Node> l) {
        String name = l.get(0).getCommandName();
        CommandNode c = (CommandNode) l.get(1);
        return new ArgumentNode(m.createInstruction(name, c) ? "1" : "0");
    }

    public Node getInstruction(List<Node> l) {
        String name = l.get(0).getCommandName();
        return m.getInstruction(name);
    }

//        public Node setBackground (List<Node> l) {
//
//        }
//
//        public Node setPenColor (List<Node> l) {
//
//        }
//
//        public Node setPenSize (List<Node> l) {
//
//        }
//
//        public Node setShape (List<Node> l) {
//
//        }
//
//        public Node setPalette (List<Node> l) {
//
//        }
//
//        public Node getPenColor (List<Node> l) {
//
//        }
//
//        public Node getShape (List<Node> l) {
//
//        }
//
//        public Node stamp (List<Node> l) {
//
//        }
//
//        public Node clearStamps (List<Node> l) {
//
//        }

}

