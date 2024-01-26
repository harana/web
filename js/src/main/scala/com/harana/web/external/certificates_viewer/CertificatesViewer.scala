package com.harana.web.external.certificates_viewer

import slinky.core.ExternalComponent
import slinky.core.annotations.react
import slinky.core.facade.ReactElement

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("@peculiar/certificates-viewer-react", "PeculiarCertificateViewer")
@js.native
object ReactCertificatesViewer extends js.Object

@react object CertificatesViewer extends ExternalComponent {

  case class Props(certificate: String)

  override val component = ReactCertificatesViewer
}