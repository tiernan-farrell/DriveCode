package FinalSolution.services;

import java.util.HashMap;
import java.util.Map;

import FinalSolution.models.Company;

public class RelationshipPrinterService {
    
    public void printResults(HashMap<String, Company> companies) {
         companies.values().stream()
                .sorted((c1, c2) -> c1.getCompanyName().compareTo(c2.getCompanyName()))
                .forEach(company -> {
                    Map.Entry<String, Integer> topPartner = company.getPartnerships().entrySet()
                            .stream()
                            .max(Map.Entry.comparingByValue())
                            .orElse(null);

                    if (topPartner != null) {
                        System.out.println(company.getCompanyName() + ": " + topPartner.getKey() + " (" + topPartner.getValue() + ")");
                    } else {
                        System.out.println(company.getCompanyName() + ": No current relationship");
                    }
                });
    }

}
