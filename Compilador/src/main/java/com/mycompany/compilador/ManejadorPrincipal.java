package com.mycompany.compilador;

import Analizador.*;
import Archivos.*;
import Frontend.*;
import Token.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;

public class ManejadorPrincipal {

    private buscador buscador;
    private GuardarTexto guardar;
    private AbrirTexto lector;
    private File fichero;
    private ArrayList<Token> listaTokens;
    private ArrayList<Errores> listaErrores;
    private Analizador2 analizar;
    private ReporteTokens report;
    private Lexemas lexemas;
    private ContadorLexemas contador;
    private ArrayList<Lexemas> listaLexemas;

    public ManejadorPrincipal() {
        this.buscador = new buscador();
        this.buscador.setVisible(true);
        this.guardar = new GuardarTexto();
        this.lector = new AbrirTexto();
        this.listaTokens = new ArrayList<>();
        this.listaErrores = new ArrayList<>();
        this.analizar = new Analizador2();
        this.report = new ReporteTokens();
        this.contador = new ContadorLexemas();

        this.buscador.getAnalizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                if (buscador.getAreaTexto().getText().length() != 0) {
                    analizar.analizar(buscador.getAreaTexto(), listaErrores, listaTokens);
                    mostrarErrores();
                    if (listaErrores == null || listaErrores.size() == 0) {
                        report.setVisible(true);
                        reporteTokens();
                        report.getjComboBox1().removeAllItems();
                        for (Token o : listaTokens) {
                            report.getjComboBox1().addItem(o.getLexema());
                        }
                        lexemas = new Lexemas(listaTokens);
                        listaLexemas = lexemas.getListaLexemas();

                    }
                    listaErrores.clear();
                    listaTokens.clear();
                } else {
                    JOptionPane.showMessageDialog(null, "Recuerda que debes subir un archivos para iniciar");
                }

            }
        });
        this.buscador.getSubirDoc().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser fileChosser = new JFileChooser();
                int seleccion = fileChosser.showOpenDialog(buscador.getSubirDoc());
                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    fichero = fileChosser.getSelectedFile();
                    try {
                        lector.leerFichero(fichero, buscador.getAreaTexto());
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error al leer el archivo");
                    }
                }
            }
        });

        this.buscador.getGuardar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String documento = buscador.getAreaTexto().getText();
                guardar.GuardarArchivos(fichero, documento);
            }
        });

        this.buscador.getBuscarP().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    Bucador look = new Bucador();
                    look.Buscador(buscador.getAreaTexto());
                } catch (BadLocationException ex) {
                    Logger.getLogger(ManejadorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        this.report.getGenerar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String item = (String) report.getjComboBox1().getSelectedItem();
                analizar.analizador2(item, report.getjTable2());

            }
        });
        this.report.getLexema().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                contador.setVisible(true);
                llenarLexemas();
            }
        });

    }

    public void mostrarErrores() {
        DefaultTableModel modelo = (DefaultTableModel) buscador.getjTable1().getModel();
        modelo.setRowCount(0);
        for (Errores j : listaErrores) {
            Object[] row = new Object[3];
            row[0] = j.getCadena();
            row[1] = j.getColumna();
            row[2] = j.getFila();
            modelo.addRow(row);
        }
    }

    public void reporteTokens() {
        DefaultTableModel modelo = (DefaultTableModel) report.getjTable1().getModel();
        modelo.setRowCount(0);
        for (Token T : listaTokens) {
            Object[] row = new Object[5];
            row[0] = T.getTipoToken();
            row[1] = T.getLexema();
            row[2] = T.getFila();
            row[3] = T.getColumna();
            modelo.addRow(row);
        }
    }

    public void llenarLexemas() {
        DefaultTableModel modelo = (DefaultTableModel) contador.getjTable1().getModel();
        modelo.setRowCount(0);
        for (int i = 0; i < this.listaLexemas.size(); i++) {
        modelo.addRow(listaLexemas.get(i).getArray());
        }    
    }

}
