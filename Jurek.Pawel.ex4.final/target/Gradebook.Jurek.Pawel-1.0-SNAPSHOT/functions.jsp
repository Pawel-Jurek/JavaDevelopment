<%-- 
    Document   : functions
    Description: options menu
    Created on : 26 lis 2023, 19:45:06
    Author     : pawel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="pl.polsl.model.StudentGradebook" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gradebook</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <script>
            document.addEventListener('DOMContentLoaded', function(){
                var studentGradebook = '<%= session.getAttribute("studentGradebook") %>';
                console.log("gradebook", studentGradebook);
                if (studentGradebook === 'null') {
                    document.getElementById('errorAlert').style.display = 'block';
                }
            });
        </script>
    </head>
    <body style="padding: 10px">
         <div id="errorAlert" class="alert alert-danger alert-dismissible" style="display: none;">
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            <strong>Error!</strong> The file is not in the correct format. Please, reupload it.
        </div>

        <div style="text-align: center;">
            <h1>Your Gradebook Page</h1>
            <h4>Choose option to see details</h4>
        </div>
        <ul class="list-group">
            <li class="list-group-item">
                <h2>Summary</h2>
                    <form action="summary" method="GET">
                        <input type="submit" class="btn btn-success" style="width: 200px;" value="Summary"/>
                    </form>
            </li>
            <li class="list-group-item">
                <h2>Grades from chosen subject</h2>
                <form action="gradesFromSubject" method="GET">  
                    <select class="form-select" id="validationCustom04" name="selectedSubject" style="width: 350px;" required>
                        <option selected disabled value="">Choose subject</option>
                        <% 
                            StudentGradebook studentGradebook = (StudentGradebook) session.getAttribute("studentGradebook");
                            if (studentGradebook != null) {
                                for (String name : studentGradebook.getSubjectsNames()) {
                        %>
                        <option value="<%= name %>"><%= name %></option>
                        <% 
                                }
                            }
                        %>
                    </select>
                    <div class="invalid-feedback">
                        Please select subject
                    </div>
                    <input type="submit" class="btn btn-primary" style="width: 200px; margin-top: 10px;" value="Open Grades"/>
                </form>
            </li>
            <li class="list-group-item">
                 <h2>Not passing subjects</h2>
                <form action="notPassing" method="GET">
                    <input type="submit" class="btn btn-danger" style="width: 200px;" value="Not passing subjects"/>
                </form>                   
            </li>
            <li class="list-group-item">
                <a href="index.html" class="btn btn-dark" style="margin-top: 10px; width: 200px; ">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-left-short" viewBox="0 0 16 16">
                      <path fill-rule="evenodd" d="M12 8a.5.5 0 0 1-.5.5H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5a.5.5 0 0 1 .5.5"/>
                    </svg>
                    Return
                </a>
            </li>
        </ul>
 
    </body>
</html>
</html>
