package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.InputMismatchException;


public class TemperatureSeriesAnalysisTest {

    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.average();
        
        assertEquals(expResult, actualResult, 0.00001);        
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeviationWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.deviation();
    }

    @Test
    public void testDeviation(){
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 3.7416573867739;

        double actualResult = seriesAnalysis.deviation();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMin(){
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -5.0;

        double actualResult = seriesAnalysis.min();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testMax(){
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 5.0;

        double actualResult = seriesAnalysis.max();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testFindTempClosestToValueEmpty(){
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        double actualResult = seriesAnalysis.findTempClosestToValue(-7.0);
    }

    @Test
    public void testFindTempClosestToValue(){
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -5.0;

        double actualResult = seriesAnalysis.findTempClosestToValue(-7.0);

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToZero(){
        double[] temperatureSeries = {3.0, -5.0, -1.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.findTempClosestToZero();

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsLessThen(){
        double[] temperatureSeries = {3.0, -5.0, -1.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {3.0, -5.0, -1.0, 1.0};

        double[] actualResult = seriesAnalysis.findTempsLessThen(3.5);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempsGreaterThen(){
        double[] temperatureSeries = {3.0, -5.0, -1.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] expResult = {5.0};

        double[] actualResult = seriesAnalysis.findTempsGreaterThen(3.5);

        assertArrayEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testAddTemps(){
        double[] temperatureSeries = {3.0, -5.0, -1.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 28.0;

        double actualResult = seriesAnalysis.addTemps(27.0, 5.0, -10.0, 3.0, 0.0);

        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = InputMismatchException.class)
    public void testAddTempsErr(){
        double[] temperatureSeries = {3.0, -5.0, -1.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 28.0;

        double actualResult = seriesAnalysis.addTemps(27.0, 5.0, -300.0, 3.0, 0.0);

        assertEquals(expResult, actualResult, 0.00001);
    }
}
