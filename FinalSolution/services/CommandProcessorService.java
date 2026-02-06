package FinalSolution.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import FinalSolution.models.Company;
import FinalSolution.models.Employee;
import FinalSolution.models.Partner;

public class CommandProcessorService {
    
    private HashMap<String, Company> companies;
    private HashMap<String, Employee> employees;
    private List<Partner> partners;

    public CommandProcessorService() {
        this.companies = new HashMap<String, Company>();
        this.employees = new HashMap<String, Employee>();
        this.partners = new ArrayList<Partner>();
    }


    public HashMap<String, Company> getCompanies() {
        return companies;
    }

    public void processFile(File inputFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String command;
            while ((command = reader.readLine()) != null) {
                processCommand(command);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + e.getMessage());
        }
    }

    private void processCommand(String command) {
        String[] parts = command.split(" ");
        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid command: " + command);
        }

        switch (parts[0]) {
            case "Company":
                addCompany(parts[1]);
                break;
            case "Partner":
                addPartner(parts[1]);
                break;
            case "Employee":
                if (parts.length != 3) {
                    throw new IllegalArgumentException("Invalid Employee command: " + command);
                }
                addEmployee(parts[1], parts[2]);
                break;
            case "Contact":
                if (parts.length != 4) {
                    throw new IllegalArgumentException("Invalid Contact command: " + command);
                }
                addContact(parts[1], parts[2], parts[3]);
                break;
            default:
                throw new IllegalArgumentException("Unknown command: " + command);
        }
    }

    private void addCompany(String companyName) {
        companies.putIfAbsent(companyName, new Company(companyName));
    }

    private void addPartner(String partnerName) {
        partners.add(new Partner(partnerName));
    }

    private void addEmployee(String employeeName, String companyName) {
        if (!companies.containsKey(companyName)) {
            throw new IllegalArgumentException("Company not found: " + companyName);
        }
        // Add employee to company and to employees map to access companies by employee
        Employee employee = new Employee(employeeName, companyName);
        companies.get(companyName).addEmployee(employee);
        employees.put(employeeName, employee);
    }

    private void addContact(String employeeName, String partnerName, String contactType) {
        if (!contactType.matches("email|call|coffee")) {
            throw new IllegalArgumentException("Invalid contact type: " + contactType);
        }
        Employee employee = employees.get(employeeName);
        if (employee == null) {
            throw new IllegalArgumentException("Employee not found: " + employeeName);
        }
        // access company by employee
        Company company = companies.get(employee.getCompanyName());
        company.addPartnership(partnerName);
    }
    
}
