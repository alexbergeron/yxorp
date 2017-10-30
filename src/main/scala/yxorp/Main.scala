package yxorp

import com.twitter.finagle.Http
import io.finch._

object Main {
  def main(args: Array[String]): Unit = {

    val routes: RoutesManager = RoutesManager(Map.empty, Map.empty)
    val holder: RoutesManagerHolder = new RoutesManagerHolder(routes)
    val controller: Controller = new Controller(holder)
    val watcher: KubernetesWatcher = new KubernetesWatcher(holder)

    watcher.start()
    val _ = Http.server.serve(":8080", controller.routeGet.toServiceAs[Text.Plain])
  }
}
