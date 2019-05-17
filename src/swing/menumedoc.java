/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

import static swing.windows.f;
import classe.DAO.MedicamentDAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import medecin.metier.Medicament;
import myconnections.DBConnection;

/**
 *
 * @author jerom
 */
public class menumedoc extends javax.swing.JPanel {

    /**
     * Creates new form menumedoc
     */
    String nom, description,code = "";
    Statement stmt;
    ResultSet rs = null;
    Medicament medoc;
    int id;
    public menumedoc() {
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

        txtMedoc = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btCreaMedoc = new javax.swing.JButton();
        btAffMedoc = new javax.swing.JButton();
        btModifMedoc = new javax.swing.JButton();
        BtSupMedoc = new javax.swing.JButton();
        btRechdesc = new javax.swing.JButton();
        btRetour = new javax.swing.JButton();

        setBackground(new java.awt.Color(153, 255, 153));
        setMinimumSize(new java.awt.Dimension(200, 136));
        setPreferredSize(new java.awt.Dimension(550, 400));
        setLayout(new java.awt.GridLayout(4, 2, 20, 20));

        txtMedoc.setFont(new java.awt.Font("Elephant", 1, 24)); // NOI18N
        txtMedoc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtMedoc.setText("Menu Médicament");
        add(txtMedoc);
        add(jLabel1);

        btCreaMedoc.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btCreaMedoc.setText("Ajouter");
        btCreaMedoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCreaMedocActionPerformed(evt);
            }
        });
        add(btCreaMedoc);

        btAffMedoc.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btAffMedoc.setText("Afficher");
        btAffMedoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAffMedocActionPerformed(evt);
            }
        });
        add(btAffMedoc);

        btModifMedoc.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btModifMedoc.setText("Modifier");
        btModifMedoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModifMedocActionPerformed(evt);
            }
        });
        add(btModifMedoc);

        BtSupMedoc.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        BtSupMedoc.setText("Supprimer");
        BtSupMedoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtSupMedocActionPerformed(evt);
            }
        });
        add(BtSupMedoc);

        btRechdesc.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        btRechdesc.setText("Recherche description");
        btRechdesc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRechdescActionPerformed(evt);
            }
        });
        add(btRechdesc);

        btRetour.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btRetour.setText("Retour");
        btRetour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRetourActionPerformed(evt);
            }
        });
        add(btRetour);
    }// </editor-fold>//GEN-END:initComponents

    private void btCreaMedocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCreaMedocActionPerformed
        f.setContentPane(new Creamedoc());
        f.repaint();
        f.revalidate();
        f.setSize(550,450);
    }//GEN-LAST:event_btCreaMedocActionPerformed

    private void btAffMedocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAffMedocActionPerformed
        try {
            Connection dbConnect = DBConnection.getConnection();
            String aff="Liste des médicaments : ";
            stmt = dbConnect.createStatement();
            rs = stmt.executeQuery("select * from medicament");
            while(rs.next()){                
                    id=rs.getInt("IDMEDOC");
                    nom=rs.getString("NOM");
                    description = rs.getString("DESCRIPTION");
                    code = rs.getString("CODE");
                    aff += "\n" +" "+ id +"\t "+ code +"\t "+ nom +"\t "+ description;
            }
            JOptionPane.showMessageDialog(this,aff,"Résultat",JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,e,"Erreur",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btAffMedocActionPerformed

    private void btRetourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRetourActionPerformed

        f.setContentPane(new menu());
        f.repaint();
        f.revalidate();
    }//GEN-LAST:event_btRetourActionPerformed

    private void btModifMedocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btModifMedocActionPerformed
        f.setContentPane(new Modifmedoc());
        f.repaint();
        f.revalidate();
        f.setSize(550,450);
    }//GEN-LAST:event_btModifMedocActionPerformed

    private void BtSupMedocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtSupMedocActionPerformed
        f.setContentPane(new Supprmedoc());
        f.repaint();
        f.revalidate();
        f.setSize(550,450);
    }//GEN-LAST:event_BtSupMedocActionPerformed

    private void btRechdescActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRechdescActionPerformed
        f.setContentPane(new Rechdesc());
        f.repaint();
        f.revalidate();
        f.setSize(550,450);
    }//GEN-LAST:event_btRechdescActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtSupMedoc;
    private javax.swing.JButton btAffMedoc;
    private javax.swing.JButton btCreaMedoc;
    private javax.swing.JButton btModifMedoc;
    private javax.swing.JButton btRechdesc;
    private javax.swing.JButton btRetour;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel txtMedoc;
    // End of variables declaration//GEN-END:variables
}
