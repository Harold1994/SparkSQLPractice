import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}

object HiveContextApp {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName("HiveContextApp")
    val sc = new SparkContext(conf)
    val sqlContext = new HiveContext(sc)
    sqlContext.table("people").show()
    sc.stop()
  }
}
