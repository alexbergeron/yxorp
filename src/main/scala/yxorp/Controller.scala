package yxorp

import io.finch._

class Controller(manager: RoutesManagerHolder) {

  val routeGet: Endpoint[String] = get(paths[String]) { (p: Seq[String]) ⇒
    manager.value.serviceFor(Path(p.mkString("/", "/", ""))) match {
      case Right(s)  ⇒ Ok(s.toString)
      case Left(msg) ⇒ NotFound(new Exception(msg))
    }
  }
}
