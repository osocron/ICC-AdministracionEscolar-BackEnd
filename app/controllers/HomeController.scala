package controllers

import javax.inject._

import model.Entities.{Ciclos, Curso, Programa, Resultado}
import play.api.mvc._
import services.DbAccessService
import play.api.libs.json._

import scala.concurrent.{ExecutionContext, Future}
/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents,
                               dbService: DbAccessService)
                              (implicit ec: ExecutionContext) extends AbstractController(cc) {

  implicit val ciclosReads = Json.reads[Ciclos]
  implicit val ciclosWrites = Json.writes[Ciclos]
  implicit val programasReads = Json.reads[Programa]
  implicit val programasWrites = Json.writes[Programa]
  implicit val cursosWrites = Json.writes[Curso]
  implicit val cursosReads = Json.reads[Curso]
  implicit val resultadosReads = Json.reads[Resultado]
  implicit val resultadosWrites = Json.writes[Resultado]

  def getCiclos() = Action.async{ implicit result =>
    dbService.getCiclos().map(ciclos => Ok(Json.toJson(ciclos).toString()))
  }

  def searchCiclos(query: String) = Action.async{ implicit result =>
    dbService.searchCiclos(query).map(ciclos => Ok(Json.toJson(ciclos).toString()))
  }

  def getProgramas() = Action.async { implicit result =>
    dbService.getProgramas().map(programas => Ok(Json.toJson(programas).toString()))
  }

  def searchProgramas(query: String) = Action.async { implicit result =>
    dbService.searchProgramas(query).map(programas => Ok(Json.toJson(programas).toString()))
  }

  def getCursos(programa: String) = Action.async { implicit result =>
    dbService.getCursos(programa).map(cursos => Ok(Json.toJson(cursos).toString()))
  }

  def searchCursos(programa: String, query: String) = Action.async { implicit result =>
    dbService.searchCursos(programa, query).map(cursos => Ok(Json.toJson(cursos).toString()))
  }

  def getAllMaterias: Action[AnyContent] = Action.async {
    dbService
      .getMaterias()
      .map(okResultadosResult).recover {
      case cause => serverErrorResult(cause)
    }
  }

  def getMateriasPorPrograma: Action[AnyContent] = Action.async { implicit request =>
    val data = request.body.asFormUrlEncoded
    (for {
      programa <- data.get("programa").map(_.toString).headOption
    } yield dbService
      .getMateriasPorPrograma(programa)
      .map(okResultadosResult).recover {
      case cause => serverErrorResult(cause)
    }).getOrElse(futureBadRequestResult)
  }

  def getMateriasPorProgramaCurso: Action[AnyContent] = Action.async { implicit request =>
    val data = request.body.asFormUrlEncoded
    (for {
      programa <- data.get("programa").map(_.toString).headOption
      curso <- data.get("curso").map(_.toString).headOption
    } yield dbService
      .getMateriasPorProgramaCurso(programa, curso)
      .map(okResultadosResult).recover {
      case cause => serverErrorResult(cause)
    }).getOrElse(futureBadRequestResult)
  }

  def getMateriasPorPeriodo: Action[AnyContent] = Action.async { implicit request =>
    val data = request.body.asFormUrlEncoded
    (for {
      periodo <- data.get("periodo").map(_.toString).headOption
    } yield dbService
      .getMateriasPorPeriodo(periodo)
      .map(okResultadosResult).recover {
      case cause => serverErrorResult(cause)
    }).getOrElse(futureBadRequestResult)
  }

  def getMateriasPorPeriodoPrograma: Action[AnyContent] = Action.async { implicit request =>
    val data = request.body.asFormUrlEncoded
    (for {
      periodo <- data.get("periodo").map(_.toString).headOption
      programa <- data.get("programa").map(_.toString).headOption
    } yield dbService
      .getMateriasPorPeriodoPrograma(periodo, programa)
      .map(okResultadosResult).recover {
      case cause => serverErrorResult(cause)
    }).getOrElse(futureBadRequestResult)
  }

  def getMateriasPorPeriodoProgramaCurso: Action[AnyContent] = Action.async { implicit request =>
    val data = request.body.asFormUrlEncoded
    (for {
      periodo <- data.get("periodo").map(_.toString).headOption
      programa <- data.get("programa").map(_.toString).headOption
      curso <- data.get("curso").map(_.toString).headOption
    } yield dbService
      .getMateriasPorPeriodoProgramaCurso(periodo, programa, curso)
      .map(okResultadosResult).recover {
      case cause => serverErrorResult(cause)
    }).getOrElse(futureBadRequestResult)
  }

  def okResultadosResult(resultados: Seq[Resultado]): Result = Ok(jsonResult(error = false, Json.toJson(resultados)))

  def serverErrorResult(cause: Throwable): Result = InternalServerError(jsonResult(error = true, JsString(cause.getMessage)))

  def futureBadRequestResult: Future[Result] =
    Future.successful(BadRequest(jsonResult(error = true, JsString("Error en los parametros"))))

  def jsonResult(error: Boolean, value: JsValue): JsObject = Json.obj("error" -> error, "value" -> value)

}
