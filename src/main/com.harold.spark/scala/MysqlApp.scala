import java.util.Properties

import org.apache.spark.sql.SparkSession

object MysqlApp {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[2]").appName("MysqlApp").getOrCreate()
    val jdbc = spark.read.format("jdbc")
      .option("url","jdbc:mysql://localhost:3306/sparksql")
      .option("dbtable","sparksql.TBLS")
      .option("user","hive@localhost")
      .option("password","Hive_123")
      .option("driver","com.mysql.cj.jdbc.Driver")
      .load()

    jdbc.printSchema()
    jdbc.show()

    val connectionProperties = new Properties()
    connectionProperties.put("user","hive@localhost")
    connectionProperties.put("password","Hive_123")
    connectionProperties.put("driver", "com.mysql.cj.jdbc.Driver")
    val jdbc2 = spark.read.jdbc("jdbc:mysql://localhost:3306/sparksql","sparksql.TBLS", connectionProperties)
    jdbc2.show()

    spark.stop()
  }
}
