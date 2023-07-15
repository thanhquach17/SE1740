<%--
    Document   : check-out
    Created on : Jun 26, 2023, 12:31:44 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"></jsp:include>
    <!DOCTYPE html>
    <html>
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css">
            <title>Checkout</title>
            <style>
                .container {
                    display: flex;
                    justify-content: space-between;
                }
                .shipping-info {
                    width: 55%;
                }
                .order-details {
                    width: 35%;
                }
                .product-image {
                    max-width: 50px;
                    max-height: 50px;
                    margin-right: 10px;
                }
            </style>
        </head>
        <body>
            <form action="order" method="post">
                <div class="container">
                    <div class="shipping-info">
                        <h2>Renting Information</h2>
                        <div class="mb-3">
                            <label for="name" class="form-label">Full Name</label>
                            <input type="hidden" class="form-control" name="accountId" required value="${acc.id}">
                            <input type="hidden" class="form-control" name="productId" required value="${p.id}">
                        <input type="text" class="form-control" id="name" required value="${acc.fullname}">
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email Address</label>
                        <input type="email" class="form-control" id="email" value="${acc.email}" required>
                    </div>
                    <div class="mb-3">
                        <label for="phone" class="form-label">Phone</label>
                        <input type="text" class="form-control" id="phone" value="${acc.phone}" required>
                    </div>
                    <div class="mb-3">
                        <label for="address" class="form-label">Address</label>
                        <input type="text" class="form-control" id="address" value="${acc.address}" required>
                    </div>
                    <div class="mb-3">
                        <label for="address" class="form-label">Time Start </label>
                        <input type="datetime-local" name="start" id="start_datetime" value="" required>

                    </div>
                    <div class="mb-3">
                        <label for="address" class="form-label">Time End </label>
                        <input type="datetime-local" name="end" id="">
                    </div>
                    <button type="submit" class="btn btn-primary">Place Order</button>
                </div>
                <div class="order-details">
                    <h2 style="text-align: center">Order Details</h2>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Product</th>
                                <th>Price</th>
                                <th>Image</th>
                            </tr>
                        </thead>
                        <tbody>

                            <tr>
                                <td>
                                    ${p.name}
                                </td>
                                <td>${p.price}</td>
                        <input type="hidden" value="${p.price}" name="price">
                                <td> <img style="max-width: 59px" src="${p.image}" alt="alt" /></td>
                        </tbody>
                    </table>
                </div>
            </div>
        </form>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>

</html>