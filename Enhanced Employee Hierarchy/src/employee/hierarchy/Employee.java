/*
 * Divyesh Johri
   2/12/2020
 */
package employee.hierarchy;

public class Employee {
    
    //Will utilize CompensationModel objects
    
    //private class variables
    private final String firstName;
    private final String lastName;
    private final String socialSecurityNumber;
    private CompensationModel model;
    
    //Constructor
    public Employee(String firstName, String lastName, String socialSecurityNumber,
            CompensationModel c)
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
    public void setCompensation(CompensationModel c)
    {
        model = c;
    }
    
    // return employee earnings through CompensationModel object
    public double earnings()
    {
        return(model.earnings());
    }
    
    // calls CompensationModel object's raise function
    public void raise(double percent)
    {
        model.raise(percent);
    }
    
    // toString representation of Employee object
    // Will incorporate toString of CompensationModel object
    @Override
    public String toString(){
        return String.format("%s %s%n%s: %s%n%s%n%s: %.2f%n",
                firstName, lastName, "Social Security Number", socialSecurityNumber,
                model.toString(), "Earnings", earnings());
    }
    
}
