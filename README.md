# Approach

I will create two solutions. One will be the bare bones, MVP version which I will get to work first.
The second solution will be a more elegant solution that will handle errors and be easier to iterate upon if input or requirements change.

The reasoning behind this is to show how I iterate on my solutions. My first goal is always to get something to work and then to find the
optimal solution and harden it for potential future requirement changes.

My first solution will be a one file solution with a few helper methods.

# Initial Solution

## Planning

1. First I want to read the text file
   - I will create a for loop in the main method and it will call a helper method, readInputLine
   - readInputLine will parse the text one line at a time and the for loop will end at EOF
2. I will create a class to represent companies and its employees and contacts.
3. While parsing, I will create lists of Partners, Companies and add employees to companies.
4. If a command is a contact, I will check to see if this partner has made contact with an employee of that company. I will increment or add initial contact for that partner to the company.
5. Once I reach the end of the file, I will sort the companies list alphabetically
6. I will then print the companies and the partner with the highest contacts with the count of contacts.

## Implementing

Note: I wrote the plan before implementing and will not change the plan as I implement and potentially deviate slightly from it. The intent is for me to share my approach and how it will change through the implementation process. Any deviations from the plan will be listed here with the reasoning.

As this is still my initial solution, I may see places to optimize, but hold off for polished solution.

While implementing this solution, it became clear that if I am able to make the assumptions outlined in the description of the problem. I can simplify this quite a bit.

In order to make the solution super clean and easy to read, I will implement a new way to do this in python.

## Requirements:

Java installed - I have:
`tiernanfarrell@Mac DriveCode % java --version
    java 25.0.2 2026-01-20 LTS
    Java(TM) SE Runtime Environment (build 25.0.2+10-LTS-69)
    Java HotSpot(TM) 64-Bit Server VM (build 25.0.2+10-LTS-69, mixed mode, sharing)`

## Executing:

`$java InitialSolution.java input.txt`

# Simpler solution

## Planning:

I have now realized, based on the format of the txt file and gaurantees made in the description, I can ignore storing a few things.
Really, all I need are 3 things.

1. every time I come across a company I need to save it
2. every time I come across an employee I can index that employee in a dict to a company (since employee names are unique across companies)
3. every time I see a contact I add a contact entry to the company of the employee for the partner

Now at the end I can simply sort companies and then find the count of the most entered parnter and print the values

The two big assumptions I am using to simplify are 1. We don't need to be concerned about storing partners because they will appear in the contact command 2. employee names are unique across companies so I can use them as an index for a company essentially.

## Implementing

Implementing in python to condense code and improve readabilty
Since this is the "risky" or "greedy" implementation, I will remove all basic error handling from initial solution.

This solution is nice because I only have two loops. I loop through the input one time and do my 3 steps outlined in my plan. I then loop through the companies when printing. Although, the max function is essentially a third loop.

## Requirements

Have python installed. I have python3 but this should work with any version

## Executing

`$python3 SimplerSolution.py input.txt`
or depending on version of python
`$python SimplerSolution.py input.txt`

# Final Solution

## Planning

For this solution, I want to:

1. Validate the input and set up proper error handling.
2. Create data types that will be easy to add to if the problem becomes more complex
3. Create some sort of service or method that will read the line, validate the format and process.
4. Store lists of companies, employees and partners inside the service
5. Add a second service that will print results. This will just take the companies list from the first service as its only input.
6. With all of that setup, the main method will simply validate that it was passed exactly one txt file and that it can be opened. It will then just call the service that reads the input and then the service that will print.

My structure for files will be

```
- FinalSolution
    - Solution.java
    - models
        - Company.java
        - Employee.java
        - Partner.java
    - services
        - CommandProcessorService.java
        - RelationshipPrinterService.java
```

With this structure I can easily modify things without having one file that gets overly complex.

## Implementing

Early in the implementation, it was super fast to setup the code for the main method in solution.java now that I have services.

The models were very quick to setup.

The code for process file is essentially the same as initial solution, same with printResults. For PrintResults I wanted to make it cleaner, so I implemented the solution with the .stream() method.

There's some unnecessary code here at the moment given the assumptions we can make. The tradeoff is the modularity will allow for easy modifications later.

## Executing

Same requirements as initial solution.

`$java FinalSolution/Solution.java input.txt`

# Notes

## LLM

For this solution, I strayed away from LLMs for the actual solution; however, I consistently use LLM's in my workflows at home and at work. I use them as a tool to speed up development. Often times the inline suggestions are not great, but I have found that if I am architecting a modular solution they are much better. At my current role, we have copilot licenses and I have seen improved development speed with the use of them - especially when you give it an instructions.md with the project best practices and guidelines.

I have seen pull requests at my current role that are great examples of what poor usage of LLMs can look like for code generation if you aren't thoughtful about the approach.

AI without a strategy is just faster chaos.

Additionally, I am experimenting with building AI agents for my side project using AWS bedrock.
Also, looking forward to experimenting with Kiro when I have a chance.

## Final Notes

I know the instructions said breif description of the approach, but I hope this outline gave a good insight into how I approach problem solving. I intentionally left some of my thoughts from the beginning that changed as I developed my solutions.

Let me know if there are any questions or issues with my submission!
