package com.akka.db

import akka.actor.{Actor, ActorSystem, Props, Status}
import akka.event.Logging
import com.akka.db.messages.{GetRequest, KeyNotFoundException, SetRequest}

import scala.collection.mutable.HashMap

class AkkaDb extends Actor {
  val map = new HashMap[String, Object]
  val log = Logging(context.system, this)

  override def receive: PartialFunction[Any, Unit] = {
    case SetRequest(key, value) =>
      log.info("received SetRequest - key: {} value: {}", key, value)
      map.put(key, value)
      sender() ! Status.Success
    case GetRequest(key) =>
      log.info("received GetRequest - key: {}", key)
      val response: Option[Object] = map.get(key)
      response match {
        case Some(x) => sender() ! x
        case None => sender() ! Status.Failure(new KeyNotFoundException(key))
      }
      case o => Status.Failure(new ClassNotFoundException)
    }
  }

object Main extends App {
  val system = ActorSystem("akkademy")
  val helloActor = system.actorOf(Props[AkkaDb], name = "akkademy-db")
}