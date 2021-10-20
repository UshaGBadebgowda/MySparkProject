package CodeWithCore;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

public class WorkWithPairRDD {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("First application").setMaster("local");
        JavaSparkContext javaSparkContext = new JavaSparkContext(conf);
        javaSparkContext.setLogLevel("ERROR");

        List<Integer> l1 = Arrays.asList(23, 7, 8,-11,43);
        JavaRDD<Integer> parallelize = javaSparkContext.parallelize(l1);

        List<String> l2 = Arrays.asList("Ram","Sita","Laxman","Barath","Shatrugna");
        JavaRDD<String> parallelize1 = javaSparkContext.parallelize(l2);

        JavaPairRDD<Integer, Integer> integerJavaPairRDD = parallelize.mapToPair(x -> new Tuple2<>(x, 1));
        integerJavaPairRDD.foreach(x-> System.out.println(x));
        javaSparkContext.close();
    }
}
