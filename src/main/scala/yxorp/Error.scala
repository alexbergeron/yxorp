package yxorp

sealed trait Error

case class RouteNotFoundError(message: String = "") extends Error
