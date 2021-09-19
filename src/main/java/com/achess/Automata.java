/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.achess;

import java.util.ArrayList;

/**
 *
 * @author achess
 */

public class Automata {    
    private ArrayList<Token> tokens = new ArrayList<>();
    private ArrayList<Token> errors = new ArrayList<>();
    private static Automata automata;      
    private Estado s0;
    private Automata(){
        s0 = new Estado();                
        Estado s1 = new Estado(TokenType.IDENTIFICADOR);
        s0.addNext(Alfabeto.LETRA, s1);
        s1.addNext(Alfabeto.LETRA, s1);
        
    }
    
    public static Automata getAutomata(){
        if(automata == null){
            automata = new Automata();
        }
        return automata;
    }        
    
    public void analizar(String text){
        tokens.clear();
        errors.clear();
        int index = 0;
        int fila = 1;
        int columna = 1;
        String lexema = "";
        Estado aux1, aux2;
        aux1 = s0;
        while(index < text.length()){      
            char value = text.charAt(index);
            char chr = Alfabeto.obtenerAlfabeto(value);            
            if(chr == Alfabeto.SEPARADOR.getId()){                                    
                if(aux1.isAceptacion()){
                    tokens.add(new Token(aux1.getTokenType(), lexema, fila, columna));
                }
                else{
                    lexema = lexema + " Se esperaba "+ aux1.nextValues();
                    errors.add(new Token(aux1.getTokenType(), lexema, fila, columna));
                }
                aux1 = s0;
                lexema = "";                                         
                if(value == '\n'){
                    fila++;
                    columna = 0;
                }
            }
            else{
                aux2 = aux1.getNext(chr);
                lexema += value;
                if(aux2 == null){
                    String message = "";
                    if(aux1 == s0){
                        message = "Caracter desconocido";
                    }
                    else{
                       message = "Se esperaba " + aux1.nextValues();                                             
                    }
                    lexema = lexema + " " + message;
                    errors.add(new Token(TokenType.ERROR, lexema, fila, columna));
                    aux1 = s0;
                    lexema = "";
                }
                else if (index == text.length() - 1){
                    if(aux2.isAceptacion()){
                        tokens.add(new Token(aux1.getTokenType(), lexema, fila, columna));
                    }
                    else{
                        lexema = lexema + " Se esperaba "+ aux2.nextValues();
                        errors.add(new Token(aux1.getTokenType(), lexema, fila, columna));
                    }
                }
                else{
                    aux1 = aux2;
                }
            }
            columna++;
            index++;
        }
    }
    
    public ArrayList getTokens(){
        return tokens;
    }
    
    public ArrayList getErrors(){
        return errors;
    }
    
}
