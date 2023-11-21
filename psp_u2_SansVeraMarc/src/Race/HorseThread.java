package Race;

public class HorseThread extends Thread {
    private Horse horse;
    private int distance;

    public HorseThread(Horse horse, int distance) {
        this.horse = horse;
        this.distance = distance;
    }

    public Horse getHorse() {
        return horse;
    }

    public int getDistance() {
        return distance;
    }

    public int getPosition() {
        return horse.getDistance();
    }

    public static int changeSpeed() {
        int rnd = (int) (Math.random() * 5);
        return rnd;
    }

    @Override
    public void run() {
        while (getDistance() < distance) {
            horse.move();
            try {
                sleep(1000); // Simula un segundo de espera
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
