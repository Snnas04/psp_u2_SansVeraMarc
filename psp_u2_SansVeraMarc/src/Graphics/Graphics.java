package Graphics;

import Race.*;
import Info.Info;

import java.util.ArrayList;
import java.util.List;

public class Graphics {
    public void printRace(List<HorseThread> horseList, Info info) {
        List<Horse> podioList = new ArrayList<>(); // Lista para almacenar los caballos ganadores
        List<Horse> finishedList = new ArrayList<>(); // Lista para almacenar los caballos que han completado la carrera
        int position = 0;
        int completedHorses = 0; // Contador de caballos que han completado la carrera
        int time = 0;

        while (true) {
            // Imprimir la carrera
            printBoard(time, horseList, info);
            time++;

            // Verificar si algún caballo ha completado la carrera
            for (HorseThread horseThread : horseList) {
                if (horseThread.getPosition() >= info.getDistance() && !horseThread.isFinished) {
                    horseThread.isFinished = true;
                    completedHorses++;
                    finishedList.add(horseThread.getHorse());
                    // Almacenar el caballo ganador en la lista de ganadores
                    if (podioList.size() < 3) {
                        podioList.add(horseThread.getHorse());
                    }
                }
            }

            // Verificar si la carrera ha terminado
            if (stateRaceControl(completedHorses, horseList.size())) {
                break;
            }
        }

        printFinishBoard(podioList, finishedList, position, info);
    }

    private void printBoard(int time, List<HorseThread> horseList, Info info) {
        systemClear();
        // Print the header for each second
        System.out.println("Second " + time + ":");

        // Print the position of each horse in that second
        for (HorseThread horseThread : horseList) {
            Horse horse = horseThread.getHorse();
            var postitionFormat = String.format("%5s", horseThread.getPosition() + "m");
            var nameFormat = String.format("%-8s", horse.getName());

            var printLeaderBoard = String.format("%45s", nameFormat + ": " + postitionFormat + " at " + horse.getSpeed() + "km/h\n");

            System.out.printf(printLeaderBoard);
        }

        // Print the progress bar for each horse
        for (HorseThread horseThread : horseList) {
            Horse horse = horseThread.getHorse();
            printProgressBar(horse.getName(), horseThread.getPosition(), info.getDistance(), horse.getSpeed());
        }

        // Esperar un segundo antes de pasar al siguiente segundo
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void systemClear() {
        // Limpiar la consola
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private synchronized static boolean stateRaceControl(int completedHorses, int horseListSize) {
        // Verificar si tres caballos han completado la carrera
        if (completedHorses >= 3) {
            notifyRace();

            // aturar la carrera si l'usuari no vol continuar
            if (!Info.askToContinue()) {
                return true;
            }
        }

        // aturar la carrera si tots els cavalls han completat la carrera
        return completedHorses >= horseListSize;
    }

    public synchronized static boolean notifyRace() {
        return true;
    }

    private void printProgressBar(String horseName, int currentPosition, int totalDistance, int speed) {
        int progressBarLength = 50;
        int progress = (int) ((double) currentPosition / totalDistance * progressBarLength);

        // Formatea el nombre del caballo para que siempre tenga 8 caracteres
        var horseNameFormat = String.format("%-8s", horseName);

        // Calcula la longitud de la barra de progreso sin el indicador de velocidad
        int progressBarWithoutSpeedLength = progressBarLength - speedIndicator(speed).length();

        // Calcula la longitud total de la barra de progreso incluyendo espacios y el indicador de velocidad
        int totalProgressBarLength = progressBarWithoutSpeedLength + horseNameFormat.length() + 6; // 6 es la longitud de los espacios y barras

        // Calcula la posición de inicio para centrar la barra de progreso
//        int startPosition = (totalProgressBarLength - progressBarWithoutSpeedLength) / 2;

        System.out.print(horseNameFormat + ": ");

        for (int i = 0; i < progressBarWithoutSpeedLength; i++) {
            if (i == progress) {
                System.out.print(speedIndicator(speed) + "\uD83D\uDC0E"); // icono de caballo 🐎
            } else if (i < progress) {
                System.out.print("_");
            } else {
                System.out.print("-");
            }
        }
        System.out.println("");
    }

    private void printFinishBoard(List<Horse> podio, List<Horse> finishedBoard, int position, Info info) {
        systemClear();
        // Imprimir los caballos ganadores
        System.out.print("\nPodio of the " + info.getDistance() + "m race: \n");

        System.out.println("");
        System.out.printf("%30s",podio.get(0).getName() + "\n");
        System.out.printf("%30s", "1st place" + "\n");
        System.out.printf("%15s",podio.get(1).getName() + "\n");
        System.out.printf("%15s","2nd place"+ "\n");
        System.out.printf("%45s",podio.get(2).getName() + "\n");
        System.out.printf("%45s","3rd place" + "\n");
        System.out.println("");
        System.out.println("Finished Board: ");
        for (Horse horse : finishedBoard) {
            position++;
            System.out.println("P" + position + " -> " + horse.getName());
        }
        System.out.println("");
    }

    private String speedIndicator(int speed) {
        if (speed >= 15 && speed <= 19) {
            return "<<<";
        } else if (speed >= 20 && speed <= 29) {
            return "<<";
        } else if (speed >= 30 && speed <= 39) {
            return "<";
        } else if (speed >= 40 && speed <= 49) {
            return ">";
        } else if (speed >= 50 && speed <= 59) {
            return ">>";
        } else if (speed >= 60 && speed <= 69) {
            return ">>>";
        } else if (speed >= 70 && speed <= 79) {
            return ">>>>";
        } else {
            return "";
        }
    }
}
