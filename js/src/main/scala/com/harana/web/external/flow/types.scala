package com.harana.web.external.flow

import com.harana.web.external.flow.types._

import scala.scalajs.js
import scala.scalajs.js.|

object types {
  type FlowElementId = String
  type HandleType = String
  type Position = String
}

trait Box extends js.Object {
  val x: Int
  val x2: Int
  val y: Int
  val y2: Int
}

trait Dimensions extends js.Object {
  val width: Int
  val height: Int
}

trait Rect extends Dimensions with XYPosition

trait XYPosition extends js.Object {
  val x: Int
  val y: Int
}

trait SelectionRect extends Rect {
  val startX: Int
  val startY: Int
  val draw: Boolean
}

trait Connection extends Rect {
  val source: FlowElementId
  val target: FlowElementId
  val sourceHandle: String
  val targetHandle: String
}

trait SetConnectionId extends Rect {
  val connectionNodeId: FlowElementId
  val connectionHandleType: HandleType
}

trait HandleElement extends XYPosition with Dimensions {
  val id: js.UndefOr[FlowElementId] = js.undefined
  val position: String
}

trait NodePosUpdate extends js.Object {
  val id: FlowElementId
  val pos: XYPosition
}

trait NodeDiffUpdate extends js.Object {
  val id: FlowElementId
  val diff: js.UndefOr[XYPosition] = js.undefined
  val isDragging: js.UndefOr[Boolean] = js.undefined
}

trait FlowTransform extends js.Object {
  val x: Int
  val y: Int
  val zoom: Int
}

trait FitViewParams extends js.Object {
  val padding: Int
}

trait FlowInstance[T <: js.Object] extends js.Object {
  def zoomIn(): Unit
  def zoomOut(): Unit
  def zoomTo(number: Int): Unit
  def fitView(params: js.UndefOr[FitViewParams] = js.undefined): Unit
  def project(position: XYPosition): XYPosition
  def getElements: List[ReactFlowNode[T] | ReactFlowEdge]
  def setTransform(transform: FlowTransform): Unit
}

trait OnConnectStartParams extends js.Object {
  val nodeId: js.UndefOr[FlowElementId] = js.undefined
  val handleType: js.UndefOr[HandleType] = js.undefined
}

trait ReactFlowEdge extends js.Object {
  val id: FlowElementId
  val source: FlowElementId
  val target: FlowElementId
  val sourceHandle: FlowElementId
  val targetHandle: FlowElementId
  val `type`: js.UndefOr[String] = js.undefined
  val label: js.UndefOr[String] = js.undefined
  val labelStyle: js.UndefOr[js.Dynamic] = js.undefined
  val labelShowBg: js.UndefOr[Boolean] = js.undefined
  val labelBgStyle: js.UndefOr[js.Dynamic] = js.undefined
  val labelBgPadding: js.UndefOr[(Int, Int)] = js.undefined
  val labelBgBorderRadius: js.UndefOr[Int] = js.undefined
  val arrowHeadType: js.UndefOr[String] = js.undefined
  val style: js.UndefOr[js.Dynamic] = js.undefined
  val animated: js.UndefOr[Boolean] = js.undefined
  val hidden: js.UndefOr[Boolean] = js.undefined
  val deletable: js.UndefOr[Boolean] = js.undefined
  val focusable: js.UndefOr[Boolean] = js.undefined
  val data: js.UndefOr[js.Object] = js.undefined
  val className: js.UndefOr[String] = js.undefined
  val selected: js.UndefOr[Boolean] = js.undefined
}

trait ReactFlowNode[T <: js.Object] extends js.Object {
  val id: FlowElementId
  val position: XYPosition
  val data: T
  val `type`: String
  val __rf: js.UndefOr[js.Any] = js.undefined
  val style: js.UndefOr[js.Dynamic] = js.undefined
  val className: js.UndefOr[String] = js.undefined
  val targetPosition: js.UndefOr[Position] = js.undefined
  val sourcePosition: js.UndefOr[Position] = js.undefined
  val hidden: js.UndefOr[Boolean] = js.undefined
  val selected: js.UndefOr[Boolean] = js.undefined
  val dragging: js.UndefOr[Boolean] = js.undefined
  val draggable: js.UndefOr[Boolean] = js.undefined
  val selectable: js.UndefOr[Boolean] = js.undefined
  val connectable: js.UndefOr[Boolean] = js.undefined
  val deletable: js.UndefOr[Boolean] = js.undefined
  val focusable: js.UndefOr[Boolean] = js.undefined
  val dragHandle: js.UndefOr[String] = js.undefined
  val width: js.UndefOr[Int] = js.undefined
  val height: js.UndefOr[Int] = js.undefined
  val parentNode: js.UndefOr[String] = js.undefined
  val zIndex: js.UndefOr[Int] = js.undefined
  val expandParent: js.UndefOr[Boolean] = js.undefined
  val positionAbsolute: js.UndefOr[Position] = js.undefined
  val ariaLabel: js.UndefOr[String] = js.undefined
}