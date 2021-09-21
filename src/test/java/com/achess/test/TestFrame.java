/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.achess.test;

import com.achess.backend.Automaton;
import com.achess.backend.Token;
import com.achess.backend.WordAutomaton;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

/**
 *
 * @author achess
 */
public class TestFrame extends javax.swing.JFrame {

    /**
     * Creates new form TestFrame
     */
    public TestFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        textSpace = new javax.swing.JTextArea();
        buttonAnalize = new javax.swing.JButton();
        textFWord = new javax.swing.JTextField();
        buttonSearch = new javax.swing.JButton();
        labelCords = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textSpace.setColumns(20);
        textSpace.setRows(5);
        textSpace.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                textSpaceCaretUpdate(evt);
            }
        });
        textSpace.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textSpaceFocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(textSpace);

        buttonAnalize.setText("Analizar");
        buttonAnalize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAnalizeActionPerformed(evt);
            }
        });

        buttonSearch.setText("Buscar");
        buttonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchActionPerformed(evt);
            }
        });

        labelCords.setText("Ln: 1 Col: 1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(textFWord, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonAnalize)
                            .addComponent(buttonSearch)
                            .addComponent(labelCords))))
                .addGap(38, 38, 38))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(labelCords)
                .addGap(57, 57, 57)
                .addComponent(buttonAnalize)
                .addGap(50, 50, 50)
                .addComponent(textFWord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonSearch)
                .addContainerGap(120, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAnalizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAnalizeActionPerformed
        // TODO add your handling code here:
        String text = textSpace.getText();
        Automaton.getAutomaton().analize(text);
        String response = "";
        for(Token tk : Automaton.getAutomaton().getTokens()){            
            System.out.println(tk);
        }
        System.out.println("Errores-------------------------------");
        for(Token tk : Automaton.getAutomaton().getErrors()){
            System.out.println(tk);            
        }        
        
        System.out.println(response);
    }//GEN-LAST:event_buttonAnalizeActionPerformed

    private void buttonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchActionPerformed
        // TODO add your handling code here:        
        textSpace.getHighlighter().removeAllHighlights();
        Highlighter hilit;
        Highlighter.HighlightPainter painter;
        hilit = new DefaultHighlighter();        
        painter = new DefaultHighlighter.DefaultHighlightPainter(Color.CYAN);
        textSpace.setHighlighter(hilit);
        String text = textSpace.getText();
        String word = textFWord.getText();
        WordAutomaton autW = new WordAutomaton(word);
        autW.analize(text);
        System.out.println("---------------------");
        for(Token tk : autW.getWords()){
            try {            
                hilit.addHighlight(tk.getBegin(),tk.getEnd() ,painter);
                textSpace.setCaretPosition(tk.getEnd());
            } catch (BadLocationException ex) {
                ex.printStackTrace(System.out);
            }
        }               
    }//GEN-LAST:event_buttonSearchActionPerformed

    private void textSpaceFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textSpaceFocusGained
        // TODO add your handling code here:
        textSpace.getHighlighter().removeAllHighlights();
    }//GEN-LAST:event_textSpaceFocusGained

    private void textSpaceCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_textSpaceCaretUpdate
        // TODO add your handling code here:
        try{
            JTextArea txt = this.textSpace;
            int caretOffset = txt.getCaretPosition();
            int lineNumber = txt.getLineOfOffset(caretOffset);                    
            int col = caretOffset - txt.getLineStartOffset(lineNumber) + 1;
            lineNumber+=1;            
            labelCords.setText("Ln:" + lineNumber +" Col: " + col);            
        }
        catch(Exception ex){
            ex.printStackTrace(System.out);
        }
    }//GEN-LAST:event_textSpaceCaretUpdate

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TestFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAnalize;
    private javax.swing.JButton buttonSearch;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelCords;
    private javax.swing.JTextField textFWord;
    private javax.swing.JTextArea textSpace;
    // End of variables declaration//GEN-END:variables
}
