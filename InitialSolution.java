import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



class Company {
    String companyName;
    List<String> employees;
    HashMap<String, Integer> partnerships;

    Company(String companyName) {
        this.companyName = companyName;
        this.employees = new ArrayList<>();
        this.partnerships = new HashMap<>();
    }
}

public class InitialSolution {

    static List<Company> companies = new ArrayList<>();
    static List<String> partners = new ArrayList<>();

    private static void readInputLine(String line) {
        // Split line and process by cases
        String[] parts = line.split(" ");
        switch (parts[0]) {
            case "Company":
                companies.add(new Company(parts[1]));
                break;
            case "Partner":
                partners.add(parts[1]);
                break;
            case "Employee":
                // Find the company (parts[2]) and add the employee (parts[1])
                for (Company company : companies) {
                    if (company.companyName.equals(parts[2])) {
                        company.employees.add(parts[1]);
                    }
                }
                break;
            case "Contact":
                // Find the company with the employee (parts[1]) and add the partnership
                for (Company company : companies) {
                    if (company.employees.contains(parts[1])) {
                        if (company.partnerships.containsKey(parts[2])) {
                            company.partnerships.put(parts[2], company.partnerships.get(parts[2]) + 1);
                        }
                        else {  
                            company.partnerships.put(parts[2], 1);
                        }
                        break;
                    }
                }
                break;
            default:
                break;
        }
    }

    private static void printResults() { 
        // Sort the companies alphabetically by name
        companies.sort((c1, c2) -> c1.companyName.compareTo(c2.companyName));
        
        for (Company company : companies) {
            // Identify top partner and total contacts
            String topPartner = null;
            int totalContacts = 0;

            for (String partner : company.partnerships.keySet()) {
                int contacts = company.partnerships.get(partner);
                if (contacts > totalContacts) {
                    totalContacts = contacts;
                    topPartner = partner;
                }
            }

            if (topPartner != null) {
                System.out.println(company.companyName + ": " + topPartner + "(" + totalContacts + ")");
            } else {
                System.out.println(company.companyName + ": No current relationship");
            }
        }
    }

    public static void main(String[] args) {

        // First validate we are getting a txt file as input
        if (args.length != 1 || !args[0].endsWith(".txt")) {
            System.out.println("Provide a single .txt file as an argument.");
            return;
        }

        // Get the file and make sure it exists
        String inputFilePath = args[0];
        File inputFile = new File(inputFilePath);
        if (!inputFile.exists()) {
            System.out.println("File: " + inputFilePath + " does not exist.");
            return;
        }

        // Read through the file
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                readInputLine(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }

        printResults();
    }
}