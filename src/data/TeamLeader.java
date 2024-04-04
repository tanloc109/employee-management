package data;

import java.util.ArrayList;

public class TeamLeader extends Developer {

    private double bonus_rate;

    public TeamLeader() {
    }

    public TeamLeader(String empID, String empName, int baseSal, String teamName, ArrayList<String> programmingLanguages, int expYear, double bonus_rate) {
        super(empID, empName, baseSal, teamName, programmingLanguages, expYear);
        this.bonus_rate = bonus_rate;
    }

    public double getBonusRate() {
        return bonus_rate;
    }

    public void setBonusRate(double bonus_rate) {
        this.bonus_rate = bonus_rate;
    }

    public double getSalary() {
        if (expYear >= 5) {
          return (baseSal + expYear * 2000000) * (1 + bonus_rate);
      } else if (expYear >= 3) {
          return (baseSal + expYear * 1000000) * (1 + bonus_rate);
      } else {
          return (baseSal) * (1 + bonus_rate);
      }
    }
}
