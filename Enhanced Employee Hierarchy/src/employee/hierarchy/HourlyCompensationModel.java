/*
 * Divyesh Johri
   2/12/20
 */
package employee.hierarchy;


public class HourlyCompensationModel extends CompensationModel {
    
    //Instance variables
    private double wage;
    private double hours;
    
    //Constructor, assigns wage and hours to inputted values
    public HourlyCompensationModel(double w, double h)
    {
        wage = w;
        hours = h;
    }
    
    //Implementation of abstract methods
    @Override
    public double earnings()
    {
        if(hours > 40)
            return(((wage * 1.5) * (hours - 40)) + (wage * 40));
        else
            return(wage * hours);
    }
    
    @Override
    public void raise(double percent)
    {
        wage = wage * (1.0 + percent);
    }
    
    //Class specific toString
    @Override
    public String toString()
    {
        return String.format("%s:%n%s: %.2f%n%s: %.2f",
                "Hourly Compensation with",
                "Wage of", wage,
                "Hours worked of", hours);
    }
}
