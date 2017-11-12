package yxorp

import io.fabric8.kubernetes.api.model.extensions.{ Ingress, IngressRule, HTTPIngressPath }
import io.fabric8.kubernetes.api.model.IntOrString

import scala.collection.JavaConverters._

object KubernetesDataConverters {
  def toKubeIngress(ingress: Ingress): KubeIngress = {
    KubeIngress(
      KubeIngressName(ingress.getMetadata.getName),
      ingress.getSpec.getRules.asScala.flatMap(toKubeIngressRules)
    )
  }

  private def toKubeIngressRules(ingressRule: IngressRule): Seq[KubeIngressRule] = {
    ingressRule.getHttp.getPaths.asScala.map(toKubeIngressRule)
  }

  private def toKubeIngressRule(ingressRule: HTTPIngressPath): KubeIngressRule = {
    KubeIngressRule(
      Path(ingressRule.getPath),
      KubeService(
        KubeServiceName(ingressRule.getBackend.getServiceName),
        toKubePort(ingressRule.getBackend.getServicePort)
      )
    )
  }

  private def toKubePort(port: IntOrString): KubeServicePort = {
    KubeServicePort(Option(port.getStrVal).getOrElse(port.getIntVal.toString))
  }
}
