package FinalSolution.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Company {
    private String companyName;
    private List<Employee> employees;
    private HashMap<String, Integer> partnerships;

    public Company(String companyName) {
        this.companyName = companyName;
        this.employees = new ArrayList<>();
        this.partnerships = new HashMap<>();
    }

    public String getCompanyName() {
        return companyName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public HashMap<String, Integer> getPartnerships() {
        return partnerships;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void addPartnership(String partner) {
        partnerships.put(partner, partnerships.getOrDefault(partner, 0) + 1);
    }
}
