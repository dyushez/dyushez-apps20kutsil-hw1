package ua.edu.ucu.tempseries;


public class TempSummaryStatistics {
    private final double averageTemp;
    private final double deviationTemp;
    private final double minTemp;
    private final double maxTemp;

    public TempSummaryStatistics(double averageTemp, double deviationTemp,
                                 double minTemp, double maxTemp) {
        this.averageTemp = averageTemp;
        this.deviationTemp = deviationTemp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }
}
