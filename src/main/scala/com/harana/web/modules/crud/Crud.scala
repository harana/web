package com.harana.web.modules.crud

import com.harana.modules.vertx.models.Response
import com.harana.sdk.shared.models.common.Entity
import com.harana.sdk.shared.models.common.User.UserId
import io.circe.{Decoder, Encoder}
import io.vertx.ext.web.RoutingContext
import org.mongodb.scala.bson.BsonDocument
import zio.macros.accessible
import zio.Task

import scala.reflect.ClassTag
import scala.reflect.runtime.universe._

@accessible
trait Crud {

    def userId(rc: RoutingContext): Task[Option[String]]

    def owners[E <: Entity](collection: String, rc: RoutingContext)(implicit ct: ClassTag[E], tt: TypeTag[E], d: Decoder[E], e: Encoder[E]): Task[Map[String, Int]]

    def ownersResponse[E <: Entity](collection: String, rc: RoutingContext)(implicit ct: ClassTag[E], tt: TypeTag[E], d: Decoder[E], e: Encoder[E]): Task[Response]

    def tags[E <: Entity](collection: String, rc: RoutingContext)(implicit ct: ClassTag[E], tt: TypeTag[E], d: Decoder[E], e: Encoder[E]): Task[Map[String, Int]]

    def tagsResponse[E <: Entity](collection: String, rc: RoutingContext)(implicit ct: ClassTag[E], tt: TypeTag[E], d: Decoder[E], e: Encoder[E]): Task[Response]

    def list[E <: Entity](collection: String, rc: RoutingContext)(implicit ct: ClassTag[E], tt: TypeTag[E], d: Decoder[E], e: Encoder[E]): Task[List[E]]

    def listResponse[E <: Entity](collection: String, rc: RoutingContext)(implicit ct: ClassTag[E], tt: TypeTag[E], d: Decoder[E], e: Encoder[E]): Task[Response]

    def search[E <: Entity](collection: String, rc: RoutingContext)(implicit ct: ClassTag[E], tt: TypeTag[E], d: Decoder[E], e: Encoder[E]): Task[List[E]]

    def searchResponse[E <: Entity](collection: String, rc: RoutingContext)(implicit ct: ClassTag[E], tt: TypeTag[E], d: Decoder[E], e: Encoder[E]): Task[Response]

    def get[E <: Entity](collection: String, rc: RoutingContext)(implicit ct: ClassTag[E], tt: TypeTag[E], d: Decoder[E], e: Encoder[E]): Task[Option[E]]

    def getResponse[E <: Entity](collection: String, rc: RoutingContext)(implicit ct: ClassTag[E], tt: TypeTag[E], d: Decoder[E], e: Encoder[E]): Task[Response]

    def delete[E <: Entity](collection: String, rc: RoutingContext)(implicit ct: ClassTag[E], tt: TypeTag[E], d: Decoder[E]): Task[Unit]

    def deleteResponse[E <: Entity](collection: String, rc: RoutingContext)(implicit ct: ClassTag[E], tt: TypeTag[E], d: Decoder[E]): Task[Response]

    def create[E <: Entity](collection: String, rc: RoutingContext)(implicit ct: ClassTag[E], tt: TypeTag[E], d: Decoder[E], e: Encoder[E]): Task[E]

    def createResponse[E <: Entity](collection: String, rc: RoutingContext)(implicit ct: ClassTag[E], tt: TypeTag[E], d: Decoder[E], e: Encoder[E]): Task[Response]

    def update[E <: Entity](collection: String, rc: RoutingContext)(implicit ct: ClassTag[E], tt: TypeTag[E], d: Decoder[E], e: Encoder[E]): Task[E]

    def updateResponse[E <: Entity](collection: String, rc: RoutingContext)(implicit ct: ClassTag[E], tt: TypeTag[E], d: Decoder[E], e: Encoder[E]): Task[Response]

    def creatorOrPublic(userId: Option[UserId]): List[BsonDocument]

}
