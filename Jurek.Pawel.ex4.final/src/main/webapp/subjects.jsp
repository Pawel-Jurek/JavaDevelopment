<%-- 
    Document   : summary
    Description: displaying summary from subjectList
    Created on : 27 lis 2023, 11:14:57
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
            <th>Subject</th>
            <th>Average</th>
            <th>Final Grade</th>
        </tr>
        <c:if test="${not empty subjectsList}">
            <c:forEach var="subject" items="${subjectsList}">
                <c:set var="trClass" value="${subject.subjectFinalGrade >= 2 ? 'success' : 'danger'}" />
                <tr class="table-${trClass}">
                    <td>${subject.name}</td>
                    <td><fmt:formatNumber type="number" value="${subject.calculateAverage()}" pattern="#,##0.00" /></td>
                    <td>${subject.subjectFinalGrade}</td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty subjectsList}">
            <tr class="table">
                <td colspan="your_column_count">No subjects</td>
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
