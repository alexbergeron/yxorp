package yxorp

class RoutesManagerHolder(@volatile private var _value: RoutesManager) {
  def value = _value

  def withNewService(service: KubeService): Unit = {
    _value = value.withNewService(service)
  }

  def withNewIngress(ingress: KubeIngress): Unit = {
    _value = value.withNewIngress(ingress)
  }
}
