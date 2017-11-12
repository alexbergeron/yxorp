package yxorp

import io.fabric8.kubernetes.api.model.extensions.Ingress
import io.fabric8.kubernetes.client.{ DefaultKubernetesClient, Watcher }

import scala.collection.JavaConverters._

class KubernetesClient {

  private val client = new DefaultKubernetesClient().inNamespace("default")

  def registerIngressWatcher(watcher: Watcher[Ingress]): Unit = {
    val _ = client.extensions.ingresses.watch(watcher)
  }

  def getIngresses(): Seq[Ingress] = {
    client.extensions.ingresses.list().getItems.asScala
  }
}
