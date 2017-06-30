package xyorp

case class ServiceName(name: String) extends AnyVal
case class IngressName(name: String) extends AnyVal
case class Address(address: String) extends AnyVal
case class Path(path: String) extends AnyVal

case class IngressRule(path: Path, serviceName: ServiceName)
case class Ingress(name: IngressName, rules: Seq[IngressRule])

case class Service(name: ServiceName, addresses: Seq[Address], port: Int)
