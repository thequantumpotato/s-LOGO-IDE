package backend;

public class Command {

    public Command(){

    }

    public String forward(Turtle t, String distance){
        Double d = Double.parseDouble(distance);
        t.move(d);
        return distance;
    }

    public String backward(Turtle t, String distance){
        Double d = Double.parseDouble(distance);
        t.move(-d);
        return distance;
    }

    public String left(Turtle t, String degree){
        Double d = Double.parseDouble(degree);
        t.turn(d);
        return degree;
    }

    public String right(Turtle t, String degree){
        Double d = Double.parseDouble(degree);
        t.turn(-d);
        return degree;
    }

    public String setHeading(Turtle t, String degree){
        Double d = Double.parseDouble(degree);
        t.setHeading(d);
        return degree;
    }

    public String setTowards(Turtle t, String arguments){
        String[] position = arguments.split(" ");
        double x = Double.parseDouble(position[0]) - t.getX();
        double y = Double.parseDouble(position[1]) - t.getY();
        double newDir = angleWithXAxis(x, y);
        double oldDir = t.getDirection();
        t.setHeading(newDir);
        return ((Double)Math.abs(newDir - oldDir)).toString();
    }

    /**
     * return angle with x axis given x and y
     * @param x
     * @param y
     * @return positive number between 0 and 2PI
     */
    private double angleWithXAxis(double x, double y){
        double res = 0;
        if(x == 0){
            if(y > 0){
                res = Math.PI / 2;
            }
            if(y < 0){
                res = 1.5*Math.PI;
            }
            return res;
        }
        double tmp = Math.atan(y/x);
        if(x > 0){
            res = tmp;
        }
        else{
            res = tmp + Math.PI;
        }
        if(res <= 0){
            res += 2 * Math.PI;
        }
        return res;
    }

    public String setPosition(Turtle t, String arguments){
        String[] position = arguments.split(" ");
        double x = Double.parseDouble(position[0]);
        double y = Double.parseDouble(position[1]);
        double distance = calcDist(x, y, t.getX(), t.getY());
        t.setPosition(x, y);
        return ((Double) distance).toString();
    }

    private double calcDist(double x1, double y1, double x2, double y2){
        return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }

    public String penDown(Turtle t){
        t.penDown();
        return "1";
    }

    public String penUp(Turtle t){
        t.penUp();
        return "0";
    }

    public String showTurtle(Turtle t){
        t.show();
        return "1";
    }

    public String hideTurtle(Turtle t){
        t.hide();
        return "0";
    }

    public String home(Turtle t){
        double dist = calcDist(t.getX(), t.getY(), 0, 0);
        t.setPosition(0, 0);
        return ((Double)dist).toString();
    }

    //public String clearScreen(){
//
    //}
//
    //public String xCoordinate(Turtle t){
//
    //}
//
    //public String yCoordinate(Turtle t){
//
    //}
//
    //public String heading(Turtle t){
//
    //}
//
    //public String isPenDown(Turtle t){
//
    //}
//
    //public String isShowing(Turtle t){
//
    //}
//
    //public String sum(String arguments){
//
    //}
//
    //public String difference(String arguments){
//
    //}
//
    //public String product(String arguments){
//
    //}
//
    //public String quotient(String arguments){
//
    //}
//
    //public String remainder(String arguments){
//
    //}
//
    //public String minus(String arguments){
//
    //}
//
    //public String random(String arguments){
//
    //}
//
    //public String sine(String arguments){
//
    //}
//
    //public String cosine(String arguments){
//
    //}
//
    //public String tangent(String arguments){
//
    //}
//
    //public String arcTangent(String arguments){
//
    //}
//
    //public String naturalLog(String arguments){
//
    //}
//
    //public String power(String arguments){
//
    //}
//
    //public String pi(String arguments){
//
    //}
//
    //public String lessThan(String arguments){
//
    //}
//
    //public String greaterThan(String arguments){
//
    //}
//
    //public String equal(String arguments){
//
    //}
//
    //public String notEqual(String arguments){
//
    //}
//
    //public String and(String arguments){
//
    //}
//
    //public String or(String arguments){
//
    //}
//
    //public String not(String arguments){
//
    //}
//
    //public String makeVariable(String arguments){
//
    //}
//
    //public String repeat(String arguments){
//
    //}
//
    //public String doTimes(String arguments){
//
    //}
//
    //public String makeUserInstructions(String arguments){
//
    //}
//
    //public String setBackground(String arguments){
//
    //}
//
    //public String setPenColor(String arguments){
//
    //}
//
    //public String setPenSize(String arguments){
//
    //}
//
    //public String setShape(String arguments){
//
    //}
//
    //public String setPalette(String arguments){
//
    //}
//
    //public String getPenColor(String arguments){
//
    //}
//
    //public String getShape(String arguments){
//
    //}
//
    //public String stamp(String arguments){
//
    //}
//
    //public String clearStamps(String arguments){
//
    //}

//    public static void main(String[] args){
//        System.out.println(Command.angleWithXAxis(1.0, 1.0));
//    }

}
