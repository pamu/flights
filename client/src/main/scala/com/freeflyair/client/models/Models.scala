package com.freeflyair.client.models

case class LoginFormModel(username: String, password: String)

case class ClientState(loginFormModel: LoginFormModel = LoginFormModel("", ""))
