package Info;

import java.util.Scanner;

public class Info {
    Scanner scn = new Scanner(System.in);
    private final int nHorses;
//    private final int nRaces;
//    private final int laps;
    private final int distance;

    public Info() {
        System.out.println("Enter the number of horses: ");
        this.nHorses = scn.nextInt();
        System.out.println("Enter the distance (meters) of the race: ");
        this.distance = scn.nextInt();
    }

    public int getNHorses() {
        return nHorses;
    }

    public int getDistance() {
        return this.distance;
    }

    public static boolean raceState() {
        Scanner scn = new Scanner(System.in);
        System.out.println("Do you want to continue? (y/n)");
        String answer = scn.nextLine();
        if (answer.equals("y")) {
            return true;
        } else {
            return false;
        }
    }
}
