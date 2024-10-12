package com.event.driven.actor

import akka.actor.{Actor, ActorLogging}
import com.event.driven.messages.{OrderShipped, PaymentProcessed}

class ShippingProcessorActor extends Actor with ActorLogging {
  def receive: Receive = {
    case PaymentProcessed(orderId) =>
      log.info(s"Shipping order: $orderId")
      sender() ! OrderShipped(orderId)
      log.info(s"Order $orderId has been shipped.")
  }
}
