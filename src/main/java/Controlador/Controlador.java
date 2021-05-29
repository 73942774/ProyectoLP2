
package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDAO;
import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import Modelo.Producto;
import Modelo.ProductoDAO;
import Modelo.Venta;
import Modelo.VentaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Equipo
 */
@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {

     //Instancias Modelo
    Producto pr = new Producto();
    ProductoDAO prdao = new ProductoDAO();
    
    Cliente cl = new Cliente();
    ClienteDAO cdao = new ClienteDAO();
    
    Empleado em = new Empleado();
    EmpleadoDAO edao = new EmpleadoDAO();
    int ide;
    
    
    
    Venta v = new Venta();
    List<Venta> lista = new ArrayList<>();
    int item;
    int cod;
    String descripcion;
    double precio;
    int cant;
    double subtotal;
    double total;
    String numeroserie;
    VentaDAO vdao = new VentaDAO();
    VentaDAO vdao2 = new VentaDAO();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String menu = request.getParameter("menu");//creacion de varibale menu
        String accion = request.getParameter("accion");//creacion de variable accion
        
        //condiciones 
        if (menu.equals("Principal")) {//peticion es principal redirecciona a principal.jsp
            request.getRequestDispatcher("Principal.jsp").forward(request, response);
        }
        if (menu.equals("Empleado")) {////peticion empleado
            
            switch (accion) {//opciones
                
                case "listar"://listará todos los empleados
                    //creacion de lista de objetos e invocando metodos
                    List lista = edao.listar();
                    request.setAttribute("empleados", lista);
                    break;
                case "Agregar":
                    //capturando parametros
                    String dni=request.getParameter("txtDni");
                    String nom=request.getParameter("txtNombres");
                    String tel=request.getParameter("txtTelefono");
                    String est=request.getParameter("txtEstado");
                    String user=request.getParameter("txtUsuario");
                    //ingresando
                    em.setDni(dni);
                    em.setNom(nom);
                    em.setTel(tel);
                    em.setEstado(est);
                    em.setUser(user);
                    edao.agregar(em);
                    //accion listar
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=listar").forward(request, response);
                    break;
                case "Editar"://indentifica al id
                    
                     ide=Integer.parseInt(request.getParameter("id"));//capturando el id
                     Empleado e=edao.ListarId(ide);//invoca al metodo listarid que va a listar por el id ingresado
                     request.setAttribute("empleado", e);
                     //accion listar
                     request.getRequestDispatcher("Controlador?menu=Empleado&accion=listar").forward(request, response);
                    break;
                case "Actualizar":
                    //captura nuevos datos del empleado q se quiere actualizar
                    String dni1=request.getParameter("txtDni");
                    String nom1=request.getParameter("txtNombres");
                    String tel1=request.getParameter("txtTelefono");
                    String est1=request.getParameter("txtEstado");
                    String user1=request.getParameter("txtUsuario");
                    //ingresa los datos
                    em.setDni(dni1);
                    em.setNom(nom1);
                    em.setTel(tel1);
                    em.setEstado(est1);
                    em.setUser(user1);
                    em.setId(ide);
                    //invocando metodo actualizar
                    edao.actualizar(em);
                    //accion listar
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=listar").forward(request, response);
                    break;
                
                case "Delete":
                    //captura del id del empleado a eliminar
                    ide=Integer.parseInt(request.getParameter("id"));
                    //invoca al metodo eliminar
                    edao.delete(ide);
                    //accion listar
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=listar").forward(request, response);
                    break;
                default:
                    //devuelve mensaje de error si es ningun caso de los anteriores
                    throw new AssertionError();
            }//fin switch
            //muestra la pagina empleado.jsp
            request.getRequestDispatcher("Empleado.jsp").forward(request, response);
        }
        if (menu.equals("Cliente")) {
            switch (accion) {
                case "listar":
                    List lista = cdao.listar();
                    request.setAttribute("clientes", lista);
                    break;
                case "Agregar":
                    String dni=request.getParameter("txtDni");
                    String nom=request.getParameter("txtNombres");
                    String dir=request.getParameter("txtDireccion");
                    String est=request.getParameter("txtEstado");
                    cl.setDni(dni);
                    cl.setNom(nom);
                    cl.setDir(dir);
                    cl.setEstado(est);
                    cdao.agregar(cl);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=listar").forward(request, response);
                    break;
                case "Editar":
                     ide=Integer.parseInt(request.getParameter("id"));
                     Cliente c=cdao.ListarId(ide);
                     request.setAttribute("cliente", c);
                     request.getRequestDispatcher("Controlador?menu=Cliente&accion=listar").forward(request, response);
                    break;
                case "Actualizar":
                    
                    String dni1=request.getParameter("txtDni");
                    String nom1=request.getParameter("txtNombres");
                    String dir1=request.getParameter("txtDireccion");
                    String est1=request.getParameter("txtEstado");
                    cl.setDni(dni1);
                    cl.setNom(nom1);
                    cl.setDir(dir1);
                    cl.setEstado(est1);
                    cl.setId(ide);
                    cdao.actualizar(cl);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=listar").forward(request, response);
                    break;
                case "Delete":
                    ide=Integer.parseInt(request.getParameter("id"));
                    cdao.delete(ide);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Cliente.jsp").forward(request, response);
        }
        if (menu.equals("Producto")) {
            switch (accion) {
                case "listar":
                    List lista = prdao.listar();
                    request.setAttribute("productos", lista);
                    break;
                case "Agregar":
                    String nom=request.getParameter("txtNombres");
                    double precio=Double.parseDouble(request.getParameter("Precio"));
                    int stock=Integer.parseInt(request.getParameter("Cantidad"));
                    String est=request.getParameter("txtEstado");
                   
                    pr.setNom(nom);
                    pr.setPrecio(precio);
                    pr.setStock(stock);
                    pr.setEstado(est);
                    prdao.agregar(pr);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=listar").forward(request, response);
                    break;
                case "Editar":
                     ide=Integer.parseInt(request.getParameter("id"));
                     Producto p=prdao.listarId(ide);
                     request.setAttribute("producto", p);
                     request.getRequestDispatcher("Controlador?menu=Producto&accion=listar").forward(request, response);
                    break;
                case "Actualizar":
                    
                   
                    String nom1=request.getParameter("txtNombres");
                    double preciod1=Double.parseDouble(request.getParameter("Precio"));
                    int stock1=Integer.parseInt(request.getParameter("Cantidad"));
                    String est1=request.getParameter("txtEstado");
                  
                    
                    pr.setNom(nom1);
                    pr.setPrecio(preciod1);
                    pr.setStock(stock1);
                    pr.setEstado(est1);
                    pr.setId(ide);
                    prdao.actualizar(pr);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=listar").forward(request, response);
                    break;
                case "Delete":
                    ide=Integer.parseInt(request.getParameter("id"));
                    prdao.delete(ide);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Producto.jsp").forward(request, response);
        }
        if (menu.equals("Home")) {
            //no tiene acciones, solo muestra
            request.getRequestDispatcher("Home.jsp").forward(request, response);
        }
        if (menu.equals("RegistrarVenta")) {
            switch (accion) {
                //otras acciones
                case "BuscarCliente"://buscando cliente por el dni
                    String dni=request.getParameter("codigocliente");
                    cl.setDni(dni);
                    cl=cdao.buscar(dni);
                    request.setAttribute("cl",cl);
                    break;
                    
                case "BuscarProducto"://buscando porducto con el codigo
                    int id = Integer.parseInt(request.getParameter("codigoproducto"));
                    pr = prdao.listarId(id);
                    request.setAttribute("cl",cl);
                    request.setAttribute("producto", pr);
                    request.setAttribute("lista", lista);
                    request.setAttribute("total", total);
                    request.setAttribute("nserie", numeroserie);
                    break;
                case "AgregarProducto"://insertando producto en la boleta con el numero de serie y monto total
                    
                    request.setAttribute("cl",cl);
                    //request.setAttribute("producto", pr);
                    total = 0.0;
                    item = item + 1;
                    cod = pr.getId();
                    descripcion = request.getParameter("nombreproducto");
                    precio = Double.parseDouble(request.getParameter("precio"));
                    cant = Integer.parseInt(request.getParameter("cant"));
                    subtotal = precio * cant;

                    v = new Venta();
                    v.setItem(item);
                    v.setIdproducto(cod);
                    v.setDescripcionP(descripcion);
                    v.setPrecio(precio);
                    v.setCantidad(cant);
                    v.setSubtotal(subtotal);
                    lista.add(v);
                    for (int i = 0; i < lista.size(); i++) {
                        total = total + lista.get(i).getSubtotal();
                    }
                    request.setAttribute("nserie", numeroserie);
                    request.setAttribute("total", total);
                    request.setAttribute("lista", lista);
                    break;
                    
                case "GenerarVenta"://genra la venta con el dia y monto
                    
                    v.setIdcliente(cl.getId());//captura el id ingresando el dni del cliente
                    v.setIdempleado(1);//id del empleado 1 por defecto
                    
                    v.setNumserie(numeroserie);
                    java.util.Date ahora=new java.util.Date();
                    SimpleDateFormat formateador = new SimpleDateFormat("yyy/MM/dd");
                    v.setFecha(formateador.format(ahora));
                    v.setMonto(total);              
                    v.setEstado("1");
                    vdao.guardarVenta(v);

                    //Guardar Detalle venta --------------------------------
                    int idv = Integer.parseInt(vdao.IdVentas());
                    for (int i = 0; i < lista.size(); i++) {
                        v = new Venta();
                        v.setId(idv);
                        v.setIdproducto(lista.get(i).getIdproducto());
                        v.setCantidad(lista.get(i).getCantidad());
                        v.setPrecio(lista.get(i).getPrecio());
                        vdao.guardarDetalleVentas(v);
                    }

                    //Actualizar Stock --------------------------------
                    //por cada venta se disminuye del stock
                    for (int i = 0; i < lista.size(); i++) {
                        Producto pr = new Producto();
                        int cantidad = lista.get(i).getCantidad();
                        int idproducto = lista.get(i).getIdproducto();
                        ProductoDAO aO = new ProductoDAO();
                        pr = aO.buscar(idproducto);
                        int sac = pr.getStock()-cantidad;
                        aO.actualizarstock(idproducto, sac);
                    }
                    
                    lista = new ArrayList<>();
                    break;
                default:
                    lista = new ArrayList<>();
                    item=0;
                    numeroserie = vdao.GenerarSerie();
                    if (numeroserie == null) {
                        numeroserie = "00001";
                        request.setAttribute("nserie", numeroserie);
                    } else {
                        int incrementar = Integer.parseInt(numeroserie);
                        numeroserie = vdao2.NumeroSerie(incrementar);
                        request.setAttribute("nserie", numeroserie);
                    }
                      
                    request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
            }//fin del switch
            //actualiza y muestra la ventana registrarventa
            request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
        }
        if (menu.equals("Ordenes")){
            switch (accion) {
                case "listar"://muestra las ventas que se han realizado
                    List lista = vdao.listar();
                    request.setAttribute("ventas", lista);
                    break;
                
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Ordenes.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
