package com.event.driven.actor

import akka.actor.{Actor, ActorLogging, ActorRef}
import com.event.driven.messages.{OrderFailed, OrderValidated, PlaceOrder}

class OrderProcessorActor(paymentProcessor: ActorRef) extends Actor with ActorLogging {
  def receive: Receive = {
    case PlaceOrder(orderId, item, price) =>
      log.info(s"Validating order: $orderId")
      if (price > 0) {
        log.info(s"Order $orderId validated.")
        sender() ! OrderValidated(orderId)
        paymentProcessor ! OrderValidated(orderId)  // Trigger payment processing
      } else {
        log.warning(s"Order $orderId failed: Invalid price.")
        sender() ! OrderFailed(orderId, "Invalid price.")
      }
  }
}
