/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.achess;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author achess
 */
public class Estado {
    private boolean aceptacion;    
    private TokenType tokenType;    
    HashMap<Character,Estado> siguientes = new HashMap<Character, Estado>();
    ArrayList<Alfabeto> alfabetoSiguiente = new ArrayList();
    public Estado() {
        this.aceptacion = false;        
        this.tokenType = TokenType.ERROR;
    }
    
    public Estado(TokenType tokenType){        
        this.aceptacion = true;
        this.tokenType = tokenType;
    }
    
    public void addNext(Alfabeto alfabeto, Estado estado){
        siguientes.put(alfabeto.getId(), estado);
        alfabetoSiguiente.add(alfabeto);
    }
    
    public Estado getNext(char alfabeto){
        return siguientes.get(alfabeto);        
    }
    
    public boolean isAceptacion(){
        return this.aceptacion;
    }

    public TokenType getTokenType() {
        return tokenType;
    }
        
    
    public String nextValues(){
        String sig = "";
        for(Alfabeto alfabeto : alfabetoSiguiente){
            sig += alfabeto.toString() + " o ";
        }
        int last = sig.lastIndexOf("o");
        if(last > 0){
            sig = sig.substring(0, last - 1);
        }
        return sig;
    }
}
