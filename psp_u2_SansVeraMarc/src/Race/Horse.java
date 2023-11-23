package Race;

public class Horse {
    private String name;
    private int speed;
    private int distance;

    public Horse(String name, int speed) {
        this.name = name;
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        if (speed > 70) {
            speed = speed;
        } else if (speed < 15) {
            speed = speed;
        } else {
            speed += HorseThread.changeSpeed();
        }
        return speed;
    }

    public int getDistance() {
        return move();
    }

    public int move() {
        return distance += (int) (speed / 3.6);
    }
}
