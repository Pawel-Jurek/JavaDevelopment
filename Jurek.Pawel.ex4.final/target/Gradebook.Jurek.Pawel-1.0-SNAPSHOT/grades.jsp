<%-- 
    Document   : grades
    Description: displaying grades from given subject
    Created on : 27 lis 2023, 12:10:17
    Author     : pawel
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>${pageTitle}</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
</head>
<body style="padding: 10px">
    <h1 style="text-align: center;">${pageTitle}</h1>

    <table class="table" border="1">
        <tr class="table-secondary">
            <th>Grade Value</th>
            <th>Weight</th>
        </tr>
        <c:if test="${not empty grades}">
            <c:forEach var="grade" items="${grades}">
                <c:set var="trClass" value="${grade.value >= 1.75 ? 'success' : 'danger'}" />
                <tr class="table-${trClass}">
                    <td>${grade.value}</td>
                    <td>${grade.weight}</td>
                </tr>
            </c:forEach>
            <tr class="table">
                <td colspan="your_column_count"><strong>Your average: <fmt:formatNumber type="number" value="${average}" pattern="#,##0.00" /></strong></td>
            </tr>
                
        </c:if>
        <c:if test="${empty grades}">
            <tr class="table">
                <td colspan="your_column_count">No Grades from selected subject</td>
            </tr>
        </c:if>
    </table>

    <a href="functions.jsp" class="btn btn-dark" style="width: 200px;">
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-left-short" viewBox="0 0 16 16">
            <path fill-rule="evenodd" d="M12 8a.5.5 0 0 1-.5.5H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5a.5.5 0 0 1 .5.5"/>
        </svg>
        Return
    </a>
</body>
</html>
