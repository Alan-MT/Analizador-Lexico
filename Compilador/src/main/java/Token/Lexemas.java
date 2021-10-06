package Token;

import java.util.ArrayList;

/**
 *
 * @author ALAN
 */
public class Lexemas{
    

    private String cadena;
    private TipoToken tipoToken;
    private int repeticiones;
    private ArrayList<Token> listaTokens;
    private ArrayList<Lexemas> Lexemas;

    public Lexemas(ArrayList<Token> listaTokens) {
        this.listaTokens = listaTokens;
        Lexemas = new ArrayList<>();
        crearListadoLexemas();
    }

    private Lexemas(String cadena, TipoToken token) {
        this.cadena = cadena;
        this.tipoToken = token;
        this.repeticiones = 0;
    }
    
    public Object[] getArray() {
        
        Object[] lexema = new Object[4];
        lexema[0] = this.getCadena();
        lexema[1] = this.getToken();
        lexema[2] = this.getRepeticiones();
        
        return lexema;
    }

    private void crearListadoLexemas() {
        
        agregarLexema(listaTokens.get(0));
        Lexemas.get(0).Repeticiones();

        for (int i = 1; i < listaTokens.size(); i++) {
            
            int contador = 0;
            for (int j = 0; j < Lexemas.size(); j++) {

                if (igualar(listaTokens.get(i).getLexema(), Lexemas.get(j).getCadena())) {
                    Lexemas.get(j).Repeticiones();
                    break;
                } else {
                    contador++;
                }
            }

            if (contador == Lexemas.size()) {
                agregarLexema(listaTokens.get(i));
                Lexemas.get(Lexemas.size()-1).Repeticiones();
            }
        }
        
    }

    

    private void agregarLexema(Token token) {
        String lexema = token.getLexema();
        TipoToken tipoTk = token.getTipoToken();
        Lexemas.add(new Lexemas(lexema, tipoTk));
    }
    
        public boolean igualar(String cadena1, String cadena2) {
        
        if (cadena1.length() == cadena2.length()) {            
            for (int i = 0; i < cadena1.length(); i++) {                
                if (cadena1.charAt(i) != cadena2.charAt(i)) {                    
                    return false;
                }
            }
            return true;            
        } else {
            return false;
        }
        
    }

    public ArrayList<Lexemas> getListaLexemas() {
        return Lexemas;
    }

    public void setListaLexemas(ArrayList<Lexemas> listaLexemas) {
        this.Lexemas = listaLexemas;
    }
    
    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public TipoToken getToken() {
        return tipoToken;
    }

    public void setToken(TipoToken token) {
        this.tipoToken = token;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void Repeticiones() {
        this.repeticiones++;
    }

}