package com.akka.db

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import com.akka.db.messages.SetRequest
import org.scalatest.{BeforeAndAfterEach, FunSpecLike, Matchers}

class AkkaDbTest extends FunSpecLike with Matchers with BeforeAndAfterEach {
  implicit val system = ActorSystem()
  describe("akkaDb") {
    val actorRef = TestActorRef(new AkkaDb)
    describe("given SetRequest") {
      it("should place key/value into map") {
        actorRef ! SetRequest("key", "value")
        val akkaDb = actorRef.underlyingActor
        akkaDb.map.get("key") should equal(Some("value"))
      }
    }
  }
}
