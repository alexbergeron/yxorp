package yxorp

import com.twitter.finagle.Http
import io.finch._

object Main {
  def main(args: Array[String]): Unit = {

    val client = new KubernetesClient()
    val ingresses = client.getIngresses.map(KubernetesDataConverters.toKubeIngress)
    val routes = RoutesManager.fromIngresses(ingresses)
    val holder = new RoutesManagerHolder(routes)
    val controller = new Controller(holder)
    val watcher = new KubernetesWatcher(client, holder)

    watcher.start()
    val _ = Http.server.serve(":8080", controller.routeGet.toServiceAs[Text.Plain])
  }
}
