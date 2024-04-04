package data;

public class Tester extends Employee{
    private double bonusRate;
    private String type;

    public Tester() {
    }

    public Tester(String empID, String empName, int baseSal, double bonusRate, String type) {
        super(empID, empName, baseSal);
        this.bonusRate = bonusRate;
        this.type = type;
    }

    public double getBonusRate() {
        return bonusRate;
    }

    public String getType() {
        return type;
    }

    public void setBonusRate(double bonusRate) {
        this.bonusRate = bonusRate;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public double getSalary() {
        return baseSal * (1 + bonusRate);
    }

    @Override
    public String toString() {
        return getEmpID() + "_" + getEmpName() + "_" + getBaseSal();
    }
    
    
    
}
