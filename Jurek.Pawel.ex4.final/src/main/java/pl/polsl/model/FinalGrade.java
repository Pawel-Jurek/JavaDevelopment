/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.model;

/**
 *
 * @author pawel
 */

/**
 * The final grade is class for the final grade calculated for all subjects
 */
public class FinalGrade extends Grade {
    /**
     * Constructor
     * The exception is being thrown when the value is out of the range 1-6
     * @param value
     * @throws pl.polsl.model.InvalidGradeException
     */
    public FinalGrade(double value) throws InvalidGradeException {
        super(value);
    }
    
    /**
     * Method calculates value of the grade
     * @param value is a number in the range 1-6 with a step of 0.5
     * @return final grade after rounding
     * @throws pl.polsl.model.InvalidGradeException
     */
    public double calculateValue(double value) throws InvalidGradeException{
        if(value > 6 || value < 1){
            throw new InvalidGradeException("Error: Grade must be between 1 and 6");
        }
        int beforeDecimal = (int) value;
        double afterDecimal = value - beforeDecimal;

        if(afterDecimal >= 0.75){
            return beforeDecimal + 1;
        } else if(afterDecimal >= 0.25){
            return beforeDecimal + 0.5;
        } else {
            return beforeDecimal;
        }
        
    }
    
    /**
     * Overridden method built to setting value for final grade in schema (1-6 with 0.5 step)
     * @param value
     * @throws pl.polsl.model.InvalidGradeException
     */
    @Override
    public void setValue(double value) throws InvalidGradeException{
        double newValue = this.calculateValue(value);
        super.setValue(newValue);
    }
    
    /**
     * Overridden method to simpler and nicer displaying final grade
     */
    @Override
    public String toString(){
        return Double.toString(this.getValue());
    }
}
