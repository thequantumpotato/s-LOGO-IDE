package frontend;

import Nodes.ArgumentNode;

import java.util.List;

public class Command {

    public ArgumentNode forward(Turtle t, List<ArgumentNode> l){
        String distance = l.get(0).getCommandName();
        Double d = Double.parseDouble(distance);
        t.move(d);
        return new ArgumentNode(distance);
    }

//    public String backward(Turtle t, List<ArgumentNode> l){
//        String distance = l.get(0).getCommandName();
//        Double d = Double.parseDouble(distance);
//        t.move(-d);
//        return distance;
//    }
//
//    public String left(Turtle t, List<ArgumentNode> l){
//        String degree = l.get(0).getCommandName();
//        Double d = Double.parseDouble(degree);
//        t.turn(d);
//        return degree;
//    }
//
//    public String right(Turtle t, List<ArgumentNode> l){
//        String degree = l.get(0).getCommandName();
//        Double d = Double.parseDouble(degree);
//        t.turn(-d);
//        return degree;
//    }
//
//    public String setHeading(Turtle t, List<ArgumentNode> l){
//        String degree = l.get(0).getCommandName();
//        Double d = Double.parseDouble(degree);
//        t.setHeading(d);
//        return degree;
//    }
//
//    private double[] getDoubleArguments(List<ArgumentNode> l){
//        double[] position = new double[2];
//        for(int i = 0; i < l.size(); i++){
//            position[i] = Double.parseDouble(l.get(i).getCommandName());
//        }
//        return position;
//    }
//
//    private int[] getIntArguments(List<ArgumentNode> l){
//        int[] position = new int[2];
//        for(int i = 0; i < l.size(); i++){
//            position[i] = Integer.parseInt(l.get(i).getCommandName());
//        }
//        return position;
//    }
//
//    public String setTowards(Turtle t, List<ArgumentNode> l){
//        double[] position = getDoubleArguments(l);
//        double x = position[0] - t.getX();
//        double y = position[1] - t.getY();
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
//    public double angleWithXAxis(double x, double y){
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
//    public String setPosition(Turtle t, List<ArgumentNode> l){
//        double[] position = getDoubleArguments(l);
//        double x = position[0];
//        double y = position[1];
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
////    public String clearScreen(Turtle t, ModelController m){
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
//    public String sum(List<ArgumentNode> l){
//        double[] args = getDoubleArguments(l);
//        return ((Double) (args[0]+args[1])).toString();
//    }
//
//    public String difference(List<ArgumentNode> l){
//        double[] args = getDoubleArguments(l);
//        return ((Double) (args[0] - args[1])).toString();
//    }
//
//    public String product(List<ArgumentNode> l){
//        double[] args = getDoubleArguments(l);
//        return ((Double) (args[0]*args[1])).toString();
//    }
//
//    public String quotient(List<ArgumentNode> l){
//        double[] args = getDoubleArguments(l);
//        if(args[1] == 0){
//            System.err.println("cannot divide by zero");
//            return null;
//        }
//        return ((Double) (args[0]/args[1])).toString();
//    }
//
//    public String remainder(List<ArgumentNode> l){
//        double[] args = getDoubleArguments(l);
//        if(args[1] == 0){
//            System.err.println("cannot divide by zero");
//            return null;
//        }
//        return ((Double) (args[0]/args[1])).toString();
//    }
//
//    public String minus(List<ArgumentNode> l){
//        double num = Double.parseDouble(l.get(0).getCommandName());
//        return ((Double)(-1*num)).toString();
//    }

//    public String random(List<ArgumentNode> l){
//        double max_num = Double.parseDouble(l.get(0).getCommandName());
//        return ((Double)new Random().nextDouble(max_num)).toString();
//    }
//
//    public String sine(List<ArgumentNode> l){
//        double degrees = Double.parseDouble(arguments);
//        return ((Double)Math.sin(Math.toRadians(degrees))).toString();
//    }
//
//    public String cosine(List<ArgumentNode> l){
//        double degrees = Double.parseDouble(arguments);
//        return ((Double)Math.cos(Math.toRadians(degrees))).toString();
//    }
//
//    public String tangent(List<ArgumentNode> l){
//        double degrees = Double.parseDouble(arguments);
//        return ((Double)Math.tan(Math.toRadians(degrees))).toString();
//    }
//
//    public String arcTangent(List<ArgumentNode> l){
//        double value = Double.parseDouble(arguments);
//        return ((Double)Math.atan(value)).toString();
//    }
//
//    public String naturalLog(List<ArgumentNode> l){
//        double value = Double.parseDouble(arguments);
//        return ((Double)Math.log(value)).toString();
//    }
//
//    public String power(List<ArgumentNode> l){
//        double[] tmp = parseDoubles(arguments);
//        return ((Double)Math.pow(tmp[0], tmp[1])).toString();
//    }
//
//    public String pi(List<ArgumentNode> l){
//        return ((Double)Math.PI).toString();
//    }
//
//    public String lessThan(List<ArgumentNode> l){
//        double[] tmp = parseDoubles(arguments);
//        return tmp[0] < tmp[1] ? "1":"0";
//    }
//
//    public String greaterThan(List<ArgumentNode> l){
//        double[] tmp = parseDoubles(arguments);
//        return tmp[0] > tmp[1] ? "1":"0";
//    }
//
//    public String equal(List<ArgumentNode> l){
//        double[] tmp = parseDoubles(arguments);
//        return tmp[0] == tmp[1] ? "1":"0";
//    }
//
//    public String notEqual(List<ArgumentNode> l){
//        double[] tmp = parseDoubles(arguments);
//        return tmp[0] != tmp[1] ? "1":"0";
//    }
//
//    public String and(List<ArgumentNode> l){
//        int[] tmp = parseIntegers(arguments);
//        return tmp[0] == 1 && tmp[1] == 1 ? "1":"0";
//    }
//
//    public String or(List<ArgumentNode> l){
//        int[] tmp = parseIntegers(arguments);
//        return tmp[0] == 0 && tmp[1] == 0 ? "0":"1";
//    }
//
//    public String not(List<ArgumentNode> l){
//        int tmp = Integer.parseInt(arguments);
//        return tmp == 1 ? "1":"0";
//    }
//
////    public String makeVariable(List<ArgumentNode> l){
////
////    }
////
////    public String repeat(List<ArgumentNode> l){
////
////    }
////
////    public String doTimes(List<ArgumentNode> l){
////
////    }
////
////    public String makeUserInstructions(List<ArgumentNode> l){
////
////    }
////
////    public String setBackground(View view, List<ArgumentNode> l){
////
////    }
////
////    public String setPenColor(Turtle t, List<ArgumentNode> l){
////
////    }
////
////    public String setPenSize(Turtle t, List<ArgumentNode> l){
////
////    }
////
////    public String setShape(List<ArgumentNode> l){
////
////    }
////
////    public String setPalette(List<ArgumentNode> l){
////
////    }
////
////    public String getPenColor(Turtle t, List<ArgumentNode> l){
////
////    }
////
////    public String getShape(List<ArgumentNode> l){
////
////    }
////
////    public String stamp(List<ArgumentNode> l){
////
////    }
////
////    public String clearStamps(List<ArgumentNode> l){
////
////    }
//
////    public static void main(String[] args){
////        System.out.println(Command.angleWithXAxis(1.0, 1.0));
////    }
//
//    public static void main(String[] args){
//        Command c = new Command();
//        double tmp = c.angleWithXAxis(1, 1);
//        System.out.println(tmp);
//    }

}
