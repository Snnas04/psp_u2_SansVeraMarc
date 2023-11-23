package Race;

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
        int completedHorses = 0; // Contador de caballos que han completado la carrera

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

        completedHorses = 0;
        int time = 0;
        while (true) {
            System.out.println("Second " + time + ":");

            // Imprimir la posición de cada caballo en ese segundo
            for (HorseThread horseThread : horseList) {
                Horse horse = horseThread.getHorse();
                System.out.println(horse.getName() + ": " +
                        horseThread.getPosition() + "m at " +
                        horse.getSpeed() + "km/h");
            }

            for (HorseThread horseThread : horseList) {
                if (horseThread.getPosition() > info.getDistance()) {
                    completedHorses++;
                }
            }

            // Esperar un segundo antes de pasar al siguiente segundo
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (completedHorses > 3) {
                interruptHorseThreads(horseList);
                break;
            }

            time++;
        }

        // Una vez que tres caballos han completado la carrera, interrumpir los hilos restantes

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
    private void interruptHorseThreads(List<HorseThread> horseList) {
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
