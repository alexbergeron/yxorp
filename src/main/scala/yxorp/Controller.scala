package xyorp

// Arbitrary controller - one of my choices would provably look similar
class Controller(manager: RoutesManagerHolder) {
  type Request = String
  type Response = Nothing

  def routes: PartialFunction[Request, Response] = {
    case s"/${path}" =>
      manager.value.serviceFor(Path(path)) match {
        case Right(s) => delegateTo(s)
        case Left(msg) => error(msg)
      }
  }

  def delegateTo(s: Service) = ???
  def error(s: String) = ???
}
