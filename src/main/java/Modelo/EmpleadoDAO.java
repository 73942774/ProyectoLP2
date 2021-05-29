package Modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
//clase que va interactura con la base de datos

public class EmpleadoDAO {
    
    Conexion cn = new Conexion();//creacion de variable conexion
    Connection con;//creacion de variable connection
    PreparedStatement ps;////creacion de variable preparedstatement para consultas sql
    ResultSet rs;////creacion de variable resultset que guarda resultado de las consultas
    int r;

    public Empleado validar(String user, String dni) {//creacion de metodo que va permitir validar el user y contraseña
        Empleado em = new Empleado();//instanciando la clase empleado
        String sql = "select * from empleado where User=? and Dni=?";//consulta sql 
        try {//excepeciones
            con = cn.Conexion();
            
            ps = con.prepareStatement(sql);//preparando consulta
            
            ps.setString(1, user);//dando parametros user y dni
            ps.setString(2, dni);
            
            rs = ps.executeQuery();//ejecutando consulta y guardandola
            while (rs.next()) {//recorrer los datos
                
                //capturando datos
                em.setId(rs.getInt("IdEmpleado"));
                em.setUser(rs.getString("User"));
                em.setDni(rs.getString("Dni"));
                em.setNom(rs.getString("Nombres"));
            }
        } catch (Exception e) {
        }
        return em;//retorna el empleado validado
    }

    public List listar() {//imprime los empleados
        String sql = "select * from empleado";
        List<Empleado> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Empleado em = new Empleado();
                em.setId(rs.getInt(1));
                em.setDni(rs.getString(2));
                em.setNom(rs.getString(3));
                em.setTel(rs.getString(4));
                em.setEstado(rs.getString(5));
                em.setUser(rs.getString(6));
                lista.add(em);
            }
        } catch (Exception e) {

        }
        return lista;
    }

    public int agregar(Empleado em) {//inserta empleado ingresando los  parametros
        String sql = "insert into empleado (Dni, Nombres, Telefono, Estado, User)values(?,?,?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, em.getDni());
            ps.setString(2, em.getNom());
            ps.setString(3, em.getTel());
            ps.setString(4, em.getEstado());
            ps.setString(5, em.getUser());
            ps.executeUpdate();
        } catch (Exception e) {

        }
        return r;
    }

    public Empleado ListarId(int id) {//llamar por id
        Empleado emp = new Empleado();
        String sql = "select * from empleado where IdEmpleado="+id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                emp.setDni(rs.getString(2));
                emp.setNom(rs.getString(3));
                emp.setTel(rs.getString(4));
                emp.setEstado(rs.getString(5));
                emp.setUser(rs.getString(6));

            }
        } catch (Exception e) {
        }
        return emp;
    }

    public int actualizar(Empleado em) {//actualizar empleado ingresando parametros
        String sql = "update empleado set Dni=?, Nombres=?, Telefono=?, Estado=?, User=? where IdEmpleado=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, em.getDni());
            ps.setString(2, em.getNom());
            ps.setString(3, em.getTel());
            ps.setString(4, em.getEstado());
            ps.setString(5, em.getUser());
            ps.setInt(6, em.getId());
            ps.executeUpdate();
        } catch (Exception e) {

        }
        return r;

    }

    public void delete(int id) {//eliminar empleado por el id
        String sql = "delete  from empleado where IdEmpleado="+id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }

    }
}
