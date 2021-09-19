/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.achess.backend;

/**
 *
 * @author achess
 */
public class Token {
    private TokenType type;
    private String text;
    private int row;
    private int column;

    public Token(TokenType type, String text, int row, int column) {
        this.type = type;
        this.text = text;
        this.row = row;
        this.column = column;
    }

    @Override
    public String toString() {
        return "Token{" + "type=" + type + ", text=" + text + ", fila=" + row + ", columna=" + column + '}';
    }
    

  
            
}
