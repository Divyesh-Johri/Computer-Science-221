/*
 * Divyesh Johri
   2/5/2020
 */
package employee.hierarchy;

public class Employee {
    
    //Will utilize CommissionCompensationModel objects
    
    //private class variables
    private final String firstName;
    private final String lastName;
    private final String socialSecurityNumber;
    private CommissionCompensationModel model;
    
    //Constructor
    public Employee(String firstName, String lastName, String socialSecurityNumber,
            CommissionCompensationModel c)
    {
        //Assign variables
        this.firstName = firstName; 
        this.lastName = lastName; 
        this.socialSecurityNumber = socialSecurityNumber;
        this.model = c;
    }
    
    // return first name
    public String getFirstName() {return firstName;}
    
    // return last name
    public String getLastName() {return lastName;}
    
    // return social security number
    public String getSocialSecurityNumber() {return socialSecurityNumber;}
    
    // assign new employee compensation model
    public void setCompensation(CommissionCompensationModel c)
    {
        model = c;
    }
    
    // return employee earnings through CommissionCompensationModel object
    public double earnings()
    {
        return(model.earnings());
    }
    
    // toString representation of Employee object
    // Will incorporate toString of CommissionCompensationModel object
    @Override
    public String toString(){
        return String.format("%s %s%n%s: %s%n%s%n%s: %.2f%n",
                firstName, lastName, "Social Security Number", socialSecurityNumber,
                model.toString(), "Earnings", earnings());
    }
    
}
