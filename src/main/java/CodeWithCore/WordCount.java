package CodeWithCore;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

public class WordCount {
    public static void main(String[] args) throws InterruptedException {
        SparkConf sparkConf = new SparkConf().setAppName("Word count App").setMaster("local");
        JavaSparkContext javaSparkContext = new JavaSparkContext(sparkConf);
        javaSparkContext.setLogLevel("ERROR");

        JavaRDD<String> stringJavaRDD = javaSparkContext.textFile("file:///home/labuser/myFile");
        stringJavaRDD.foreach(x-> System.out.println(x));

        System.out.println(stringJavaRDD.flatMap(x-> Arrays.asList(x.split(" ")).iterator()).mapToPair(x-> new Tuple2<>(x,1)).countByKey());

        JavaPairRDD<String, Integer> reduceByKeyCntRdd = stringJavaRDD.flatMap(x -> Arrays.asList(x.split(" ")).iterator()).mapToPair(x -> new Tuple2<>(x, 1)).reduceByKey((x, y) -> x + y);

        reduceByKeyCntRdd.foreach(x-> System.out.println(x));

        Thread.sleep(5000000);
        javaSparkContext.close();


    }
}
