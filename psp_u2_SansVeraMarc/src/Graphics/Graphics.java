package Graphics;

import Race.*;
import Info.Info;

import java.util.ArrayList;
import java.util.List;

public class Graphics {
    public void printRace(List<HorseThread> horseList, Info info) {
        List<Horse> winners = new ArrayList<>(); // Lista para almacenar los caballos ganadores

        int completedHorses = 0; // Contador de caballos que han completado la carrera

        int time = 0;
        while (true) {
            // Print the header for each second
            System.out.println("\nSecond " + time + ":");

            // Print the position of each horse in that second
            for (HorseThread horseThread : horseList) {
                Horse horse = horseThread.getHorse();
                System.out.println("  " + horse.getName() + ": " + horseThread.getPosition() + "m at " + horse.getSpeed() + "km/h");
            }

            System.out.println("\n______________________________ TV ______________________________");

            // Print the progress bar for each horse
            for (HorseThread horseThread : horseList) {
                Horse horse = horseThread.getHorse();
                printProgressBar(horse.getName(), horseThread.getPosition(), info.getDistance(), horse.getSpeed());
            }
            System.out.println("________________________________________________________________");
            // Verificar si algún caballo ha completado la carrera en este segundo
            for (HorseThread horseThread : horseList) {
                if (horseThread.getPosition() >= info.getDistance() && !horseThread.isFinished) {
                    horseThread.isFinished = true;
                    completedHorses++;

                    // Almacenar el caballo ganador en la lista de ganadores
                    if (winners.size() < 3) {
                        winners.add(horseThread.getHorse());
                    }
                }
            }

            // Verificar si tres caballos han completado la carrera
            if (completedHorses >= 3) {
                Race.interruptHorseThreads(horseList);
                break;
            }

            // Esperar un segundo antes de pasar al siguiente segundo
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            time++;
        }

        // Imprimir los caballos ganadores
        System.out.print("\nWinning horses of the "+ info.getDistance() + " race: \n");
        System.out.println("1st place: " + winners.get(0).getName());
        System.out.println("2nd place: " + winners.get(1).getName());
        System.out.println("3rd place: " + winners.get(2).getName());
        System.out.println();
    }

    private void printProgressBar(String horseName, int currentPosition, int totalDistance, int speed) {
        int progressBarLength = 50;
        int progress = (int) ((double) currentPosition / totalDistance * progressBarLength);

        // Calcula la longitud de la barra de progreso sin el indicador de velocidad
        int progressBarWithoutSpeedLength = progressBarLength - speedIndicator(speed).length();

        // Formatea el nombre del caballo para que siempre tenga 8 caracteres
        var horseNameFormat = String.format("%-8s", horseName);

        System.out.print(" " + horseNameFormat + ": ");

        for (int i = 0; i < progressBarWithoutSpeedLength; i++) {
            if (i == progress) {
                System.out.print(speedIndicator(speed) + "\uD83D\uDC0E"); // icona de caball 🐎
            }
            else if (i < progress) {
                System.out.print("_");
            } else {
                System.out.print("-");
            }
        }
        System.out.println();
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
