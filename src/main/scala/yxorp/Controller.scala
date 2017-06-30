package xyorp

// Arbitrary controller - one of my choices would provably look similar
class Controller(manager: RoutesManagerHolder) {
  type Request = String
  type Response = Nothing

  def routes: PartialFunction[Request, Response] = {
    case path ⇒
      manager.value.serviceFor(Path(path)) match {
        case Right(s)  ⇒ delegateTo(s)
        case Left(msg) ⇒ error(msg)
      }
  }

  def delegateTo(s: Service): Response = ???
  def error(s: String): Response = ???
}
