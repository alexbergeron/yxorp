package yxorp

case class KubeServiceName(name: String) extends AnyVal
case class KubeServicePort(port: String) extends AnyVal
case class KubeIngressName(name: String) extends AnyVal
case class Path(path: String) extends AnyVal

case class KubeIngressRule(path: Path, service: KubeService)
case class KubeIngress(name: KubeIngressName, rules: Seq[KubeIngressRule])

case class KubeService(name: KubeServiceName, port: KubeServicePort)
