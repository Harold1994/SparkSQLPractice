import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SparkSession}

object RDDToDataFrame {

  case class Info(id: Int, Name: String, age: Int)



  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("RDDToDataFrame").master("local[2]").getOrCreate()
    //inferReflection(spark)
    program(spark)
    spark.stop()
  }

  private def program(spark: SparkSession) = {
    val rdd = spark.sparkContext.textFile("/Users/harold/Documents/Code/SparkSQLPractice/src/main/resources/infos")
    val df = rdd.map(x => x.split(",")).map(line => Row(line(0).toInt, line(1), line(2).toInt))
    val struct = StructType(Array(StructField("id",IntegerType,true), StructField("name", StringType,true), StructField("age",IntegerType,true)))
    spark.createDataFrame(df, struct).show()
  }

  private def inferReflection(spark: SparkSession) = {
    val rdd = spark.sparkContext.textFile("/Users/harold/Documents/Code/SparkSQLPractice/src/main/resources/infos")
    import spark.implicits._
    val df = rdd.map(x => x.split(",")).map(line => Info(line(0).toInt, line(1), line(2).toInt)).toDF()
    df.createTempView("infos")
    spark.sql("select * from infos where age > 30").show()
  }
}
