/* Generated By:JavaCC: Do not edit this line. minilengcompiler.java */
package JavaCC;

import java.io.FileNotFoundException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import tablaSimbolos.*;


public class minilengcompiler implements minilengcompilerConstants {
  public static boolean verbose = false;
  public static boolean panicmode = false;

  // Semantico
  public static int nivel = 0;
  public static Tabla_simbolos tabla;

  // Para evitar sacar siempre el mismo error

  public static int nivelMaxError = 1;
  public static int nivelError = 0;
  public static int lineaError = 0;
  public static int columnaError = 0;



 private static void errorSintactico(Exception e, String informacion) {
    String mensajeError = e.getMessage();
    //System.out.println(mensajeError);
    Token t =  getToken(1); // porque es como una pila inversa, el 0 es el del lookahead
        if (nivelError == nivelMaxError && columnaError == t.beginColumn && lineaError == t.beginLine) {
                                System.out.print("WARNING SINTACTICO("+t.beginLine+","+t.beginColumn+"): ");
                                System.out.println("Ignorado el simbolo "+t.image+", se pasa al siguiente.");
                nivelError = 0;
                getNextToken();
        }
        else {
               System.out.print("ERROR SINTACTICO("+t.beginLine+","+t.beginColumn+"): ");
                                System.out.println("Encontrado simbolo "+t.image+" cuando esperaba otra cosa.");
                                nivelError++;
                if (t.beginLine != lineaError && t.beginColumn != columnaError) {
                  nivelError = 1;
                }
                lineaError = t.beginLine;
                columnaError = t.beginColumn;
        }

  }

  private static void warningPanicMode(Exception e) {
//    String mensajeError = e.getMessage();
//        
//	Pattern pattern = Pattern.compile("Encountered (\" .*) at");
//	Matcher matcher = pattern.matcher(mensajeError);
//	matcher.find();
//	String token = matcher.group(1);
//
//	pattern = Pattern.compile("at line ([0-9]+),");
//	matcher = pattern.matcher(mensajeError);
//	matcher.find();
//	String linea = matcher.group(1);
//
//	pattern = Pattern.compile(", column ([0-9]+).");
//	matcher = pattern.matcher(mensajeError);
//	matcher.find();
//	String columna = matcher.group(1);
//
//	pattern = Pattern.compile("(Was expecting:)");
//	matcher = pattern.matcher(mensajeError);
//	matcher.find();
//	boolean encontrado = true;
//	String decidir = "";
//	try { 
//	decidir = matcher.group(1);
//} catch (IllegalStateException i) {
//    encontrado = false;
//}
//	String esperaba = "nada";
//	if (encontrado) {
//	  pattern = Pattern.compile("Was expecting:((.*\n)*)");
//	  matcher = pattern.matcher(mensajeError);
//	  matcher.find();
//	  esperaba = matcher.group(1);
//	}
//	else {
//	  pattern = Pattern.compile("Was expecting one of:((.*\n)*)");
//	  matcher = pattern.matcher(mensajeError);
//	  matcher.find();
//	  esperaba = matcher.group(1);
//	}
//	System.out.print("WARNING SINTACTICO (PANIC MODE ACTIVADO)("+linea+","+columna+"): ");
//	System.out.println("Encontrado "+token+" cuando esperaba: "+esperaba);
  }


