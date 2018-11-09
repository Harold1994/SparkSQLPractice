import org.apache.spark.sql.SparkSession

object ParquetApp {
  def main(args: Array[String]): Unit = {
    val builder = SparkSession.builder().master("local[2]").appName("ParquetApp").getOrCreate()
    val table = builder.read.format("parquet").load("/Users/harold/Documents/Code/spark-2.3.0/examples/src/main/resources/users.parquet")
    table.printSchema()
    table.show()
   table.select("name","favorite_color").show
    table.select("name","favorite_color").write.format("json").save("file:///Users/harold/Documents/hello.js")

  }
}
