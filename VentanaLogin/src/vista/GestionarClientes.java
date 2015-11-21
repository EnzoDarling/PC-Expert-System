/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelo.conexion.conectar;

/**
 *
 * @author Enzo
 */
public class GestionarClientes extends javax.swing.JFrame {
    DefaultTableModel model;
    String cod,ape,nomb,tel,iiva;
    public GestionarClientes() {
        initComponents();
        setSize(911, 474);
        setLocationRelativeTo(null);
        setTitle("Formulario Modificar Clientes");
        cargar("");
        //deshabilitar();
    }
    void limpiar(){
        cli_dni.setText("");
        cli_ap.setText("");
        cli_nom.setText("");
        cli_tel.setText("");
        combocli_iva.setSelectedIndex(0);
    }
    void cargar(String valor){
    	String [] titulos={"DNI","Nombre","Apellido","Telefono","IVA"};
        String [] registros = new String[5];
        String sql = "SELECT * FROM clientes WHERE cli_nom LIKE '%"+valor+"%' ORDER BY cli_nom ASC";
        model = new DefaultTableModel (null,titulos);
        conectar cc=new conectar();
        Connection cn= cc.conexion();
        try {
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()){
            registros[0]=rs.getString("cli_dni");
            registros[1]=rs.getString("cli_nom");
            registros[2]=rs.getString("cli_ap");
            registros[3]=rs.getString("cli_tel");
            registros[4]=rs.getString("cli_iva");
            model.addRow(registros);
        }
        t_tabclientes.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error:"+e);
        }
    }
    void guardar(){
        conectar cc= new conectar();
        Connection cn= cc.conexion();
        
        String sql="";
        cod=cli_dni.getText();
        ape=cli_ap.getText();
        nomb=cli_nom.getText();
        tel=cli_tel.getText();
        String ivarespon=combocli_iva.getSelectedItem().toString();
        sql="INSERT INTO clientes (cli_cod, cli_nom, cli_ap, cli_tel, cli_iva) VALUES (?,?,?,?,?)";
        try {
        PreparedStatement psd= cn.prepareStatement(sql);
        psd.setString(1,cod);
        psd.setString(2,ape);
        psd.setString(3,nomb);
        psd.setString(4,tel);
        psd.setString(5,ivarespon);
        int n=psd.executeUpdate();
        if(n>0){
            JOptionPane.showMessageDialog(null,"REGISTRO GUARDADO CON EXITO!");
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:"+e);
        }
        cargar("");
        limpiar();
    }
    void eliminar(){
        conectar cc=new conectar();
        Connection cn= cc.conexion();
        cod=cli_dni.getText();
        String comboiva=combocli_iva.getSelectedItem().toString();
        String sql ="DELETE FROM clientes WHERE cli_dni=?";
        int resp;
        resp = JOptionPane.showConfirmDialog(null,"¿Esta seguro de eliminar este registro?","Alerta!", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION){
        if(comboiva.equals("Seleccione")){
        	JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de contribuyente al IVA",
  				  "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);        	
        }else{
        	if(cod.equals("") || cod==null)
        		{
        		JOptionPane.showMessageDialog(null, "Debe ingresar el codigo de barras del producto",
        				  "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
	        	}else{
			        try {
			            PreparedStatement psd= cn.prepareStatement(sql);
			            psd.setString(1,cod);
			            int x= psd.executeUpdate();
			            if(x>0){
			                JOptionPane.showMessageDialog(null,"Registro eliminado correctamente");
			            }
			        } catch (Exception e) {
			            JOptionPane.showMessageDialog(null,"Error :"+e);
			        }
	        	}
        	}
        }
        cargar("");        
    }
    void modificar(){	
        conectar cc= new conectar();
        Connection cn= cc.conexion();
        /*codd=t_codigo.getText();
        insu=t_insumos.getText();
        cant=t_cantidad.getText();
        preci=t_precio.getText();
        iva=t_iva.getText();*/
        String sql="UPDATE clientes SET cli_ap='"+cli_ap.getText()+"', cli_nom= '"+cli_nom.getText()+"', cli_tel='"+cli_tel.getText()+"', cli_iva='"+combocli_iva.getSelectedItem().toString()+"' WHERE cli_dni='"+cli_dni.getText()+"'";
        int resp;
        String comboitem= combocli_iva.getSelectedItem().toString();
        resp = JOptionPane.showConfirmDialog(null,"¿Esta seguro de eliminar este registro?","Alerta!", JOptionPane.YES_NO_OPTION);
        if(resp == JOptionPane.YES_OPTION){
        	if(comboitem.equals("Seleccione")){
        		JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de contribuyente al IVA",
        				  "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        	}else{
		        try {
		            PreparedStatement psd= cn.prepareStatement(sql);
		            int x = psd.executeUpdate();
		            if(x==1){
		                JOptionPane.showMessageDialog(null,"Registro modificado");
		            }else{
		                JOptionPane.showMessageDialog(null,"No se existe ningun registro con ese codigo");
		            }
		        } catch (Exception e) {
		            JOptionPane.showMessageDialog(null,"Error :"+e);
		        }
		        cargar("");
        	}
        }
    }
    void habilitar(){
        cli_dni.setEnabled(true);
        cli_ap.setEnabled(true);
        cli_nom.setEnabled(true);
        cli_tel.setEnabled(true);
        combocli_iva.setEnabled(true);
    }
    /*void deshabilitar(){
        cli_num.setEnabled(false);
        cli_ap.setEnabled(false);
        cli_nom.setEnabled(false);
        cli_tel.setEnabled(false);
        combocli_iva.setEnabled(false);
        
    }*/

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cli_tel = new javax.swing.JTextField();
        cli_dni = new javax.swing.JTextField();
        cli_nom = new javax.swing.JTextField();
        cli_ap = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        combocli_iva = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        cli_buscar = new javax.swing.JTextField();
        btn_cmostrar = new javax.swing.JButton();
        btn_climpiar = new javax.swing.JButton();
//        btn_cguardar = new javax.swing.JButton();
        btn_cmodificar = new javax.swing.JButton();
        btn_celiminar = new javax.swing.JButton();
        btn_catras = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_tabclientes = new javax.swing.JTable();
//        btn_nuevo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Personales"));
        jPanel1.setLayout(null);

        jLabel1.setText("DNI");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(16, 31, 58, 14);

        jLabel2.setText("Apellido");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(16, 66, 58, 14);

        jLabel3.setText("Nombre");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(16, 104, 58, 14);

        jLabel5.setText("Tel/Cel");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(16, 142, 58, 14);
        
        jPanel1.add(cli_tel);
        cli_tel.setBounds(159, 139, 106, 24);
        jPanel1.add(cli_dni);
        cli_dni.setBounds(159, 25, 106, 24);
        jPanel1.add(cli_nom);
        cli_nom.setBounds(159, 101, 106, 24);
        jPanel1.add(cli_ap);
        cli_ap.setBounds(159, 63, 106, 24);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 307, 176);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("IVA"));
        jPanel2.setLayout(null);

        combocli_iva.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "Responsable Inscripto", "Consumidor Final", "Monotributista" }));
        jPanel2.add(combocli_iva);
        combocli_iva.setBounds(16, 27, 132, 26);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 182, 205, 66);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar"));
        
        jPanel3.setLayout(null);
        jPanel3.add(cli_buscar);
        cli_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
            	cli_buscarKeyReleased(evt);
            }
        });
        cli_buscar.setBounds(16, 22, 122, 24);

