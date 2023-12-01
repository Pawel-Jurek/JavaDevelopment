/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package pl.polsl.controller;

import jakarta.servlet.http.Part;
import java.io.BufferedReader;
import pl.polsl.model.StudentGradebook;
import pl.polsl.model.InvalidGradeException;
import pl.polsl.model.Subject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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
     * @param studentGradebook
     * @param filePart
     * @return exceptions 
     * @throws pl.polsl.model.InvalidGradeException
     */
    public boolean importSubjects(StudentGradebook studentGradebook, Part filePart) throws InvalidGradeException {
        boolean exceptions = false;
        try {
            // Get the InputStream from the Part
            InputStream fileContent = filePart.getInputStream();

            // Use BufferedReader to read the file content line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent));

            String subjectName;
            String line;

            while ((line = reader.readLine()) != null && !exceptions) {
                // Loading the next line from the file
                List<PartialGrade> grades = new ArrayList<>();

                if (line.contains(": ")) {
                    // Dividing it into subject and grades
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
                                    exceptions = true;
                                }
                            } else {
                                exceptions = true;
                            }
                        }
                } 
                else{
                    subjectName = line;
                }

                subjectName = formatSubjectName(subjectName);
                Subject subject = new Subject(subjectName, grades);
                subject.calculateSubjectFinalGrade();
                studentGradebook.addSubject(subject);
            }
            reader.close();
        } catch (IOException | InvalidGradeException e) {
            exceptions = true;
        } finally {
            if(exceptions == true){
                studentGradebook.clear();
            }
        }
        return exceptions;
    }    
    
}

