package com.akka

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import com.akka.messages.SetRequest
import org.scalatest.{BeforeAndAfterEach, FunSpecLike, Matchers}

class AkkaDbTest extends FunSpecLike with Matchers with BeforeAndAfterEach {
  implicit val system = ActorSystem()
  describe("akkaDb") {
    describe("given SetRequest") {
      it("should place key/value into map") {
        val actorRef = TestActorRef(new AkkaDb)
        actorRef ! SetRequest("key", "value")
        val akkaDb = actorRef.underlyingActor
        akkaDb.map.get("key") should equal(Some("value"))
      }
    }
    describe("unknown message") {
      it("should log message"){
        val actorRef = TestActorRef(new AkkaDb)
        actorRef ! new AkkaDb
        val akkaDb = actorRef.underlyingActor
        akkaDb.map.get("key") should equal(Some("value"))

      }
    }
  }
}
