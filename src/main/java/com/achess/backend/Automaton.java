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

public class Automaton {    
    private ArrayList<Token> tokens = new ArrayList<>();
    private ArrayList<Token> errors = new ArrayList<>();
    private static Automaton automata;      
    private State s0;
    private Automaton(){
        s0 = new State();                
        State s1 = new State(TokenType.IDENTIFICADOR);
        State s2 = new State(TokenType.NUMERO);
        State s3 = new State();
        State s4 = new State(TokenType.DECIMAL);
        State s5 = new State(TokenType.PUNTUACIÓN);
        State s6 = new State(TokenType.OPERADOR);
        State s7 = new State(TokenType.AGRUPACION);
        //Estados desde s0
        s0.addNext(Alphabet.LETRA, s1);
        s0.addNext(Alphabet.DIGITO, s2);
        s0.addNext(Alphabet.PUNTO, s5);
        s0.addNext(Alphabet.PUNTUACION, s5);
        s0.addNext(Alphabet.OPERADOR, s6);
        s0.addNext(Alphabet.AGRUPACION, s7);
        //Estados desde s1
        s1.addNext(Alphabet.LETRA, s1);
        s1.addNext(Alphabet.LETRA, s1);
        s1.addNext(Alphabet.DIGITO, s1);
        //Estados desde s2
        s2.addNext(Alphabet.DIGITO, s2);
        s2.addNext(Alphabet.PUNTO, s3);
        //Estados desde s3
        s3.addNext(Alphabet.DIGITO, s4);
        //Estados desde s4
        s4.addNext(Alphabet.DIGITO, s4);
        //Estados desde s5
        s5.addNext(Alphabet.PUNTO, s5);
        s5.addNext(Alphabet.PUNTUACION, s5);
        //Estados desde s6
        s6.addNext(Alphabet.OPERADOR, s6);
        //Estados desde s7
        s7.addNext(Alphabet.AGRUPACION, s7);
        
        
    }
    
    public static Automaton getAutomaton(){
        if(automata == null){
            automata = new Automaton();
        }
        return automata;
    }       
    
    private void addToken(){
        
    }
    
    private void addError(TokenType tokenType, String lexeme, int row, int column){
        
    }
    
    public void analize(String text){
        tokens.clear();
        errors.clear();
        int index = 0;
        int row = 1;
        int column = 1;
        String lexeme = "";
        State aux1;
        State aux2;
        aux1 = s0;
        while(index < text.length()){      
            char value = text.charAt(index);
            char chr = Alphabet.getAlpabhet(value);            
            if(chr == Alphabet.SEPARADOR.getId()){                                    
                if(aux1.isAcceptState()){
                    tokens.add(new Token(aux1.getTokenType(), lexeme, row, column));
                }
                else{
                    lexeme = lexeme + " Se esperaba "+ aux1.nextValues();
                    errors.add(new Token(aux1.getTokenType(), lexeme, row, column));
                }
                aux1 = s0;
                lexeme = "";                                         
                if(value == '\n'){
                    row++;
                    column = 0;
                }
            }
            else{
                aux2 = aux1.getNext(chr);
                lexeme += value;
                if(aux2 == null){
                    String message = "";
                    if(aux1 == s0){
                        message = "Caracter desconocido";
                    }
                    else{
                       message = "Se esperaba " + aux1.nextValues();                                             
                    }
                    lexeme = lexeme + " " + message;
                    errors.add(new Token(TokenType.ERROR, lexeme, row, column));
                    aux1 = s0;
                    lexeme = "";
                }
                else if (index == text.length() - 1){
                    if(aux2.isAcceptState()){
                        tokens.add(new Token(aux1.getTokenType(), lexeme, row, column));
                    }
                    else{
                        lexeme = lexeme + " Se esperaba "+ aux2.nextValues();
                        errors.add(new Token(aux1.getTokenType(), lexeme, row, column));
                    }
                }
                else{
                    aux1 = aux2;
                }
            }
            column++;
            index++;
        }
    }
    
    public ArrayList<Token> getTokens(){
        return tokens;
    }
    
    public ArrayList<Token> getErrors(){
        return errors;
    }
    
}
