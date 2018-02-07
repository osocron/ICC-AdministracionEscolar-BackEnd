package model

object Entities {

  case class Resultado(matricula: String, nombre: String, programa: String, nciclo: Int, ciclo: String, curso: String, calif: String)

  case class Ciclos(periodo: String, periodoCorto: String, nciclo: Int)

  case class Programa(nombre: String)

  case class Curso(nombre: String)

}
