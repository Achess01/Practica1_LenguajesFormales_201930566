package com.achess.backend;

import java.util.ArrayList;

/**
 *
 * @author achess
 */
public class WordAutomaton implements Automatons{
    private ArrayList<Token> words;
    private static WordAutomaton wordAutomaton;
    private State s0;    
    
    public WordAutomaton(String word){        
        words = new ArrayList<Token>();
        int len = word.length();
        State aux;        
        State sf = new State(TokenType.PALABRA, len);
        aux = sf;        
        for(int x = len - 1; x >= 0; x--){
            State newState = new State(x);
            newState.addNext(word.charAt(x), aux);
            aux = newState;
        }
        s0 = aux;        
    }
    /**     
     * @param text Es el texto a analizar
     */
    public void analize(String text){
        words.clear();
        String lexeme = "";
        int row = 1; 
        int column = 1;
        int index = 0;
        State aux = s0;
        while(index < text.length()){
            char chr = text.charAt(index);
            aux = aux.getNext(chr);
            lexeme += chr;
            index++;            
            if(aux == null){
                aux = s0;
                lexeme = "";
            }
            else if(aux.isAcceptState()){
                addToken(lexeme, row, column, index);
                aux = s0;
                lexeme = "";
            }                  
            if(chr == '\n'){
                row++;
                column = 0;
            }            
            column++;
        }
    }
    
    public void addToken(String lexeme, int row, int column, int index){        
        words.add(new Token(TokenType.PALABRA, lexeme, row, column, index));
    }

    public ArrayList<Token> getWords(){
        return words;
    }       
}
