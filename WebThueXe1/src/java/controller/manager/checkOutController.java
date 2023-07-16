/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager;

import dao.AccountDAO;
import dao.ProductDAO;
import entites.Account;
import entites.Product;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "checkOutController", urlPatterns = {"/check-out"})
public class checkOutController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet checkOutController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet checkOutController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        ProductDAO dao = new ProductDAO();
        Product p = dao.getProductByID(id);
        request.setAttribute("p", p);
        AccountDAO aDao = new AccountDAO();
        Account a = aDao.getAccountByID(account.getId());
        request.setAttribute("acc", a);
        request.getRequestDispatcher("check-out.jsp").forward(request, response);

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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the cart information from the request parameters
//     
//        if (account != null) {
//            String[] productIds = request.getParameterValues("productId[]");
//            String[] productNames = request.getParameterValues("productName[]");
//            String[] productPrices = request.getParameterValues("productPrice[]");
//            String[] productQuantities = request.getParameterValues("productQuantity[]");
//            String total = request.getParameter("total");
//
//            // Set the cart information as attributes in the request
//            request.setAttribute("productIds", productIds);
//            request.setAttribute("productNames", productNames);
//            request.setAttribute("productPrices", productPrices);
//            request.setAttribute("productQuantities", productQuantities);
//            request.setAttribute("total", total);
//           
//            // Forward the request to check-out.jsp
//        } else {
//            response.sendRedirect("home");
//        }
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
