/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pl.polsl.view;

import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import pl.polsl.model.InvalidGradeException;
import pl.polsl.model.StudentGradebook;
import pl.polsl.controller.GradesImporter;
import pl.polsl.model.PartialGrade;
import pl.polsl.model.Subject;


/**
 * Gui class
 * @author pawel
 */
public class GUI extends javax.swing.JFrame {
    private static GradesImporter gradesImporter; //grades importer
    private String selectedSubject; //selected subject by user in the combobox
    private static StudentGradebook studentGradebook; // student gradebook
    private DefaultTableModel tableModel; //table model
    /**
     * Creates new form GUI
     * Add shortcuts and tooltips for buttons and combobox
     * Initialize componets
     */
    public GUI() {
        initComponents();
        jTextArea1.setEditable(false);
        selectedSubject = (String) jComboBox1.getSelectedItem();
        jScrollPane1.setViewportView(jTable1);
        tableModel = (DefaultTableModel) jTable1.getModel();
        jButton2.setToolTipText("Generate summary and display all subjects. (Ctrl + Shift + S)");
        jButton4.setToolTipText("Display not passing subjects. (Ctrl + Shift + N)");
        jButton1.setToolTipText("Display grades for the selected subject. (Ctrl + Shift + D)");
        jComboBox1.setToolTipText("Select a subject from the list. (Ctrl + Shift + C)");

        //Shortcuts
        jButton1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ctrl shift D"), "displayGrades");
        jButton2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ctrl shift S"), "generateSummary");
        jButton4.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ctrl shift N"), "displayNotPassing");
        jComboBox1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ctrl shift C"), "selectSubject");

        // Actions
        jButton1.getActionMap().put("displayGrades", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton1ActionPerformed(e);
            }
        });
        jButton2.getActionMap().put("generateSummary", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton2ActionPerformed(e);
            }
        });
        jButton4.getActionMap().put("displayNotPassing", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButton4ActionPerformed(e);
            }
        });
        jComboBox1.getActionMap().put("selectSubject", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jComboBox1ActionPerformed(e);
                jComboBox1.showPopup();
            }
        });

        if (studentGradebook.getSubjects().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Incorrect filename", "Error", JOptionPane.ERROR_MESSAGE);
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

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Grades from selected subject");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Summary");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("Display not passing subjects");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setText("Gradebook");
        jLabel1.setMaximumSize(new java.awt.Dimension(50, 30));
        jLabel1.setPreferredSize(new java.awt.Dimension(50, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("Select option");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(3);
        jScrollPane2.setViewportView(jTextArea1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(studentGradebook.getSubjectsNames().toArray(new String[0])));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        jTable1.setRowHeight(25);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 22, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(41, 41, 41)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE))
                .addGap(9, 9, 9))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(24, 24, 24)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(102, 102, 102))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Button action for displaying grades from choosen subject
     * @param evt 
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jTextArea1.setText(selectedSubject + " grades");
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
        tableModel.addColumn("Value");
        tableModel.addColumn("Weight");
        Subject subject = studentGradebook.getSubject(selectedSubject);

        if (subject != null) {
            List<PartialGrade> partialGrades = subject.getGrades();

            for (PartialGrade grade : partialGrades) {
                Object[] rowData = {
                    grade.getValue(),
                    grade.getWeight()
                };
                tableModel.addRow(rowData);
            }
        } else {
            jTextArea1.setText("Select subject");
        }   
        
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * Subject Comparator
     */
    interface SubjectComparator extends Comparator<Subject> {
    }
    
    /**
     * Method for displaying sorted by name subjects in the table with grades average
     * @param subjects
     * @param columnNames
     * @throws InvalidGradeException 
     */
    private void displaySubjects(List<Subject> subjects, List<String> columnNames) throws InvalidGradeException{
        tableModel.setColumnCount(0);
        tableModel.setRowCount(0);

        for (String columnName : columnNames) {
            tableModel.addColumn(columnName);
        }

        SubjectComparator subjectNameComparator = (subject1, subject2) -> subject1.getName().compareTo(subject2.getName());
        List<Subject> sortedSubjectsByName = subjects
            .stream()
            .sorted(subjectNameComparator)
            .collect(Collectors.toList());
        for (Subject subject : sortedSubjectsByName) {
            Object[] rowData = {
                    subject.getName(),
                    String.format("%.2f", subject.calculateAverage()),
                    subject.getSubjectFinalGrade()
            };
            tableModel.addRow(rowData);
        }

    }
    /**
     * Button action for displaying summary: every subject with average and final subject grade in the table
     * and calculated final grade from all subjects in textBox
     * @param evt 
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jTextArea1.setText("Summary\nFinal Grade: ");
        jTextArea1.append(String.format("%.2f", studentGradebook.getFinalGrade()) + "\n");      
        jTextArea1.append("---" + (studentGradebook.getNotPassingSubjectsAmount() > 0 || studentGradebook.getFinalGrade() < 1.75 ? "Not passing" : "Passing") + "---");
        

        List<String> columnNames = Arrays.asList("Subject", "Average", "Final subject grade");
        try {
            displaySubjects(studentGradebook.getSubjects(), columnNames);
        } catch (InvalidGradeException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * Button action displaying not passing subjects with average and final grade form these subjects
     * @param evt 
     */
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        jTextArea1.setText("Not passing subjects");
        List<String> columnNames = Arrays.asList("Subject", "Average", "Final subject grade");
        try {
            displaySubjects(studentGradebook.getNotPassingSubjects(), columnNames);

        } catch (InvalidGradeException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * ComboBox action opening combobox and getting selected subject from list
     * @param evt 
     */
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        JComboBox cb = (JComboBox) evt.getSource();
        selectedSubject = (String) cb.getSelectedItem();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    /**
     * Main method
     * You can provide a filename in the project run arguments, for example "grades.txt"
     * Schema in file looks like:
     * "(string)subject name": "(double)grade value"-"grade weight",...
     * example: "Maths: 2-1, 4-2, 6-3, 2-1"
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
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        try {
            String filename;
            if(args.length > 0){
                filename = args[0];
            } else {
                filename = JOptionPane.showInputDialog(null, "Write filename:");
            }

            gradesImporter = new GradesImporter();
            studentGradebook = new StudentGradebook();
            gradesImporter.importSubjects(studentGradebook, filename);
            
            
        } catch (InvalidGradeException e) {
            e.printStackTrace();
        }
        
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
                
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
