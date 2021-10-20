package CodeWithCore;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class FirstSparkCode implements Serializable {
    public static void main(String[] args) throws InterruptedException {

        SparkConf conf = new SparkConf().setAppName("First application").setMaster("local");
        JavaSparkContext javaSparkContext = new JavaSparkContext(conf);
        javaSparkContext.setLogLevel("ERROR");

        /*List<Integer> l1 = Arrays.asList(23, 7, 8,-11,43);
        JavaRDD<Integer> parallelize = javaSparkContext.parallelize(l1);
        parallelize.foreach(System.out::println);*/

        JavaRDD<String> stringJavaRDD = javaSparkContext.textFile("file:///home/labuser/myFile");
       /* stringJavaRDD.foreach(x-> System.out.println(x));*/
        System.out.println(stringJavaRDD.first());
        System.out.println(stringJavaRDD.count());

        JavaRDD<String> javaRDD = stringJavaRDD.flatMap(x -> Arrays.asList(x.split(" ")).iterator());
        javaRDD.foreach(x->System.out.println(x));
        System.out.println("No of words: " + javaRDD.count());

        javaSparkContext.stop();



    }
}

class Employee
{
    int empId;
    String fname,lname;

    public Employee(int empId, String fname, String lname) {
        this.empId = empId;
        this.fname = fname;
        this.lname = lname;
    }
}
