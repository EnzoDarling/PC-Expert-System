
package vista;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import modelo.conexion.conectar;

import java.util.*;
import java.text.*;
public class RegistrarVentas extends javax.swing.JFrame {
 DefaultTableModel model;
 String codvent,numventa,codcli,codproduct,detallevent,cantventa,unitventa,totalventa,responventa,pagoventa;
 int preci=0;
 int cant=0;
 int total=0;
 String precio,cantidad,tot;  
     public RegistrarVentas() {
        initComponents();
        setSize(1280,600);
        setLocationRelativeTo(null);
        setTitle("Formulario Registrar Ventas");
        //deshabilitar();
        cargarventas("");
        cargarclientes("");
        cargarproductos("");
    }
    void cargarventas(String valor){
       String [] titulos={"N� Factura/Ticket","DNI Clientes","CB Productos","Detalles","Cantidad","Prec. Unitario","Prec. Total","Responsable IVA","Tipo Pago"};
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
    void cargarclientes(String valor){
        String [] titulos={"DNI","Nombre","Apellido","Telefono","IVA"};
        String [] registros = new String[5];
        String sql = "SELECT * FROM clientes WHERE cli_dni LIKE '%"+valor+"%' ORDER BY cli_dni ASC";
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
        t_cliente.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error:"+e);
        }        
    }
    void cargarproductos(String valor){
        String [] titulos={"Codigo de Barras","Insumos","En Stock","Precio Unit."};
        String [] registros = new String[5];
        String sql = "SELECT * FROM productos WHERE prod_cod LIKE '%"+valor+"%' ORDER BY prod_cod ASC";
        model = new DefaultTableModel (null,titulos);
        conectar cc=new conectar();
        Connection cn= cc.conexion();
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
    void guardar(){
        conectar cc= new conectar();
        Connection cn= cc.conexion();
        String sql="";
        /*String di,me,años;
        int dia=fechaventa.getCalendar().get(Calendar.DAY_OF_MONTH);
        int mes=fechaventa.getCalendar().get(Calendar.MONTH)+1;
        int año=fechaventa.getCalendar().get(Calendar.YEAR);
        di=Integer.toString(dia);
        me=Integer.toString(mes);
        años=Integer.toString(año);*/
        //SimpleDateFormat sdf= new SimpleDateFormat("yyyy-mm-dd", Locale.getDefault());
//        String d= sdf.format(fechaventa.getDate());
        numventa=t_codventa.getText();
        codcli=t_codcli.getText();
        codproduct=cod_producto.getText();
        detallevent=t_detalles.getText();
        cantventa=t_cantidad.getText();
        unitventa=t_preunit.getText();
        totalventa=t_prectotal.getText();
        String value1=combo_respons.getSelectedItem().toString();
        String value2=combopago.getSelectedItem().toString();
        if(value1.equals("Seleccione")){
        	JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de contribuyente al IVA",
    				  "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        }
        	if(value2.equals("Seleccione")){
        	JOptionPane.showMessageDialog(null, "Debe seleccionar un metodo de pago",
    				  "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        }else{        
	        sql="INSERT INTO ventas (vent_cod, prod_cod, cli_cod, vent_detalle, vent_cantidad, vent_precunitario, vent_prectotal, vent_respon, vent_tipopago) VALUES (?,?,?,?,?,?,?,?,?)";
	        try {
	            PreparedStatement psd= cn.prepareStatement(sql);
	            psd.setString(1,numventa);
	            psd.setString(2,codcli);
	            psd.setString(3,codproduct);
	            psd.setString(4,detallevent);
	            psd.setString(5,cantventa);
	            psd.setString(6,unitventa);
	            psd.setString(7,totalventa);
	            psd.setString(8,value1);
	            psd.setString(9,value2);
	            //psd.setDate(8,sdf.format(fechaventa.getDate()));
	            int n=psd.executeUpdate();
	            if(n>0){
	        	 	JOptionPane.showMessageDialog(null,"REGISTRO GUARDADO CON EXITO!");
	            }
		     } catch (Exception e) {
		        JOptionPane.showMessageDialog(null, "Error:"+e);
		     }
        }
        //codventa=codventa+1;
    }
    void eliminar(){
        conectar cc=new conectar();
        Connection cn= cc.conexion();
        codvent=ventbuscar.getText();
        String sql ="DELETE FROM ventas WHERE vent_cod=?";
        int resp;
        resp = JOptionPane.showConfirmDialog(null,"¿Realmente desea eliminar?","Alerta!", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION){
        try {
            PreparedStatement psd= cn.prepareStatement(sql);
            psd.setString(1,codvent);
            int x= psd.executeUpdate();
            if(x>0){
                JOptionPane.showMessageDialog(null,"Registro eliminado correctamente");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error :"+e);
        }
        }
        cargarventas("");
    }
    void modificar(){
        conectar cc= new conectar();
        Connection cn= cc.conexion();
        /*codd=t_codigo.getText();
        insu=t_insumos.getText();
        cant=t_cantidad.getText();
        preci=t_precio.getText();
        iva=t_iva.getText();*/
        String sql="UPDATE ventas SET vent_detalle='"+t_detalles.getText()+"', vent_cantidad='"+t_cantidad.getText()+"', vent_precunitario= '"+t_preunit.getText()+"', vent_prectotal='"+t_prectotal.getText()+"', combo_respons='"+combo_respons.getSelectedItem().toString()+"', vent_tipopago='"+combopago.getSelectedItem().toString()+"' WHERE vent_cod='"+ventbuscar.getText()+"'";
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error :"+e);
        }
        cargarventas("");
        }
    }
    void habilitar(){
        t_codcli.setEnabled(true);
        cod_producto.setEnabled(true);
        t_detalles.setEnabled(true);
        t_cantidad.setEnabled(true);
        t_preunit.setEnabled(true);
        t_prectotal.setEnabled(true);
        combo_respons.setEnabled(true);
        combopago.setEnabled(true);        
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
    void limpiar(){
        t_codcli.setText("");
        cod_producto.setText("");
        t_detalles.setText("");
        t_cantidad.setText("");
        t_preunit.setText("");
        t_prectotal.setText("");
        combo_respons.setSelectedIndex(0);
        combopago.setSelectedIndex(0);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        t_prectotal = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        t_detalles = new javax.swing.JTextField();
        t_cantidad = new javax.swing.JTextField();
        t_preunit = new javax.swing.JTextField();
        combo_respons = new javax.swing.JComboBox();
        combopago = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        cod_producto = new javax.swing.JTextField();
//        btn_modificar = new javax.swing.JButton();
        btn_ventatras = new javax.swing.JButton();
        btn_limpiar = new javax.swing.JButton();
        btn_guardar = new javax.swing.JButton();
        t_codcli = new javax.swing.JTextField();
        btn_nuevo = new javax.swing.JButton();
        t_codventa = new javax.swing.JTextField();
//        btn_eliminar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        ventbuscar = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        //jComboBox1 = new javax.swing.JComboBox();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tab_ventas = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        t_datos = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        t_cliente = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(null);

        jLabel1.setText("N� de Venta");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(12, 16, 75, 14);
        jPanel1.add(t_prectotal);
        t_prectotal.setBounds(122, 261, 107, 24);

        jLabel2.setText("DNI Cliente");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(12, 66, 96, 14);

        jLabel3.setText("Detalles Producto");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(12, 153, 104, 14);

        jLabel4.setText("Cantidad");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(12, 188, 75, 14);

        jLabel5.setText("Precio Unitario");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(12, 226, 96, 14);

        jLabel6.setText("Precio Total");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(12, 267, 75, 14);

        t_detalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_detallesActionPerformed(evt);
            }
        });
        jPanel1.add(t_detalles);
        t_detalles.setBounds(122, 147, 107, 24);

        t_cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                t_cantidadKeyPressed(evt);
            }
        });
        jPanel1.add(t_cantidad);
        t_cantidad.setBounds(122, 185, 107, 24);
        jPanel1.add(t_preunit);
        t_preunit.setBounds(122, 223, 107, 24);

        combo_respons.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione","Responsable Inscripto", "Consumidor Final", "Monotributista" }));
        jPanel1.add(combo_respons);
        combo_respons.setBounds(2, 299, 132, 26);

        combopago.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione","Efectivo", "Tarj. de Credito" }));
        jPanel1.add(combopago);
        combopago.setBounds(2, 332, 132, 26);

        jLabel7.setText("Codigo Producto");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(12, 112, 96, 14);
        jPanel1.add(cod_producto);
        cod_producto.setBounds(122, 109, 107, 24);

