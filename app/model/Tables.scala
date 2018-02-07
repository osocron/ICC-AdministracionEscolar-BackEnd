package model
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.driver.MySQLDriver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.driver.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Materias.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Materias
    *  @param idregistro Database column idregistro SqlType(INT), AutoInc, PrimaryKey
    *  @param matricula Database column matricula SqlType(VARCHAR), Length(20,true), Default(None)
    *  @param contrasenia Database column contrasenia SqlType(VARCHAR), Length(20,true), Default(None)
    *  @param nombre Database column nombre SqlType(VARCHAR), Length(150,true), Default(None)
    *  @param clavepro Database column clavepro SqlType(VARCHAR), Length(20,true), Default(None)
    *  @param programa Database column programa SqlType(VARCHAR), Length(250,true), Default(None)
    *  @param nciclo Database column nciclo SqlType(INT), Default(None)
    *  @param ciclo Database column ciclo SqlType(VARCHAR), Length(100,true), Default(None)
    *  @param ncursos Database column ncursos SqlType(VARCHAR), Length(10,true), Default(None)
    *  @param nplan Database column nplan SqlType(INT), Default(None)
    *  @param curso Database column curso SqlType(VARCHAR), Length(300,true), Default(None)
    *  @param calif Database column calif SqlType(VARCHAR), Length(10,true), Default(None)
    *  @param estatus Database column estatus SqlType(VARCHAR), Length(150,true), Default(None)
    *  @param afectado Database column afectado SqlType(VARCHAR), Length(20,true), Default(None)
    *  @param beca Database column beca SqlType(FLOAT), Default(None)
    *  @param observacion Database column observacion SqlType(TEXT), Default(None) */
  case class MateriasRow(idregistro: Int, matricula: Option[String] = None, contrasenia: Option[String] = None, nombre: Option[String] = None, clavepro: Option[String] = None, programa: Option[String] = None, nciclo: Option[Int] = None, ciclo: Option[String] = None, ncursos: Option[String] = None, nplan: Option[Int] = None, curso: Option[String] = None, calif: Option[String] = None, estatus: Option[String] = None, afectado: Option[String] = None, beca: Option[Float] = None, observacion: Option[String] = None)
  /** GetResult implicit for fetching MateriasRow objects using plain SQL queries */
  implicit def GetResultMateriasRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[Option[Int]], e3: GR[Option[Float]]): GR[MateriasRow] = GR{
    prs => import prs._
      MateriasRow.tupled((<<[Int], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[Int], <<?[String], <<?[String], <<?[Int], <<?[String], <<?[String], <<?[String], <<?[String], <<?[Float], <<?[String]))
  }
  /** Table description of table materias. Objects of this class serve as prototypes for rows in queries. */
  class Materias(_tableTag: Tag) extends Table[MateriasRow](_tableTag, "materias") {
    def * = (idregistro, matricula, contrasenia, nombre, clavepro, programa, nciclo, ciclo, ncursos, nplan, curso, calif, estatus, afectado, beca, observacion) <> (MateriasRow.tupled, MateriasRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(idregistro), matricula, contrasenia, nombre, clavepro, programa, nciclo, ciclo, ncursos, nplan, curso, calif, estatus, afectado, beca, observacion).shaped.<>({r=>import r._; _1.map(_=> MateriasRow.tupled((_1.get, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _12, _13, _14, _15, _16)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column idregistro SqlType(INT), AutoInc, PrimaryKey */
    val idregistro: Rep[Int] = column[Int]("idregistro", O.AutoInc, O.PrimaryKey)
    /** Database column matricula SqlType(VARCHAR), Length(20,true), Default(None) */
    val matricula: Rep[Option[String]] = column[Option[String]]("matricula", O.Length(20,varying=true), O.Default(None))
    /** Database column contrasenia SqlType(VARCHAR), Length(20,true), Default(None) */
    val contrasenia: Rep[Option[String]] = column[Option[String]]("contrasenia", O.Length(20,varying=true), O.Default(None))
    /** Database column nombre SqlType(VARCHAR), Length(150,true), Default(None) */
    val nombre: Rep[Option[String]] = column[Option[String]]("nombre", O.Length(150,varying=true), O.Default(None))
    /** Database column clavepro SqlType(VARCHAR), Length(20,true), Default(None) */
    val clavepro: Rep[Option[String]] = column[Option[String]]("clavepro", O.Length(20,varying=true), O.Default(None))
    /** Database column programa SqlType(VARCHAR), Length(250,true), Default(None) */
    val programa: Rep[Option[String]] = column[Option[String]]("programa", O.Length(250,varying=true), O.Default(None))
    /** Database column nciclo SqlType(INT), Default(None) */
    val nciclo: Rep[Option[Int]] = column[Option[Int]]("nciclo", O.Default(None))
    /** Database column ciclo SqlType(VARCHAR), Length(100,true), Default(None) */
    val ciclo: Rep[Option[String]] = column[Option[String]]("ciclo", O.Length(100,varying=true), O.Default(None))
    /** Database column ncursos SqlType(VARCHAR), Length(10,true), Default(None) */
    val ncursos: Rep[Option[String]] = column[Option[String]]("ncursos", O.Length(10,varying=true), O.Default(None))
    /** Database column nplan SqlType(INT), Default(None) */
    val nplan: Rep[Option[Int]] = column[Option[Int]]("nplan", O.Default(None))
    /** Database column curso SqlType(VARCHAR), Length(300,true), Default(None) */
    val curso: Rep[Option[String]] = column[Option[String]]("curso", O.Length(300,varying=true), O.Default(None))
    /** Database column calif SqlType(VARCHAR), Length(10,true), Default(None) */
    val calif: Rep[Option[String]] = column[Option[String]]("calif", O.Length(10,varying=true), O.Default(None))
    /** Database column estatus SqlType(VARCHAR), Length(150,true), Default(None) */
    val estatus: Rep[Option[String]] = column[Option[String]]("estatus", O.Length(150,varying=true), O.Default(None))
    /** Database column afectado SqlType(VARCHAR), Length(20,true), Default(None) */
    val afectado: Rep[Option[String]] = column[Option[String]]("afectado", O.Length(20,varying=true), O.Default(None))
    /** Database column beca SqlType(FLOAT), Default(None) */
    val beca: Rep[Option[Float]] = column[Option[Float]]("beca", O.Default(None))
    /** Database column observacion SqlType(TEXT), Default(None) */
    val observacion: Rep[Option[String]] = column[Option[String]]("observacion", O.Default(None))
  }
  /** Collection-like TableQuery object for table Materias */
  lazy val Materias = new TableQuery(tag => new Materias(tag))
}
