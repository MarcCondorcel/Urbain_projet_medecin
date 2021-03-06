/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

import static swing.windows.f;
import java.sql.*;
import medecin.metier.Prescription;
import medecin.metier.Medecin;
import medecin.metier.Patient;
import classe.DAO.PrescriptionDAO;
import classe.DAO.MedecinDAO;
import classe.DAO.PatientDAO;
import javax.swing.JOptionPane;
import myconnections.DBConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 *
 * @author jerom
 */
public class Creapres extends javax.swing.JPanel {

    /**
     * Creates new form Creapres
     */
    String datepres,nompat,nommed;
    Statement stmt;
    ResultSet rs = null;
    int id,idmed,idpat;
    Prescription pres;
        static Locale locale = Locale.getDefault();
	static java.util.Date actuelle = new java.util.Date();
 
	//	* Definition du format utilise pour les dates
	static DateFormat dateFormat =
                 new SimpleDateFormat("dd/MM/YY");
 
	//	* Donne la date au format "aaaa-mm-jj"
	public static String date()
	{
		String dat = dateFormat.format(actuelle);
		return dat;
	}

    public Prescription getPres() {
        return pres;
    }
    
    
        
        
    public Creapres() {
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

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMed = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPat = new javax.swing.JTextField();
        btSuivant = new javax.swing.JButton();
        btAnnuler = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 204, 102));
        setLayout(new java.awt.GridLayout(4, 2, 10, 15));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Prescription");
        add(jLabel1);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("Rédaction");
        add(jLabel3);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Je soussigné docteur");
        add(jLabel2);

        txtMed.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        add(txtMed);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("prescrivant au patient");
        add(jLabel4);

        txtPat.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        add(txtPat);

        btSuivant.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btSuivant.setText("Suivant");
        btSuivant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSuivantActionPerformed(evt);
            }
        });
        add(btSuivant);

        btAnnuler.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        btAnnuler.setText("Annuler");
        btAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAnnulerActionPerformed(evt);
            }
        });
        add(btAnnuler);
    }// </editor-fold>//GEN-END:initComponents

    private void btAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAnnulerActionPerformed
        f.setContentPane(new menupres());
        f.repaint();
        f.revalidate();
    }//GEN-LAST:event_btAnnulerActionPerformed

    private void btSuivantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSuivantActionPerformed
        try{
            Connection dbConnect = DBConnection.getConnection();
            nommed=txtMed.getText();
            stmt = dbConnect.createStatement();
            rs = stmt.executeQuery("select * from medecin");
            while(rs.next()){
                if(rs.getString("NOM").equals(nommed)){
                    idmed=rs.getInt("IDMED");
                }
            }
            nompat=txtPat.getText();
            stmt = dbConnect.createStatement();
            rs = stmt.executeQuery("select * from patient");
            while(rs.next()){
                if(rs.getString("NOM").equals(nompat)){
                    idpat=rs.getInt("IDPAT");
                }
            }
            datepres=date();
            PrescriptionDAO pdao = new PrescriptionDAO();
            id=0;
            pres= new Prescription(id,datepres,idmed,idpat);
            try{
                pres=pdao.create(pres);
                int idp=pres.getIdpres();
                //JOptionPane.showMessageDialog(this,idp,"Succès",JOptionPane.INFORMATION_MESSAGE);
                f.setContentPane(new Infopres(idp));
                f.repaint();
                f.revalidate();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(this,e,"Erreur",JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,e,"Erreur",JOptionPane.INFORMATION_MESSAGE);
        }
       
    }//GEN-LAST:event_btSuivantActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAnnuler;
    private javax.swing.JButton btSuivant;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtMed;
    private javax.swing.JTextField txtPat;
    // End of variables declaration//GEN-END:variables
}
