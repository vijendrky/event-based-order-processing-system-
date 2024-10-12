package com.event.driven

import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}
import com.event.driven.actor.{CustomerActor, OrderProcessorActor, PaymentProcessorActor, ShippingProcessorActor}
import com.event.driven.messages.{OrderFailed, OrderShipped, OrderValidated, PaymentProcessed, PlaceOrder}

object EventDrivenOrderProcessingApp extends App {
  // Create the ActorSystem
  val system = ActorSystem("OrderProcessingSystem")

  // Create actors
  val shippingProcessor = system.actorOf(Props[ShippingProcessorActor], "shippingProcessor")
  val paymentProcessor = system.actorOf(Props(new PaymentProcessorActor(shippingProcessor)), "paymentProcessor")
  val orderProcessor = system.actorOf(Props(new OrderProcessorActor(paymentProcessor)), "orderProcessor")
  val customer = system.actorOf(Props(new CustomerActor(orderProcessor)), "customer")

  // Place an order (Customer initiates the process)
  customer ! PlaceOrder("order-123", "Laptop", 35000.0)

  // Shutdown the system after a short delay
  Thread.sleep(2000)
  system.terminate()
}




