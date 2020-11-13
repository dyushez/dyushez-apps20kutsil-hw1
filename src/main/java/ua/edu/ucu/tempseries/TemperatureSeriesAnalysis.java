package ua.edu.ucu.tempseries;


import java.util.InputMismatchException;
import static java.util.Arrays.copyOf;


public class TemperatureSeriesAnalysis {

    public double[] temperatures;
    public final double MINIMUM = -273;
    public final double EMPTYCELL = -274;

    public TemperatureSeriesAnalysis() {
        temperatures = new double[]{};
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        for (double temperature : temperatureSeries) {
            if (temperature < MINIMUM) {
                throw new InputMismatchException("Value less than -273C");
            }
        }
        temperatures = temperatureSeries;
    }

    public double average() {
        if (temperatures.length == 0) {
            throw new IllegalArgumentException("array is empty");
        } else {
            double sum = 0;
            for (double temperature : temperatures) {
                sum += temperature;
            }
            return sum / temperatures.length;
        }
    }

    public double deviation() {
        if (temperatures.length == 0){
            throw new IllegalArgumentException("array is empty");
        } else {
            double mean = average();
            float devSum = 0;
            for (double temperature : temperatures) {
                devSum += Math.pow(temperature - mean, 2);
            }
            return Math.sqrt(devSum/temperatures.length);
        }
    }

    public double min() {
        if (temperatures.length == 0) {
            throw new IllegalArgumentException("array is empty");
        } else {
            double minTemp = temperatures[0];
            for (double temperature : temperatures) {
                if (temperature < minTemp) {
                    minTemp = temperature;
                }
            }
            return minTemp;
        }
    }

    public double max() {
        if (temperatures.length == 0) {
            throw new IllegalArgumentException("array is empty");
        } else {
            double maxTemp = temperatures[0];
            for (double temperature : temperatures) {
                if (temperature > maxTemp) {
                    maxTemp = temperature;
                }
            }
            return maxTemp;
        }
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0.0);
    }

    public double findTempClosestToValue(double tempValue) {
        if (temperatures.length == 0) {
            throw new IllegalArgumentException("array is empty");
        } else {
            double closest = temperatures[0];
            for (double temperature : temperatures) {
                if (Math.abs(temperature - tempValue) < Math.abs(closest - tempValue)) {
                    closest = temperature;
                } else if (Math.abs(temperature - tempValue) == Math.abs(closest - tempValue) && temperature > 0) {
                    closest = temperature;
                }
            }
            return closest;
        }
    }

    public double[] findTempsLessThen(double tempValue) {
        double[] tempTemperatures = new double[temperatures.length];
        int i =  0;
        for (double temperature : temperatures) {
            if (temperature < tempValue) {
                tempTemperatures[i] = temperature;
                i++;
            }
        }
        double[] newTemperatures;
        newTemperatures = copyOf(tempTemperatures, i);
        return newTemperatures;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        double[] tempTemperatures = new double[temperatures.length];
        int i =  0;
        for (double temperature : temperatures) {
            if (temperature > tempValue) {
                tempTemperatures[i] = temperature;
                i++;
            }
        }
        double[] newTemperatures;
        newTemperatures = copyOf(tempTemperatures, i);
        return newTemperatures;
    }

    public TempSummaryStatistics summaryStatistics() {
        if (temperatures.length == 0){
            throw new IllegalArgumentException("array is empty");
        }
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }


    public double addTemps(double... temps) {
        double sum = 0;
        for (double temp : temperatures){
            sum += temp;
        }

        int index = temperatures.length;
        for (int i = 0; i < temperatures.length; i++) {
            if (temperatures[i] == -EMPTYCELL) {
                index = i;
                break;
            }
        }

        int newSize = temperatures.length;
        while (newSize < temperatures.length + temps.length) {
            newSize *= 2;
        }
        temperatures = copyOf(temperatures, newSize);
        for (int j = 0; j < temps.length; j++) {
            if (temps[j] < MINIMUM) {
                throw new InputMismatchException("Value less than -273C");
            } else {
                temperatures[index] = temps[j];
                sum += temps[j];
                index++;
            }
        }
        for (int i = index; i < temperatures.length; i++) {
            temperatures[i] = EMPTYCELL;
        }
        return sum;
    }
}
