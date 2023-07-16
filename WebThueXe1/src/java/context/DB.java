/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import dao.AccountDAO;

/**
 *
 * @author Quách Thành
 */
public class DB {
    
    
    public static void main(String[] args) {
        AccountDAO dao = new AccountDAO();
        System.out.println(dao.updatePassword(11, "11"));
//        System.out.println(dao.updateAccount(1, "Thai", "thai@gmail.com", "HN"));
//        System.out.println(a);
    }
}
