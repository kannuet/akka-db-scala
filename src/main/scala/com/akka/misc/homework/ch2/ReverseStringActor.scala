package com.akka.misc.homework.ch2

import akka.actor.{Actor, Status}

class ReverseStringActor extends Actor{
  override def receive: Receive = {
    case str:String => sender() ! str.reverse
    case _ => sender() ! Status.Failure(new Exception("unknown message"))
  }

}
