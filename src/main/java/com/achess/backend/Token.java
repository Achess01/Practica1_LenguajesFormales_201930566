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
    private String lexeme;
    private int row;
    private int column;
    private String description;

    public Token(TokenType type, String text, int row, int column) {
        this.type = type;
        this.lexeme = text;
        this.row = row;
        this.column = column;
        this.description = type.getType();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Token{" + "type=" + type + ", text=" + lexeme + ", row=" + row + ", column=" + column + ", description=" + description + '}';
    }          
}
