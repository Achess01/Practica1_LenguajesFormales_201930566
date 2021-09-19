/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.achess;

/**
 *
 * @author achess
 */
public class Token {
    private TokenType type;
    private String text;
    private int fila;
    private int columna;

    public Token(TokenType type, String text, int fila, int columna) {
        this.type = type;
        this.text = text;
        this.fila = fila;
        this.columna = columna;
    }

    @Override
    public String toString() {
        return "Token{" + "type=" + type + ", text=" + text + ", fila=" + fila + ", columna=" + columna + '}';
    }
    

  
            
}
