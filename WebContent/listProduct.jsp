<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>All Products</title>
</head>
<body>
    <p><a href="Controller?action=insert">Add Product</a></p>
    <table border=2>
        <thead>
            <tr>
                <th>Product Id </th>
                <th>Product Name</th>
                <th>Product Description</th>
                <th>Related Products</th>
                <th colspan=2>Edit or Delete</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${products}" var="product">
                <tr>
                    <td><c:out value="${product.productid}" /></td>
                    <td><c:out value="${product.productname}" /></td>
                    <td><c:out value="${product.productdesc}" /></td>
                    <td><c:out value="${product.relatedproducts}" /></td>
                    <td><a href="Controller?action=edit&productId=<c:out value="${product.productid}"/>">Update</a></td>
                    <td><a href="Controller?action=delete&productId=<c:out value="${product.productid}"/>">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>    
</body>
</html>