package yxorp

// To be handled through an agent-like structure
case class RoutesManager(services: Map[KubeServiceName, KubeService], ingresses: Map[KubeIngressName, KubeIngress]) {

  lazy val pathsToKubeServices: Map[Path, KubeService] = ingresses.flatMap {
    case (_, KubeIngress(_, rules)) ⇒
      rules.map {
        case KubeIngressRule(path, serviceName) ⇒ path → services(serviceName) //TODO
      }
  }.toMap

  def serviceFor(path: Path): Either[String, KubeService] = {
    // Best practice between choosing first or alerting on conflict
    // Incoherent - two source of truths for available paths
    pathsToKubeServices.keys.find(ingressPath ⇒ pathMatch(path, ingressPath)).map(pathsToKubeServices) match {
      case Some(s) ⇒ Right(s)
      case None    ⇒ Left("KubeService not found")
    }
  }

  def pathMatch(requestPath: Path, ingressPath: Path): Boolean = {
    requestPath.path.startsWith(ingressPath.path)
  }

  def withNewService(service: KubeService): RoutesManager = {
    if (services.contains(service.name)) {
      copy(services = services.updated(service.name, service))
    } else {
      copy(services = services + (service.name → service))
    }
  }

  def withNewIngress(ingress: KubeIngress): RoutesManager = {
    if (ingresses.contains(ingress.name)) {
      copy(ingresses = ingresses.updated(ingress.name, ingress))
    } else {
      copy(ingresses = ingresses + (ingress.name → ingress))
    }
  }
}
