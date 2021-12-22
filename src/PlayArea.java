import bagel.Image;
import bagel.util.Rectangle;

public class PlayArea {

    private Rectangle area;
    private Image border;
    private final Rectangle LEVEL1_AREA = new Rectangle(0,0,1200, 750);
    private final Rectangle LEVEL2_AREA = new Rectangle(150,93.5,900, 563);
    private final Image LEVEL1_BORDER = new Image("res/level1border.png");
    private final Image LEVEL2_BORDER = new Image("res/level2border.png");

    public PlayArea(int level) {

        if (level == 2) {
            area = LEVEL2_AREA;
            border = LEVEL2_BORDER;
        }
        else {
            area = LEVEL1_AREA;
            border = LEVEL1_BORDER;
        }
    }

    public Rectangle getRect() {
        return area;
    }

    public void update() {
        border.drawFromTopLeft(area.topLeft().x, area.topLeft().y);
    }

}