  public static void main(String args []) throws ParseException
  {
//    minilengcompiler parser = new minilengcompiler(System.in);
//	if (args.length == 2 && args[1].equals("-v")) {
//	  minilengcompiler.verbose = true;
//	  System.out.println("VERBOSE ACTIVADO");
//	}

        // El 0 siempre es el fichero
        for(int i = 1; i < args.length; i++) {
          switch(args[i]) {
            case "-v":
                System.out.println("VERBOSE TRUEEEE");
                minilengcompiler.verbose = true;
                break;
            case "-p": // panic mode
                System.out.println("PANIC MODE TRUEE");
                minilengcompiler.panicmode = true;
                break;
          }
        }

        // Inicializar la tabla de simbolos
        tabla = new Tabla_simbolos();
        tabla.inicializar_tabla();

        try{
            System.out.println("COMPRUEBO EL FICHERO: "+args[0]);
        minilengcompiler parser = new minilengcompiler(new java.io.FileInputStream(args[0]));
    }
    catch (FileNotFoundException ex)
    {
    }
try {
          minilengcompiler.one_line();
         }
      catch (Exception e)
      {
        System.out.println("NOK.");
        System.out.println(e.getMessage());
        minilengcompiler.ReInit(System.in);
      }
      catch (Error e)
      {
        //System.out.println("Oops.");
        System.out.println(e.getMessage());
        String mensajeError = e.getMessage();

                Pattern pattern = Pattern.compile("line ([0-9]+)");
                Matcher matcher = pattern.matcher(mensajeError);
                matcher.find();
                String linea = matcher.group(1);

                pattern = Pattern.compile("column ([0-9]+)");
                matcher = pattern.matcher(mensajeError);
                matcher.find();
                String columna = matcher.group(1);

                pattern = Pattern.compile("Encountered: (.*) after :");
                matcher = pattern.matcher(mensajeError);
                matcher.find();
                String simbolo = matcher.group(1);

                System.out.println("ERROR LEXICO ("+ linea + ", " + columna + "): simbolo no reconocido: " + simbolo);
      }
      //minilengcompiler.mostrarEstadisticas();
  }

// PANIC MODE
  static final public void ptocoma() throws ParseException {
    try {
      jj_consume_token(tPTOCOMA);
    } catch (Exception e) {
    if (minilengcompiler.panicmode) {
        minilengcompiler.warningPanicMode(e);
            Token t;
            do {
              t = getNextToken();
            } while (t.kind != tPTOCOMA && t != null && t.kind != EOF);
        }
        else {
          {if (true) throw e;}
        }
    }
  }

// Programa ::= <tPROGRAMA> <tIDENTIFICADOR> ";" declaracion_variables declaracion_acciones bloque_sentencias
  static final public void programa() throws ParseException {
    try {
      jj_consume_token(tPROGRAMA);
      jj_consume_token(tIDENTIFICADOR);
      ptocoma();
      declaracion_variables();
      declaracion_acciones();
      bloque_sentencias();
    } catch (Exception e) {
  System.out.println("ERROR AQUI");
  minilengcompiler.errorSintactico(e, "programa");
    }
  }

// declaracion_variables ::= ( declaracion ";" )*
  static final public void declaracion_variables() throws ParseException {
    try {
      label_1:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case tENTERO:
        case tBOOLEANO:
        case tCARACTER:
          ;
          break;
        default:
          jj_la1[0] = jj_gen;
          break label_1;
        }
        declaracion();
        ptocoma();
      }
    } catch (Exception e) {
  minilengcompiler.errorSintactico(e, "declaracion_variables");
    }
  }

// declaracion ::= tipo_variables() identificadores()
  static final public void declaracion() throws ParseException {
  Tipo_variable tipoVar;
  Simbolo s;
    try {
      tipoVar = tipo_variables();
    s = new Simbolo("", tipoVar, Clase_parametro.REF, 0, 0); // El nivel y direccion da igual porque luego se machaca

      identificadores(s);
    } catch (Exception e) {
  minilengcompiler.errorSintactico(e, "declaracion");
    }
  }

//tipo_variables ::= <tENTERO> | <tCARACTER> | <tBOOLEANO>
  static final public Tipo_variable tipo_variables() throws ParseException {
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case tENTERO:
        jj_consume_token(tENTERO);
                {if (true) return Tipo_variable.ENTERO;}
        break;
      case tCARACTER:
        jj_consume_token(tCARACTER);
                    {if (true) return Tipo_variable.CARACTER;}
        break;
      case tBOOLEANO:
        jj_consume_token(tBOOLEANO);
                    {if (true) return Tipo_variable.BOOLEANO;}
        break;
      default:
        jj_la1[1] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } catch (Exception e) {
  minilengcompiler.errorSintactico(e, "tipo_variables");
    }
    throw new Error("Missing return statement in function");
  }

