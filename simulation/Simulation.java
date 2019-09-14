package simulation;

import java.io.*;
import java.util.Scanner;

public class Simulation {

    public static PrintStream logStream;

    public static void main(String argv[]) {

        File inputFile; Scanner inputScanner;

        if (argv.length != 1) {
            System.out.println("Error: missing argument");
            return;
        }
        try { inputFile = new File(argv[0]); inputScanner = new Scanner(inputFile); }
        catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
            return ;
        }

        try {
            logStream = new PrintStream(new File("simulation.txt"));
        }
        catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
            return;
        }

        int Q;
        try { Q = inputScanner.nextInt(); if (Q <= 0) throw new bonusException(); }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return;
        }

        WeatherTower tower = new WeatherTower();

        int lineNb = 0;
        while (inputScanner.hasNext()) {
            lineNb++;
            String line = inputScanner.nextLine();
            if (line.isEmpty())
                continue;
            try {
                Scanner lineScanner = new Scanner(line);

                String  aircraftType = lineScanner.next(),
                        aircraftName = lineScanner.next();

                int     longitude = lineScanner.nextInt(),
                        attitude = lineScanner.nextInt(),
                        height = lineScanner.nextInt();

                if (longitude < 0 || attitude < 0 || height < 0)
                    throw new bonusException();

                AircraftFactory.newAircraft(aircraftType, aircraftName,longitude, attitude, height).registerTower(tower);

            }
            catch (Exception ex) {
                System.out.println("Error on line " + lineNb + ' ' + ex.getMessage());
            }
            catch (ExceptionInInitializerError ex) {
                System.out.println("Error on line " + lineNb + ' ' + ex.getMessage());
            }
        }

        while (Q-- != 0)
            tower.changeWeather();

    }

}

class bonusException extends Exception {
    @Override public String getMessage() {
        return "Argument must be strictly positive";
    }
}
