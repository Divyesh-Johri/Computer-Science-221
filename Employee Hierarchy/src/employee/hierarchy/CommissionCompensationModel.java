/*
 * Divyesh Johri
   2/5/2020
 */
package employee.hierarchy;

public class CommissionCompensationModel{
    
     private double grossSales; // gross weekly sales
     private double commissionRate; // commission percentage

     // Constructor
     public CommissionCompensationModel(double grossSales, double commissionRate) {
        // implicit call to Object constructor occurs here

        // if grossSales is invalid throw exception
        if (grossSales < 0.0) {
           throw new IllegalArgumentException("Gross sales must be >= 0.0");
        }  

        // if commissionRate is invalid throw exception
        if (commissionRate <= 0.0 || commissionRate >= 1.0) {
           throw new IllegalArgumentException(
               "Commission rate must be > 0.0 and < 1.0");
        }  
        
        //Assign variables
        this.grossSales = grossSales;
        this.commissionRate = commissionRate;
     }

     // set gross sales amount
     public void setGrossSales(double grossSales) {
        if (grossSales < 0.0) {
           throw new IllegalArgumentException("Gross sales must be >= 0.0");
        }  

        this.grossSales = grossSales;
     }

     // return gross sales amount
     public double getGrossSales() {return grossSales;}

     // set commission rate
     public void setCommissionRate(double commissionRate) {
        if (commissionRate <= 0.0 || commissionRate >= 1.0) {
           throw new IllegalArgumentException(
              "Commission rate must be > 0.0 and < 1.0");
        }

        this.commissionRate = commissionRate;
     }

     // return commission rate
     public double getCommissionRate() {return commissionRate;}

     // calculate earnings
     public double earnings() {
        return getCommissionRate() * getGrossSales();
     }

     // return String representation of CommissionEmployee object
     @Override
     public String toString() {
        return String.format("%s:%n%s: %.2f%n%s: %.2f",
           "Commission Compensation with",
           "Gross sales of", getGrossSales(),
           "Commission Rate of", getCommissionRate());
     }

}
