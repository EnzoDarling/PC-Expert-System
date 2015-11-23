
package vista;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import modelo.conexion.conectar;

import java.util.*;
import java.text.*;
public class MostrarVentas extends javax.swing.JFrame {
 DefaultTableModel model;
 String codvent,numventa,codcli,codproduct,detallevent,cantventa,unitventa,totalventa,responventa,pagoventa;
 int preci=0;
 int cant=0;
 int total=0;
 String precio,cantidad,tot;  
     public MostrarVentas() {
        initComponents();
        setSize(755, 600);
        setTitle("Lista de Ventas");
        setLocationRelativeTo(null);
        //deshabilitar();
        cargarventas("");
        
    }
    void cargarventas(String valor){
       String [] titulos={"Codigo Ventas","Codigo Clientes","Codigo Productos","Detalles","Cantidad","Prec. Unitario","Prec. Total","Responsable IVA","Tipo Pago"};
        String [] registros = new String[20];
        String sql = "SELECT * FROM ventas WHERE vent_cod LIKE '%"+valor+"%' ORDER BY vent_cod ASC";
        model = new DefaultTableModel (null,titulos);
        conectar cc=new conectar();
        Connection cn= cc.conexion();
        try {
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()){
            registros[0]=rs.getString("vent_cod");
            registros[1]=rs.getString("cli_cod");
            registros[2]=rs.getString("prod_cod");
            registros[3]=rs.getString("vent_detalle");
            registros[4]=rs.getString("vent_cantidad");
            registros[5]=rs.getString("vent_precunitario");
            registros[6]=rs.getString("vent_prectotal");
            registros[7]=rs.getString("vent_respon");
            registros[8]=rs.getString("vent_tipopago");
            model.addRow(registros);
        }
        tab_ventas.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error:"+e);
        } 
    }
       /*void deshabilitar(){
        t_codcli.setEnabled(false);
        cod_producto.setEnabled(false);
        t_detalles.setEnabled(false);
        t_cantidad.setEnabled(false);
        t_preunit.setEnabled(false);
        t_prectotal.setEnabled(false);
        combo_respons.setEnabled(false);
        combopago.setEnabled(false);
    }*/
       @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        ventbuscar = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tab_ventas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar"));
        jPanel2.setLayout(null);
        jPanel2.add(ventbuscar);
        ventbuscar.setBounds(6, 28, 135, 20);

        jButton3.setText("Mostrar Todo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);
        jButton3.setBounds(159, 27, 110, 23);
        
        jButton4.setText("Volver");
        getContentPane().add(jButton4);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	jButton4ActionPerformed(evt);
            }
        });
        jButton4.setBounds(10, 540, 99, 23);

        tab_ventas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tab_ventas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_ventasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tab_ventas);

        jPanel2.add(jScrollPane2);
        jScrollPane2.setBounds(6, 56, 730, 378);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 80, 750, 451);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Lista de Ventas");
        jLabel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 10, 720, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    protected void jButton4ActionPerformed(ActionEvent evt) {
    	this.dispose();
	}
	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        cargarventas("");
    }//GEN-LAST:event_jButton3ActionPerformed
    private void tab_ventasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_ventasMouseClicked
        
    }//GEN-LAST:event_tab_ventasMouseClicked
    
        /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Ventas().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tab_ventas;
    private javax.swing.JTextField ventbuscar;
    // End of variables declaration//GEN-END:variables
}
