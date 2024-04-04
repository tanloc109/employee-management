package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class CompanyManagement {

    private ArrayList<Employee> empList;

    public ArrayList<Employee> getEmpList() {
        return empList;
    }

    public CompanyManagement(String path1, String path2) throws FileNotFoundException, IOException {
        empList = new ArrayList<>();
        empList = getEmployeeFromFile(path1, path2);
    }

    @SuppressWarnings("empty-statement")
    public ArrayList<Employee> getEmployeeFromFile(String path1, String path2) throws FileNotFoundException, IOException {
        ArrayList<Employee> list = null;
        try {
            File fPLinfo = new File(path2);
            String fullPath = fPLinfo.getAbsolutePath();
            FileInputStream filePLInfo = new FileInputStream(fullPath);
            BufferedReader myInputPLInfo;
            myInputPLInfo = new BufferedReader(new InputStreamReader(filePLInfo));
            HashMap<String, ArrayList<String>> hashPLInfo = null;
            String thisLinePLInfo;
            while ((thisLinePLInfo = myInputPLInfo.readLine()) != null) {
                if (!thisLinePLInfo.trim().isEmpty()) {
                    String[] split = thisLinePLInfo.split(",");
                    if (hashPLInfo == null) {
                        hashPLInfo = new HashMap<>();

                    }
                    String key = split[0].trim();
                    ArrayList<String> plInfo = new ArrayList<>();
                    for (int i = 1; i < split.length; i++) {
                        plInfo.add(split[i].trim());
                    }
                    hashPLInfo.put(key, plInfo);
                }
            }
            myInputPLInfo.close();
            fPLinfo = new File(path1);
            fullPath = fPLinfo.getAbsolutePath();
            FileInputStream fileEmp = new FileInputStream(fullPath);
            String thisLine;
            BufferedReader myInputEmp;
            myInputEmp = new BufferedReader(new InputStreamReader(fileEmp));
            //read line until the end of the line

            while ((thisLine = myInputEmp.readLine()) != null) {
                Employee emp = null;
                if (!thisLine.trim().isEmpty()) {
                    String[] split = thisLine.split(",");
                    if (split.length == 8) {
                        String id = split[1].trim();
                        String name = split[2].trim();
                        String team = split[3].trim();
                        int expY = Integer.parseInt(split[4].trim());
                        double bonus = Double.parseDouble(split[6].trim());
                        int balS = Integer.parseInt(split[7].trim());
                        ArrayList<String> progL = hashPLInfo.get(id);

                        emp = new TeamLeader(id, name, balS, team, progL, expY, bonus);

                        System.out.println(emp.toString());
                    } else if (split[1].trim().charAt(0) == 'D') {
                        String id = split[1].trim();
                        String name = split[2].trim();
                        String team = split[3].trim();
                        int expY = Integer.parseInt(split[4].trim());
                        int balS = Integer.parseInt(split[5].trim());

                        ArrayList<String> progL = hashPLInfo.get(id);

                        emp = new Developer(id, name, balS, team, progL, expY);

                        System.out.println(emp.toString());
                    } else {
                        String id = split[1].trim();
                        String name = split[2].trim();
                        double bonus = Double.parseDouble(split[3].trim());
                        String type = split[4].trim();
                        int balS = Integer.parseInt(split[5].trim());

                        emp = new Tester(id, name, balS, bonus, type);

                        System.out.println(emp.toString());
                    }
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(emp);
                }

            }
            myInputEmp.close();
        } catch (IOException | NumberFormatException ex) {
            throw ex;
        }
        return list;
    }

    public ArrayList<Developer> getDeveloperByProgrammingLanguage(String p) {
        if (empList == null) {
            return null;
        }

        ArrayList<Developer> listExpert = new ArrayList<>();
        for (Employee emp : empList) {
            if (emp instanceof Developer) {
                Developer developer = (Developer) emp;
                ArrayList<String> programmingLanguages = developer.getProgrammingLanguages();
                if (programmingLanguages != null) {
                    for (String pl : programmingLanguages) {
                        if (pl.equalsIgnoreCase(p)) {
                            listExpert.add(developer);
                        }
                    }
                }
            }
        }
        return listExpert;
    }

    public ArrayList<Tester> getTestersHaveSalaryGreaterThan(double value) {
        if (empList == null) {
            return null;
        }
        if (value < 0) {
            return null;
        }

        ArrayList<Tester> salaryGreaterValue = null;
        for (Employee emp : empList) {
            if (emp instanceof Tester) {
                if (emp.getSalary() > value) {
                    if (salaryGreaterValue == null) {
                        salaryGreaterValue = new ArrayList<>();
                    }
                    salaryGreaterValue.add((Tester) emp);
                }
            }
        }
        return salaryGreaterValue;
    }

    public Employee getEmployeeWithHighestSalary() {
        if (empList == null) {
            return null;
        }

        Employee emax = null;
        for (Employee emp : empList) {
            if (emax == null) {
                emax = emp;
            }
            if (emax.getSalary() <= emp.getSalary()) {
                emax = emp;
            }
        }

        return emax;
    }

    public TeamLeader getLeaderWithMostEmployees() {
        TeamLeader leader = null;
        TreeSet<String> teamName = new TreeSet<>();
        for (Employee emp : empList) {
            if (emp instanceof Developer) {
                teamName.add(((Developer) emp).getTeamName());
            }
        }
        Map<String, Integer> teamEmployeeCount = new HashMap<>();
        int maxEmp = 0, curentCount = 0;
        String nameMaxEmp = null;
        for (String str : teamName) {
            for (Employee emp : empList) {
                if (emp instanceof Developer) {
                    if (((Developer) emp).getTeamName().equals(str)) {
                        curentCount++;
                    }
                }
            }

            teamEmployeeCount.put(str, curentCount);
            curentCount = 0;
        }

        for (Employee emp : empList) {
            if (emp instanceof TeamLeader) {
                for (Map.Entry m : teamEmployeeCount.entrySet()) {
                    if (((TeamLeader) emp).getTeamName().equals((String) (m.getKey()))) {
                        if ((int) (m.getValue()) > maxEmp || ((int) (m.getValue()) == maxEmp && ((TeamLeader) emp).getExpYear() > leader.getExpYear())) {
                            nameMaxEmp = (String) (m.getKey());
                            leader = (TeamLeader) emp;
                            maxEmp = (int) m.getValue();
                        }

                    }
                }
            }
        }
        return leader;
    }

    public ArrayList<Employee> sorted() {
        if (empList == null) {
            return null;
        }

        ArrayList<Employee> sortList = null;
        sortList = (ArrayList<Employee>) empList.clone();
        Comparator com = new Comparator<Employee>() {
            @Override
            public int compare(Employee e1, Employee e2) {
                if (e1.getSalary() == e2.getSalary()) {
                    int lastIndexName1 = e1.getEmpName().lastIndexOf(" ");

                    int lastIndexName2 = e2.getEmpName().lastIndexOf(" ");

                    return (e1.getEmpName().charAt(lastIndexName1 + 1) - e2.getEmpName().charAt(lastIndexName2 + 1));
                } else {
                    return (int) (e2.getSalary() - e1.getSalary());
                }
            }
        };
        sortList.sort(com);
        return sortList;
    }

    public boolean writeFile(String path, ArrayList<Employee> list) {
        try {
            FileWriter writer = new FileWriter(path);
            for (Employee emp : list) {
                writer.write(emp.toString());
                writer.write("\n");
            }
            writer.close();
            System.out.println("Successfully wrote to the file.");
            return true;
        } catch (IOException e) {
            System.out.println("Error occurred while writing to the file.");
            return false;
        }
    }

    public <E> boolean writeFile(String path, E object) {
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(object.toString());
            writer.close();
            System.out.println("Successfully wrote to the file.");
            return true;
        } catch (IOException ex) {
            System.out.println("Error!");
            return false;
        }
    }

    public void printEmpList(ArrayList<Employee> list) {
        for (Employee tmp : empList) {
            System.out.println(tmp);
        }
    }

}
