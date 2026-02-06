package FinalSolution.models;

public class Employee {
    private String name;
    private String companyName;

    public Employee(String name, String companyName) {
        this.name = name;
        this.companyName = companyName;
    }

    public String getName() {
        return name;
    }

    public String getCompanyName() {
        return companyName;
    }
}
