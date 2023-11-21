package Race;

import Info.Info;

public class Race {
    public Race() {
        Info info = new Info();
        if (raceControl(info)) {
            System.out.println("Race has started!");
            theRace(info);
        }
    }

    private void theRace(Info info) {
        int numHorses = info.getNHorses();
        for (int i = 0; i < numHorses; i++) {
            Horse horse = new Horse("Horse " + (i + 1), 50);
            HorseThread horseThread = new HorseThread(horse, info.getDistance());
            horseThread.start();

            while (horseThread.getPosition() < info.getDistance()) {
                System.out.println(horse.getName() + " has done " + horseThread.getPosition() + "m at " + horse.getSpeed() + "km/h");
            }

            try {
                horseThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean raceControl(Info info) {
        if (info.getNHorses() < 10) {
            System.out.println("Race has been canceled due to lack of horses\nIt require at least 10 horses to start a Race");
            return false;
        } else if (info.getDistance() < 100) {
            System.out.println("Race has been canceled, due to lack of distance\nIt require at least 100m to start a Race");
            return false;
        } else {
            return true;
        }
    }
}
