package com.event.driven.events

sealed trait OrderEvent
case class OrderPlaced(orderId: String, item: String, price: Double) extends OrderEvent
case class OrderConfirmed(orderId: String) extends OrderEvent
case class PaymentCompleted(orderId: String) extends OrderEvent
case class ItemShipped(orderId: String) extends OrderEvent
