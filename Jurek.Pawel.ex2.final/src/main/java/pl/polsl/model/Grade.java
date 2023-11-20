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
 * Grade class
 */
public class Grade {
     private double value;      // grade value
     
    /**
     * Contructor 
     * The exception is being thrown when the value is out of the range 1-6
     * @param value
     * @throws pl.polsl.model.InvalidGradeException
     */
     public Grade(double value) throws InvalidGradeException{
        if (value >= 1 && value <= 6) {
            this.value = value;
        } else {
            throw new InvalidGradeException("Error: Grade must be between 1 and 6");
        } 
    }
     
    /**
     * Grade value getter
     * @return value
     */
    public double getValue(){
        return this.value;
    }
    
    /**
     * Grade value setter
     * @param value
     * @throws pl.polsl.model.InvalidGradeException
     */
    public void setValue(double value)throws InvalidGradeException{
        if (value >= 1 && value <= 6) {
            this.value = value;
        } else {
            throw new InvalidGradeException("Error: Grade must be between 1 and 6");
        } 
    }
}
