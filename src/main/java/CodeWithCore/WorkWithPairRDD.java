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

        List<Integer> l1 = Arrays.asList(4, 7, 6,-11,9);
        JavaRDD<Integer> parallelize = javaSparkContext.parallelize(l1);

        List<String> l2 = Arrays.asList("Rama","Sita","Laxman","Barath","Shatrugna");
        JavaRDD<String> parallelize1 = javaSparkContext.parallelize(l2);

        JavaPairRDD<Integer, Integer> integerJavaPairRDD = parallelize.mapToPair(x -> new Tuple2<>(x, 1));
        integerJavaPairRDD.foreach(x-> System.out.println(x));

        JavaPairRDD<Integer, String> stringIntegerJavaPairRDD = parallelize1.mapToPair(x -> new Tuple2<>(x.length(),x));
        stringIntegerJavaPairRDD.foreach(x-> System.out.println(x));

        System.out.println("------------------ Zip-------------------");
        JavaPairRDD<Integer,String> pair44 = parallelize.zip(parallelize1);
        pair44.foreach(x->System.out.println(x));

        System.out.println("----Keys-----");
        pair44.keys().foreach(x-> System.out.println(x));

        System.out.println("----Values-----");
        pair44.values().foreach(x-> System.out.println(x));

        System.out.println("----SortBy-----");
        pair44.sortByKey().foreach(x-> System.out.println(x));

        System.out.println("----GroupBy-----");
        stringIntegerJavaPairRDD.groupByKey().foreach(x-> System.out.println(x));

        System.out.println("----Join-----");
        stringIntegerJavaPairRDD.join(pair44).foreach(x-> System.out.println(x));


        javaSparkContext.close();

    }
}
