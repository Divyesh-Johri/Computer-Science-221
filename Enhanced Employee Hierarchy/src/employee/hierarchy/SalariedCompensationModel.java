/*
 * Divyesh Johri
   2/12/20
 */
package employee.hierarchy;


public class SalariedCompensationModel extends CompensationModel{
    
    //Instance variables
    private double weeklySalary;
    
    //Constructor, assigns value to weeklySalary
    public SalariedCompensationModel(double salary)
    {
        weeklySalary = salary;
    }
    
    //Implementation of abstract methods    
    @Override
    public double earnings()
    {
        return weeklySalary;
    }
    
    @Override
    public void raise(double percent)
    {
        weeklySalary = weeklySalary * (1.0 + percent);
    }
    
    //Class specific toString
    @Override
    public String toString()
    {
        return String.format("%s:%n%s: %.2f",
                "Salaried Compensation with",
                "Weekly Salary of", weeklySalary);
    }
    
}
