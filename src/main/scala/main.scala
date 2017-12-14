import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import scala.io._
object threadDemo {

  val pattern = """\d{2}-\d{2}""".r
  val patternSpa = """\s+""".r

  def main(args: Array[String]): Unit = {


    val file = Source.fromFile("H:\\IdeaProjects\\thread_demo\\detail-productdb-service.2017-11-29.log")
    val list = file.getLines.toList.filter(x => x.contains("trans-pool-1-thread-") || x.contains("qbScheduler-"))//.filter(_.contains("com.")).filter(!_.contains("header")).map(_.replaceAll("\\(|\\)|\\:|\\[|\\]", ""))
//    list(0).split(" ")(2)::list(0).split(" ").toList.filter(_.contains("com.")).filter(!_.contains("header")).map(_.replaceAll("\\(|\\)|\\:|\\[|\\]", ""))
//    println(newlist)
//    val bufferList = new ListBuffer[List[String]]
//    for (i <- 0 to list.length-1){
//       val a = list(i).split(" ")(2)::list(i).split(" ").toList.filter(_.contains("com.")).filter(!_.contains("header")).map(_.replaceAll("\\(|\\)|\\:|\\[|\\]", ""))
//      println(newlist)
//      if(a.length==2) {
//
//      }
//
//    }
    val buffs = new ListBuffer[(Int,String)]
//    val ls = Nil
    for (l <- list){
      val arr = patternSpa.split(l)

      val str = arr.filter(_.contains("com.")).filter(!_.contains("header")).map(_.replaceAll("\\(|\\)|\\:|\\[|\\]", "")).map(_.split("\\@")(0))
      if (str.length > 0){
        buffs.append((arr(2).toInt,str(0)))
      }
//      ls :+ arr(2)(0)
    }
//    println(ls)
    val count = buffs.groupBy(_._2)

      for (i <- count) {
      //        val num = i._2*1.0  //调用次数
      println("服务名：" + i._1 + "            " + "调用次数：" + i._2.size + "            " +"总时长："+BigDecimal(i._2.map(_._1).sum)+"ms" +"        "+"平均时长:"+BigDecimal(i._2.map(_._1).sum)./(i._2.size).setScale(2,BigDecimal.RoundingMode.HALF_UP))
    }




//    val map = list.map(_.split(" ")) //.map(_.contains("com."))
//      for (i <- 0 to map.length-1){
//        println(map(i)(2))
//      }
//    for (i <- 0 to map.length-1){
//      for (j <- 0 to map(i).length-1){
//        val str = map(i)(j)
//        arrBuffer += str
//      }
//    }
//    for (i <- list){
//      println(i)
//    }
  }



//    val lines = Source.fromFile("H:\\IdeaProjects\\thread_demo\\detail-productdb-service.2017-11-29.log").getLines()
//    val aryBuffer = new ArrayBuffer[String]
//    while (lines.hasNext) {
//      val line: String = lines.next
//      val flag = pattern.findFirstIn(line) match {
//        case None => false
//        case _ => true
//      }
//      if (flag) {
//        val ary = patternSpa.split(line)
//        val str = ary.filter(_.contains("com.")).filter(!_.contains("header")).map(_.replaceAll("\\(|\\)|\\:|\\[|\\]", "")).map(_.split("\\@")(0))
//        //          .map(_.split("\\@")(0))
//        aryBuffer ++= str
//      }
//    }
//    val count = aryBuffer.map((_, 66)).groupBy(_._1).map(n => (n._1, n._2.size))
//
//    //    println(count.keys)
//    //    println(count.values)
//    for (i <- count) {
//      println("服务名：" + i._1 + "            " + "调用次数：" + i._2)
//    }

}