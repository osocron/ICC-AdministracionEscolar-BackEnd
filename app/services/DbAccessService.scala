package services

import javax.inject.{Inject, Singleton}

import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.{GetResult, JdbcProfile}
import slick.jdbc.MySQLProfile.api._
import model.Entities.{Ciclos, Curso, Programa, Resultado}

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class DbAccessService @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)
                                (implicit ec: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile] {

  implicit val ciclosResult = GetResult(r => Ciclos(r.<<, r.<<, r.<<))
  implicit val programasResult = GetResult(r => Programa(r.<<))
  implicit val cursosResult = GetResult(r => Curso(r.<<))
  implicit val resultadoResult = GetResult(r => Resultado(r.<<, r.<<, r.<<, r.<<, r.<<, r.<<, r.<<))

  def getCiclos(): Future[Seq[Ciclos]] = {
    val dbio = sql"SELECT periodo, periodocorto, nciclo FROM periodos ORDER BY periodo".as[Ciclos]
    db.run(dbio)
  }

  def searchCiclos(query: String): Future[Seq[Ciclos]] = {
    val dbio = sql"SELECT periodo, periodocorto, nciclo FROM periodos WHERE periodo LIKE '%#$query%' ORDER BY periodo".as[Ciclos]
    db.run(dbio)
  }

  def getProgramas(): Future[Seq[Programa]] = {
    val dbio = sql"SELECT programa FROM materias GROUP BY programa".as[Programa]
    db.run(dbio)
  }

  def searchProgramas(query: String): Future[Seq[Programa]] = {
    val dbio = sql"SELECT programa FROM materias WHERE programa LIKE '%#$query%' GROUP BY programa".as[Programa]
    db.run(dbio)
  }

  def getCursos(programa: String): Future[Seq[Curso]] = {
    val dbio = sql"SELECT curso FROM materias WHERE programa='#$programa' GROUP BY curso".as[Curso]
    db.run(dbio)
  }

  def searchCursos(programa: String, query: String): Future[Seq[Curso]] = {
    val dbio = sql"SELECT curso FROM materias WHERE programa='#$programa' AND curso LIKE '%#$query%' GROUP BY curso".as[Curso]
    db.run(dbio)
  }

  def getMaterias(): Future[Seq[Resultado]] = {
    val dbio = sql"SELECT matricula, nombre, programa, nciclo, ciclo, curso, calif FROM materias".as[Resultado]
    db.run(dbio)
  }

  def getMateriasPorPrograma(nombrePrograma: String): Future[Seq[Resultado]] = {
    val dbio = sql"SELECT matricula, nombre, programa, nciclo, ciclo, curso, calif FROM materias WHERE programa='#$nombrePrograma'".as[Resultado]
    db.run(dbio)
  }

  def getMateriasPorProgramaCurso(nombrePrograma: String, nombreCurso: String): Future[Seq[Resultado]] = {
    val dbio = sql"SELECT matricula, nombre, programa, nciclo, ciclo, curso, calif FROM materias WHERE programa='#$nombrePrograma' AND curso='#$nombreCurso'".as[Resultado]
    db.run(dbio)
  }

  def getMateriasPorPeriodo(periodo: String): Future[Seq[Resultado]] = {
    val dbio = sql"SELECT matricula, nombre, programa, nciclo, ciclo, curso, calif FROM materias WHERE ciclo='#$periodo'".as[Resultado]
    db.run(dbio)
  }

  def getMateriasPorPeriodoPrograma(periodo: String, nombrePrograma: String): Future[Seq[Resultado]] = {
    val dbio = sql"SELECT matricula, nombre, programa, nciclo, ciclo, curso, calif FROM materias WHERE programa='#$nombrePrograma' AND ciclo='#$periodo'".as[Resultado]
    db.run(dbio)
  }

  def getMateriasPorPeriodoProgramaCurso(periodo: String, nombrePrograma: String, nombreCurso: String): Future[Seq[Resultado]] = {
    val dbio = sql"SELECT matricula, nombre, programa, nciclo, ciclo, curso, calif FROM materias WHERE programa='#$nombrePrograma' AND curso='#$nombreCurso' AND ciclo='#$periodo'".as[Resultado]
    db.run(dbio)
  }

}
