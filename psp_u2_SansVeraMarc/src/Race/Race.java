package Race;

import Graphics.Graphics;
import Info.Info;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

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

    public synchronized static void raceState(List<HorseThread> horseList) {
        if (Graphics.notifyRace() == true) {
            try {
                horseList.wait();
                var raceStat = Info.askToContinue();

                if (raceStat == false) {
                    System.out.println("Race has finished!");
                    interruptHorseThreadList(horseList);
                } else {
                    System.out.println("Race will continue!");
                    horseList.notifyAll();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Nuevo método para interrumpir los hilos de los caballos
    private static void interruptHorseThreadList(List<HorseThread> horseList) {
        for (HorseThread horseThread : horseList) {
            horseThread.interrupt();
        }
    }

    private boolean raceControl(Info info) {
        if (info.getNHorses() < 10 || info.getNHorses() > 20) {
            System.out.println("Race has been canceled due to lack of horses\nIt require at least 10 horses with a maximum of 20 to start a Race");
            return false;
        } else if (info.getDistance() < 100 || info.getDistance() > 10000) {
            System.out.println("Race has been canceled, due to lack of distance\nIt require at least 100m with a maximum of 10000m to start a Race");
            return false;
        } else {
            return true;
        }
    }
}
