package Analizador;

import Token.*;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alanm
 */
public class Analizador2 {

    private Transicion trans;

    public Analizador2() {
        this.trans = new Transicion();
    }

    public void analizar(JTextArea entrada, ArrayList<Errores> listaErrores, ArrayList<Token> listaTokens) {

        String texto = "", cadena = "";
        int estadoActual = 0;
        texto = entrada.getText()+" ";
        int fila = 0;
        int columna = 0;

        for (int i = 0; i < texto.length(); i++) {

            if ((texto.charAt(i) != ' ') && (texto.charAt(i) != '\n')) {
                estadoActual = trans.nuevoEstado(texto.charAt(i), estadoActual);
                cadena += texto.charAt(i);

                if (estadoActual == -1) {
                    Errores er = new Errores(cadena, fila + 1, columna - 1);
                    listaErrores.add(er);
                    estadoActual = 0;
                    cadena = "";
                } else if (estadoActual == -5) {
                    listaErrores.add(new Errores(cadena, fila + 1, columna - 1));
                    estadoActual = 0;
                    cadena = "";
                }

            } else {

                if ((i > 0) && (estadoActual == 0)) {
                    i--;
                    fila--;
                } else {

                    for (TipoToken tk : TipoToken.values()) {
                        if (tk.getEstadoAcept() == estadoActual) {
                            Token nuevoToken = new Token(cadena, tk, fila + 1, columna + 1);
                            listaTokens.add(nuevoToken);

                            estadoActual = 0;
                            cadena = "";
                        }
                    }

                }
            }
            if ((texto.charAt(i) != ' ') && (texto.charAt(i) == '\n')) {
                fila++;
                columna = 0;
            }
            columna++;

        }

    }

    public void analizador2(String texto, JTable tabla) {
        int estadoActual = 0;
        int estadoSiguiente = 0;
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
        for (int i = 0; i < texto.length(); i++) {
            Object[] row = new Object[5];
            row[0] = estadoActual;
            row[1] = texto.charAt(i);
            estadoSiguiente = trans.nuevoEstado(texto.charAt(i), estadoActual);
            row[2] = estadoSiguiente;
            modelo.addRow(row);
            estadoActual = estadoSiguiente;
        }

    }

}
