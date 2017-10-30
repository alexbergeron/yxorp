package yxorp

import io.fabric8.kubernetes.api.model.extensions.Ingress
import io.fabric8.kubernetes.client.{ DefaultKubernetesClient, KubernetesClientException, Watcher }

class KubernetesWatcher(holder: RoutesManagerHolder) {

  private val client = new DefaultKubernetesClient().inNamespace("default")

  private lazy val ingressWatcher = new BaseWatcher[Ingress](handleIngress, startWatcher)

  private def handleIngress(action: Watcher.Action, ingress: Ingress): Unit = action match {
    case Watcher.Action.ADDED ⇒
      holder.withNewIngress(toKubeIngress(ingress))
    case Watcher.Action.MODIFIED ⇒
      holder.withNewIngress(toKubeIngress(ingress))
    case Watcher.Action.DELETED ⇒
    case Watcher.Action.ERROR ⇒
      ()
  }

  private def toKubeIngress(ingress: Ingress): KubeIngress = ???

  private def startWatcher(self: BaseWatcher[Ingress]): Unit = {
    val _ = client.extensions.ingresses.watch(self)
  }

  def start(): Unit = {
    startWatcher(ingressWatcher)
  }

}

class BaseWatcher[A](onReceive: (Watcher.Action, A) ⇒ Unit, retry: BaseWatcher[A] ⇒ Unit) extends Watcher[A] {
  def eventReceived(action: Watcher.Action, data: A): Unit = onReceive(action, data)
  def onClose(ex: KubernetesClientException): Unit = {
    if (ex != null) {
      retry(this)
    }
  }
}
