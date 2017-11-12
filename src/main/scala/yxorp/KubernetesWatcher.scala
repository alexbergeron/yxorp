package yxorp

import io.fabric8.kubernetes.api.model.extensions.Ingress
import io.fabric8.kubernetes.client.{ KubernetesClientException, Watcher }

class KubernetesWatcher(client: KubernetesClient, holder: RoutesManagerHolder) {

  private lazy val ingressWatcher = new BaseWatcher[Ingress](handleIngress, startWatcher)

  private def handleIngress(action: Watcher.Action, ingress: Ingress): Unit = action match {
    case Watcher.Action.ADDED ⇒
      holder.withNewIngress(KubernetesDataConverters.toKubeIngress(ingress))
    case Watcher.Action.MODIFIED ⇒
      holder.withNewIngress(KubernetesDataConverters.toKubeIngress(ingress))
    case Watcher.Action.DELETED ⇒
    case Watcher.Action.ERROR ⇒
      ()
  }

  private def startWatcher(self: BaseWatcher[Ingress]): Unit = {
    client.registerIngressWatcher(self)
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
