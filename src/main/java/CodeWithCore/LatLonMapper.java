package CodeWithCore;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

public class LatLonMapper {
    public static void main(String[] args) throws InterruptedException {
        SparkConf sparkConf = new SparkConf().setAppName("Word count App").setMaster("local").set("spark.ui.port","4049");
        JavaSparkContext javaSparkContext = new JavaSparkContext(sparkConf);
        javaSparkContext.setLogLevel("ERROR");

        JavaRDD<String> stringJavaRDD = javaSparkContext.textFile("file:///home/labuser/latlon.tsv",4);
        System.out.println("Num of partitions : " +stringJavaRDD.getNumPartitions());
        JavaPairRDD<Tuple2<String, String>, String> tuple2StringJavaPairRDD = stringJavaRDD.map(x -> Arrays.asList(x.split(" ")))
                .mapToPair(x -> new Tuple2<>(new Tuple2<String, String>(x.get(1), x.get(2)), x.get(3)));
        tuple2StringJavaPairRDD.foreach(x-> System.out.println(x));
        tuple2StringJavaPairRDD.glom().foreach(x-> System.out.println(x));

        Thread.sleep(5000000);
        javaSparkContext.close();
    }
}
