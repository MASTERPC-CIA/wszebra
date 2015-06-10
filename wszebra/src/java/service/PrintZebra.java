
package service;

import imprimirzebra.PrintEtiqueta;
import java.io.IOException;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.print.PrintException;

/**
 *
 * @author Mariuxi
 */
@WebService(serviceName = "PrintZebra")
public class PrintZebra {
   
    /**
     * This is a sample web service operation
     *
     *
     * @param num_fact
     * @param cod_produc
     * @param name_provee
     * @param date_compra
     * @param name_impr
     * @return 
     * @throws javax.print.PrintException
     * @throws java.io.IOException
     */
    @WebMethod(operationName = "print_zebra")
    public String print_zebra(
            @WebParam(name = "num_fact") String num_fact,
            @WebParam(name = "cod_produc") String cod_produc,
            @WebParam(name = "name_provee") String name_provee,
            @WebParam(name = "date_compra") String date_compra,
            @WebParam(name="name_impr") String name_impr) throws PrintException, IOException {
        // Se crea un objeto de la clase 
        PrintEtiqueta.imprimirEtiq(num_fact, cod_produc, name_provee, date_compra, name_impr);
        return "OK";
    }

}
