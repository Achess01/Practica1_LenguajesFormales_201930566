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
public class State{
    private boolean acceptState;    
    private boolean finalState = false;
    private TokenType tokenType;    
    HashMap<Character,State> nextStates = new HashMap<Character, State>();
    ArrayList<Alphabet> nextAlphabet = new ArrayList();
    private String actualMovement;
    private int name;
    
    public State(int name) {
        this.name = name;
        this.acceptState = false;        
        this.tokenType = TokenType.ERROR;
    }
    
    public State(TokenType tokenType, int name){        
        this.name = name;
        this.acceptState = true;
        this.tokenType = tokenType;
    }

    public int getName() {
        return name;
    }
   
    public void addNext(Alphabet alfabeto, State estado){
        nextStates.put(alfabeto.getId(), estado);
        nextAlphabet.add(alfabeto);
    }
    
    public void addNext(char chr, State state){
        nextStates.put(chr, state);        
    }
    
    public State getNext(char alfabeto){
        return nextStates.get(alfabeto);        
    }
    
    public State getNext(char alfabeto, char chr){
        State nextState = nextStates.get(alfabeto);
        String state = "ERROR";        
        if(nextState != null){
            state = nextState.getName() + "";
        }
        String movement = "Me moví del estado " + this.getName() + " al estado " + state + " usando '" + chr + "'";
        this.setActualMovement(movement);
        return nextState;
    }

    public String getActualMovement() {
        return actualMovement;
    }

    private void setActualMovement(String actualMovement) {
        this.actualMovement = actualMovement;
    }
    
    
    
    public boolean isAcceptState(){
        return this.acceptState;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public boolean isFinalState() {
        return finalState;
    }

    public void setFinalState(boolean finalState) {
        this.finalState = finalState;
    }
    
        
    
    public String nextValues(){
        String sig = "Se esperaba ";
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
