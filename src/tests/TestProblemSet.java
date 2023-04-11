package tests;

import org.junit.Test;
import ratings.ProblemSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class TestProblemSet {
    private final double EPSILON = .001;
    public void compareDoubles (double d1,double d2){
        assertTrue(Math.abs(d1-d2) < EPSILON);
    }

    @Test
    public void testAverageWithPositives(){
        ArrayList<Double> numbers1 = new ArrayList<>();
        numbers1.add(1.0);
        numbers1.add(2.0);
        numbers1.add(3.0);

        compareDoubles(ProblemSet.average(numbers1),2.0);

        ArrayList<Double> numbers2 = new ArrayList<>();
        numbers2.add(6.5);
        numbers2.add(6.5);
        numbers2.add(8.5);
        numbers2.add(8.5);

        compareDoubles(ProblemSet.average(numbers2),7.5);

        ArrayList<Double> numbers3 = new ArrayList<>();
        numbers3.add(7.5);

        compareDoubles(ProblemSet.average(numbers3),7.5);

    }
    @Test
    public void testAverageWithNegatives(){
        ArrayList<Double> numbers1 = new ArrayList<>();
        numbers1.add(-1.0);
        numbers1.add(-2.0);
        numbers1.add(-3.0);

        compareDoubles(ProblemSet.average(numbers1),-2.0);

        ArrayList<Double> numbers2 = new ArrayList<>();
        numbers2.add(-6.5);
        numbers2.add(-6.5);
        numbers2.add(-8.5);
        numbers2.add(-8.5);

        compareDoubles(ProblemSet.average(numbers2),-7.5);

        ArrayList<Double> numbers3 = new ArrayList<>();
        numbers3.add(-7.5);

        compareDoubles(ProblemSet.average(numbers3),-7.5);

    }
    @Test
    public void testAverageWithEdgeCases(){
        ArrayList<Double> numbers1 = new ArrayList<>();

        compareDoubles(ProblemSet.average(numbers1),0.0);

        ArrayList<Double> numbers2 = new ArrayList<>();
        numbers2.add(-6.5);
        numbers2.add(6.5);

        compareDoubles(ProblemSet.average(numbers2),0.0);

    }
    @Test
    public void testSumOfDigits(){
        int num1 = 123;
        int num2 = 57;
        int num3 = -36;
        int num4 = 0000;
        int num5 = 12345;

        assertTrue(ProblemSet.sumOfDigits(num1)==6);
        assertTrue(ProblemSet.sumOfDigits(num2)==12);
        assertTrue(ProblemSet.sumOfDigits(num3)==9);
        assertTrue(ProblemSet.sumOfDigits(num4)==0);
        assertTrue(ProblemSet.sumOfDigits(num5)==15);
    }
    @Test
    public void testBestKey(){
        HashMap<String,Integer> TestP1 = new HashMap<>();
        TestP1.put("CSE", 100);
        TestP1.put("MTH", 90);
        TestP1.put("MGT", 10);
        String TestP1Answer = ProblemSet.bestKey(TestP1);
        assertTrue(TestP1Answer.equals("CSE"));

        HashMap<String,Integer> TestP2 = new HashMap<>();
        TestP2.put("CSE", 0);
        TestP2.put("MTH", 15);
        TestP2.put("MGT", 10);
        String TestP2Answer = ProblemSet.bestKey(TestP2);
        assertTrue(TestP2Answer.equals("MTH"));

        HashMap<String,Integer> TestP3 = new HashMap<>();
        TestP3.put("CSE", 0);
        TestP3.put("MTH", -20);
        TestP3.put("MGT", 10);
        String TestP3Answer = ProblemSet.bestKey(TestP3);
        assertTrue(TestP3Answer.equals("MGT"));


        HashMap<String,Integer> MultipleBest1 = new HashMap<>();
        MultipleBest1.put("cat", 5);
        MultipleBest1.put("dog", 5);
        MultipleBest1.put("fox", 4);
        String MultipleBestAnswer1 = ProblemSet.bestKey(MultipleBest1);
        assertTrue(MultipleBestAnswer1.equals("cat") || MultipleBestAnswer1.equals("dog") );

        HashMap<String,Integer> MultipleBest2 = new HashMap<>();
        MultipleBest2.put("dog", 5);
        MultipleBest2.put("fox", 5);
        MultipleBest2.put("cat", 5);
        String MultipleBestAnswer2 = ProblemSet.bestKey(MultipleBest2);
        assertTrue( MultipleBestAnswer2.equals("cat") || MultipleBestAnswer2.equals("dog") || MultipleBestAnswer2.equals("fox") );

        HashMap<String,Integer> MultipleBestNegative = new HashMap<>();
        MultipleBestNegative.put("wrong", -100);
        MultipleBestNegative.put("correct", -1);
        MultipleBestNegative.put("wrong", -55);
        String MultipleBestNegativeAnswer = ProblemSet.bestKey(MultipleBestNegative);
        assertTrue(MultipleBestNegativeAnswer.equals("correct"));

        HashMap<String,Integer> Test3 = new HashMap<>();
        String Test3Answer = ProblemSet.bestKey(Test3);
        assertTrue(Test3Answer.equals(""));





    }







    // TODO: Write testing for all 3 methods of the ratings.ProblemSet class


}