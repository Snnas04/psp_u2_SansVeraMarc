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

//    public Info() {
//        System.out.print("Enter the number of horses: ");
//        this.nHorses = scn.nextInt();
//        System.out.print("Enter the number of races: ");
//        this.nRaces = scn.nextInt();
//        System.out.print("Enter the laps per race: ");
//        this.laps = scn.nextInt();
//    }

    public int getNHorses() {
        return nHorses;
    }

    public int getDistance() {
        return this.distance;
    }

//    public int getNRaces() {
//        return this.nRaces;
//    }
//
//    public int getLaps() {
//        return this.laps;
//    }
}