// identificadores ::= <tIDENTIFICADOR> ( "," <tIDENTIFICADOR> )*
  static final public void identificadores(Simbolo s) throws ParseException {
  Token t1, t2 = null;
    try {
      t1 = jj_consume_token(tIDENTIFICADOR);
    Errores.validarIdentificador(t1, s);
      label_2:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case tCOMA:
          ;
          break;
        default:
          jj_la1[2] = jj_gen;
          break label_2;
        }
        jj_consume_token(tCOMA);
        t2 = jj_consume_token(tIDENTIFICADOR);
     Errores.validarIdentificador(t2, s);
      }
    } catch (Exception e) {
  minilengcompiler.errorSintactico(e, "identificadores");
    }
  }

// declaracion_acciones ::= ( declaracion_accion )*
  static final public void declaracion_acciones() throws ParseException {
    try {
      label_3:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case tACCION:
          ;
          break;
        default:
          jj_la1[3] = jj_gen;
          break label_3;
        }
        declaracion_accion();
      }
    } catch (Exception e) {
  minilengcompiler.errorSintactico(e, "declaracion_acciones");
    }
  }

// declaracion_accion ::= cabecera_accion() ";" declaracion_variables() declaracion_acciones() bloque_sentencias()
  static final public void declaracion_accion() throws ParseException {
    try {
      cabecera_accion();
      ptocoma();
      declaracion_variables();
      declaracion_acciones();
      bloque_sentencias();
    minilengcompiler.tabla.eliminar_variables_parametros(minilengcompiler.nivel);
    minilengcompiler.nivel--;
    } catch (Exception e) {
  minilengcompiler.errorSintactico(e, "declaracion_accion");
    }
  }

// cabecera_accion ::= <tACCION> <tIDENTIFICADOR> parametros_formales()
  static final public void cabecera_accion() throws ParseException {
  Token t;
  Simbolo s = null;
    try {
      jj_consume_token(tACCION);
      t = jj_consume_token(tIDENTIFICADOR);
    try {
      s = minilengcompiler.tabla.introducir_accion(t.image, minilengcompiler.nivel, 0);
    } catch (SimboloYaExistenteException e) {
      Errores.error_semantico("Identificador de accion duplicado");
    }
      parametros_formales(s);
    } catch (Exception e) {
  minilengcompiler.errorSintactico(e, "cabecera_accion");
    }
  }

// parametros_formales ::= ( < tABREPARENTESIS > lista_parametros() < tCIERREPARENTESIS >)?
  static final public void parametros_formales(Simbolo s) throws ParseException {
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case tABREPARENTESIS:
        jj_consume_token(tABREPARENTESIS);
        lista_parametros(s);
        jj_consume_token(tCIERREPARENTESIS);
        break;
      default:
        jj_la1[4] = jj_gen;
        ;
      }
    } catch (Exception e) {
  minilengcompiler.errorSintactico(e, "parametros_formales");
    }
  }

//lista_parametros ::= parametros ( < tPTOCOMA > parametros )* 
  static final public void lista_parametros(Simbolo s) throws ParseException {
    try {
      parametros(s);
      label_4:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case tPTOCOMA:
          ;
          break;
        default:
          jj_la1[5] = jj_gen;
          break label_4;
        }
        ptocoma();
        parametros(s);
      }
    } catch (Exception e) {
  minilengcompiler.errorSintactico(e, "lista_parametros");
    }
  }

// parametros ::= clase_parametros tipo_variables identificadores
  static final public void parametros(Simbolo accionParaAsociar) throws ParseException {
  Simbolo identificador;
  Clase_parametro clase;
  Tipo_variable tipo;
    try {
      clase = clase_parametros();
      tipo = tipo_variables();
    identificador = new Simbolo("", tipo, clase, 0, 0);
    identificador.setAccionAsociada(accionParaAsociar);
      identificadores(identificador);
    } catch (Exception e) {
  minilengcompiler.errorSintactico(e, "parametros");
    }
  }

