/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.achess.backend;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author achess
 */
public class State {
    private boolean finalState;    
    private TokenType tokenType;    
    HashMap<Character,State> nextStates = new HashMap<Character, State>();
    ArrayList<Alphabet> nextAlphabet = new ArrayList();
    public State() {
        this.finalState = false;        
        this.tokenType = TokenType.ERROR;
    }
    
    public State(TokenType tokenType){        
        this.finalState = true;
        this.tokenType = tokenType;
    }
    
    public void addNext(Alphabet alfabeto, State estado){
        nextStates.put(alfabeto.getId(), estado);
        nextAlphabet.add(alfabeto);
    }
    
    public State getNext(char alfabeto){
        return nextStates.get(alfabeto);        
    }
    
    public boolean isFinalState(){
        return this.finalState;
    }

    public TokenType getTokenType() {
        return tokenType;
    }
        
    
    public String nextValues(){
        String sig = "";
        for(Alphabet alfabeto : nextAlphabet){
            sig += alfabeto.toString() + " o ";
        }
        int last = sig.lastIndexOf("o");
        if(last > 0){
            sig = sig.substring(0, last - 1);
        }
        return sig;
    }
}
