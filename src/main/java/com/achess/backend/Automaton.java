package com.achess.backend;

import java.util.ArrayList;

/**
 *
 * @author achess
 */

public class Automaton implements Automatons{    
    private ArrayList<Token> tokens;
    private ArrayList<Token> errors;
    private static Automaton automaton;      
    private State s0;
    private Automaton(){
        tokens = new ArrayList<Token>();
        errors = new ArrayList<Token>();        
        s0 = new State(0);                
        State s1 = new State(TokenType.IDENTIFICADOR, 1);
        State s2 = new State(TokenType.NUMERO, 2);
        State s3 = new State(3);
        State s4 = new State(TokenType.DECIMAL, 4);
        State s5 = new State(TokenType.PUNTUACIÓN, 5);
        State s6 = new State(TokenType.OPERADOR, 6);
        State s7 = new State(TokenType.AGRUPACION, 7);
        s5.setFinalState(true);
        s6.setFinalState(true);
        s7.setFinalState(true);
        //Estados desde s0
        s0.addNext(Alphabet.LETRA, s1);
        s0.addNext(Alphabet.DIGITO, s2);
        s0.addNext(Alphabet.PUNTO, s5);
        s0.addNext(Alphabet.PUNTUACION, s5);
        s0.addNext(Alphabet.OPERADOR, s6);
        s0.addNext(Alphabet.AGRUPACION, s7);
        //Estados desde s1
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
        //s5.addNext(Alphabet.PUNTO, s5);
        //s5.addNext(Alphabet.PUNTUACION, s5);
        //Estados desde s6
        //s6.addNext(Alphabet.OPERADOR, s6);
        //Estados desde s7
        //s7.addNext(Alphabet.AGRUPACION, s7);
        
        
    }
    
    public static Automaton getAutomaton(){
        if(automaton == null){
            automaton = new Automaton();
        }
        return automaton;
    }       
    
    private void addToken(TokenType tokenType, String lexeme, int row, int column, int index, String movementes){        
        Token tk = new Token(tokenType, lexeme, row, column, index);
        tk.setMovements(movementes);
        tokens.add(tk);
    }    
    
    private void addToken(String lexeme, int row, int column, String description, int index, String movementes){
        Token tk = new Token(TokenType.ERROR, lexeme, row, column, description, index);
        tk.setMovements(movementes);
        errors.add(tk);
    }
    
    public void analize(String text){
        tokens.clear();
        errors.clear();
        int index = 0;
        int row = 1;
        int column = 1;
        String lexeme = "";
        String movements = "";
        State aux1;
        State aux2;
        aux1 = s0;
        while(index < text.length()){      
            char value = text.charAt(index);
            char chr = Alphabet.getAlpabhet(value);                
            if(chr == Alphabet.SEPARADOR.getId()){                                    
                if(aux1.isAcceptState()){
                    this.addToken(aux1.getTokenType(), lexeme, row, column,index, movements);
                }
                else if(aux1 != s0){
                    String des = aux1.nextValues();                    
                    this.addToken(lexeme, row, column, des,index, movements);
                }
                aux1 = s0;
                lexeme = "";
                movements = "";
                if(value == '\n'){
                    row++;
                    column = 0;
                }
            }
            else{
                aux2 = aux1.getNext(chr, value);
                movements += aux1.getActualMovement() + '\n';
                lexeme += value;
                if(aux2 == null){
                    String des = aux1.nextValues();                                                                 
                    this.addToken(lexeme, row, column+1, des,index+1, movements);
                    aux1 = s0;
                    lexeme = "";
                    movements = "";
                }
                else if (index == text.length() - 1 || aux2.isFinalState()){
                    if(aux2.isAcceptState()){
                        this.addToken(aux2.getTokenType(), lexeme, row, column+1,index+1, movements);
                    }
                    else{
                        String des = aux2.nextValues();                        
                        this.addToken(lexeme, row, column+1, des,index+1, movements);
                    }
                    aux1 = s0;
                    lexeme = "";
                    movements = "";
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
