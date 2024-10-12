package com.event.driven.actor

import akka.actor.{Actor, ActorLogging, ActorRef}
import com.event.driven.messages.PlaceOrder

class CustomerActor(orderProcessor: ActorRef) extends Actor with ActorLogging{

  override def receive: Receive = {
    case order: PlaceOrder =>
      log.info(s"Placing order: ${order.orderId} for item: ${order.item}")
      orderProcessor ! order
  }
}
