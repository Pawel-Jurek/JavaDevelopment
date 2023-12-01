/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.polsl.model;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 *
 * @author SuperStudent.PL
 */

/**
 * FinalGradeTest
 */
public class FinalGradeTest {

    private static FinalGrade grade;    //grade

    /**
     * Method sets up grade before testing
     * @throws pl.polsl.model.InvalidGradeException
     */
    @BeforeAll
    public static void setUp() throws InvalidGradeException {
        grade = new FinalGrade(4.3);
    }

    /**
     * Grade values
     */
    static Stream<Arguments> gradeValues() {
        return Stream.of(
            Arguments.of(3.75, 4.0),
            Arguments.of(3.74, 3.5),
            Arguments.of(3.76, 4.0),
            Arguments.of(2.25, 2.5),
            Arguments.of(6.0, 6.0),
            Arguments.of(0.7, 1.0),
            Arguments.of(6.25, 6.00)
        );
    }

    /**
     * Testing method
     * @param inputGrade
     * @param expectedValue
     */
    @ParameterizedTest
    @MethodSource("gradeValues")
    public void testValue(double inputGrade, double expectedValue) {
        //Checking if grade is correct
        if(inputGrade > 6 || inputGrade < 1){
            try {
                grade.calculateValue(inputGrade);
                fail("Calculating value for " + inputGrade + " failed. Grade out of the range.");
            }
            catch(InvalidGradeException e)
            {
            }
        } else {
            try {
                assertEquals(grade.calculateValue(inputGrade), expectedValue, 0.01, "Value after calculating should match expected value");
            } catch (InvalidGradeException e) {
                fail("Calculating value for " + inputGrade + " failed");
            }
        }
        
    }
}