//        btn_cmostrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Formularios/iconos/1438381115_x-office-spreadsheet.png"))); // NOI18N
        btn_cmostrar.setText("Mostrar Todo");
        jPanel3.add(btn_cmostrar);
        btn_cmostrar.setBounds(156, 16, 127, 24);

        getContentPane().add(jPanel3);
        jPanel3.setBounds(333, 11, 299, 55);

//        btn_climpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Formularios/iconos/1438376270_edit-clear.png"))); // NOI18N
        btn_climpiar.setText("Limpiar");
        btn_climpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_climpiarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_climpiar);
        btn_climpiar.setBounds(10, 262, 95, 35);

//        btn_cguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Formularios/iconos/drive_user.png"))); // NOI18N
//        btn_cguardar.setText("Guardar");
//        btn_cguardar.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                btn_cguardarActionPerformed(evt);
//            }
//        });
//        getContentPane().add(btn_cguardar);
//        btn_cguardar.setBounds(10, 305, 109, 35);

//        btn_cmodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Formularios/iconos/1414193400_group_edit.png"))); // NOI18N
        btn_cmodificar.setText("Modificar");
        btn_cmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cmodificarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cmodificar);
        btn_cmodificar.setBounds(110, 262, 95, 35);

//        btn_celiminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Formularios/iconos/user_delete.png"))); // NOI18N
        btn_celiminar.setText("Eliminar");
        btn_celiminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_celiminarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_celiminar);
        btn_celiminar.setBounds(210, 262, 95, 35);

