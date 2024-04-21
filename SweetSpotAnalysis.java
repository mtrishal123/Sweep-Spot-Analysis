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
        String[] factorTypes = new String[numOfFactors]; // "text" or "numeric"

        // Input factor names and types
        for (int i = 0; i < numOfFactors; i++) {
            System.out.print("Enter factor " + (i + 1) + " name: ");
            factorNames[i] = scanner.nextLine();
            System.out.print("Enter factor " + factorNames[i] + " type (text/numeric): ");
            factorTypes[i] = scanner.nextLine();

            if ("text".equalsIgnoreCase(factorTypes[i])) {
                System.out.println("Choices for " + factorNames[i] + ": high/medium/low");
            }
        }

        // Input Strategies and Factors
        for (int i = 0; i < numOfStrategies; i++) {
            System.out.print("Enter Strategy " + (i + 1) + " name: ");
            strategyNames[i] = scanner.nextLine();
            for (int j = 0; j < numOfFactors; j++) {
                System.out.print("Enter factor " + factorNames[j] + " value: ");
                factorValues[i][j] = scanner.nextLine();

                if ("text".equalsIgnoreCase(factorTypes[j])) {
                    while (!isValidChoice(factorValues[i][j])) {
                        System.out.println("Invalid choice! Choices for " + factorNames[j] + ": high/medium/low");
                        System.out.print("Enter factor " + factorNames[j] + " value: ");
                        factorValues[i][j] = scanner.nextLine();
                    }
                }
            }
        }

        // Sort strategies based on factors
        sortStrategies(strategyNames, factorValues, factorTypes, numOfFactors);

        // Display the table
        System.out.println("\nStrategy Analysis:");
        // Print header with factor names
        System.out.print("Strategy/Factor\t");
        for (String factorName : factorNames) {
            System.out.print(factorName + "\t");
        }
        System.out.println();

        // Print sorted strategy names and factor values
        for (int i = 0; i < numOfStrategies; i++) {
            System.out.print(strategyNames[i] + "\t\t");
            for (int j = 0; j < numOfFactors; j++) {
                System.out.print(factorValues[i][j] + "\t\t");
            }
            System.out.println();
        }

        scanner.close();
    }

    public static void sortStrategies(String[] strategyNames, String[][] factorValues, String[] factorTypes, int numOfFactors) {
        for (int i = 0; i < strategyNames.length - 1; i++) {
            for (int j = i + 1; j < strategyNames.length; j++) {
                if (compareStrategies(factorValues[i], factorValues[j], factorTypes, numOfFactors) > 0) {
                    // Swap strategy names
                    String tempName = strategyNames[i];
                    strategyNames[i] = strategyNames[j];
                    strategyNames[j] = tempName;

                    // Swap factor values
                    String[] tempValues = factorValues[i];
                    factorValues[i] = factorValues[j];
                    factorValues[j] = tempValues;
                }
            }
        }
    }

    public static int compareStrategies(String[] factors1, String[] factors2, String[] factorTypes, int numOfFactors) {
        for (int i = 0; i < numOfFactors; i++) {
            int comparisonResult;
            if ("numeric".equalsIgnoreCase(factorTypes[i])) {
                int num1 = Integer.parseInt(factors1[i]);
                int num2 = Integer.parseInt(factors2[i]);
                comparisonResult = Integer.compare(num2, num1); // Sort in descending order
            } else {
                comparisonResult = factors1[i].compareToIgnoreCase(factors2[i]);
            }

            if (comparisonResult != 0) {
                return comparisonResult;
            }
        }
        // If all factors are same, compare by strategy name
        return factors1[0].compareToIgnoreCase(factors2[0]);
    }

    public static boolean isValidChoice(String choice) {
        return "high".equalsIgnoreCase(choice) || "medium".equalsIgnoreCase(choice) || "low".equalsIgnoreCase(choice);
    }
}
