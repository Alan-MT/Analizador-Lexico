package Archivos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;

public class GuardarTexto {

    public void GuardarArchivos(File archivos, String documento) {

        FileOutputStream salida = null;
        try {
            salida = new FileOutputStream(archivos);
            byte[] bytxt = documento.getBytes();
            salida.write(bytxt);
            JOptionPane.showMessageDialog(null, "Guardado");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                salida.close();
            } catch (IOException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }

}
