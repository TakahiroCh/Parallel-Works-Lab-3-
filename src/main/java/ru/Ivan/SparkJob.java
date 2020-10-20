package ru.Ivan;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

public class SparkJob {

    private static final String DELIMITER = "\",";
    private static final int DESTAEROPORTID = 0;

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> distOfAirportDelays = sc.textFile("664600583_T_ONTIME_sample.csv");
        JavaRDD<String> distOfAirportNames = sc.textFile("664600583_T_ONTIME_sample.csv");

        JavaPairRDD<String, Long> dataOfAiportNames =
                distOfAirportNames
                        .filter(str -> str.contains("Code"))
                        .mapToPair(value -> {
                            String[] table = value.split(DELIMITER);
                            int destAiroportID = Integer.parseInt(table[DESTAEROPORTID]
                                    .replaceAll("\"", ""));
                            return new Tuple2<>(destAiroportID)

                        });




    }
}
