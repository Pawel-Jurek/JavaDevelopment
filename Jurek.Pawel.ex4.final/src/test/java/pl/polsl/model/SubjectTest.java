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
 * @author SuperStudent.PL
 */

/**
 * SubjectTest
 */
public class SubjectTest {

    private Subject subject;    //subject

    /**
     * Method sets up subject before each test
     */
    @BeforeEach
    public void setUp() throws InvalidGradeException {
        subject = new Subject("Math", new ArrayList<>());
    }

    /**
     * Grade data
     */
    static Stream<Arguments> gradeData() throws InvalidGradeException {
        return Stream.of(
            Arguments.of(new ArrayList<PartialGrade>(), 1.0), // Test case for empty list
            Arguments.of(
                List.of(new PartialGrade(3.5, 2)), 
                3.5
            ),
            Arguments.of(
                List.of(new PartialGrade(5.0, 1), new PartialGrade(4.0, 1), new PartialGrade(3.0, 1)), 
                4.0 
            ),
            Arguments.of(
                List.of(new PartialGrade(2.0, 2), new PartialGrade(4.0, 2), new PartialGrade(5.0, 1)), 
                3.4 
            )
        );
    }

    /**
     * Testing method
     * @param partialGrades
     * @param expectedAverage
     */
    @ParameterizedTest
    @MethodSource("gradeData")
    public void testCalculateAverage(List<PartialGrade> partialGrades, double expectedAverage){
        try{
            for(PartialGrade grade: partialGrades){
                subject.addPartialGrade(grade);
            }
            double actualAverage = subject.calculateAverage();
            assertEquals(expectedAverage, actualAverage, 0.01, "Average calculation is incorrect.");
        }
        catch(InvalidGradeException e)
        {}
    }
}
