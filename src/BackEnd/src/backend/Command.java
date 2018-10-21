package backend;

import backend.Nodes.BasicNode;
import backend.Nodes.LoopNode;
import backend.Nodes.argumentNode;

import java.util.List;

public class Command {

    public Command() {

    }

    public BasicNode Forward(Turtle t, List<BasicNode> values) {
        Double d = 0.0;
        for (BasicNode node : values) {
            d = Double.parseDouble(node.getCommandName());
            t.move(d);
        }

        t.notifyObservers();
        return new argumentNode(d.toString());
    }
    public BasicNode Backward (Turtle t, List<BasicNode> values){
        Double d = 0.0;
        for (BasicNode node : values) {
            d = Double.parseDouble(node.getCommandName());
            t.move(-1*d);
        }
        t.notifyObservers();
        return new argumentNode(d.toString());
    }
//
//            public String left (Turtle t, String degree){
//                Double d = Double.parseDouble(degree);
//                t.turn(d);
//                return degree;
//            }
//
    public BasicNode Right (Turtle t, List<BasicNode> values){
            Double d = null;
            for(BasicNode n:values){
                d = Double.parseDouble(n.getCommandName());
                t.turn(-d);
            }

            return new argumentNode(d.toString());
        }
//
//            public String setHeading (Turtle t, String degree){
//                Double d = Double.parseDouble(degree);
//                t.setHeading(d);
//                return degree;
//            }
//
//            public String setTowards (Turtle t, String arguments){
//                String[] position = arguments.split(" ");
//                double x = Double.parseDouble(position[0]) - t.getX();
//                double y = Double.parseDouble(position[1]) - t.getY();
//                double newDir = angleWithXAxis(x, y);
//                double oldDir = t.getDirection();
//                t.setHeading(newDir);
//                return ((Double) Math.abs(newDir - oldDir)).toString();
//            }
//
//            /**
//             * return angle with x axis given x and y
//             * @param x
//             * @param y
//             * @return positive number between 0 and 2PI
//             */
//            public double angleWithXAxis ( double x, double y){
//                double res = 0;
//                if (x == 0) {
//                    if (y > 0) {
//                        res = Math.PI / 2;
//                    }
//                    if (y < 0) {
//                        res = 1.5 * Math.PI;
//                    }
//                    return res;
//                }
//                double tmp = Math.atan(y / x);
//                if (x > 0) {
//                    res = tmp;
//                }
//                System.out.println("Worked!");
//                return d;
//            }
//            public String penUp (Turtle t){
//                t.liftPenUp();
//                return "0";
//            }
//
//            public String showTurtle (Turtle t){
//                t.show();
//                return "1";
//            }
//
//            public String hideTurtle (Turtle t){
//                t.hide();
//                return "0";
//            }
//
//            public String home (Turtle t){
//                double dist = calcDist(t.getX(), t.getY(), 0, 0);
//                t.setPosition(0, 0);
//                return ((Double) dist).toString();
//            }
//
//    public String clearScreen(Turtle t, ModelController m){
//        m.clearScreen();
//        return this.home(t);
//    }
//
//    public String left(Turtle t, String degree){
//        Double d = Double.parseDouble(degree);
//        t.turn(d);
//        return degree;
//    }
//
    //   public Double Right(Turtle t, List<BasicNode> values){
    //       double d = 0.0;
    //       for (BasicNode node : values) {
    //           d = Double.parseDouble(node.getCommandName());
    //           t.turn(-d);
    //       }
    //       t.notifyObservers();
    //       return d.toString();
    //   }
//
//    public String setHeading(Turtle t, String degree){
//        Double d = Double.parseDouble(degree);
//        t.setHeading(d);
//        return degree;
//    }
//
//    public String setTowards(Turtle t, String arguments){
//        String[] position = arguments.split(" ");
//        double x = Double.parseDouble(position[0]) - t.getX();
//        double y = Double.parseDouble(position[1]) - t.getY();
//        double newDir = angleWithXAxis(x, y);
//        double oldDir = t.getDirection();
//        t.setHeading(newDir);
//        return ((Double)Math.abs(newDir - oldDir)).toString();
//    }
//
//    /**
//     * return angle with x axis given x and y
//     * @param x
//     * @param y
//     * @return positive number between 0 and 2PI
//     */
//    private double angleWithXAxis(double x, double y){
//        double res = 0;
//        if(x == 0){
//            if(y > 0){
//                res = Math.PI / 2;
//            }
//            if(y < 0){
//                res = 1.5*Math.PI;
//            }
//            return res;
//        }
//        double tmp = Math.atan(y/x);
//        if(x > 0){
//            res = tmp;
//        }
//        else{
//            res = tmp + Math.PI;
//        }
//        if(res <= 0){
//            res += 2 * Math.PI;
//        }
//        return res;
//    }
//
//    public String setPosition(Turtle t, String arguments){
//        String[] position = arguments.split(" ");
//        double x = Double.parseDouble(position[0]);
//        double y = Double.parseDouble(position[1]);
//        double distance = calcDist(x, y, t.getX(), t.getY());
//        t.setPosition(x, y);
//        return ((Double) distance).toString();
//    }
//
//    private double calcDist(double x1, double y1, double x2, double y2){
//        return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
//    }
//
//    public String penDown(Turtle t){
//        t.putPenDown();
//        return "1";
//    }
//
//    public String penUp(Turtle t){
//        t.liftPenUp();
//        return "0";
//    }
//
//    public String showTurtle(Turtle t){
//        t.show();
//        return "1";
//    }
//
//    public String hideTurtle(Turtle t){
//        t.hide();
//        return "0";
//    }
//
//    public String home(Turtle t){
//        double dist = calcDist(t.getX(), t.getY(), 0, 0);
//        t.setPosition(0, 0);
//        return ((Double)dist).toString();
//    }
//
////    public String clearScreen(Turtle t, Model m){
////        m.clearScreen();
////        return this.home(t);
////    }
//
//    public String xCoordinate(Turtle t){
//        return ((Double) t.getX()).toString();
//    }
//
//    public String yCoordinate(Turtle t){
//        return ((Double) t.getY()).toString();
//    }
//
//    public String heading(Turtle t){
//        return ((Double) t.getDirection()).toString();
//    }
//
//    public String isPenDown(Turtle t){
//        return t.getIsPenDown() ? "1":"0";
//    }
//
//    public String isShowing(Turtle t){
//        return t.getIsShowing() ? "1":"0";
//    }
//
//    private int[] parseIntegers(String arguments){
//        String[] tmp = arguments.split(" ");
//        int a = Integer.parseInt(tmp[0]);
//        int b = Integer.parseInt(tmp[1]);
//        int[] res = new int[2];
//        res[0] = a;
//        res[1] = b;
//        return res;
//    }
//
    public BasicNode Sum(Turtle t, List<BasicNode> nodes) {
        Double sum = 0.0;
        for (BasicNode node : nodes) {
            sum += Double.parseDouble(node.getCommandName());
        }
        return new argumentNode(sum.toString());
    }
//
//    public String difference(String arguments){
//        int[] args = parseIntegers(arguments);
//        return ((Integer) (args[0] - args[1])).toString();
//    }
//
//    public String product(String arguments){
//        int[] args = parseIntegers(arguments);
//        return ((Integer) (args[0]*args[1])).toString();
//    }
//
//    public String quotient(String arguments){
//        int[] args = parseIntegers(arguments);
//        if(args[1] == 0){
//            System.err.println("cannot divide by zero");
//            return null;
//        }
//        return ((Integer) (args[0]/args[1])).toString();
//    }
//
//    public String remainder(String arguments){
//        int[] args = parseIntegers(arguments);
//        if(args[1] == 0){
//            System.err.println("cannot divide by zero");
//            return null;
//        }
//        return ((Integer) (args[0]/args[1])).toString();
//    }
//
//    public String minus(String arguments){
//        int num = Integer.parseInt(arguments);
//        return ((Integer)(-1*num)).toString();
//    }
//
//    public String random(String arguments){
//        int max_num = Integer.parseInt(arguments);
//        return ((Integer)new Random().nextInt(max_num)).toString();
//    }
//
//    public String sine(String arguments){
//        double degrees = Double.parseDouble(arguments);
//        return ((Double)Math.sin(Math.toRadians(degrees))).toString();
//    }
//
//    public String cosine(String arguments){
//        double degrees = Double.parseDouble(arguments);
//        return ((Double)Math.cos(Math.toRadians(degrees))).toString();
//    }
//
//    public String tangent(String arguments){
//        double degrees = Double.parseDouble(arguments);
//        return ((Double)Math.tan(Math.toRadians(degrees))).toString();
//    }
//
//    public String arcTangent(String arguments){
//        double value = Double.parseDouble(arguments);
//        return ((Double)Math.atan(value)).toString();
//    }
//
//    public String naturalLog(String arguments){
//        double value = Double.parseDouble(arguments);
//        return ((Double)Math.log(value)).toString();
//    }
//
//    public String power(String arguments){
//        double[] tmp = parseDoubles(arguments);
//        return ((Double)Math.pow(tmp[0], tmp[1])).toString();
//    }
//
//    private double[] parseDoubles(String arguments){
//        String[] tmp = arguments.split(" ");
//        double a = Double.parseDouble(tmp[0]);
//        double b = Double.parseDouble(tmp[1]);
//        double[] res = new double[2];
//        res[0] = a;
//        res[1] = b;
//        return res;
//    }
//
//    public String pi(String arguments){
//        return ((Double)Math.PI).toString();
//    }
//
    public BasicNode LessThan(Turtle t, List<BasicNode> nodes){
        double first = Double.parseDouble(nodes.get(0).getCommandName());
        double second = Double.parseDouble(nodes.get(1).getCommandName());
        //No need to notify observers here
        String outcome = first < second ? "1":"0";
        return new argumentNode(outcome);
    }
//
//    public String greaterThan(String arguments){
//        double[] tmp = parseDoubles(arguments);
//        return tmp[0] > tmp[1] ? "1":"0";
//    }
//
//    public String equal(String arguments){
//        double[] tmp = parseDoubles(arguments);
//        return tmp[0] == tmp[1] ? "1":"0";
//    }
//
//    public String notEqual(String arguments){
//        double[] tmp = parseDoubles(arguments);
//        return tmp[0] != tmp[1] ? "1":"0";
//    }
//
//    public String and(String arguments){
//        int[] tmp = parseIntegers(arguments);
//        return tmp[0] == 1 && tmp[1] == 1 ? "1":"0";
//    }
//
//    public String or(String arguments){
//        int[] tmp = parseIntegers(arguments);
//        return tmp[0] == 0 && tmp[1] == 0 ? "0":"1";
//    }
//
//    public String not(String arguments){
//        int tmp = Integer.parseInt(arguments);
//        return tmp == 1 ? "1":"0";
//    }
//
//    public String makeVariable(String arguments){
//
//    }
//
    public BasicNode Repeat(Turtle t, List<BasicNode> nodes){
        //Input is a turtle and a list of BasicNodes. The first one is the repSize, and the second one
        //is the listNode
        BasicNode repNode = nodes.get(0);
        int reps = Integer.parseInt(repNode.getCommandName());
        BasicNode loopNode = new LoopNode(reps, "loop");

        BasicNode list = nodes.get(1);
        for(BasicNode n: list.getChildren()){
            loopNode.addChild(n); // move children from the listNode to the loopNode
        }
        return loopNode;
    }
//
//    public String doTimes(String arguments){
//
//    }
//
//    public String makeUserInstructions(String arguments){
//
//    }
//
//    public String setBackground(View view, String arguments){
//
//    }
//
//    public String setPenColor(Turtle t, String arguments){
//
//    }
//
//    public String setPenSize(Turtle t, String arguments){
//
//    }
//
//    public String setShape(String arguments){
//
//    }
//
//    public String setPalette(String arguments){
//
//    }
//
//    public String getPenColor(Turtle t, String arguments){
//
//    }
//
//    public String getShape(String arguments){
//
//    }
//
//    public String stamp(String arguments){
//
//    }
//
//    public String clearStamps(String arguments){
//
//    }

//    public static void main(String[] args){
//        System.out.println(Command.angleWithXAxis(1.0, 1.0));
//    }


}

