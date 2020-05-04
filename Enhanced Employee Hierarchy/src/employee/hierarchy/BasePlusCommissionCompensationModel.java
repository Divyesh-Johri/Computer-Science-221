/*
 * Divyesh Johri
   2/12/2020
 */
package employee.hierarchy;

public class BasePlusCommissionCompensationModel extends CommissionCompensationModel {
    private double baseSalary; // base salary per week
 
    // six-argument constructor
    public BasePlusCommissionCompensationModel(double grossSales, double commissionRate, double baseSalary) 
    {
      super(grossSales, commissionRate); 

      // if baseSalary is invalid throw exception
      if (baseSalary < 0.0) { 
         throw new IllegalArgumentException("Base salary must be >= 0.0");
      }

      this.baseSalary = baseSalary;
   }

   // set base salary
   public void setBaseSalary(double baseSalary) {
      if (baseSalary < 0.0) { 
         throw new IllegalArgumentException("Base salary must be >= 0.0");
      }

      this.baseSalary = baseSalary; 
   }

   // return base salary
   public double getBaseSalary() {return baseSalary;}

   //Implementation of abstract methods
   @Override
   public double earnings() {return getBaseSalary() + super.earnings();}
   
   @Override
   public void raise(double percent) 
   {
       setCommissionRate(getCommissionRate() * (1.0 + percent));
       baseSalary = baseSalary * (1.0 + percent);     
   }

   // return String representation of BasePlusCommissionEmployee
   @Override
   public String toString() {
      return String.format("%s:%n%s: %.2f%n%s: %.3f%n%s: %.2f",
         "Base Plus Commission Compensation with", "Gross Sales of", getGrossSales(), 
         "Commission Rate of", getCommissionRate(), "Base Salary of", getBaseSalary());
   }
}