// clase_parametros ::= < tVAL > | < tREF >
  static final public Clase_parametro clase_parametros() throws ParseException {
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case tVAL:
        jj_consume_token(tVAL);
             {if (true) return Clase_parametro.VAL;}
        break;
      case tREF:
        jj_consume_token(tREF);
             {if (true) return Clase_parametro.REF;}
        break;
      default:
        jj_la1[6] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } catch (Exception e) {
  minilengcompiler.errorSintactico(e, "clase_parametros");
    }
    throw new Error("Missing return statement in function");
  }

// bloque_sentencias ::= <tPRINCIPIO> lista_sentencias <tFIN>
  static final public void bloque_sentencias() throws ParseException {
    try {
      jj_consume_token(tPRINCIPIO);
      lista_sentencias();
      jj_consume_token(tFIN);
    } catch (Exception e) {
  minilengcompiler.errorSintactico(e, "bloque_sentencias");
    }
  }

// lista_sentencias ::= (sentencia)*
  static final public void lista_sentencias() throws ParseException {
    try {
      label_5:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case tSI:
        case tMQ:
        case tESCRIBIR:
        case tLEER:
        case tIDENTIFICADOR:
          ;
          break;
        default:
          jj_la1[7] = jj_gen;
          break label_5;
        }
        sentencia();
      }
    } catch (Exception e) {
  minilengcompiler.errorSintactico(e, "lista_sentencias");
    }
  }

//sentencia ::= leer ";"
// | escribir ";"
// | asignacion
// | invocacion_accion
// | seleccion
// | mientras_que
  static final public void sentencia() throws ParseException {
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case tLEER:
        leer();
        ptocoma();
        break;
      case tESCRIBIR:
        escribir();
        ptocoma();
        break;
      case tIDENTIFICADOR:
        jj_consume_token(tIDENTIFICADOR);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case tOPAS:
          asignacion();
          break;
        case tPTOCOMA:
        case tABREPARENTESIS:
          invocacion_accion();
          break;
        default:
          jj_la1[8] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        break;
      case tSI:
        seleccion();
        break;
      case tMQ:
        mientras_que();
        break;
      default:
        jj_la1[9] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } catch (Exception e) {
  minilengcompiler.errorSintactico(e, "sentencia");
    }
  }

// leer ::= <tLEER> "(" lista_asignables ")"
  static final public void leer() throws ParseException {
    try {
      jj_consume_token(tLEER);
      jj_consume_token(tABREPARENTESIS);
      lista_asignables();
      jj_consume_token(tCIERREPARENTESIS);
    } catch (Exception e) {
  minilengcompiler.errorSintactico(e, "leer");
    }
  }

//lista_asignables ::= identificadores
  static final public void lista_asignables() throws ParseException {
    try {
      identificadores(null);
    } catch (Exception e) {
  minilengcompiler.errorSintactico(e, "lista_asignables");
    }
  }

// escribir ::= <tESCRIBIR> "(" lista_escribibles ")"
  static final public void escribir() throws ParseException {
    jj_consume_token(tESCRIBIR);
    jj_consume_token(tABREPARENTESIS);
    lista_escribibles();
    jj_consume_token(tCIERREPARENTESIS);
  }

// lista_escribibles ::= lista_expresiones
  static final public void lista_escribibles() throws ParseException {
    try {
      lista_expresiones();
    } catch (Exception e) {
  minilengcompiler.errorSintactico(e, "lista_escribibles");
    }
  }

// asignación ::= <tIDENTIFICADOR> <tOPAS> expresion ";"
  static final public void asignacion() throws ParseException {
    try {
      jj_consume_token(tOPAS);
      expresion();
      ptocoma();
    } catch (Exception e) {
  minilengcompiler.errorSintactico(e, "asignacion");
    }
  }

// invocacion_accion ::= <tIDENTIFICADOR> argumentos ";"
  static final public void invocacion_accion() throws ParseException {
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case tABREPARENTESIS:
        argumentos();
        break;
      default:
        jj_la1[10] = jj_gen;
        ;
      }
      ptocoma();
    } catch (Exception e) {
  minilengcompiler.errorSintactico(e, "invocacion_accion");
    }
  }

// mientras_que ::= <tMQ> expresion lista_sentencias <tFMQ>
  static final public void mientras_que() throws ParseException {
    try {
      jj_consume_token(tMQ);
      expresion();
      lista_sentencias();
      jj_consume_token(tFMQ);
    } catch (Exception e) {
  minilengcompiler.errorSintactico(e, "mientras_que");
    }
  }

