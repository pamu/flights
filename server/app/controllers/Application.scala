package controllers

import com.google.inject.{Inject, Singleton}
import play.api.mvc._

@Singleton
class Application @Inject() () extends Controller {

  def index = Action {
    Ok(views.html.index("Freeflyair"))
  }

}