//        btn_modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Formularios/iconos/1438381254_Modify.png"))); // NOI18N
//        btn_modificar.setText("Modificar");
//        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                btn_modificarActionPerformed(evt);
//            }
//        });
//        jPanel1.add(btn_modificar);
//        btn_modificar.setBounds(2, 380, 105, 33);

//        btn_ventatras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Formularios/iconos/1438376156_Log Out.png"))); // NOI18N
        btn_ventatras.setText("Volver");
        btn_ventatras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ventatrasActionPerformed(evt);
            }
        });
        jPanel1.add(btn_ventatras);
        btn_ventatras.setBounds(2, 515, 105, 32);

//        btn_limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Formularios/iconos/1438381989_edit-clear.png"))); // NOI18N
        btn_limpiar.setText("Limpiar");
        btn_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limpiarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_limpiar);
        btn_limpiar.setBounds(117, 420/*420*/, 107, 31);

        //btn_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Formularios/iconos/1438381897_floppy_disk_save.png"))); // NOI18N
        btn_guardar.setText("Guardar");
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_guardar);
        btn_guardar.setBounds(117, 380/*420*/, 107, 33);
        jPanel1.add(t_codcli);
        t_codcli.setBounds(122, 63, 107, 24);

        //btn_nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Formularios/iconos/1438381852_add.png"))); // NOI18N
        btn_nuevo.setText("Nuevo");
        btn_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoActionPerformed(evt);
            }
        });
        jPanel1.add(btn_nuevo);
        btn_nuevo.setBounds(2, 380, 105, 33);
        jPanel1.add(t_codventa);
        t_codventa.setBounds(122, 13, 107, 24);

