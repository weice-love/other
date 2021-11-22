package com.junit_demo.app.java8.scala

import scala.io.Source

object Hello {


  def main(args: Array[String]) {
    collectUse()
    fortest()
    bibao()
    shujujiegou()
    filterTest

    testLong
    println(multiplyCurry(2)(10))
    val intToInt : Int => Int = multiplyCurry(10)

    println("测试： " + intToInt(20))

    class Student(var name: String, var id: Int)
    val s = new Student("Raoul", 1)
    println(s.name)
    s.id = 1337
    println(s.id)

  }

  /*
  科里化
   */
  def multiplyCurry(x :Int)(y : Int) = x * y

  private def testLong = {
    val isLongTweet: String => Boolean
    = new Function[String, Boolean] {
      def apply(tweet: String): Boolean = tweet.length() > 60
    }
    isLongTweet.apply("A very short tweet")
    isLongTweet("A very short tweet")
  }

  def fortest() {
    2 to 6 foreach { n => println(s"Hello ${n} bottles of beer") }
  }


  def collectUse(): Unit = {
    val fileLines = Source.fromFile("D://data.txt").getLines
//    println(fileLines.filter(l => l.length() > 3)
//      .map(l => l.toUpperCase()).foreach(println))

//    println(fileLines filter (_.length() > 10) map(_.toUpperCase()).foreach(println))

  }

  // 一等函数
  def isJavaMentioned(tweet: String) : Boolean = tweet.contains("Java")
  def isShortTweet(tweet: String) : Boolean = tweet.length() < 20

  def filterTest(): Unit = {
    val  tweets = List(
    "I love the new features in Java 8",
    "How's it going?",
    "An SQL query walks into a bar, sees two tables and says 'Can I join you?'"
    )
    tweets.filter(isJavaMentioned).foreach(println)
    tweets.filter(isShortTweet).foreach(println)
  }

//  def getCarInsuranceName(person: Option[Person], minAge: Int) =
//    person.filter(_.getAge() >= minAge)
//      .flatMap(_.getCar)
//      .flatMap(_.getInsurance)
//      .map(_.getName).getOrElse("Unknown")

  def shujujiegou(): Unit = {
    // 元组
    val raoul = ("Raoul", "+ 44 887007007")
    val alan = ("Alan", "+44 883133700")
    //
    val authorsToAge = Map("Raoul" -> 23, "Mario" -> 40, "Alan" -> 53)
    val authors = List("Raoul", "Mario", "Alan")
    val numbers = Set(1, 1, 2, 3, 5, 8)
    val newNumbers = numbers + 8
    println(newNumbers)
    println(numbers)
  }

  def bibao(): Unit ={
    var count = 0
    val inc = () => count+=1
    inc()
    println(count)
    inc()
    println(count)
  }
}