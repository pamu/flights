package com.freeflyair.client.components

import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.vdom.all._
import japgolly.scalajs.react.component.Scala.BackendScope

object LoginFormComponent {
  case class Props()

  case class Form(username: String, password: String, rememberMe: Boolean = false)

  class Backend($: BackendScope[Props, Form]) {

    def onSubmit(): Callback = {
      ???
    }

    def render() = div(
      label("Username"),
      input(`type` := "text"),
      label("Password"),
      input(`type` := "password"),
      input(`type` := "submit", onClick --> onSubmit())
    )
  }


}
