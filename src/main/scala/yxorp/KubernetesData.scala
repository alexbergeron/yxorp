package yxorp

case class KubeServiceName(name: String) extends AnyVal
case class KubeIngressName(name: String) extends AnyVal
case class Address(address: String) extends AnyVal
case class Path(path: String) extends AnyVal

case class KubeIngressRule(path: Path, serviceName: KubeServiceName)
case class KubeIngress(name: KubeIngressName, rules: Seq[KubeIngressRule])

case class KubeService(name: KubeServiceName, addresses: Seq[Address], port: Int)

