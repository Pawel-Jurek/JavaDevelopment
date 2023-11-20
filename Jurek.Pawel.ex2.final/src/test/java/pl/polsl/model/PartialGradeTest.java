/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.polsl.model;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 *
 * @author pawel
 */

/**
 * PartialGradeTest
 */
public class PartialGradeTest {   

    /**
     * grade values
     */
    static Stream<Arguments> gradeValues() {
        return Stream.of(
            Arguments.of(3.75,1, 3.75),
            Arguments.of(3.74,2, 7.48),
            Arguments.of(3.76,3, 11.28),
            Arguments.of(2.25,4, 9.0),
            Arguments.of(6.0,2, 12.0),
            Arguments.of(0.7,1, 0.7),
            Arguments.of(0.7,-11, 0),
            Arguments.of(6.25,0, 0)
        );
    }

    /**
     * Testing method
     * @param inputGrade
     * @param inputGradeWeight
     * @param expectedValue
     */
    @ParameterizedTest
    @MethodSource("gradeValues")
    public void testValue(double inputGrade, int inputGradeWeight, double expectedValue) {
        
        try {
            PartialGrade grade = new PartialGrade(inputGrade, inputGradeWeight);
            double result = grade.getWeightedGrade();
            if (inputGrade >= 1.0 && inputGrade <= 6.0 && inputGradeWeight >= 0) {
                assertEquals(result, expectedValue, 0.01, "Value after calculating should match expected value");
            } else {
                fail("Expected InvalidGradeException for input grade: " + inputGrade);
            }
        } 
        catch (InvalidGradeException e) {
            if (inputGrade >= 1.0 && inputGrade <= 6.0) {
                fail("Unexpected InvalidGradeException for input grade: " + inputGrade);
            }
        }
    }
}

