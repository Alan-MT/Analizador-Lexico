/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.compilador;

import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

/**
 *
 * @author alanm
 */
public class Bucador {

    public void Buscador(JTextArea textArea) throws BadLocationException {
        String seleccion = JOptionPane.showInputDialog("Ingrese lo que desea buscar", JOptionPane.QUESTION_MESSAGE);
//        String ptxt = textArea.getText();
        DefaultHighlighter.DefaultHighlightPainter highlightPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN); //pinta el fondo de una pantalla
        Highlighter h = textArea.getHighlighter();
        h.removeAllHighlights();
        String text = textArea.getText();
        
        int contador, indice;
        for (int i = 0; i < text.length()+1; i++) {
            contador = 0;
            indice = i;
            String palabra = "";
            int inicio[] = new int [seleccion.length()];
            for (int j = 0; j < seleccion.length(); j++) {
                if (text.charAt(indice) == seleccion.charAt(j)) {
                    palabra = (palabra + text.charAt(indice));
                    inicio[j] = indice;
                    if (seleccion.equals(palabra)) {
                    h.addHighlight(inicio[0], inicio[seleccion.length()-1]+1, highlightPainter);
                    }

                    contador++;
                }
                indice++;
            }
            
            
        }
    }

}
