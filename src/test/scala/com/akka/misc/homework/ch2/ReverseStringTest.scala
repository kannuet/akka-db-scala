package com.akka.misc.homework.ch2

import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

class ReverseStringTest extends FunSpecLike with Matchers {
  val system = ActorSystem()
  implicit val timeout = Timeout(5 seconds)
  val rStrActor = system.actorOf(Props(classOf[ReverseStringActor]))
  describe("Reverse String"){
    it("should reverse an input string."){
      val future: Future[Any] = rStrActor ? "ok"
      val result = Await.result(future.mapTo[String], 1 second)
      assert(result == "ko")
    }
    it("should fail on unknown message") {
      val future = rStrActor ?  123
      val caught = intercept[Exception] {
        Await.result(future.mapTo[String], 1 second)
      }
      assert(caught.getMessage == "unknown message")
    }
  }

}