//        btn_catras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Formularios/iconos/1438376156_Log Out.png"))); // NOI18N
        btn_catras.setText("Volver");
        btn_catras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_catrasActionPerformed(evt);
            }
        });
        getContentPane().add(btn_catras);
        btn_catras.setBounds(10, 399, 113, 35);

        t_tabclientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        t_tabclientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_tabclientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(t_tabclientes);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(333, 72, 568, 382);

//        btn_nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Formularios/iconos/1414193194_group_add.png"))); // NOI18N
//        btn_nuevo.setText("Nuevo");
//        btn_nuevo.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                btn_nuevoActionPerformed(evt);
//            }
//        });
//        getContentPane().add(btn_nuevo);
//        btn_nuevo.setBounds(10, 262, 109, 35);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    protected void cli_buscarKeyReleased(KeyEvent evt) {
    	cargar(cli_buscar.getText());		
	}	
	private void btn_catrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_catrasActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_catrasActionPerformed

    private void btn_climpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_climpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btn_climpiarActionPerformed

//    private void btn_cguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cguardarActionPerformed
//        guardar();
//        limpiar();
//        //deshabilitar();
//    }//GEN-LAST:event_btn_cguardarActionPerformed

    private void btn_cmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cmodificarActionPerformed
        modificar();
        limpiar();
        //deshabilitar();
    }//GEN-LAST:event_btn_cmodificarActionPerformed

    private void btn_celiminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_celiminarActionPerformed
        eliminar();
        limpiar();
        //deshabilitar();
    }//GEN-LAST:event_btn_celiminarActionPerformed

    private void btn_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoActionPerformed
        habilitar();
        limpiar();
    }//GEN-LAST:event_btn_nuevoActionPerformed

    private void t_tabclientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_tabclientesMouseClicked
        int fila=t_tabclientes.getSelectedRow();
        if(fila>=0){
                cli_dni.setText(t_tabclientes.getValueAt(fila,0).toString());
                cli_ap.setText(t_tabclientes.getValueAt(fila,1).toString());
                cli_nom.setText(t_tabclientes.getValueAt(fila,2).toString());
                cli_tel.setText(t_tabclientes.getValueAt(fila,3).toString());
                combocli_iva.setSelectedItem(t_tabclientes.getValueAt(fila,4).toString());
        }
    }//GEN-LAST:event_t_tabclientesMouseClicked

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
//            java.util.logging.Logger.getLogger(ModificarClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ModificarClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ModificarClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ModificarClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ModificarClientes().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_catras;
    private javax.swing.JButton btn_celiminar;
//    private javax.swing.JButton btn_cguardar;
    private javax.swing.JButton btn_climpiar;
    private javax.swing.JButton btn_cmodificar;
    private javax.swing.JButton btn_cmostrar;
//    private javax.swing.JButton btn_nuevo;
    private javax.swing.JTextField cli_ap;
    private javax.swing.JTextField cli_buscar;
    private javax.swing.JTextField cli_nom;
    private javax.swing.JTextField cli_dni;
    private javax.swing.JTextField cli_tel;
    private javax.swing.JComboBox combocli_iva;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable t_tabclientes;
    // End of variables declaration//GEN-END:variables
	public void asignarPrivilegios(String usuario) {
		// TODO Auto-generated method stub
		
	}
}
