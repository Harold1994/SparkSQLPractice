import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

object SQLContextApp {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setAppName("SQLContextApp").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    val people = sqlContext.read.format("json").load("/Users/harold/Documents/Code/SparkSQLPractice/src/main/resources/people.json")
    people.printSchema()
    people.show()
    sc.stop()
  }
}