package imprimirzebra;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.standard.PrinterName;

/**
 * Clase creada con la finalidad de imprimir una etiqueta en Impresora Zebra
 *
 * @author Mariuxi Fecha: 05/06/2015
 */
public class PrintEtiqueta {

    /*Método que recibe como parametros los datos a imprimir en la etiqueta, a más del código de barras*/
    public static void imprimirEtiq(String num_fact, String codigo, String name_provee, String date_compra, String name_impr) throws PrintException, FileNotFoundException, IOException {

    // Se asigna en una variable el nombre de la impresora que se recibe como parámetro
        String printName = name_impr;

    // Se crea un arreglo para obtener todas las impresoras instaladas en el equipo
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);

    // Se define la impresora con la que se va a trabajar           
        AttributeSet aset = new HashAttributeSet();
        aset.add(new PrinterName(printName, null));
        services = PrintServiceLookup.lookupPrintServices(null, aset);

    // Se crea las lineas de comandos que se envian a imprimir
//        String zplCommand = "N\n"
//                + "B220,40,0,1,2,4,100,N,\"test3\"\n"
//                + "A220,150,0,1,1,1,N,\"\"+codigo+\"\n"
//                + "P1\n";

     String zplCommand = "^XA"   
                        + "^FO300, 18^ADN, 11, 7^FD Nro. Factura "+ num_fact+"^FS"
                        + "^FO320, 46^ADN, 11, 7^FD" +name_provee+"^FS   "
                        //+ "^FO320, 74^ADN, 11, 7^FD "+date_compra+ " ^FS   "
                        + "^FO230, 102^ADN, 11, 7 "
                        + "^BCN, 60, Y, Y, N^FD >"+codigo+" ^FS "
                        + "^XZ "; 
    // Se convierte el comando a bytes
        byte[] by = zplCommand.getBytes();
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        Doc doc = new SimpleDoc(by, flavor, null);
    // Se crea el printjob
        DocPrintJob job = services[0].createPrintJob();
    // Se imprime
        job.print(doc, null);

    }

}
