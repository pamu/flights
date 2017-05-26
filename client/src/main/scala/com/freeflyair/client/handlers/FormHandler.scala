package com.freeflyair.client.handlers

import com.freeflyair.client.actions.LoginAction
import com.freeflyair.client.models.LoginFormModel
import diode.{ActionHandler, ActionResult, ModelRW}

class FormHandler[M](modelRW: ModelRW[M, LoginFormModel]) extends ActionHandler(modelRW) {
  override protected def handle: PartialFunction[Any, ActionResult[M]] = {
    case LoginAction(username, password) =>
      updated(value.copy(username = username).copy(password = password))
  }
}
