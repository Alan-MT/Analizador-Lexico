/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Archivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JTextArea;

/**
 *
 * @author alanm
 */
public class AbrirTexto {

    //File=archivo
//FileReader necesita a un archivo para poder leerlo
//BufferedReader lee el texto del archivo
    public void leerFichero(File archivo, JTextArea text) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);
        String linea;
        while ((linea = br.readLine()) != null) {
            //poner el operador de tokens para verificacion
            text.append("\n" + linea);
        }
        text.setEditable(false);
        fr.close();
        
    }
}
