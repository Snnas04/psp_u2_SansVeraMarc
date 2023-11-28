package Race;

import Graphics.Graphics;
import Info.Info;

import java.util.ArrayList;
import java.util.List;

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
        List<HorseThread> horseList = new ArrayList<>();


        // Crear y agrupar los hilos antes de iniciar
        for (int i = 0; i < numHorses; i++) {
            Horse horse = new Horse("Horse " + (i + 1), 50);
            HorseThread horseThread = new HorseThread(horse, info.getDistance());
            horseList.add(horseThread);
        }

        // Iniciar todos los hilos al mismo tiempo
        for (HorseThread horseThread : horseList) {
            horseThread.start();
        }

        // Imprimir la carrera utilizan la clase Graphics
        Graphics graphics = new Graphics();
        graphics.printRace(horseList, info);


        // Esperar a que todos los hilos terminen
        for (HorseThread horseThread : horseList) {
            try {
                horseThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Nuevo método para interrumpir los hilos de los caballos
    public static void interruptHorseThreads(List<HorseThread> horseList) {
        for (HorseThread horseThread : horseList) {
            horseThread.interrupt();
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
