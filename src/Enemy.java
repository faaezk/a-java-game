import bagel.util.Point;

public class Enemy extends Character {

    public Enemy(String rightImage, String leftImage, double speed, double x, double y) {
        super(rightImage, leftImage, speed, x, y);
    }

    public void resetPosition(int level) {
        if (level == 2) {
            this.position = new Point(200, 600);
        }
    }

    public void update(Point playerPosition) {

        double moveX = 0;
        double moveY;

        if (position.distanceTo(playerPosition) > 10.0) {

            moveX = (playerPosition.x - position.x)/30.0;
            moveY = (playerPosition.y - position.y)/30.0;

            position = new Point(position.x + moveX, position.y + moveY);
        }

        if (moveX < 0) {
            characterIMGLeft.draw(position.x, position.y);
        }
        else {
            characterIMGRight.draw(position.x, position.y);
        }
    }
}
