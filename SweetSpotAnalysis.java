package edu.neu.mgen;

import java.util.Scanner;

public class SweetSpotAnalysis {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of strategies: ");
        int numOfStrategies = scanner.nextInt();
        scanner.nextLine();  // Consume newline left-over

        System.out.print("Enter the number of factors: ");
        int numOfFactors = scanner.nextInt();
        scanner.nextLine();  // Consume newline left-over

        String[] factorNames = new String[numOfFactors];
        String[][] factorValues = new String[numOfStrategies][numOfFactors];
        String[] strategyNames = new String[numOfStrategies];

        // Input factor names
        for (int i = 0; i < numOfFactors; i++) {
            System.out.print("Enter factor " + (i + 1) + " name: ");
            factorNames[i] = scanner.nextLine();
        }

        // Input Strategies and Factors
        for (int i = 0; i < numOfStrategies; i++) {
            System.out.print("Enter Strategy " + (i + 1) + " name: ");
            strategyNames[i] = scanner.nextLine();
            for (int j = 0; j < numOfFactors; j++) {
                System.out.print("Enter factor " + factorNames[j] + " value (high/medium/low): ");
                factorValues[i][j] = scanner.nextLine();
            }
        }

        // Display the table
        System.out.println("\nSweet Spot Analysis:");
        // Print header with factor names
        System.out.print("Strategy/Factor\t");
        for (String factorName : factorNames) {
            System.out.print(factorName + "\t");
        }
        System.out.println();

        // Print strategy names and factor values
        for (int i = 0; i < numOfStrategies; i++) {
            System.out.print(strategyNames[i] + "\t\t");
            for (int j = 0; j < numOfFactors; j++) {
                System.out.print(factorValues[i][j] + "\t\t");
            }
            System.out.println();
        }

        scanner.close();
    }
}
