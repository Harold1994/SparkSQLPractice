import org.apache.spark.sql.SparkSession

object dataSetApp {
  case class people(name:String, age:Int, job:String)
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[2]").appName("dataSetApp").getOrCreate()
    val table = spark.read.option("header","true").option("inferSchema","true").csv("/Users/harold/Documents/Code/SparkSQLPractice/src/main/resources/people.csv")
    import spark.implicits._
    table.show()
    val ds = table.as[people]
    ds.map(line => line.job).show()
    spark.stop()
  }
}
