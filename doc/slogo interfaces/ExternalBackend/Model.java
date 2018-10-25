//External ExternalAPI backend.Model

// These are the methods that are callable by the front-end, essentially just ways that the front-end 
//can directly get the positions of the turtle, or set the positions of the turtle
interface Model {
    public Point2D getTurtlePos();

    public void setTurtlePos(Point2D newPos);

    public Point2D getTurtleAngle();

    public void setTurtleAndgle(Point2D newPos)
}