<%-- 
    Document   : home
    Created on : Jun 25, 2023, 11:44:09 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%--
    Document   : home
    Created on : Jun 25, 2023, 11:44:09 PM
    Author     : ADMIN
--%>


<div class="container">
    <form action="home" method="get" class="mb-3">
        <div class="input-group">
            <input type="text" class="form-control" name="search" placeholder="Search for products">
            <input type="hidden" name="page" value="${currentPage}">
            <button class="btn btn-primary" type="submit">Search</button>
        </div>
    </form>
    <div class="row mt-5">
        <div class="col-md-3">
            <div class="card">
                <div class="card-header">
                    Filter by Category
                </div>
                <div class="card-body">
                    <form>
                        <div class="mb-3">
                            <select class="form-select" name="category">
                                <option value="">All</option>
                                <c:forEach items="${listC}" var="c">
                                    <option value="${c.categoryId}" <c:if test="${param.category == c.categoryId}">selected</c:if>>${c.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Apply</button>
                        </div>
                    </form>
                </div> 
            </div>
            <div class="card mt-3">
                <div class="card-header">
                    Sort
                </div>
                <div class="card-body">
                    <form>
                        <div class="mb-3">
                            <select class="form-select" name="sort">
                                <option value="asc">Lowest to Highest</option>
                                <option value="desc">Highest to Lowest</option>
                                <option value="new">Newest Product</option>
                                <option value="sale">Best Saler</option>
                            </select>
                        </div>
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Apply</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-9">
            <div class="row">
                <c:forEach items="${listP}" var="product">
                    <div class="col-md-4 mt-3">
                        <div class="card">

                            <a href="product-details?productId=${product.id}">
                                <img src="${product.image}" class="card-img-top" alt="Product Image"height="300" >
                            </a>
                            <div class="card-body">
                                <h5 class="card-title">${product.name}</h5>
                                <p class="card-text">Price/day: $${product.price}</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <nav class="mt-3" aria-label="Page navigation example">
                <ul class="pagination">
                    <!-- Disable the "Previous" link if on the first page -->
                    <c:if test="${currentPage > 1}">
                        <li class="page-item">
                            <a class="page-link" href="?category=${param.category}&amp;search=${param.search}&amp;sort=${param.sort}&amp;page=${currentPage - 1}">Previous</a>
                        </li>
                    </c:if>

                    <!-- Display the page numbers as links -->
                    <c:forEach var="pageNum" begin="1" end="${totalPages}">
                        <li class="page-item <c:if test='${pageNum == currentPage}'>active</c:if>">
                            <a class="page-link" href="?category=${param.category}&amp;search=${param.search}&amp;sort=${param.sort}&amp;page=${pageNum}">${pageNum}</a>
                        </li>
                    </c:forEach>

                    <!-- Disable the "Next" link if on the last page -->
                    <c:if test="${currentPage < totalPages}">
                        <li class="page-item">
                            <a class="page-link" href="?category=${param.category}&amp;search=${param.search}&amp;sort=${param.sort}&amp;page=${currentPage + 1}">Next</a>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>