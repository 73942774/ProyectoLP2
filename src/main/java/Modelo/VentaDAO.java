 package Modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



public class VentaDAO {
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps; 
    ResultSet rs;  
    int dato;
    String cnumero;
    int r;
     
     // METODOS SERIE ---------------------------
    
    public String NumeroSerie(int inc){//numero de serie
        this.dato=inc+1;
        if((this.dato>=10000)&(this.dato<=100000)){
            cnumero=""+dato;
        }
        if((this.dato>=1000)&(this.dato<=10000)){
            cnumero="0"+dato;
        }if((this.dato>=100)&(this.dato<=1000)){
            cnumero="00"+dato;
        }if((this.dato>=10)&(this.dato<=100)){
            cnumero="000"+dato;
        }
        if((this.dato<10)){
           cnumero="0000"+dato;
        }
        return cnumero;
    }
    
    
    public String GenerarSerie(){//generar serie de la venta
        String numeroserie="";
        String sql="select max(NumeroSerie) from ventas";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
               numeroserie=rs.getString(1);
            }
        } catch (Exception e) {
        }
        return  numeroserie;
    }
    

    
    public String IdVentas(){ //obtener el mayor id de las ventas
        String idventas="";
        String sql="select max(IdVentas) from ventas";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()){
               idventas=rs.getString(1);
            }
        } catch (Exception e) {
        }
        return idventas;
    }
    
    public int guardarVenta(Venta ve){//ingresa los elementos en la tabla ventas
        String sql="insert into ventas (IdCliente, IdEmpleado, NumeroSerie, FechaVentas, Monto, Estado)values(?,?,?,?,?,?)";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setInt(1, ve.getIdcliente());
            ps.setInt(2, ve.getIdempleado());
            ps.setString(3, ve.getNumserie());
            ps.setString(4, ve.getFecha());
            ps.setDouble(5, ve.getMonto());//le cambie getPrecio
            ps.setString(6, ve.getEstado());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }
    
    public int guardarDetalleVentas(Venta venta){//ingresar elementos en la tabla detalle venta
        String sql="insert into detalle_ventas (IdVentas, IdProducto, Cantidad, PrecioVenta) values(?,?,?,?)";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setInt(1, venta.getId());
            ps.setInt(2, venta.getIdproducto());
            ps.setInt(3, venta.getCantidad());
            ps.setDouble(4, venta.getPrecio());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }
    public List listar(){
        //lsitar ordenes
        String sql = "select * from ventas";
        List<Venta> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Venta ve = new Venta();
                ve.setId(rs.getInt(1));
                ve.setIdcliente(rs.getInt(2));
                ve.setIdempleado(rs.getInt(3));
                ve.setNumserie(rs.getString(4));
                ve.setFecha(rs.getString(5));
                ve.setMonto(rs.getDouble(6));
                ve.setEstado(rs.getString(7));;
                lista.add(ve);
            }
        } catch (Exception e) {

        }
        return lista;
    }
    
    
}
