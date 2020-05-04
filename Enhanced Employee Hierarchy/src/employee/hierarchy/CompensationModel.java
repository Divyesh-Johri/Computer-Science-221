/*
 * Divyesh Johri
   2/12/20
 */
package employee.hierarchy;


public abstract class CompensationModel implements Compensation {
    //Implements methods from interface Compensation
    @Override
    public abstract double earnings();
    @Override
    public abstract void raise(double percent);
    
}
