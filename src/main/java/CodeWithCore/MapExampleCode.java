package CodeWithCore;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.List;

public class MapExampleCode {

    public static void main(String[] args) throws InterruptedException {
        SparkConf conf = new SparkConf().setAppName("Map application").setMaster("spark://ip-172-31-16-95.ap-south-1.compute.internal:7077");
        JavaSparkContext javaSparkContext = new JavaSparkContext(conf);
        javaSparkContext.setLogLevel("ERROR");

        List<Integer> l1 = Arrays.asList(23, 7, 8,11,43);
        JavaRDD<Integer> parallelize = javaSparkContext.parallelize(l1);
        JavaRDD<Integer> integerJavaRDD = parallelize.map(x -> x + 100);
        /*integerJavaRDD.foreach(x-> System.out.println(x));*/

        List<Integer> l2 = Arrays.asList(23, 8,98,21,56,55);
        JavaRDD<Integer> rdd = javaSparkContext.parallelize(l1);
        parallelize.union(rdd).foreach(x-> System.out.println(x));

        System.out.println("-------- Intersection --------");
        parallelize.intersection(rdd).foreach(x-> System.out.println(x));

        System.out.println("------- Subtract --------");
        parallelize.subtract(rdd).foreach(x-> System.out.println(x));

        System.out.println("------Reduce------");
        System.out.println("Sum : " + parallelize.reduce((x,y)->x+y));
        Thread.sleep(500000);
        javaSparkContext.stop();
    }
}
