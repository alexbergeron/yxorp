
package object yxorp {

  type Try[+T] = Either[Error, T]
}
