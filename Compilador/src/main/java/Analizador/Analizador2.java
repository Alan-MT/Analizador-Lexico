/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analizador;

import Token.*;
import java.util.ArrayList;
import javax.security.auth.callback.TextOutputCallback;
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
        texto = entrada.getText();
        int fila = 0;
        int columna = 0;

        for (int i = 0; i < texto.length(); i++) {

            if ((texto.charAt(i) != ' ') && (texto.charAt(i) == '\n')) {
                fila++;
                columna = 0;
            }
            columna++;

            if ((texto.charAt(i) != ' ') && (texto.charAt(i) != '\n')) {
                System.out.print("S" + estadoActual + " -> ");
                estadoActual = trans.nuevoEstado(texto.charAt(i), estadoActual);
                cadena += texto.charAt(i);
                System.out.println(texto.charAt(i) + " -> S" + estadoActual);

                if (estadoActual == -1) {
                    System.out.println(cadena + " -> Error lexico");
                    Errores er = new Errores(cadena, fila + 1, columna - 1);
                    listaErrores.add(er);
                    estadoActual = 0;
                    cadena = "";
                } else if (estadoActual == -5) {
                    System.out.println(cadena + " -> Error: No se reconoce el simbolo (" + texto.charAt(i) + ")");
                    listaErrores.add(new Errores(cadena, fila + 1, columna - 1));
                    estadoActual = 0;
                    cadena = "";
                }

            } else {

                if ((i > 0) && (estadoActual == 0)) {
                    i--;
                    fila--;
                } else {

                    System.out.println(cadena);

                    for (TipoToken tk : TipoToken.values()) {
                        if (tk.getEstadoAcept() == estadoActual) {
                            //System.out.println(tk.name() + " <- otra forma -> " + tk.toString());
                            Token nuevoToken = new Token(cadena, tk, fila + 1, columna + 1);
                            listaTokens.add(nuevoToken);
                            System.out.println("Lexema: " + nuevoToken.getLexema() + " Token: "
                                    + nuevoToken.getTipoToken().name());

                            System.out.println();

                            estadoActual = 0;
                            cadena = "";
                        }
                    }

                }
            }

        }

        System.out.println(texto);

        System.out.println(
                "\nCantidad de Tokens: " + listaTokens.size());
        for (Token tok : listaTokens) {
            System.out.println(tok);
        }

        System.out.println(
                "\nCantidad de Errores: " + listaErrores.size());
        for (Errores e : listaErrores) {
            System.out.println(e);
        }

        System.out.println();
    }
    
    public void analizador2(String texto, JTable tabla){
        int estadoActual = 0;
        int estadoSiguiente =0;
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
