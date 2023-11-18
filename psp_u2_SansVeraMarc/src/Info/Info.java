package Info;

import java.util.Scanner;

public class Info {
    Scanner scn = new Scanner(System.in);
    private final int nHorses;
    private final int nRaces;
    private final int laps;

    public Info() {
        System.out.print("Enter the number of horses: ");
        this.nHorses = scn.nextInt();
        System.out.print("Enter the number of races: ");
        this.nRaces = scn.nextInt();
        System.out.print("Enter the laps pre race: ");
        this.laps = scn.nextInt();
    }

    public int getNHorses() {
        return this.nHorses;
    }

    public int getNRaces() {
        return this.nRaces;
    }

    public int getLaps() {
        return this.laps;
    }
}
