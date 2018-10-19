package frontend;

import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class PathDrawTransition extends Transition {
    private static final double EPSILON = 1e-12;
    private double startX;
    private double startY;
    private double startZ;
    private double deltaX;
    private double deltaY;
    private double deltaZ;

    /**
     * The target path of this {@code PathDrawTransition}.
     * <p>
     * It is not possible to change the target {@code Path} of a running
     * {@code PathDrawTransition}. If the value of {@code Path} is changed for
     * a running {@code PathDrawTransition}, the animation has to be stopped
     * and started again to pick up the new value.
     */
    private ObjectProperty<Path> path;
    private static final Path DEFAULT_PATH = null;

    public final void setPath(Path value) {
        if ((path != null) || (value != null /* DEFAULT_PATH */)) {
            nodeProperty().set(value);
        }
    }

    public final Path getPath() {
        return (path == null)? DEFAULT_PATH : path.get();
    }

    public final ObjectProperty<Path> nodeProperty() {
        if (path == null) {
            path = new SimpleObjectProperty<Path>(this, "node", DEFAULT_PATH);
        }
        return path;
    }

    private Path cachedNode;

    /**
     * The duration of this {@code PathDrawTransition}.
     * <p>
     * It is not possible to change the {@code duration} of a running
     * {@code PathDrawTransition}. If the value of {@code duration} is changed
     * for a running {@code PathDrawTransition}, the animation has to be
     * stopped and started again to pick up the new value.
     * <p>
     * Note: While the unit of {@code duration} is a millisecond, the
     * granularity depends on the underlying operating system and will in
     * general be larger. For example animations on desktop systems usually run
     * with a maximum of 60fps which gives a granularity of ~17 ms.
     *
     * Setting duration to value lower than {@link Duration#ZERO} will result
     * in {@link IllegalArgumentException}.
     *
     * @defaultValue 400ms
     */
    private ObjectProperty<Duration> duration;

    private static final Duration DEFAULT_DURATION = Duration.millis(400);

    public final void setDuration(Duration value) {
        if ((duration != null) || (!DEFAULT_DURATION.equals(value))) {
            durationProperty().set(value);
        }
    }

    public final Duration getDuration() {
        return (duration == null)? DEFAULT_DURATION : duration.get();
    }

    public final ObjectProperty<Duration> durationProperty() {
        if (duration == null) {
            duration = new ObjectPropertyBase<Duration>(DEFAULT_DURATION) {

                @Override
                public void invalidated() {
                    try {
                        setCycleDuration(getDuration());
                    } catch (IllegalArgumentException e) {
                        if (isBound()) {
                            unbind();
                        }
                        set(getCycleDuration());
                        throw e;
                    }
                }

                @Override
                public Object getBean() {
                    return PathDrawTransition.this;
                }

                @Override
                public String getName() {
                    return "duration";
                }
            };
        }
        return duration;
    }

    /**
     * Specifies the start X coordinate value of this
     * {@code PathDrawTransition}.
     * <p>
     * It is not possible to change {@code fromX} of a running
     * {@code PathDrawTransition}. If the value of {@code fromX} is changed for
     * a running {@code PathDrawTransition}, the animation has to be stopped
     * and started again to pick up the new value.
     *
     * @defaultValue {@code Double.NaN}
     */
    private DoubleProperty fromX;
    private static final double DEFAULT_FROM_X = Double.NaN;

    public final void setFromX(double value) {
        if ((fromX != null) || (!Double.isNaN(value))) {
            fromXProperty().set(value);
            getPath().getElements().add(new MoveTo(value,getFromY()));
        }
    }

    public final double getFromX() {
        return (fromX == null) ? DEFAULT_FROM_X : fromX.get();
    }

    public final DoubleProperty fromXProperty() {
        if (fromX == null) {
            fromX = new SimpleDoubleProperty(this, "fromX", DEFAULT_FROM_X);
        }
        return fromX;
    }

    /**
     * Specifies the start Y coordinate value of this
     * {@code PathDrawTransition}.
     * <p>
     * It is not possible to change {@code fromY} of a running
     * {@code PathDrawTransition}. If the value of {@code fromY} is changed for
     * a running {@code PathDrawTransition}, the animation has to be stopped
     * and started again to pick up the new value.
     *
     * @defaultValue {@code Double.NaN}
     */
    private DoubleProperty fromY;
    private static final double DEFAULT_FROM_Y = Double.NaN;

    public final void setFromY(double value) {
        if ((fromY != null) || (!Double.isNaN(value))) {
            fromYProperty().set(value);
            getPath().getElements().add(new MoveTo(getFromX(),value));
        }
    }

    public final double getFromY() {
        return (fromY == null)? DEFAULT_FROM_Y : fromY.get();
    }

    public final DoubleProperty fromYProperty() {
        if (fromY == null) {
            fromY = new SimpleDoubleProperty(this, "fromY", DEFAULT_FROM_Y);
        }
        return fromY;
    }

    /**
     * Specifies the stop X coordinate value of this {@code PathDrawTransition}.
     * <p>
     * It is not possible to change {@code toX} of a running
     * {@code PathDrawTransition}. If the value of {@code toX} is changed for a
     * running {@code PathDrawTransition}, the animation has to be stopped and
     * started again to pick up the new value.
     *
     * @defaultValue {@code Double.NaN}
     */
    private DoubleProperty toX;
    private static final double DEFAULT_TO_X = Double.NaN;

    public final void setToX(double value) {
        if ((toX != null) || (!Double.isNaN(value))) {
            toXProperty().set(value);
        }
    }

    public final double getToX() {
        return (toX == null)? DEFAULT_TO_X : toX.get();
    }

    public final DoubleProperty toXProperty() {
        if (toX == null) {
            toX = new SimpleDoubleProperty(this, "toX", DEFAULT_TO_X);
        }
        return toX;
    }

    /**
     * Specifies the stop Y coordinate value of this {@code PathDrawTransition}.
     * <p>
     * It is not possible to change {@code toY} of a running
     * {@code PathDrawTransition}. If the value of {@code toY} is changed for a
     * running {@code PathDrawTransition}, the animation has to be stopped and
     * started again to pick up the new value.
     *
     * @defaultValue {@code Double.NaN}
     */
    private DoubleProperty toY;
    private static final double DEFAULT_TO_Y = Double.NaN;

    public final void setToY(double value) {
        if ((toY != null) || (!Double.isNaN(value))) {
            toYProperty().set(value);
        }
    }

    public final double getToY() {
        return (toY == null)? DEFAULT_TO_Y : toY.get();
    }

    public final DoubleProperty toYProperty() {
        if (toY == null) {
            toY = new SimpleDoubleProperty(this, "toY", DEFAULT_TO_Y);
        }
        return toY;
    }

    /**
     * The constructor of {@code PathDrawTransition}
     *
     * @param duration
     *            The duration of the {@code PathDrawTransition}
     * @param path
     *            The {@code node} which will be translated
     */
    public PathDrawTransition(Duration duration, Path path) {
        setDuration(duration);
        setPath(path);
        setCycleDuration(duration);
    }

    /**
     * The constructor of {@code PathDrawTransition}
     *
     * @param duration
     *            The duration of the {@code PathDrawTransition}
     */
    public PathDrawTransition(Duration duration) {
        this(duration, null);
    }

    /**
     * The constructor of {@code PathDrawTransition}
     */
    public PathDrawTransition() {
        this(DEFAULT_DURATION, null);
    }

    @Override
    protected void interpolate(double frac) {
       /* //example 1
        if (!Double.isNaN(startX)) {
            cachedNode.setTranslateX(startX + frac * deltaX);
        }
        if (!Double.isNaN(startY)) {
            cachedNode.setTranslateY(startY + frac * deltaY);
        }
        if (!Double.isNaN(startZ)) {
            cachedNode.setTranslateZ(startZ + frac * deltaZ);
        }
        //example 2
        final int length = content.length();
        final int n = Math.round(length * (float) frac);
        text.setText(content.substring(0, n));*/
        //my version
        final float xnudge = (float) (getToX()-getFromX());
        final float ynudge = (float) (getToY()-getFromY());
        if(!Double.isNaN(startX)){
            getPath().getElements().add(new LineTo(getFromX()+(float)frac*xnudge,getFromY()+(float)frac*ynudge));
            //getPath().getElements().add(new LineTo(startX+frac*deltaX,startY+frac*deltaY));
        }
    }
}
