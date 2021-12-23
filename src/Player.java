import bagel.Input;
import bagel.Keys;
import bagel.util.Point;

public class Player extends Character {

    private int health = 100;
    private String direction = "Right";

    public Player(String rightImage, String leftImage, double speed, double x, double y) {
        super(rightImage, leftImage, speed, x, y);
    }

    public int getHealth() {
        return health;
    }

    public void gotHit() {
        this.health = this.health - 1;
    }

    public void resetPosition(int level) {
        if (level == 2) {
            this.position = new Point(200, 200);
        }

        if (level == 3) {
            this.position = new Point(300, 300);
        }
    }

    public void update(Input input, PlayArea arena) {

        if (input.isDown(Keys.LEFT)) {

            direction = "Left";
            if (this.getRect().left() >= (arena.getRect().left() + 10)) {
                this.position = new Point(position.x - this.speed, position.y);
            }
        }

        if (input.isDown(Keys.RIGHT)) {

            direction = "Right";
            if (this.getRect().right() <= (arena.getRect().right() - 10)) {
                this.position = new Point(position.x + this.speed, position.y);
            }
        }

        if (input.isDown(Keys.UP) && this.getRect().top() >= (arena.getRect().top() + 10)) {
            this.position = new Point(position.x, position.y - this.speed);
        }

        if (input.isDown(Keys.DOWN) && this.getRect().bottom() <= (arena.getRect().bottom() - 10)) {
            this.position = new Point(position.x, position.y + this.speed);
        }

        if (direction.equals("Right")) {
            this.characterIMGRight.draw(position.x, position.y);
        }
        else {
            this.characterIMGLeft.draw(position.x, position.y);
        }
    }
}
