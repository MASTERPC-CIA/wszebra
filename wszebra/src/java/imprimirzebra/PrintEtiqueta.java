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
    public static void imprimirEtiq(String codigo, String name_produc, String precio_produc, String date_compra, String name_impr) throws PrintException, FileNotFoundException, IOException {

    // Se asigna en una variable el nombre de la impresora que se recibe como parámetro
        String printName = name_impr;

    // Se crea un arreglo para obtener todas las impresoras instaladas en el equipo
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);

    // Se define la impresora con la que se va a trabajar           
        AttributeSet aset = new HashAttributeSet();
        aset.add(new PrinterName(printName, null));
        services = PrintServiceLookup.lookupPrintServices(null, aset);
        precio_produc="260";
    // Se crea las lineas de comandos que se envian a imprimir
        String zplCommand ="";
        if(name_produc.length()>27){
            zplCommand = "N\n"
                + "A10,1,0,3,1,1,N,\""+date_compra+"\"\n"
                + "B10,31,0,1,3,7,40,N,\""+codigo+"\"\n"
                + "A10,80,0,1,1,1,N,\""+name_produc.substring(0, 27)+"\"\n"
                + "A10,110,0,1,1,1,N,\""+name_produc.substring(27)+"\"\n"
                + "A10,140,0,1,2,2,N,\"P.V.P. "+precio_produc+"\"\n"
                + "P1\n";
        }else{
            zplCommand = "N\n"
                + "A10,1,0,3,1,1,N,\""+date_compra+"\"\n"
                + "B10,31,0,1,3,7,40,N,\""+codigo+"\"\n"
                + "A10,80,0,1,1,1,N,\""+name_produc+"\"\n"
                + "A10,120,0,1,2,2,N,\"P.V.P. "+precio_produc+"\"\n"
                + "P1\n";
            
        }
        

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
