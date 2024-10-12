package com.event.driven.actor

import akka.actor.{Actor, ActorLogging, ActorRef}
import com.event.driven.messages.{OrderValidated, PaymentProcessed}

class PaymentProcessorActor(shippingProcessor: ActorRef) extends Actor with ActorLogging {
  def receive: Receive = {
    case OrderValidated(orderId) =>
      log.info(s"Processing payment for order: $orderId")
      // Assuming payment is successful, send the message to ShippingProcessor
      sender() ! PaymentProcessed(orderId)
      shippingProcessor ! PaymentProcessed(orderId)  // Trigger shipping
  }
}
