package ru.Ivan;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

public class SparkJob {

    private static final String DELIMITER = "\",";
    private static final int DESTAIRPORTID = 0;
    private static final int NAMEAIRPORT = 1;

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> distOfAirportDelays = sc.textFile("664600583_T_ONTIME_sample.csv");
        JavaRDD<String> distOfAirportNames = sc.textFile("664600583_T_ONTIME_sample.csv");

        JavaPairRDD<String, Integer> dataOfAiportNames =
                distOfAirportNames
                        .filter(str -> str.contains("Code"))
                        .mapToPair(value -> {
                            String[] table = value.split(DELIMITER);
                            Integer destAirportID = Integer.valueOf(table[DESTAIRPORTID]
                                    .replaceAll("\"", ""));
                            return new Tuple2<>(table[NAMEAIRPORT], destAirportID);
                        });

        

    }
}
