package yxorp

import okhttp3.{ OkHttpClient, Request, Response }

import scala.util.Try

class RequestForwarder {
  private val client = new OkHttpClient()

  def fetch(service: KubeService): Try[String] = {
    val url = buildUrl(service)
    val request = new Request.Builder()
      .url(url)
      .build()
    val response = Try(client.newCall(request).execute())
    response.flatMap(responseToBody)
  }

  private def buildUrl(service: KubeService): String = {
    s"http://${service.name.name}.default.svc.cluster.local:${service.port.port}/"
  }

  private def responseToBody(response: Response): Try[String] = {
    val body = Try(response.body().string())
    response.close()
    body
  }
}
