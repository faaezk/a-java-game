import bagel.Image;
import bagel.util.Rectangle;

public class PlayArea {

    private final Rectangle LEVEL1_AREA = new Rectangle(0,0,1200, 750);
    private final Rectangle LEVEL2_AREA = new Rectangle(150,93.5,900, 563);
    private final Rectangle LEVEL3_AREA = new Rectangle(300,187,600, 375);
    private final Image LEVEL1_BORDER = new Image("res/level1border.png");
    private final Image LEVEL2_BORDER = new Image("res/level2border.png");
    private final Image LEVEL3_BORDER = new Image("res/level3border.png");

    private Rectangle area = LEVEL1_AREA;
    private Image border = LEVEL1_BORDER;

    public PlayArea(int level) {

        if (level == 2) {
            area = LEVEL2_AREA;
            border = LEVEL2_BORDER;
        }

        if (level == 3) {
            area = LEVEL3_AREA;
            border = LEVEL3_BORDER;
        }
    }

    public Rectangle getRect() {
        return area;
    }

    public void update() {
        border.drawFromTopLeft(area.topLeft().x, area.topLeft().y);
    }

}
