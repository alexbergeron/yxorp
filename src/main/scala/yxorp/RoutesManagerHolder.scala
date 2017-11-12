package yxorp

class RoutesManagerHolder(@volatile private var _value: RoutesManager) {
  def value = _value

  def withNewIngress(ingress: KubeIngress): Unit = {
    _value = value.withNewIngress(ingress)
  }
}
