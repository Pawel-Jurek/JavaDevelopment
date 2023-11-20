/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.view;

import pl.polsl.model.Subject;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author pawel
 */

/**
 * View class including printing and user communication methods 
 */
public class View {
    private final DecimalFormat decimalFormat = new DecimalFormat("#.##"); // decimal format field with pattern: '#.##'
    
    /** 
     * Lambda interface to compare subjects
     */
    interface SubjectComparator extends Comparator<Subject> {
    }
    /**
     * Method is asking for filename
     */
    public void askForFilename(){
        System.out.println("Write filename with grades. For example \"grades.txt\". ");
    }
    /**
     * Method is asking for subject name
     */
    public void askForSubject(){
        System.out.println("Write subject name: ");
    }
    /**
     * Method is diplaying grades average from given subject
     * @param subject
     * @param average
     */
    public void displayGradesAverage(String subject, double average){
        String formattedAverage = decimalFormat.format(average);
        System.out.println(subject + " grades average: " + formattedAverage);
    }
    /**
     * Method is displaying grades with weight from given subject
     * @param subject
     */
    public void displayGrades(Subject subject) {
        System.out.println(subject);
    }
    /**
     * Method is displaying menu and getting option from user
     */
    public void displayMenu(){
        System.out.println("-----------------------");
        System.out.println("What do you want to do?");
        System.out.println("1 - Show all grades from choosen subject");
        System.out.println("2 - Show average from grades froms choosen subject");
        System.out.println("3 - Show your subjects");
        System.out.println("4 - Show your not passing subjects");
        System.out.println("5 - Show your final grade");
        System.out.println("6 - Exit");
    }
    
    /**
     * Method showing info about not founding object
     * @param obj
     */
    public void notFoundError(String obj){
         System.err.println("Error: The " + obj + " not found");
    }
    /**
     * Method showing all subjects
     * @param subjectsType
     * @param subjects
     */
    public void displaySubjects(String subjectsType, List<Subject> subjects) {
        SubjectComparator subjectNameComparator = (subject1, subject2) -> subject1.getName().compareTo(subject2.getName());
        System.out.print(subjectsType + " subjects: ");
        int size = subjects.size();
        if(size>0){
            List<Subject> sortedSubjectsByName = subjects
                .stream()
                .sorted(subjectNameComparator)
                .collect(Collectors.toList());
            for (int i = 0; i < size; i++) {
                System.out.print(sortedSubjectsByName.get(i).getName());
                if (i < size - 1) {
                    System.out.print(", ");
                }
            }
        } else {
            System.out.print("None");
        }
        
        System.out.println();
    }
    /**
     * Method showing exception message
     * @param message
     */
    public void showException(String message){
        System.err.println("Error: " + message);
    }
    /**
     * Method showing info about wrong grade format
     */
    public void wrongGradeFormat(){
        System.out.println("Error: Wrong grades format. Correct one:");
        System.out.println("Subject: grade1 value1, grade2 value2...");
    }
    
    /**
     * Method showing final grade with communicate about passing
     * @param grade
     * @param notPassingSubjects
     */
    public void displayFinalGrade(double grade, List<Subject> notPassingSubjects){
        if(grade >= 2 && notPassingSubjects.isEmpty()){
            System.out.println("You are passing this semester with grade: " + grade);
        } else{
            System.out.println("You are not passing this semester. Your grade: " + grade);
            System.out.println("Not passing subjects: " 
                    + notPassingSubjects.stream().map(s -> s.getName()).collect(Collectors.joining(", ")) 
                    + "\n");
        }      
    }
    
    /**
     * Method displaying communicate about wrong menu option
     */
    public void wrongMenuOption(){
        System.out.println("Incorrent option, try number in from range 1-6");
    }
    
}
