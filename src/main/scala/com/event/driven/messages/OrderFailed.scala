package com.event.driven.messages

// Failure messages
case class OrderFailed(orderId: String, reason: String)
