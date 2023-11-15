package com.emergentes.controller;

import com.emergentes.bean.BeanContacto;
import com.emergentes.entidades.Contacto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        BeanContacto daoContacto = new BeanContacto();////creamos el uso
        int id;  /// ponemos una variable id para que no haya errores
        Contacto c = new Contacto();///creamos un objeto
        
        String action = request.getParameter("action") != null ? request.getParameter ("action") : "view";
        
        switch (action){
            
            case "add":
                request.setAttribute("contacto", c);
                request.getRequestDispatcher("contactos-edit.jsp").forward(request, response);
                break;
                
            case "edit":
                id =Integer.parseInt(request.getParameter("id"));///obtenemos parametro
                c= daoContacto.buscar(id);//en el uso, usamos el metodo buscar, usando nuestro parametro
                request.setAttribute("contacto", c);
                request.getRequestDispatcher("contactos-edit.jsp").forward(request, response);
                break;
                
            case "dele":
                id =Integer.parseInt(request.getParameter("id"));///obtenemos parametro
                daoContacto.eliminar(id);
                response.sendRedirect("MainController");
                break;
                
            case "view":
        List<Contacto> lista = daoContacto.listartodos();// el uso usara el metodo listartodos en la coleccion de datos de Contacto
        
        request.setAttribute("contactos", lista);/// definir atributo 
        request.getRequestDispatcher("contactos.jsp").forward(request, response);/// mandar el paramatro y mandarlo a jsp "contactos.jsp"
            default:
                break;          
        }    
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
      BeanContacto daoContacto = new BeanContacto();
      Contacto c= new Contacto();
      
      c.setId(Integer.parseInt(request.getParameter("id")));
      c.setNombre(request.getParameter("nombre"));
      c.setCorreo(request.getParameter("correo"));
      c.setTelefono(request.getParameter("telefono"));
      
      if(c.getId()==0){
          System.out.println("Registro Nuevo"); 
          daoContacto.insertar(c);
      }
      else{
          daoContacto.editar(c);
      }
      response.sendRedirect("MainController");  
    }

    //private void nuevo() {
        //BeanContacto dao = new BeanContacto();
        //Contacto c = new Contacto();
       // c.setNombre("Armando Terrazas");
        //c.setTelefono("75970595");
        //c.setCorreo("armando@gmail.com");

        //dao.insertar(c);

    //}
    
    //private void editar(){
        //BeanContacto dao = new BeanContacto();
        ///id para editar
        //Integer id=2;
        
        //Contacto c = dao.buscar(id);
        //c.setNombre("Tapia Ricardo");
        //c.setCorreo("rycky@gmail.com");
        
       // dao.editar(c);
    
    //}
    
    //private void eliminar(){
    //BeanContacto dao = new BeanContacto();
    //id para eliminar
    //Integer id =3 ;
    //dao.eliminar(id);
    //}

   // private void mostrar() {
        //BeanContacto dao = new BeanContacto();

        //List<Contacto> lista = dao.listartodos();

        //for (Contacto item : lista) {
        //    System.out.println(item.toString());
       // }

    //}
    
    
}
