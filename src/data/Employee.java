package data;

public abstract class Employee {
    protected String empID, empName;
    protected int baseSal;

    public Employee() {
    }

    public Employee(String empID, String empName, int baseSal) {
        this.empID = empID;
        this.empName = empName;
        this.baseSal = baseSal;
    }

    public String getEmpID() {
        return empID;
    }

    public String getEmpName() {
        return empName;
    }

    public int getBaseSal() {
        return baseSal;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public void setBaseSal(int baseSal) {
        this.baseSal = baseSal;
    }
    
    public abstract double getSalary();

    @Override
    public String toString() {
        return empID + "_" + empName + "_" + baseSal;
    }
    
    
}
