package Tournament;

import Info.Info;

public class Tournament {
    private void tournamentControl(Info info) {
        if (info.getNHorses() < 10) {
            System.out.println("Tournament has been canceled due to lack of horses\nIt require at least 10 horses to start a Tournament");
            return;
        } else if (info.getNRaces() < 1) {
            System.out.println("Tournament has been canceled, due to lack of races\nIt require at least 1 race to start a Tournament");
        } else if (info.getLaps() < 2) {
            System.out.println("Tournament has been canceled, due to lack of laps\nIt require at least 2 lap to start a Tournament");
        } else {
            System.out.println("Tournament has been started\nENJOY!!!\n");
        }
    }

    public Tournament() {
        Info info = new Info();
        tournamentControl(info);
    }
}
