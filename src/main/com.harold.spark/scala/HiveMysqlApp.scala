import org.apache.spark.sql.SparkSession

object HiveMysqlApp {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[2]").appName("HiveMysqlApp").config("spark.sql.warehouse.dir","hdfs:///user/hive/warehouse/").getOrCreate()
    spark.conf.get("spark.sql.warehouse.dir")
    val jdbc = spark.read.format("jdbc").option("url","jdbc:mysql://localhost:3306/sparktest").option("dbtable","sparktest.dept").option("user","hive@localhost").option("password","Hive_123").option("driver","com.mysql.cj.jdbc.Driver").load()

    val hive = spark.table("clients")
    val res = hive.join(jdbc, hive.col("departno")===jdbc.col("deptno"))
    res.show()
    res.select(hive.col("name"), jdbc.col("dname")).show

spark.stop()
  }
}
