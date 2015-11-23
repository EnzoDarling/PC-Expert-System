
package vista;

import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelo.conexion.conectar;
public class RegistrarProductos extends javax.swing.JFrame {
    DefaultTableModel model= new DefaultTableModel();
    String codd,insu,cant,preci;
    public RegistrarProductos() {
        initComponents();
        setSize(959, 429);
        setLocationRelativeTo(null);
        setTitle("Formulario Registrar Productos");
        cargar("");
    }
    void cargar(String valor){
        conectar cc=new conectar();
        Connection cn= cc.conexion();
        String [] titulos={"Codigo","Insumos","En Stock","Precio Unit."};
        String [] registros = new String[5];
        String sql = "SELECT * FROM productos WHERE prod_cod LIKE '%"+valor+"%' ORDER BY prod_cod ASC";
        model = new DefaultTableModel (null,titulos);        
        try {
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()){
            registros[0]=rs.getString("prod_cod");
            registros[1]=rs.getString("prod_detalle");
            registros[2]=rs.getString("prod_cant");
            registros[3]=rs.getString("prod_precunit");
            model.addRow(registros);
        }
        t_datos.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error:"+e);
        }
        
    }
    void eliminar (){
        conectar cc=new conectar();
        Connection cn= cc.conexion();
        codd=t_codigo.getText();
        String sql ="DELETE FROM productos WHERE prod_cod=?";
        int resp;
        resp = JOptionPane.showConfirmDialog(null,"¿Realmente desea eliminar?","Alerta!", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION){
        try {
            PreparedStatement psd= cn.prepareStatement(sql);
            psd.setString(1,codd);
            int x= psd.executeUpdate();
            if(x>0){
                JOptionPane.showMessageDialog(null,"Registro eliminado correctamente");
            }
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null,"Error :"+e);
        }
        }
        cargar("");
    }
    void modificar(){
        conectar cc= new conectar();
        Connection cn= cc.conexion();
        codd=t_codigo.getText();
        insu=t_insumos.getText();
        cant=t_cantidad.getText();
        preci=t_precio.getText();
        String sql="UPDATE productos SET prod_detalle='"+t_insumos.getText()+"', prod_cant='"+t_cantidad.getText()+"', prod_precunit= '"+t_precio.getText()+"' WHERE prod_cod='"+t_codigo.getText()+"'";
        int resp;
        resp = JOptionPane.showConfirmDialog(null,"¿Realmente desea modificar?","Alerta!", JOptionPane.YES_NO_OPTION);
        if(resp == JOptionPane.YES_OPTION){
        try {
            PreparedStatement psd= cn.prepareStatement(sql);
            int x = psd.executeUpdate();
            if(x==1){
                JOptionPane.showMessageDialog(null,"Registro modificado");
            }else{
                JOptionPane.showMessageDialog(null,"No se existe ningun registro con ese codigo");
            }
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null,"Error :"+e);
        }
        cargar("");
        }
    }
    void limpiar(){
        t_codigo.setText("");
        t_insumos.setText("");
        t_cantidad.setText("");
        t_precio.setText("");
    }
    void guardar(){
    	conectar cc= new conectar();
        Connection cn= cc.conexion();
        
        String sql="";
        codd=t_codigo.getText();
        insu=t_insumos.getText();
        cant=t_cantidad.getText();
        preci=t_precio.getText();
        sql="INSERT INTO productos (prod_cod, prod_detalle, prod_cant, prod_precunit) VALUES (?,?,?,?)";
        if(codd.equals("") || codd==null || insu.equals("") || insu==null || cant.equals("") || cant==null || preci.equals("") || preci==null){
        	JOptionPane.showMessageDialog(null, "Ingrese los datos obligatorios",
  				  "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        }else{
        	if(codd.length()!=13){
        		JOptionPane.showMessageDialog(null, "El c�digo de barras debe ser de 13 d�gitos",
        				  "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        	}else{
        try {
        PreparedStatement psd= cn.prepareStatement(sql);
        psd.setString(1,codd);
        psd.setString(2,insu);
        psd.setString(3,cant);
        psd.setString(4,preci);
        int n=psd.executeUpdate();
        if(n>0){
            JOptionPane.showMessageDialog(null,"REGISTRO GUARDADO CON EXITO!");
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Compruebe que los datos ingresados sean correctos o ingrese un c�digo de barras diferente");
        }
        cargar("");
        limpiar();
        }
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        //jPanel3 = new javax.swing.JPanel();
        t_cantidad = new javax.swing.JTextField();
        t_insumos = new javax.swing.JTextField();
        t_codigo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        t_precio = new javax.swing.JTextField();
        btnnuevo = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
//        btnEliminar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
//        btnModificar = new javax.swing.JButton();
        btnvolver = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        t_buscar = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_datos = new javax.swing.JTable();

        jMenuItem1.setText("Modificar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(null);
        
        
        jPanel1.add(t_cantidad);
        t_cantidad.setBounds(125, 105, 160, 24);
        jPanel1.add(t_insumos);
        t_insumos.setBounds(125, 55, 160, 24);
        jPanel1.add(t_codigo);        
        t_codigo.setBounds(125, 10, 160, 24);        
        jLabel1.setText("*Codigo de Barras");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(5, 14, 110, 14);

        jLabel2.setText("*Insumos");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(5, 59, 60, 14);

        jLabel3.setText("*En Stock");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(5, 109, 100, 14);

        jLabel4.setText("*Precio Unit.");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(5, 164, 100, 14);
        t_precio.setBounds(125, 160, 160, 24);
        jPanel1.add(t_precio);

//      //btnnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Formularios/iconos/1438381852_add.png"))); // NOI18N
        btnnuevo.setText("Nuevo");
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });
        jPanel1.add(btnnuevo);
        btnnuevo.setBounds(5, 250, 80, 31);

        //jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Formularios/iconos/1438381989_edit-clear.png"))); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnLimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(btnLimpiar);
        btnLimpiar.setBounds(90, 250, 80, 31);

        //jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Formularios/iconos/1438382172_trash.png"))); // NOI18N
//        btnEliminar.setText("Eliminar");
//        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jButton4ActionPerformed(evt);
//            }
//        });
//        jPanel1.add(btnEliminar);
//        btnEliminar.setBounds(90, 315, 80, 33);

        //jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Formularios/iconos/1438381897_floppy_disk_save.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar);
        btnGuardar.setBounds(175, 250, 80, 31);

        //jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Formularios/iconos/1438381254_Modify.png"))); // NOI18N
//        btnModificar.setText("Modificar");
//        btnModificar.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jButton3ActionPerformed(evt);
//            }
//        });
//        jPanel1.add(btnModificar);
//        btnModificar.setBounds(5, 315, 80, 33);

        //btnvolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Formularios/iconos/1438376156_Log Out.png"))); // NOI18N
        btnvolver.setText("Volver");
        btnvolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnvolverActionPerformed(evt);
            }
        });
        jPanel1.add(btnvolver);
        btnvolver.setBounds(5, 360, 80, 33);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 300, 397);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(null);

        jLabel6.setText("BUSCAR");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(12, 17, 50, 14);

        t_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                t_buscarKeyReleased(evt);
            }
        });
        jPanel2.add(t_buscar);
        t_buscar.setBounds(66, 14, 135, 20);

        jButton7.setText("MOSTRAR TODO");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton7);
        jButton7.setBounds(221, 13, 130, 23);

        t_datos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        t_datos.setComponentPopupMenu(jPopupMenu1);
        t_datos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_datosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(t_datos);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(12, 42, 580, 340);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(340, 10, 610, 391);
       

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        limpiar();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        guardar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void t_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_buscarKeyReleased
        cargar(t_buscar.getText());
    }//GEN-LAST:event_t_buscarKeyReleased

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        cargar("");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        t_codigo.requestFocus();
        t_insumos.transferFocus();
        t_cantidad.transferFocus();
        t_precio.transferFocus();
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        int fila=t_datos.getSelectedRow();
        if(fila>=0){
            t_codigo.setText(t_datos.getValueAt(fila,0).toString());
            t_insumos.setText(t_datos.getValueAt(fila,1).toString());
            t_cantidad.setText(t_datos.getValueAt(fila,2).toString());
            t_precio.setText(t_datos.getValueAt(fila,3).toString());
        }else{
            JOptionPane.showMessageDialog(null,"No se seleccionó ninguna fila");
        }      
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void t_datosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_datosMouseClicked
        int fila=t_datos.getSelectedRow();
        if(fila>=0){
            t_codigo.setText(t_datos.getValueAt(fila,0).toString());
            t_insumos.setText(t_datos.getValueAt(fila,1).toString());
            t_cantidad.setText(t_datos.getValueAt(fila,2).toString());
            t_precio.setText(t_datos.getValueAt(fila,3).toString());            
        }else{
            JOptionPane.showMessageDialog(null,"No se seleccionó ninguna fila");
            }
        }//GEN-LAST:event_t_datosMouseClicked

    private void btnvolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvolverActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnvolverActionPerformed

    
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
            java.util.logging.Logger.getLogger(RegistrarProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistrarProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistrarProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistrarProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistrarProductos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnnuevo;
    private javax.swing.JButton btnvolver;
    private javax.swing.JButton btnGuardar;
//    private javax.swing.JButton btnModificar;
//    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField t_buscar;
    private javax.swing.JTextField t_cantidad;
    private javax.swing.JTextField t_codigo;
    private javax.swing.JTable t_datos;
    private javax.swing.JTextField t_insumos;
    private javax.swing.JTextField t_precio;
    // End of variables declaration//GEN-END:variables
	public void asignarPrivilegios(String usuario) {
		// TODO Auto-generated method stub
		
	}
}
