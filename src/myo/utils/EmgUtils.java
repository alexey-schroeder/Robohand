package myo.utils;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

import java.util.ArrayList;

/**
 * Created by Alex on 14.06.2015.
 */
public class EmgUtils {

    public static double[] calculateStandardDeviation(ArrayList<byte[]> emgData) {
        double[] result = new double[8];
        for (int i = 0; i < 8; i++) {
            DescriptiveStatistics summaryStatistics = new DescriptiveStatistics();
            for (byte[] currentData : emgData) {
                byte data = currentData[i];
                summaryStatistics.addValue(data);
            }
            result[i] = summaryStatistics.getStandardDeviation();
        }
        return result;
    }
}
