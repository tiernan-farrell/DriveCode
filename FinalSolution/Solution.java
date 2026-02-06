package FinalSolution;

import java.io.File;

import FinalSolution.services.CommandProcessorService;
import FinalSolution.services.RelationshipPrinterService;

public class Solution {
    public static void main(String[] args) {
        // Validate input argument is exactly one txt file 
        if (args.length != 1 || !args[0].endsWith(".txt")) {
            System.out.println("Provide a single .txt file as an argument.");
            return;
        }

        // Ensure file exists
        File inputFile = new File(args[0]);
        if (!inputFile.exists()) {
            System.out.println("File not found: " + args[0]);
            return; 
        }

        // Process the file and print results
        CommandProcessorService commandProcessor = new CommandProcessorService();
        commandProcessor.processFile(inputFile);
        RelationshipPrinterService relationshipPrinter = new RelationshipPrinterService();
        relationshipPrinter.printResults(commandProcessor.getCompanies());

    }
}
