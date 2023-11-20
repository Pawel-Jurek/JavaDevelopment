/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package pl.polsl.controller;

import pl.polsl.view.View;
import pl.polsl.model.StudentGradebook;
import pl.polsl.model.InvalidGradeException;
import pl.polsl.model.Subject;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import pl.polsl.model.PartialGrade;

/**
 *
 * @author SuperStudent.PL
 */

/**
 * Controller class. It's main tasks are:
 * - operations on objects
 * - menu operation 
 */
public class Controller {
    private final View view;  // view class to display messages in the console and communicate with the user
    private boolean exceptions;  // founded exceptions
    private final StudentGradebook studentGradebook;   // student gradebook contains final grade and subjects with partial grades
    private static final Scanner scanner = new Scanner(System.in);    // scanner for getting data from user
    
    /**
     * Controller constructor 
     * @throws pl.polsl.model.InvalidGradeException
     */
    public Controller() throws InvalidGradeException{
        view = new View();
        exceptions = false;
        studentGradebook = new StudentGradebook();
        
    }

    /** 
     * This method takes the name of the grade file, then imports data from it and finally opens the menu 
     * @param args
     * @throws pl.polsl.model.InvalidGradeException
     */
    public void runProgram(String[] args)throws InvalidGradeException { 
        String fileName = getGradesFileName(args);
        importSubjects(fileName);
        menu();   
    }
    
    /**
     * Method returns file name. If args are given function reads it. In other case it asks user to provide file name
     * @param args
     * @return file name
     */
    public String getGradesFileName(String[] args){
        if(args.length > 0){
            return args[0];
        }
        view.askForFilename();
        return scanner.next();
    }
    
    /** 
     * Method returns string with first uppercase letter and rest lowercase letters to ensure uniformity of names
     * @param name
     * @return string with first uppercase letter and rest lowercase letters to ensure uniformity of names
     */
    public static String formatSubjectName(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }
    
    /**
     * Method imports subjects with grades from given file name to studentGradebook field
     * @param fileName
     * @throws pl.polsl.model.InvalidGradeException
     */
    public void importSubjects(String fileName) throws InvalidGradeException{
        try {
            File file = new File(fileName);
            Scanner fileScanner = new Scanner(file);
            String subjectName;
            while (fileScanner.hasNextLine()) {
                //Loading the next line from a file
                List<PartialGrade> grades = new ArrayList<>();
                String line = fileScanner.nextLine();
                
                if(line.contains(": ")){
                    //Dividing it into subject and grades
                    String[] parts = line.split(": ");
                    subjectName = parts[0];
                    

                    //Dividing the all grades into individual grades
                    String[] gradesStr = parts[1].split(", ");

                    for (String gradeStr : gradesStr) {
                        //Dividing each grade into value and weight
                        String[] grade = gradeStr.split("-");               
                        // Checking format of grades [value, weight]
                        if (grade.length == 2) {
                            String valueStr = grade[0];
                            String weightStr = grade[1];

                            try {
                                double value = Double.parseDouble(valueStr);
                                int weight = Integer.parseInt(weightStr);
                                grades.add(new PartialGrade(value, weight));

                            } catch (NumberFormatException | InvalidGradeException e) {
                                view.showException(e.getMessage());
                                exceptions = true;
                            }
                        } else {
                            view.wrongGradeFormat();
                            exceptions = true;
                            break;
                        }
                    }
                } else{
                    subjectName = line;
                }
                
                subjectName = formatSubjectName(subjectName);
                Subject subject = new Subject(subjectName, grades);
                studentGradebook.addSubject(subject);
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            view.notFoundError("file");
            exceptions = true;
        } catch(ArrayIndexOutOfBoundsException e){
            exceptions = true;
            view.showException(e.getMessage());
        }
    }
    /** Menu method with options:
     * 1 - display grades
     * 2 - display average
     * 3 - display all subjects
     * 4 - display not passing subjects
     * 5 - display final grade
     * 6 - exit
     * If exceptions were found, the menu is not displaying 
     * @throws pl.polsl.model.InvalidGradeException
     */
    public void menu() throws InvalidGradeException{
        int option = 0;      
        if(exceptions == false)
        {
            do {
                
                try{
                    // Getting chosen option in menu by the user
                    view.displayMenu();
                    option = scanner.nextInt();
                    
                    // Checking if option will need to provide subject name
                    if (option == 1 || option == 2) {
                        view.askForSubject();
                        String searchedSubjectName = formatSubjectName(scanner.next()); 
                        Subject foundSubject = studentGradebook.getSubject(searchedSubjectName);
 
                        if (foundSubject != null) {
                            if(option == 2){
                                view.displayGradesAverage(searchedSubjectName, foundSubject.calculateAverage());
                            } else {
                                view.displayGrades(foundSubject);
                            }     
                        } else {
                            view.notFoundError("subject");
                        }

                    }
                    else if(option == 3){
                        view.displaySubjects("All",studentGradebook.getSubjects());
                    }
                    else if(option == 4 || option == 5){
                        List<Subject> notPassingSubjects = studentGradebook.getSubjects().stream()
                            .filter(subject -> subject.calculateAverage() < 1.75)
                            .collect(Collectors.toList());
                        if (option == 4){
                            view.displaySubjects("Not passing",notPassingSubjects);
                        }  
                        else {
                                view.displayFinalGrade(studentGradebook.calculateFinalGrade(), notPassingSubjects);
                        }
                    }
                        
                    else if (option != 6){
                        view.wrongMenuOption();
                    }
                }catch (java.util.InputMismatchException e) {
                        view.wrongMenuOption();
                        exceptions = true;
                        scanner.next();
                    
                }

            } while(option != 6);
        }
    }
}

