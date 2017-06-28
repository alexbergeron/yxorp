package xyorp

// Somewhat of an absurdity caused by being on a place and not in a good state to figure out what is be available to me
case class RoutesManagerHolder(@volatile private var _value: RoutesManager) {
  def value = _value

  def withNewService(service: Service): Unit = {
    _value = value.withNewService(service)
  }

  def withNewIngress(ingress: Ingress): Unit = {
    _value = value.withNewIngress(ingress)
  }
}
