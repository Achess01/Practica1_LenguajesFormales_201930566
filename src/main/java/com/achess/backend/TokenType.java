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
public enum TokenType {
    IDENTIFICADOR("Identificador"), NUMERO("Número"), DECIMAL("Decimal"), OPERADOR("Operadores aritméticos"),
    PUNTUACIÓN("Signos de puntuación"), AGRUPACION("Signos de agrupación"), ERROR("Error");
    
    private String type;    
    private TokenType(String type){
        this.type = type;
    }
    
    public String getType(){
        return this.type;
    }
}
