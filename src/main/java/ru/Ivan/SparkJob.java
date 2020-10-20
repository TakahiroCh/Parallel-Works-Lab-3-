package ru.Ivan;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

public class SparkJob {

    private static final String DELIMITERFORNAMES = "\",";
    private static final String DELIMITERFORDELAYS = ",";
    private static final int DESTAIRPORTIDFORNAMES= 0;
    private static final int NAMEAIRPORT = 1;
    private static final int ORIGINAIRPORTID = 11;
    private static final int DESTAIRPORTIDFORDELAYS = 14;
    private static final int ARRDELAY = 17;
    private static final int CANCELLED = 19;
    private static final String NULLSTR = "";
    private static final float ZERO = 0.0F;

    private static float checkNull(String current) {
        if (current.equals(NULLSTR)) {
            return ZERO;
        } else {
            return Float.parseFloat(current);
        }
    }

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> distOfAirportDelays = sc.textFile("664600583_T_ONTIME_sample.csv");
        JavaRDD<String> distOfAirportNames = sc.textFile("L_AIRPORT_ID.csv");

        JavaPairRDD<Integer, String> dataOfAiportNames =
                distOfAirportNames
                        .filter(str -> str.contains("Code"))
                        .mapToPair(value -> {
                            String[] table = value.split(DELIMITERFORNAMES);
                            Integer destAirportID = Integer.valueOf(table[DESTAIRPORTIDFORNAMES]
                                    .replaceAll("\"", ""));
                            return new Tuple2<>(destAirportID, table[NAMEAIRPORT]);
                        });

        JavaPairRDD<Tuple2<Integer, Integer>, FlightSerializable> dataOfAirportDelays =
                distOfAirportDelays
                        .filter(str -> str.contains("YEAR"))
                        .mapToPair(value -> {
                            String[] table = value.split(DELIMITERFORDELAYS);
                            int destAirportID = Integer.parseInt(table[DESTAIRPORTIDFORDELAYS]);
                            int originalAirportID = Integer.parseInt(table[ORIGINAIRPORTID]);
                            float arrDelay = checkNull(table[ARRDELAY]);
                            float iscancelled = Float.parseFloat(table[CANCELLED]);
                            return new Tuple2<>(new Tuple2<>(originalAirportID, destAirportID),
                                    new FlightSerializable(destAirportID, originalAirportID, arrDelay, iscancelled));
                        });

        JavaPairRDD<Tuple2<Integer, Integer>, FlightSerCount> flightSerCounts =
                dataOfAirportDelays
                        .combineByKey(p -> new FlightSerCount(1,
                                p.getArrDelay() > ZERO ? 1 : 0,
                                p.getArrDelay(),
                                p.getCancelled() == ZERO ? 0 : 1),
                                (flightSerCount, flightSerializable) -> flightSerCount.addValue(
                                        flightSerCount.getCountOfFlights() + 1,
                                        flightSerCount.

                                ));






    }
}
