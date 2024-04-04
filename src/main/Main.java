package main;
import data.CompanyManagement;
import data.Developer;
import data.TeamLeader;
import data.Tester;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import data.Employee;
public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        final String fileEmp = "src\\input\\ListOfEmployees.txt";
        final String filePL = "src\\input\\PLInfo.txt";
        final String fileWrite1 = "src\\output\\Req2.txt";
        final String fileWrite2 = "src\\output\\Req3.txt";
        CompanyManagement cm = null;
        //cm = new CompanyManagement(fileEmp, filePL);
        int choice;
        do {
            System.out.println("1. Read all Employees and print to screen.");
            System.out.println("2. Show staff proficient in a Programming Language.");
            System.out.println("3. Show Tester with the higher input salary.");
            System.out.println("4. Show Employee with the highest salary.");
            System.out.println("5. Show the leader of the Team with the most Employees.");
            System.out.println("6. Sort Employees by descending salary.");
            System.out.println("7. Write file.");
            System.out.println("8. Exit.");
            System.out.print("Enter your choice (1-8): ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("===============================================================");
                    cm = new CompanyManagement(fileEmp, filePL);
                    System.out.println("===============================================================");
                    break;
                case 2:
                    System.out.println("===============================================================");
                    System.out.print("Enter the Programming Language: ");
                    String p = sc.nextLine();
                    ArrayList<Developer> listExpert = cm.getDeveloperByProgrammingLanguage(p);
                    if (!listExpert.isEmpty()) {
                        for (Developer dev : listExpert) {
                            System.out.println(dev.toString());
                        }
                    } else {
                        System.out.println("No developers found proficient in the given Programming Language.");
                    }
                    System.out.println("===============================================================");
                    break;
                case 3:
                    System.out.println("===============================================================");
                    double value = 0.0;
                    System.out.print("Enter the minimum salary value: ");
                    try {
                        value = Double.parseDouble(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input! Please enter a valid salary value.");
                        continue;
                    }
                    ArrayList<Tester> testersWithHigherSalary = cm.getTestersHaveSalaryGreaterThan(value);
                    if (testersWithHigherSalary != null) {
                        for (Tester tester : testersWithHigherSalary) {
                            System.out.println(tester.toString());
                        }
                    } else {
                        System.out.println("No testers found with a salary greater than the given value.");
                    }
                    System.out.println("===============================================================");
                    break;
                case 4:
                    System.out.println("===============================================================");
                    Employee employeeWithHighestSalary = cm.getEmployeeWithHighestSalary();
                    if (employeeWithHighestSalary != null) {
                        System.out.println("Employee with the highest salary: " + employeeWithHighestSalary.toString());
                    } else {
                        System.out.println("No employees found.");
                    }
                    System.out.println("===============================================================");
                    break;
                case 5:
                    System.out.println("===============================================================");
                    TeamLeader leaderWithMostEmployees = cm.getLeaderWithMostEmployees();
                    if (leaderWithMostEmployees != null) {
                        System.out.println("Leader with the most employees: " + leaderWithMostEmployees.toString());
                    } else {
                        System.out.println("No team leaders found.");
                    }
                    System.out.println("===============================================================");
                    break;
                case 6:
                    System.out.println("===============================================================");
                    ArrayList<Employee> sortedEmployees = cm.sorted();
                    if (sortedEmployees != null) {
                        for (Employee emp : sortedEmployees) {
                            System.out.println(emp.toString());
                        }
                    } else {
                        System.out.println("No employees found.");
                    }
                    System.out.println("===============================================================");
                    break;
                case 7:
                    ArrayList<Employee> employeeList = cm.getEmpList();
                    if (employeeList != null && !employeeList.isEmpty()) {
                        cm.printEmpList(employeeList);
                        boolean checkTrue = cm.writeFile(fileWrite1, employeeList);
                        if (checkTrue) {
                            System.out.println("Successfully wrote the employee list to " + fileWrite1);
                        } else {
                            System.out.println("Failed to write the employee list to the file.");
                        }
                    } else {
                        System.out.println("No employees found.");
                    }
                    break;

                case 8:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
                    break;
            }
        } while (choice != 8);
    }
}
