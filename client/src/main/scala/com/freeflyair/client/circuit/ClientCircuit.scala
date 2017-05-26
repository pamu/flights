package com.freeflyair.client.circuit

import com.freeflyair.client.handlers.FormHandler
import com.freeflyair.client.models.ClientState
import diode.Circuit
import diode.react.ReactConnector

class ClientCircuit extends Circuit[ClientState] with ReactConnector[ClientState] {
  override protected def initialModel: ClientState = ClientState()
  override protected def actionHandler: HandlerFunction = composeHandlers(
    new FormHandler[ClientState](zoomTo(_.loginFormModel))
  )
}
