package com.harana.web.modules

import com.harana.id.jwt.{Layers => JWTLayers}
import com.harana.modules.core.{Layers => CoreLayers}
import com.harana.web.modules.crud.LiveCrud

object Layers {
  lazy val crud = CoreLayers.standard ++ CoreLayers.mongo ++ JWTLayers.jwt >>> LiveCrud.layer
}