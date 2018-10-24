package frontend.Display;

import javafx.animation.Transition;
import javafx.beans.property.*;
import javafx.scene.shape.Line;
import javafx.util.Duration;

/**
 * Custom transition class used to create a line-drawing effect.
 *
 * @author bpx
 */
public class LineDrawTransition extends Transition {
    private static final double EPSILON = 1e-12;
    private static final Line DEFAULT_PATH = null;
    private static final Duration DEFAULT_DURATION = Duration.millis(400);
    private static final double DEFAULT_FROM_X = Double.NaN;
    private static final double DEFAULT_FROM_Y = Double.NaN;
    private static final double DEFAULT_TO_X = Double.NaN;
    private static final double DEFAULT_TO_Y = Double.NaN;
    private double startX;
    private double startY;
    private double startZ;
    private double deltaX;
    private double deltaY;
    private double deltaZ;
    /**
     * The target line of this {@code LineDrawTransition}.
     * <p>
     * It is not possible to change the target {@code Line} of a running
     * {@code LineDrawTransition}. If the value of {@code Line} is changed for
     * a running {@code LineDrawTransition}, the animation has to be stopped
     * and started again to pick up the new value.
     */
    private ObjectProperty<Line> line;
    private Line cachedNode;
    /**
     * The duration of this {@code LineDrawTransition}.
     * <p>
     * It is not possible to change the {@code duration} of a running
     * {@code LineDrawTransition}. If the value of {@code duration} is changed
     * for a running {@code LineDrawTransition}, the animation has to be
     * stopped and started again to pick up the new value.
     * <p>
     * Note: While the unit of {@code duration} is a millisecond, the
     * granularity depends on the underlying operating system and will in
     * general be larger. For example animations on desktop systems usually run
     * with a maximum of 60fps which gives a granularity of ~17 ms.
     * <p>
     * Setting duration to value lower than {@link Duration#ZERO} will result
     * in {@link IllegalArgumentException}.
     *
     * @defaultValue 400ms
     */
    private ObjectProperty<Duration> duration;
    /**
     * Specifies the start X coordinate value of this
     * {@code LineDrawTransition}.
     * <p>
     * It is not possible to change {@code fromX} of a running
     * {@code LineDrawTransition}. If the value of {@code fromX} is changed for
     * a running {@code LineDrawTransition}, the animation has to be stopped
     * and started again to pick up the new value.
     *
     * @defaultValue {@code Double.NaN}
     */
    private DoubleProperty fromX;
    /**
     * Specifies the start Y coordinate value of this
     * {@code LineDrawTransition}.
     * <p>
     * It is not possible to change {@code fromY} of a running
     * {@code LineDrawTransition}. If the value of {@code fromY} is changed for
     * a running {@code LineDrawTransition}, the animation has to be stopped
     * and started again to pick up the new value.
     *
     * @defaultValue {@code Double.NaN}
     */
    private DoubleProperty fromY;
    /**
     * Specifies the stop X coordinate value of this {@code LineDrawTransition}.
     * <p>
     * It is not possible to change {@code toX} of a running
     * {@code LineDrawTransition}. If the value of {@code toX} is changed for a
     * running {@code LineDrawTransition}, the animation has to be stopped and
     * started again to pick up the new value.
     *
     * @defaultValue {@code Double.NaN}
     */
    private DoubleProperty toX;
    /**
     * Specifies the stop Y coordinate value of this {@code LineDrawTransition}.
     * <p>
     * It is not possible to change {@code toY} of a running
     * {@code LineDrawTransition}. If the value of {@code toY} is changed for a
     * running {@code LineDrawTransition}, the animation has to be stopped and
     * started again to pick up the new value.
     *
     * @defaultValue {@code Double.NaN}
     */
    private DoubleProperty toY;

    /**
     * The constructor of {@code LineDrawTransition}
     *
     * @param duration The duration of the {@code LineDrawTransition}
     * @param line     The {@code node} which will be translated
     */
    public LineDrawTransition(Duration duration, Line line) {
        setDuration(duration);
        setLine(line);
        setCycleDuration(duration);
    }

    /**
     * The constructor of {@code LineDrawTransition}
     *
     * @param duration The duration of the {@code LineDrawTransition}
     */
    public LineDrawTransition(Duration duration) {
        this(duration, null);
    }

    /**
     * The constructor of {@code LineDrawTransition}
     */
    public LineDrawTransition() {
        this(DEFAULT_DURATION, null);
    }

    public final Line getLine() {
        return (line == null) ? DEFAULT_PATH : line.get();
    }

    public final void setLine(Line value) {
        if ((line != null) || (value != null /* DEFAULT_PATH */)) {
            nodeProperty().set(value);
        }
    }

    public final ObjectProperty<Line> nodeProperty() {
        if (line == null) {
            line = new SimpleObjectProperty<Line>(this, "node", DEFAULT_PATH);
        }
        return line;
    }

    public final Duration getDuration() {
        return (duration == null) ? DEFAULT_DURATION : duration.get();
    }

    public final void setDuration(Duration value) {
        if ((duration != null) || (!DEFAULT_DURATION.equals(value))) {
            durationProperty().set(value);
        }
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
                    return LineDrawTransition.this;
                }

                @Override
                public String getName() {
                    return "duration";
                }
            };
        }
        return duration;
    }

    public final double getFromX() {
        return (fromX == null) ? DEFAULT_FROM_X : fromX.get();
    }

    public final void setFromX(double value) {
        if ((fromX != null) || (!Double.isNaN(value))) {
            fromXProperty().set(value);
            getLine().setStartX(value);
        }
    }

    public final DoubleProperty fromXProperty() {
        if (fromX == null) {
            fromX = new SimpleDoubleProperty(this, "fromX", DEFAULT_FROM_X);
        }
        return fromX;
    }

    public final double getFromY() {
        return (fromY == null) ? DEFAULT_FROM_Y : fromY.get();
    }

    public final void setFromY(double value) {
        if ((fromY != null) || (!Double.isNaN(value))) {
            fromYProperty().set(value);
            getLine().setStartY(value);
        }
    }

    public final DoubleProperty fromYProperty() {
        if (fromY == null) {
            fromY = new SimpleDoubleProperty(this, "fromY", DEFAULT_FROM_Y);
        }
        return fromY;
    }

    public final double getToX() {
        return (toX == null) ? DEFAULT_TO_X : toX.get();
    }

    public final void setToX(double value) {
        if ((toX != null) || (!Double.isNaN(value))) {
            toXProperty().set(value);
        }
        getLine().setEndX(getFromX());
    }

    public final DoubleProperty toXProperty() {
        if (toX == null) {
            toX = new SimpleDoubleProperty(this, "toX", DEFAULT_TO_X);
        }
        return toX;
    }

    public final double getToY() {
        return (toY == null) ? DEFAULT_TO_Y : toY.get();
    }

    public final void setToY(double value) {
        if ((toY != null) || (!Double.isNaN(value))) {
            toYProperty().set(value);
        }
        getLine().setEndY(getFromY());
    }

    public final DoubleProperty toYProperty() {
        if (toY == null) {
            toY = new SimpleDoubleProperty(this, "toY", DEFAULT_TO_Y);
        }
        return toY;
    }

    @Override
    protected void interpolate(double frac) {
        final float xnudge = (float) (getToX() - getFromX());
        final float ynudge = (float) (getToY() - getFromY());
        if (!Double.isNaN(startX)) {
            getLine().setEndX(getFromX() + (float) frac * xnudge);
            getLine().setEndY(getFromY() + (float) frac * ynudge);
        }
    }
}
