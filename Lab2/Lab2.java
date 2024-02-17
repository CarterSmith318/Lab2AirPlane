package Lab2;

import java.util.Scanner;

public class Lab2 {
    private boolean[][] firstClass;
    private boolean[][] economyClass;
    private final int firstClassRows = 5;
    private final int firstClassSeatsPerRow = 4;
    private final int economyClassRows = 30;
    private final int economyClassSeatsPerRow = 6;

    public Lab2() {
        firstClass = new boolean[firstClassRows][firstClassSeatsPerRow];
        economyClass = new boolean[economyClassRows][economyClassSeatsPerRow];
    }

    public void addPassengers(String travelClass, int numPassengers, String seatingPreference) {
        boolean[][] seats;
        int rows;
        int seatsPerRow;

        if (travelClass.equalsIgnoreCase("first")) {
            seats = firstClass;
            rows = firstClassRows;
            seatsPerRow = firstClassSeatsPerRow;

            // Ensure first class takes only 1 or 2 passengers
            if (numPassengers < 1 || numPassengers > 2) {
                System.out.println("Invalid number of passengers for first class. Please choose 1 or 2.");
                return;
            }

            // Seat assignment logic for first class based on seating preference
            assignFirstClassSeats(seats, rows, seatsPerRow, numPassengers, seatingPreference);
        } else if (travelClass.equalsIgnoreCase("economy")) {
            seats = economyClass;
            rows = economyClassRows;
            seatsPerRow = economyClassSeatsPerRow;

            // Ensure economy class takes 1, 2, or 3 passengers
            if (numPassengers < 1 || numPassengers > 3) {
                System.out.println("Invalid number of passengers for economy class. Please choose 1, 2, or 3.");
                return;
            }

            // Seat assignment logic for economy class based on seating preference
            assignEconomyClassSeats(seats, rows, seatsPerRow, numPassengers, seatingPreference);
        } else {
            System.out.println("Invalid travel class. Please choose 'first' or 'economy'.");
            return;
        }
    }

    private void assignFirstClassSeats(boolean[][] seats, int rows, int seatsPerRow,
                                       int numPassengers, String seatingPreference) {
        if (seatingPreference.equalsIgnoreCase("window")) {
            // Logic for assigning seats on either the right or left side
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < seatsPerRow - numPassengers + 1; j++) {
                    boolean available = true;
                    for (int k = 0; k < numPassengers; k++) {
                        if (seats[i][j + k]) {
                            available = false;
                            break;
                        }
                    }
                    if (available) {
                        for (int k = 0; k < numPassengers; k++) {
                            seats[i][j + k] = true;
                        }
                        System.out.println("Seats assigned successfully.");
                        return;
                    }
                }
            }
        } else if (seatingPreference.equalsIgnoreCase("aisle")) {
            // Logic for assigning seats in either of the center two seats
            for (int i = 0; i < rows; i++) {
                for (int j = 1; j < seatsPerRow - 2; j++) {
                    boolean available = true;
                    for (int k = 0; k < numPassengers; k++) {
                        if (seats[i][j + k]) {
                            available = false;
                            break;
                        }
                    }
                    if (available) {
                        for (int k = 0; k < numPassengers; k++) {
                            seats[i][j + k] = true;
                        }
                        System.out.println("Seats assigned successfully.");
                        return;
                    }
                }
            }
        } else if (seatingPreference.equalsIgnoreCase("center")) {
            System.out.println("Error: Center seat is not available in first class.");
        } else {
            System.out.println("Invalid seating preference. Please choose 'window', 'aisle', or 'center'.");
        }
    }

    private void assignEconomyClassSeats(boolean[][] seats, int rows, int seatsPerRow,
                                     int numPassengers, String seatingPreference) {
    if (seatingPreference.equalsIgnoreCase("window")) {
        // Logic for assigning seats on either the right or left side
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsPerRow - numPassengers + 1; j++) {
                boolean available = true;
                for (int k = 0; k < numPassengers; k++) {
                    if (seats[i][j + k]) {
                        available = false;
                        break;
                    }
                }
                if (available) {
                    for (int k = 0; k < numPassengers; k++) {
                        seats[i][j + k] = true;
                    }
                    System.out.println("Seats assigned successfully.");
                    return;
                }
            }
        }
    } else if (seatingPreference.equalsIgnoreCase("center")) {
        // Logic for assigning seats in the middle two seats
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsPerRow - numPassengers + 1; j++) {
                boolean available = true;
                for (int k = 0; k < numPassengers; k++) {
                    if (seats[i][j + k] || seats[i][j + k + 1]) {
                        available = false;
                        break;
                    }
                }
                if (available) {
                    for (int k = 0; k < numPassengers; k++) {
                        seats[i][j + k] = true;
                    }
                    System.out.println("Seats assigned successfully.");
                    return;
                }
            }
        }
    } else if (seatingPreference.equalsIgnoreCase("aisle")) {
        // Logic for assigning a single aisle seat (either of the two center seats)
        for (int i = 0; i < rows; i++) {
            int start = seatsPerRow / 2 - 1; // Start from the first center seat
            int end = start + 2; // End at the second center seat
            boolean available = true;
            for (int j = start; j < end; j++) {
                if (seats[i][j]) {
                    available = false;
                    break;
                }
            }
            if (available) {
                seats[i][start] = true; // Assign the left aisle seat
                System.out.println("Seats assigned successfully.");
                return;
            } else if (!seats[i][start + 1]) {
                seats[i][start + 1] = true; // Assign the right aisle seat if the left one is taken
                System.out.println("Seats assigned successfully.");
                return;
            }
        }
    } else {
        System.out.println("Invalid seating preference. Please choose 'window', 'aisle', or 'center'.");
        return;
    }
    System.out.println("No matching seats available. Please try again.");
}





    public void showSeating() {
        System.out.println("First Class Seating:");
        displaySeating(firstClass);

        System.out.println("Economy Class Seating:");
        displaySeating(economyClass);
    }

    private void displaySeating(boolean[][] seats) {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                System.out.print(seats[i][j] ? "X " : "O ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Lab2 airplaneSeating = new Lab2();

        while (true) {
            System.out.println("Enter a command: add passengers, show seating, or quit");
            String command = scanner.nextLine();

            if (command.equalsIgnoreCase("add passengers")) {
                System.out.println("Enter travel class (first or economy):");
                String travelClass = scanner.nextLine();

                System.out.println("Enter number of passengers (1, 2, or 3):");
                int numPassengers = scanner.nextInt();
                scanner.nextLine(); // consume the newline character

                if (numPassengers < 1 || numPassengers > 3) {
                    System.out.println("Invalid number of passengers. Please choose 1, 2, or 3.");
                    continue;
                }

                System.out.println("Enter seating preference (window, aisle, or center):");
                String seatingPreference = scanner.nextLine();

                airplaneSeating.addPassengers(travelClass, numPassengers, seatingPreference);
            } else if (command.equalsIgnoreCase("show seating")) {
                airplaneSeating.showSeating();
            } else if (command.equalsIgnoreCase("quit")) {
                break;
            } else {
                System.out.println("Invalid command. Please enter 'add passengers', 'show seating', or 'quit'.");
            }
        }

        System.out.println("Exiting the program.");
        scanner.close();
    }
}
