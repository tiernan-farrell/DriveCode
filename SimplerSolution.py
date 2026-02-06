import sys

def print_results(companies):
    for company_name in sorted(companies.keys()):
        company = companies[company_name]
        if company:  
            top_partner = max(company, key=company.get)
            print(f"{company_name}: {top_partner}({company[top_partner]})")
        else:
            print(f"{company_name}: No current relationship")

def main():
    with open(sys.argv[1], "r") as file:
        companies = {}
        employees = {}

        for line in file:
            parts = line.split()
            command = parts[0]
            if command == "Company":
                company_name = parts[1]
                if company_name not in companies:
                    companies[company_name] = {}
            elif command == "Employee":
                employee_name, company_name = parts[1], parts[2]
                employees[employee_name] = company_name
            elif command == "Contact":
                employee_name, partner_name = parts[1], parts[2]
                company_name = employees.get(employee_name)
                if partner_name in companies[company_name].keys():
                    companies[company_name][partner_name] += 1
                else:
                    companies[company_name][partner_name] = 1

        print_results(companies)

if __name__ == "__main__":
    main()