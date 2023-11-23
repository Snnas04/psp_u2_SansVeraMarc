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
        int rnd = (int) (Math.random() * 11) - 5; // Rango de -5 a 5
        return rnd;
    }

    @Override
    public void run() {
        try {
            while (getDistance() < distance && !Thread.interrupted()) {
                horse.move();
            }
        } catch (Exception e) {
            // Manejar la interrupción, si es necesario
            System.out.println("Thread interrupted: " + e.getMessage());
        }
    }


}