//        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Formularios/iconos/1414193621_gtk-cancel.png"))); // NOI18N
//        btn_eliminar.setText("Eliminar");
//        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                btn_eliminarActionPerformed(evt);
//            }
//        });
//        jPanel1.add(btn_eliminar);
//        btn_eliminar.setBounds(117, 380, 107, 32);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 10, 250, 550);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Ventas"));
        jPanel3.setLayout(null);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar"));
        jPanel2.setLayout(null);
        jPanel2.add(ventbuscar);
        ventbuscar.setBounds(15, 28, 135, 24);

        jButton3.setText("Mostrar Todo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);
        jButton3.setBounds(190, 27, 120, 23);

        //jPanel2.add(jComboBox1);
//        jComboBox1.setBounds(16, 27, 91, 22);

        jPanel3.add(jPanel2);
        jPanel2.setBounds(6, 39, 400, 67);

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

        jTabbedPane1.addTab("Ventas", jScrollPane2);

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
        t_datos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_datosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(t_datos);

        jTabbedPane1.addTab("Productos", jScrollPane1);

        t_cliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        t_cliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_clienteMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(t_cliente);

        jTabbedPane1.addTab("Clientes", jScrollPane3);

        jPanel3.add(jTabbedPane1);
        jTabbedPane1.setBounds(16, 124, 981, 403);

        getContentPane().add(jPanel3);
        jPanel3.setBounds(250, 0, 1003, 563);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ventatrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ventatrasActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_ventatrasActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        guardar();
        //deshabilitar();
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void t_detallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_detallesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_detallesActionPerformed

    private void btn_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoActionPerformed
        habilitar();
        /*String cod1=Integer.toString(codventa);
        t_codventa.setText(cod1);
        codventa=codventa+1;*/
    }//GEN-LAST:event_btn_nuevoActionPerformed

    private void t_clienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_clienteMouseClicked
int fila=t_cliente.getSelectedRow();
        if(fila>=0){
            t_codcli.setText(t_cliente.getValueAt(fila,0).toString());           
        }else{
            JOptionPane.showMessageDialog(null,"No se seleccionó ninguna fila");
            }      }//GEN-LAST:event_t_clienteMouseClicked

    private void t_datosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_datosMouseClicked
        habilitar();
        int fila=t_datos.getSelectedRow();
        if(fila>=0){
            cod_producto.setText(t_datos.getValueAt(fila,0).toString());
            t_detalles.setText(t_datos.getValueAt(fila,1).toString());
            t_preunit.setText(t_datos.getValueAt(fila,3).toString());
            
        }else{
            JOptionPane.showMessageDialog(null,"No se seleccionó ninguna fila");
            }      }//GEN-LAST:event_t_datosMouseClicked

    private void tab_ventasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_ventasMouseClicked
    int fila=tab_ventas.getSelectedRow();
        if(fila>=0){
            t_detalles.setText(tab_ventas.getValueAt(fila,0).toString());
            t_cantidad.setText(tab_ventas.getValueAt(fila,1).toString());
            //t_preunit.setText(tab_ventas.getValueAt(fila,2).toString());
            t_prectotal.setText(tab_ventas.getValueAt(fila,2).toString());            
        }else{
            JOptionPane.showMessageDialog(null,"No se seleccionó ninguna fila");
            }    
    }//GEN-LAST:event_tab_ventasMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        cargarventas("");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        modificar();// TODO add your handling code here:
    }//GEN-LAST:event_btn_modificarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        eliminar();// TODO add your handling code here:
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btn_limpiarActionPerformed

    private void t_cantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_cantidadKeyPressed
            /*precio=t_preunit.getText();
            cantidad=t_cantidad.getText();
            preci=Integer.parseInt(precio);
            cant=Integer.parseInt(cantidad);
            total=(cant*preci);
            tot=Integer.toString(total);
            t_prectotal.setText(tot);*/
    }//GEN-LAST:event_t_cantidadKeyPressed

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
            java.util.logging.Logger.getLogger(RegistrarVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistrarVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistrarVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistrarVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistrarVentas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
//    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_limpiar;
//    private javax.swing.JButton btn_modificar;
    private javax.swing.JButton btn_nuevo;
    private javax.swing.JButton btn_ventatras;
    private javax.swing.JTextField cod_producto;
    private javax.swing.JComboBox combo_respons;
    private javax.swing.JComboBox combopago;
    private javax.swing.JButton jButton3;
    //private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField t_cantidad;
    private javax.swing.JTable t_cliente;
    private javax.swing.JTextField t_codcli;
    private javax.swing.JTextField t_codventa;
    private javax.swing.JTable t_datos;
    private javax.swing.JTextField t_detalles;
    private javax.swing.JTextField t_prectotal;
    private javax.swing.JTextField t_preunit;
    private javax.swing.JTable tab_ventas;
    private javax.swing.JTextField ventbuscar;
    // End of variables declaration//GEN-END:variables
	
	public void mostrarSesion() {
		
	}
}
