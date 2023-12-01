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
        if (speed >= 70) {
            speed = 70;
        } else if (speed <= 15) {
            speed = 15;
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
