/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.achess.UI;

import com.achess.backend.Automaton;
import com.achess.backend.Token;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author achess
 */
public class DialogTokens extends javax.swing.JDialog {
    private ArrayList<Token> tokens;    
    private HashMap<String, CountToken> counts;
    private String movements;
    private DefaultTableModel modelTokens;
    private DefaultTableModel modelCountTokens;
    /**
     * Creates new form DialogTokens
     */
    public DialogTokens(java.awt.Frame parent, boolean modal, ArrayList<Token> tokens) {
        super(parent, modal);
        initComponents();
        this.tokens = tokens;
        showTokens();        
        showMovements();
    }
    
    private void showTokens(){
        if(modelTokens == null){
            modelTokens = new DefaultTableModel();              
            modelTokens.addColumn("Nombre");        
            modelTokens.addColumn("Lexema");
            modelTokens.addColumn("Posición");                              
            for(Token tk : this.tokens){                        
                String cords = "Fila: "+ tk.getRow() + " Columna: " + tk.getColumn();
                Object[] data = {tk.getType().getType(), tk.getLexeme(), cords};
                System.out.println(tk.getMovements());
                modelTokens.addRow(data);
            }            
        }
        this.jTable1.setModel(modelTokens);
    }
    
    private void countTokens(){
        if(this.counts == null){
            this.counts = new HashMap<String, CountToken>();            
            ArrayList<Token> aux = this.tokens;                                                
            CountToken count;
            for(Token tk: aux){
                count = this.counts.get(tk.getLexeme());
                if(count == null){                    
                    count = new CountToken(tk.getType().getType(), tk.getLexeme());
                    count.add();
                    this.counts.put(count.lexeme, count);
                    System.out.println(count.lexeme);
                }
                else{
                    count.add();
                }
            }            
        }               
    }
    
    private void showCountTokens(){
        countTokens();
        if( modelCountTokens == null){
            modelCountTokens = new DefaultTableModel();              
            modelCountTokens.addColumn("Lexema");        
            modelCountTokens.addColumn("Token");
            modelCountTokens.addColumn("Repeticiones");                              
            for(CountToken tk: this.counts.values()){
                Object[] data = {tk.lexeme, tk.type, tk.count};
                modelCountTokens.addRow(data);
            }
        }
        this.jTable1.setModel(modelCountTokens);
    }
    private void showMovements(){                
        if(this.movements == null){
            this.movements = "";
            for(Token tk : this.tokens){
                this.movements += tk.getDescription() + " - " + tk.getLexeme() + "\n" + tk.getMovements() + "\n";
            }
            this.textMovements.setText(this.movements);
        }          
    }
    private class CountToken{
        protected String type;
        protected String lexeme;
        protected Integer count;        

        protected CountToken(String type, String lexeme) {
            this.type = type;
            this.lexeme = lexeme;
            this.count = 0;            
        }
        
        protected void add(){
            this.count = this.count + 1;
        }

        @Override
        public String toString() {
            return "CountToken{" + "type=" + type + ", lexeme=" + lexeme + ", count=" + count + '}';
        }
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonCountLexemes1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        buttonTokens = new javax.swing.JButton();
        buttonCountLexemes = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textMovements = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        buttonCountLexemes1.setBackground(new java.awt.Color(46, 41, 46));
        buttonCountLexemes1.setForeground(new java.awt.Color(207, 191, 78));
        buttonCountLexemes1.setText("Tokens");
        buttonCountLexemes1.setBorder(null);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tokens");

        jPanel1.setBackground(new java.awt.Color(25, 23, 26));

        buttonTokens.setBackground(new java.awt.Color(46, 41, 46));
        buttonTokens.setForeground(new java.awt.Color(207, 191, 78));
        buttonTokens.setText("Tokens");
        buttonTokens.setBorder(null);
        buttonTokens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTokensActionPerformed(evt);
            }
        });

        buttonCountLexemes.setBackground(new java.awt.Color(46, 41, 46));
        buttonCountLexemes.setForeground(new java.awt.Color(207, 191, 78));
        buttonCountLexemes.setText("Conteo de lexemas");
        buttonCountLexemes.setBorder(null);
        buttonCountLexemes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCountLexemesActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(25, 23, 26));

        textMovements.setEditable(false);
        textMovements.setBackground(new java.awt.Color(46, 41, 46));
        textMovements.setColumns(20);
        textMovements.setFont(new java.awt.Font("Liberation Mono", 0, 13)); // NOI18N
        textMovements.setForeground(new java.awt.Color(255, 255, 255));
        textMovements.setRows(5);
        jScrollPane2.setViewportView(textMovements);

        jTable1.setBackground(new java.awt.Color(46, 41, 46));
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setRowHeight(25);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(67, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(buttonTokens, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(buttonCountLexemes, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(162, 162, 162))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonTokens, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCountLexemes, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCountLexemesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCountLexemesActionPerformed
        // TODO add your handling code here:
        showCountTokens();
    }//GEN-LAST:event_buttonCountLexemesActionPerformed

    private void buttonTokensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTokensActionPerformed
        // TODO add your handling code here:
        showTokens();
    }//GEN-LAST:event_buttonTokensActionPerformed
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCountLexemes;
    private javax.swing.JButton buttonCountLexemes1;
    private javax.swing.JButton buttonTokens;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea textMovements;
    // End of variables declaration//GEN-END:variables
}
