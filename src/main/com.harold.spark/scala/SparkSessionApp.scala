import org.apache.spark.sql.SparkSession

object SparkSessionApp {
  def main(args: Array[String]): Unit = {
    val builder = SparkSession.builder().master("local[2]").appName("SparkSessionApp").getOrCreate()
    val table = builder.read.json("/Users/harold/Documents/Code/SparkSQLPractice/src/main/resources/people.json")
    table.show()
    builder.stop()
  }
}
