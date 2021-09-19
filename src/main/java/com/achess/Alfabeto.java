/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.achess;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author achess
 */
public enum Alfabeto {      
   LETRA('L'), DIGITO('D'), PUNTUACION('P'), OPERADOR('O'), AGRUPACION('A'), SEPARADOR('S'), NULO('N'), PUNTO('.');
   private char id;
   private Alfabeto(char id){
       this.id = id;
   }
    
   public char getId(){
       return this.id;
   }
   
   public static char obtenerAlfabeto(char chr){
       //Agregar punto, se repite en puntuación y decimal
        int value = (int)chr;
        if(value == 32 || value == 10){
            return SEPARADOR.getId();
        }
        else if(value >= 65 && value <= 90 || value >= 97 && value <= 122){
            return LETRA.getId();
        }
        else if(Character.isDigit(chr)){
            return DIGITO.getId();
        }
        else if (chr == '.'){
            return PUNTO.getId();
        }
        else if (chr == ',' || chr == ';'|| chr == ':'){
            return PUNTUACION.getId();
        }
        else if(chr == '+' || chr == '-' || chr == '*' || chr == '/' || chr == '%'){
            return OPERADOR.getId();
        }
        else if(chr == '[' ||chr == ']' ||chr == '(' ||chr == ')' ||
                chr == '{' ||chr == '}'){
            return AGRUPACION.getId();
        }
        
        return NULO.getId();
   }
}
