package com.event.driven.messages

case class PlaceOrder(orderId: String, item: String, price: Double)
