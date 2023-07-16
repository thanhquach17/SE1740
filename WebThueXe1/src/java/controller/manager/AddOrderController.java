/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.manager;

import dao.OrderDAO;
import dao.ProductDAO;
import entites.Account;
import entites.Order;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "AddOrderController", urlPatterns = {"/order"})
public class AddOrderController extends HttpServlet {

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
            HttpSession session = request.getSession();
            Account account = (Account) session.getAttribute("account");
            if (account != null) {
                OrderDAO orderDAO = new OrderDAO();
                int accountId = Integer.parseInt(request.getParameter("accountId"));
                int productId = Integer.parseInt(request.getParameter("productId"));
                double price = Double.parseDouble(request.getParameter("price"));
                LocalDateTime start = LocalDateTime.parse(request.getParameter("start"));
                LocalDateTime end = LocalDateTime.parse(request.getParameter("end"));
                Timestamp startTimestamp = Timestamp.valueOf(start);
                Timestamp endTimestamp = Timestamp.valueOf(end);
                Order order = new Order();
                order.setAccountId(accountId);
                order.setProductId(productId);
                order.setPrice(price);
                order.setStart(startTimestamp);
                order.setEnd(endTimestamp);

                boolean isAdded = orderDAO.addOrder(order);
                if (isAdded) {
                    ProductDAO dal = new ProductDAO();
                    dal.Rent(productId);
                    response.sendRedirect("thank");
                }

            } else {
                response.sendRedirect("home");
            }
        } catch (NumberFormatException | DateTimeParseException e) {
            // Handle the exception appropriately
            e.printStackTrace();
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