// seleccion ::= <tSI> expresion lista_sentencias (tSI_NO expresion lista_sentencias tFSI_NO)? <tFSI>
  static final public void seleccion() throws ParseException {
    try {
      jj_consume_token(tSI);
      expresion();
      jj_consume_token(tENT);
      lista_sentencias();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case tSI_NO:
        jj_consume_token(tSI_NO);
        lista_sentencias();
        break;
      default:
        jj_la1[11] = jj_gen;
        ;
      }
      jj_consume_token(tFSI);
    } catch (Exception e) {
  minilengcompiler.errorSintactico(e, "seleccion");
    }
  }

// argumentos ::= "(" ( lista_expresiones )? ")"
  static final public void argumentos() throws ParseException {
    try {
      jj_consume_token(tABREPARENTESIS);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case tNOT:
      case tMAS:
      case tMENOS:
      case tTRUE:
      case tFALSE:
      case tENTACAR:
      case tCARAENT:
      case tABREPARENTESIS:
      case tIDENTIFICADOR:
      case tCONSTENTERA:
      case tCONSTCHAR:
      case tCONSTCAD:
        lista_expresiones();
        break;
      default:
        jj_la1[12] = jj_gen;
        ;
      }
      jj_consume_token(tCIERREPARENTESIS);
    } catch (Exception e) {
  minilengcompiler.errorSintactico(e, "argumentos");
    }
  }

// lista_expresiones ::= ...
  static final public void lista_expresiones() throws ParseException {
    try {
      expresion();
      label_6:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case tCOMA:
          ;
          break;
        default:
          jj_la1[13] = jj_gen;
          break label_6;
        }
        jj_consume_token(tCOMA);
        expresion();
      }
    } catch (Exception e) {
  minilengcompiler.errorSintactico(e, "lista_expresiones");
    }
  }

// expresión ::= expresión_simple (operador_relacional expresion_simple)?
  static final public void expresion() throws ParseException {
    try {
      expresion_simple();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case tMAYOR:
      case tMENOR:
      case tIGUAL:
      case tMAI:
      case tMEI:
      case tNI:
        operador_relacional();
        expresion_simple();
        break;
      default:
        jj_la1[14] = jj_gen;
        ;
      }
    } catch (Exception e) {
  minilengcompiler.errorSintactico(e, "expresion");
    }
  }

// operador_relacional ::= <tIGUAL>
// | <tMA>
// | <tME>
// | <tMEI>
// | <tMAI>
// | <tNI>
  static final public void operador_relacional() throws ParseException {
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case tIGUAL:
        jj_consume_token(tIGUAL);
        break;
      case tMENOR:
        jj_consume_token(tMENOR);
        break;
      case tMAYOR:
        jj_consume_token(tMAYOR);
        break;
      case tMEI:
        jj_consume_token(tMEI);
        break;
      case tMAI:
        jj_consume_token(tMAI);
        break;
      case tNI:
        jj_consume_token(tNI);
        break;
      default:
        jj_la1[15] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } catch (Exception e) {
  minilengcompiler.errorSintactico(e, "operacior_relacional");
    }
  }

//expresion_simple ::= (tMAS | tMENOS)? termino (operador_aditivo | or)? termino)*
// el signo se decide directamente en factor
  static final public void expresion_simple() throws ParseException {
    try {
      termino();
      label_7:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case tOR:
        case tMAS:
        case tMENOS:
          ;
          break;
        default:
          jj_la1[16] = jj_gen;
          break label_7;
        }
        operador_aditivo();
        termino();
      }
    } catch (Exception e) {
  minilengcompiler.errorSintactico(e, "expresion_simple");
    }
  }

//operador_aditivo ::= <tMAS>
// | <tMENOS>
// | <tOR>
  static final public void operador_aditivo() throws ParseException {
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case tMAS:
        jj_consume_token(tMAS);
        break;
      case tMENOS:
        jj_consume_token(tMENOS);
        break;
      case tOR:
        jj_consume_token(tOR);
        break;
      default:
        jj_la1[17] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } catch (Exception e) {
    minilengcompiler.errorSintactico(e, "operador_aditivo");
    }
  }

