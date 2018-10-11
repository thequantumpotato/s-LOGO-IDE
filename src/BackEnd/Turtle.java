package BackEnd;

/**
 * represents a turtle on the screen
 */
public class Turtle {

    private double direction;
    private double size;
    private Color color;
    private ImageView imageView;
    private Pen pen;

    public Turtle(){

    }

    public void move(double distance){
        imageView.setX(imageView.getX() + distance);
    }

    public void turn(double angle){
        direction += angle;
    }

    public void drawCircle(double centerX, double centerY, double radius){

    }


}
