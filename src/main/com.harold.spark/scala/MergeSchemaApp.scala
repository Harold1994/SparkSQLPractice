import org.apache.spark.sql.SparkSession

object MergeSchemaApp {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("MergeSchemaApp").master("local[2]").getOrCreate()
    import spark.implicits._
    val square = spark.sparkContext.makeRDD(1 to 5).map(i => (i,i*i)).toDF("value","square")
    square.write.parquet("out/test/key=1")
    val cube = spark.sparkContext.makeRDD(1 to 5).map(i => (i,i*i*i)).toDF("value","cube")
    cube.write.parquet("out/test/key=2")
     val mergedDf = spark.read.option("mergeSchema","true").parquet("out/test")
    mergedDf.printSchema()
    mergedDf.show()
    spark.stop()
  }
}
