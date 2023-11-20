/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.model;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author pawel
 */

/**
 * Student gradebook with final grade and all subjects with partial grades
 */
public class StudentGradebook {
    private final List<Subject> subjects;   //list of subjects
    private final FinalGrade finalGrade;    // final grade
    
    /**
     * Constructor
     * The exception is being thrown when the value is out of the range 1-6
     * @throws pl.polsl.model.InvalidGradeException
     */
    public StudentGradebook() throws InvalidGradeException{
        this.subjects = new ArrayList<>();
        this.finalGrade = new FinalGrade(1);
    }
    
    /**
     * Method for adding new subject
     * @param subject
     */
    public void addSubject(Subject subject){
        this.subjects.add(subject);
    }
    
    /**
     * Subjects getter
     * @return 
     */
    public List<Subject> getSubjects(){
        return this.subjects;
    }
    
    /**
     * Final grade getter 
     * @return finalGrade
     */
    public FinalGrade getFinalGrade(){
        return this.finalGrade;
    }
    
    /**
     * Final grade setter 
     * @param value
     * @throws pl.polsl.model.InvalidGradeException
     */
    public void setFinalGrade(double value) throws InvalidGradeException{
        this.finalGrade.setValue(value);
    }
    
    /**
     * Subject getter chosen by name of the subject 
     * @param searchedSubjectName
     * @return found subject
     */
    public Subject getSubject(String searchedSubjectName){
        for (Subject subject : this.subjects) {
            if (subject.getName().equals(searchedSubjectName)) {
                return subject;
            }
        }
        return null;
    }
    
    /**
     * Method calculate average from all subjects convert it and set to final grade in the set method
     * @return finalGrade
     * @throws pl.polsl.model.InvalidGradeException
     */
    public double calculateFinalGrade() throws InvalidGradeException{
        double average = subjects.stream()
                .mapToDouble(Subject::calculateAverage)
                .average()
                .orElse(1.0);
        this.finalGrade.setValue(average);
        return this.finalGrade.getValue();
    }
}