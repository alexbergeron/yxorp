package xyorp

// To be handled through an agent-like structure
case class RoutesManager(services: Map[ServiceName, Service], ingresses: Map[IngressName, Ingress]) {

  lazy val pathsToServices: Map[Path, Service] = ingresses.flatMap {
    case (_, Ingress(_, rules)) ⇒
      rules.map {
        case IngressRule(path, serviceName) ⇒ path → services(serviceName) //TODO
      }
  }.toMap

  def serviceFor(path: Path): Either[String, Service] = {
    // Best practice between choosing first or alerting on conflict
    // Incoherent - two source of truths for available paths
    pathsToServices.keys.find(ingressPath ⇒ pathMatch(path, ingressPath)).map(pathsToServices) match {
      case Some(s) ⇒ Right(s)
      case None    ⇒ Left("Service not found")
    }
  }

  def pathMatch(requestPath: Path, ingressPath: Path): Boolean = {
    requestPath.path.startsWith(ingressPath.path)
  }

  def withNewService(service: Service): RoutesManager = {
    if (services.contains(service.name)) {
      copy(services = services.updated(service.name, service))
    } else {
      copy(services = services + (service.name → service))
    }
  }

  def withNewIngress(ingress: Ingress): RoutesManager = {
    if (ingresses.contains(ingress.name)) {
      copy(ingresses = ingresses.updated(ingress.name, ingress))
    } else {
      copy(ingresses = ingresses + (ingress.name → ingress))
    }
  }
}
