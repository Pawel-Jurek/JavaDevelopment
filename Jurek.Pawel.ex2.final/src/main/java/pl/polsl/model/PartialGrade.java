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
public class PartialGrade extends Grade{
    private final int weight;     // grade weight
    
    /**
     * Contructor
     * The exception is being thrown when the value is out of the range 1-6
     * @param value
     * @param weight
     * @throws pl.polsl.model.InvalidGradeException
     */
    public PartialGrade(double value, int weight) throws InvalidGradeException {
        super(value);
        if(weight < 0){
            throw new InvalidGradeException("Grade weight must be higher than 0");
        } else {
            this.weight = weight; 
        }
        
    }
    /**
     * Grade weight getter
     * @return weight 
     */
    public int getWeight(){
        return this.weight;
    }

    /** 
     * Method returns weighted grade
     * @return weighted grade
     */
    public double getWeightedGrade(){
        return this.getValue() * this.weight;
    }
    
    /**
     * Overridden method to simpler and nicer displaying partial grade
     */
    @Override
    public String toString(){
        return "grade: " + this.getValue() + "   weight: " + weight;
    }
}
