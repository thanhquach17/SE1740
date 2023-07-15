<%-- 
    Document   : header.jsp
    Created on : Jun 25, 2023, 11:37:39 PM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>
        <style>
            .navbar-nav {
                flex-direction: row;
                align-items: center;
            }

            .navbar-nav .nav-link {
                padding-right: 0.5rem;
                padding-left: 0.5rem;
            }
            .badge {
                position: absolute;
                top: -8px;
                right: -8px;
                padding: 4px 6px;
                border-radius: 50%;
                background-color: red;
                color: white;
            }
        </style>

    </head>
    <body>

        <nav class="navbar navbar-expand-lg navbar-dark bg-dark" style=" margin-bottom: 20px">
            <div class="container">
                <ul class="navbar-nav">
                    <a class="navbar-brand" href="home">Around the town</a>
                    <c:if test="${account.role == 1}">
                        <li class="nav-item">
                            <a class="nav-link" href="product-management">Product Management</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="account-management">Account Management</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="order-management">Order Management</a>
                        </li>
                    </c:if>
                </ul>
                <ul class="navbar-nav">
             
                    <li class="nav-item">
                        <a class="nav-link" href="user-profile">
                            <i class="bi bi-person-fill"></i>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="log-out">
                            <i class="bi bi-door-closed-fill"></i>
                        </a>
                    </li>
                </ul>
            </div>
        </nav>