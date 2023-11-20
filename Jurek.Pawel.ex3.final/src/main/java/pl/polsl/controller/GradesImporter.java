/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package pl.polsl.controller;

import pl.polsl.model.StudentGradebook;
import pl.polsl.model.InvalidGradeException;
import pl.polsl.model.Subject;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
public class GradesImporter {
    
    /** 
     * Method returns string with first uppercase letter and rest lowercase letters to ensure uniformity of names
     * @param name
     * @return string with each word first uppercase letter and rest lowercase letters to ensure uniformity of names
     */
    public static String formatSubjectName(String name) {
    String[] words = name.split("\\s+|\\.");
    StringBuilder formattedName = new StringBuilder();

    for (String word : words) {
        if (!word.isEmpty()) {
            String formattedWord = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
            formattedName.append(formattedWord).append(" ");
        }
    }

    return formattedName.toString().trim();
}
    
    /**
     * Method imports subjects with grades from given file name to studentGradebook field
     * @param fileName
     * @throws pl.polsl.model.InvalidGradeException
     */
    public void importSubjects(StudentGradebook studentGradebook, String fileName) throws InvalidGradeException{
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
                            }
                        } else {
                            break;
                        }
                    }
                } else{
                    subjectName = line;
                }
                
                subjectName = formatSubjectName(subjectName);
                Subject subject = new Subject(subjectName, grades);
                subject.calculateSubjectFinalGrade();
                studentGradebook.addSubject(subject);
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
        } catch(ArrayIndexOutOfBoundsException e){
        }
    }    
    
}