//termino ::= factor (operador_multiplicativo factor)*
  static final public void termino() throws ParseException {
    try {
      factor();
      label_8:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case tAND:
        case tMUL:
        case tDIVENT:
        case tDIVDECIMAL:
        case tMOD:
          ;
          break;
        default:
          jj_la1[18] = jj_gen;
          break label_8;
        }
        operador_multiplicativo();
        factor();
      }
    } catch (Exception e) {
  minilengcompiler.errorSintactico(e, "termino");
    }
  }

//operador_multiplicativo ::= <tMUL>
// | <tDIV>
// | <tMOD>
// | <tAND>
  static final public void operador_multiplicativo() throws ParseException {
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case tMUL:
        jj_consume_token(tMUL);
        break;
      case tDIVENT:
        jj_consume_token(tDIVENT);
        break;
      case tDIVDECIMAL:
        jj_consume_token(tDIVDECIMAL);
        break;
      case tMOD:
        jj_consume_token(tMOD);
        break;
      case tAND:
        jj_consume_token(tAND);
        break;
      default:
        jj_la1[19] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } catch (Exception e) {
  minilengcompiler.errorSintactico(e, "operador_multiplicativo");
    }
  }

// factor := ...
  static final public void factor() throws ParseException {
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case tMAS:
        jj_consume_token(tMAS);
        factor();
        break;
      case tMENOS:
        jj_consume_token(tMENOS);
        factor();
        break;
      case tNOT:
        jj_consume_token(tNOT);
        factor();
        break;
      case tABREPARENTESIS:
        jj_consume_token(tABREPARENTESIS);
        expresion();
        jj_consume_token(tCIERREPARENTESIS);
        break;
      case tENTACAR:
        jj_consume_token(tENTACAR);
        jj_consume_token(tABREPARENTESIS);
        expresion();
        jj_consume_token(tCIERREPARENTESIS);
        break;
      case tCARAENT:
        jj_consume_token(tCARAENT);
        jj_consume_token(tABREPARENTESIS);
        expresion();
        jj_consume_token(tCIERREPARENTESIS);
        break;
      case tIDENTIFICADOR:
        jj_consume_token(tIDENTIFICADOR);
        break;
      case tCONSTENTERA:
        jj_consume_token(tCONSTENTERA);
        break;
      case tCONSTCHAR:
        jj_consume_token(tCONSTCHAR);
        break;
      case tCONSTCAD:
        jj_consume_token(tCONSTCAD);
        break;
      case tTRUE:
        jj_consume_token(tTRUE);
        break;
      case tFALSE:
        jj_consume_token(tFALSE);
        break;
      default:
        jj_la1[20] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } catch (Exception e) {
  minilengcompiler.errorSintactico(e, "factor");
    }
  }

  static final public int one_line() throws ParseException {
    try {
      programa();
      jj_consume_token(0);
    } catch (Exception e) {
  minilengcompiler.errorSintactico(e, "reconociendo el programa");
    }
    //System.out.println("programa reconocido");
    {if (true) return 0;}
    throw new Error("Missing return statement in function");
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public minilengcompilerTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[21];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x0,0x0,0x0,0x0,0x0,0x0,0x0,0xc0440000,0x0,0xc0440000,0x0,0x100000,0x3008000,0x0,0x0,0x0,0x3004000,0x3004000,0x3c002000,0x3c002000,0x3008000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x1c,0x1c,0x40000,0x200,0x80000,0x20000,0x3,0x200000,0xb0000,0x200000,0x80000,0x0,0x1e801e0,0x40000,0xfc00,0xfc00,0x0,0x0,0x0,0x0,0x1e801e0,};
   }

  /** Constructor with InputStream. */
  public minilengcompiler(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public minilengcompiler(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new minilengcompilerTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public minilengcompiler(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new minilengcompilerTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public minilengcompiler(minilengcompilerTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(minilengcompilerTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 21; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[59];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 21; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 59; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}
