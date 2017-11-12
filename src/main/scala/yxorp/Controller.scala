package yxorp

import io.finch._

import scala.util.{ Failure, Success, Try }

class Controller(manager: RoutesManagerHolder, forwarder: RequestForwarder) {

  val routeGet: Endpoint[String] = get(paths[String]) { (p: Seq[String]) ⇒
    getService(p).flatMap(forwarder.fetch) match {
      case Success(body)          ⇒ Ok(body)
      case Failure(ex: Exception) ⇒ NotFound(ex)
      case Failure(t)             ⇒ InternalServerError(new Exception(t))
    }
  }

  private def getService(paths: Seq[String]): Try[KubeService] = {
    manager.value.serviceFor(Path(paths.mkString("/", "/", ""))).left.map(msg ⇒ new Exception(msg)).toTry
  }
}
