import org.apache.spark.sql.SparkSession

object DataFrameApp {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("DataFrameApp").master("local[2]").getOrCreate()
    val tbl = spark.read.json("/Users/harold/Documents/Code/SparkSQLPractice/src/main/resources/people.json")
    tbl.printSchema()
    tbl.show()
    tbl.select("name").show()
    tbl.filter(tbl.col("age")>19).show()
    tbl.groupBy("age").count().show()
    spark.stop()
  }
}
