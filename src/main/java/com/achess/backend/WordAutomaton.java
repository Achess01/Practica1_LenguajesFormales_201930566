/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    public WordAutomaton(String text){
        words = new ArrayList<Token>();
        int len = text.length();
        State aux;
        State sf = new State(TokenType.PALABRA);
        aux = sf;        
        for(int x = text.length() - 1; x >= 0; x--){
            State newState = new State();
            newState.addNext(text.charAt(x), aux);
            aux = newState;
        }
        s0 = aux;        
    }
            
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
            if(aux == null){
                aux = s0;
                lexeme = "";
            }
            else if(aux.isAcceptState()){
                addToken(lexeme, row, column);
                aux = s0;
                lexeme = "";
            }                  
            if(chr == '\n'){
                row++;
                column = 0;
            }
            column++;
            index++;
        }
    }
    
    public void addToken(String lexeme, int row, int column){        
        words.add(new Token(TokenType.PALABRA, lexeme, row, column));
    }

    public ArrayList<Token> getWords(){
        return words;
    }       
}
