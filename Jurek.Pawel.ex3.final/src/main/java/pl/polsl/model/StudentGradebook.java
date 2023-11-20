/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author pawel
 */

/**
 * Student gradebook with final grade and all subjects with partial grades
 */
public class StudentGradebook {
    private final List<Subject> subjects;   //list of subjects
    private Double finalGrade;    // final grade
    private final List<Subject> notPassingSubjects; // list of not passing subjects
    
    /**
     * Constructor
     * The exception is being thrown when the value is out of the range 1-6
     * @throws pl.polsl.model.InvalidGradeException
     */
    public StudentGradebook() throws InvalidGradeException{
        this.subjects = new ArrayList<>();
        this.finalGrade = 1.0;
        this.notPassingSubjects = new ArrayList<>();
    }
    
    /**
     * Method for adding new subject
     * @param subject
     */
    public void addSubject(Subject subject){
        subjects.add(subject);
        if(subject.calculateAverage() < 1.75){
            notPassingSubjects.add(subject);
        }
    }
    
    /**
     * Not passing subjects getter
     * @return not passing subjects
     */
    public List<Subject> getNotPassingSubjects(){
        return notPassingSubjects;
    }
    
    /**
     * Not passing subjects amount getter
     * @return not passing subjects amount
     */
    public int getNotPassingSubjectsAmount(){
        return notPassingSubjects.size();
    }
    
    /**
     * Subjects getter
     * @return 
     */
    public List<Subject> getSubjects(){
        return subjects;
    }
    
    /**
     * Final grade getter 
     * @return finalGrade
     */
    public Double getFinalGrade(){
        calculateFinalGrade();
        return finalGrade;
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
     */
    public void calculateFinalGrade(){
        double average = subjects.stream()
                .mapToDouble(Subject::getSubjectFinalGrade)
                .average()
                .orElse(1.0);
        finalGrade = average;
    }
    
    /**
     * Subjects names getter
     * @return subjects names
     */
    public List<String> getSubjectsNames() {
        return subjects.stream()
            .map(subject -> subject.getName())
            .collect(Collectors.toList());
    }

}