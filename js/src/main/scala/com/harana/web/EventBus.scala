package com.harana.web

import typings.std
import typings.vertx3EventbusClient.mod.{EventBus, ^ => VertxEventBus}

import java.util.Base64
import scala.scalajs.js
import scala.scalajs.js.Dynamic.global
import scala.scalajs.js.timers._

object EventBus {

  type Address = String
  var eventBus: EventBus = _
  var ready: Boolean = false

  def sendMessage(userId: String, messageType: String, message: String): Unit =
    if (ready) {
      //println(s"Sending message: $message / $messageType on the EventBus")
      val body = new String(Base64.getEncoder.encode(message.getBytes("UTF-8")))
      eventBus.publish(userId, body, new EventBusHeaders { val `type` = messageType })
    } else
      setTimeout(100) { sendMessage(userId, messageType, message) }

  def subscribe(userId: String, messageType: String, fn: String => Unit): Unit =
    if (ready) {
      //println(s"Subscribing to $messageType on the EventBus")
      eventBus.registerHandler(userId, new EventBusHeaders { val `type` = messageType }, (error: std.Error, result: js.Any) => {
        val message = result.asInstanceOf[EventBusMessage]
        if (message.headers.`type`.equals(messageType)) {
          fn(new String(Base64.getDecoder.decode(message.body), "UTF-8"))
        }
      })
    } else
      setTimeout(100) { subscribe(userId, messageType, fn) }

  def unsubscribe(address: Address): Unit =
    if (ready)
      eventBus.unregisterHandler(address)
    else
      setTimeout(100) { unsubscribe(address) }

  def init(): Unit = {
    eventBus = new VertxEventBus("/eventbus") {
      override def onopen() = {
        ready = true
      }

      override def onclose() = {
        ready = false
        setTimeout(500)(init())
      }

      override def onerror(error: std.Error) = {
        global.console.dir(error)
        ready = false
        setTimeout(2000)(init())
      }
    }
  }
}

trait EventBusMessage extends js.Object {
  val address: String
  val body: String
  val headers: EventBusHeaders
}

trait EventBusHeaders extends js.Object {
  val `type`: String
}