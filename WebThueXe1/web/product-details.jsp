<%-- 
    Document   : product-details
    Created on : Jun 26, 2023, 12:09:13 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"></jsp:include>
        <div class="container">
            <h1 style="text-align: center">Product Details</h1>
            <div class="row">
                <div class="col-md-6">
                    <img src="${product.image}" class="card-img-top" alt="Shoe 1">
                </div>
                <div class="col-md-6">
                    <h2>${product.name}</h2>
               
                    <p>Description: ${product.description}</p>
                    <p>$${product.price}</p>
                    <!--<form action="check-out" method="POST">-->
            
                        <a href="check-out?id=${product.id}" class="btn btn-primary">Rent Now</a>
                    <!--</form>-->
                </div>
            </div>
        </div>

<jsp:include page="footer.jsp"></jsp:include>