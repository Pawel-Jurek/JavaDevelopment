/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.model;

/**
 *
 * @author pawel
 */
import java.util.List;

/**
 * Subject class
 */
public class Subject {
    private final String name; //name of the subject
    private final List<PartialGrade> partialGrades; //list of partial grades
    
    /**
     * Constructor
     * @param name
     * @param grades
     */
    public Subject(String name, List<PartialGrade> grades){
        this.name = name;
        this.partialGrades = grades;
    }

    /**
     * Method adds grade to list grade
     * InvalidGradeException appears when grade value is out of range 1-6
     * @param grade
     * @throws pl.polsl.model.InvalidGradeException
     */
    public void addPartialGrade(PartialGrade grade) throws InvalidGradeException {
        partialGrades.add(grade);
    }
    

    /**
     * Method calculates weighted average of grades
     * @return weighted average
     */
    public double calculateAverage() {
        if (partialGrades.isEmpty()) {
            return 0.0;
        }

        double sum = 0;
        int totalWeight = 0;
        for (PartialGrade grade : partialGrades) {
            sum += grade.getWeightedGrade();
            totalWeight += grade.getWeight();
        }
        return sum / totalWeight;
    }

    /**
     * Partial grades getter
     * @return partialGrades
     */
    public List<PartialGrade> getGrades() {
        return partialGrades;
    }

    /**
     * Subject name getter
     * @return name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Overridden method to simpler and nicer displaying subject
     */
    @Override
    public String toString(){
        String representation ="------\n" + name + " grades\n";
        
        if(!partialGrades.isEmpty()){
            for(Grade grade: partialGrades){
                representation += (grade + "\n");
            }
        } else {
            representation += "- No grades\n";
        }
        
        return representation;
    }
}

