package frontend.GUI.SubViews;

import frontend.API.SubView;
import frontend.GUI.View;
import frontend.Util.Sprite;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A ScrollPane that displays all the sprites
 *
 * @author Vincent Liu
 */

public class TurtleImageView implements SubView {
    public static final double SIZE = 50.0;
    private final TitledPane turtleImageView = new TitledPane();
    private final ScrollPane imagePane = new ScrollPane();
    private final HBox imageBox = new HBox();
    private Map<ImageView, Integer> idMap = new HashMap<>();
    private View myView;
    private int index = 0;

    public TurtleImageView(View myView_) {
        myView = myView_;
        setUpPane();
    }

    private void setUpPane() {
        turtleImageView.setText("Turtle Images");
        turtleImageView.setContent(imagePane);

        imageBox.setFillHeight(true);

        imagePane.setContent(imageBox);
        turtleImageView.getStyleClass().add("imageView");
    }

    public void addImage(Image image){
        var newSprite = new ImageView(image);
        newSprite.setFitWidth(SIZE);
        newSprite.setFitHeight(SIZE);
        idMap.put(newSprite, index);
        imageBox.getChildren().add(newSprite);
        newSprite.setOnMouseClicked(e -> {
            final FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(myView.getMyStage());
            if (file != null) {
                Image newImg = new Image(file.toURI().toString());
                changeThisImage(newSprite, newImg);
                myView.changeOneTurtleImage(newImg, idMap.get(newSprite));
            }
        });
        index++;
    }

    private void changeThisImage(ImageView imgView, Image img){
        imgView.setImage(img);
    }

    @Override
    public Node getView() {
        return turtleImageView;
    }
}
