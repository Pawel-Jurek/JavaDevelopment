/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.polsl.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 * @author pawel
 */

/**
 * StudentGradebookTest
 */
public class StudentGradebookTest {

    private StudentGradebook gradebook; //gradebook
    private FinalGrade finalGrade;  //final grade

    /**
     * Method sets up final grade and gradebook before each test
     * @throws pl.polsl.model.InvalidGradeException
     */
    @BeforeEach
    public void setUp() throws InvalidGradeException {
        // Inicjalizacja obiektu StudentGradebook i FinalGrade przed każdym testem
        finalGrade = new FinalGrade(1.0);
        gradebook = new StudentGradebook();
    }

    /**
     * gradebook data
     */
    static Stream<Arguments> gradebookData() throws InvalidGradeException {
        return Stream.of(
            Arguments.of(
                new ArrayList<Subject>(), 
                1.0 // Expected final grade when there are no subjects
            ),
            Arguments.of(
                List.of(
                    new Subject("Math", List.of(new PartialGrade(3.5, 2), new PartialGrade(4.0, 3))),
                    new Subject("History", List.of(new PartialGrade(3.0, 2), new PartialGrade(3.5, 1)))
                ),
                3.5 // Expected final grade
            ),
            Arguments.of(
                List.of(
                    new Subject("Science", List.of(new PartialGrade(4.0, 2), new PartialGrade(4.5, 3))),
                    new Subject("English", List.of(new PartialGrade(5.0, 1), new PartialGrade(4.0, 2)))
                ),
                4.5 // Expected final grade
            )
        );
    }

    /**
     * Testing method
     * @param subjects
     * @param expectedFinalGrade
     */
    @ParameterizedTest
    @MethodSource("gradebookData")
    public void testCalculateFinalGrade(List<Subject> subjects, double expectedFinalGrade) {
        try {
            for (Subject subject : subjects) {
                gradebook.addSubject(subject);
            }
            double actualFinalGrade = gradebook.calculateFinalGrade();
            assertEquals(expectedFinalGrade, actualFinalGrade, 0.01, "Final grade calculation is incorrect.");
        } catch (InvalidGradeException e) {
            fail("Unexpected InvalidGradeException");
        }
    }
}

