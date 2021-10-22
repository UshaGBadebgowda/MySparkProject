package sqlcode;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class FirstSqlCode {

    public static void main(String[] args) {
        SparkSession session = SparkSession.builder().appName("Sql Code - First")
                .master("local").config("spark.ui.port", 4045).getOrCreate();

        session.sparkContext().setLogLevel("ERROR");

        /*Dataset<Row> dataset = session.read().format("json").load("file:///home/labuser/data");*/
        Dataset<Row> dataset = session.read().format("parquet").load("file:///home/labuser/data/userdata1.parquet");
        dataset.printSchema();
        dataset.show();
        session.close();

    }
}
