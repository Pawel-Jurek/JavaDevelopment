/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pl.polsl.servlets;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pl.polsl.controller.GradesImporter;
import pl.polsl.model.InvalidGradeException;
import pl.polsl.model.StudentGradebook;

/**
 * FileServlet class
 * @author SuperStudent.PL
 */
@WebServlet("/getFile")
@MultipartConfig
public class FileServlet extends HttpServlet {
   

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     * Creates studentGradebook and imports data from the attached file, then redirects to the view with functions
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        var filePart = request.getPart("gradesFile");

        GradesImporter gradesImporter = new GradesImporter();
        Boolean exceptions = false;
        try{
            StudentGradebook studentGradebook = new StudentGradebook();
            if(filePart != null){
                exceptions = gradesImporter.importSubjects(studentGradebook, filePart);
            }
            HttpSession session = request.getSession();
            session.setAttribute("studentGradebook", exceptions ? null : studentGradebook);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/functions.jsp");
            dispatcher.forward(request, response);
        } catch(IOException | InvalidGradeException e){}
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
