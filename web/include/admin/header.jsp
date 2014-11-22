<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="business.Student" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Neptun - Main</title>

        <!-- CSS -->        
        <link type="text/css" href="../css/include/header.css" rel="stylesheet" />
        <link type="text/css" href="../css/main.css" rel="stylesheet" />
        <link type="text/css" href="../css/input.css" rel="stylesheet" />
        <link type="text/css" href="../css/messageDivs.css" rel="stylesheet" />
        <link type="text/css" href="../css/chosen.css" rel="stylesheet"/>                
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />        
        
        <script src="http://code.jquery.com/jquery-1.8.2.js"></script>
        <script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>
        <script src="../js/chosen/chosen.jquery.min.js"></script>
        <script src="../js/chosen/chosen.proto.min.js"></script>
        <script>
            $(function() {
                $(".datepicker").datepicker({
                    showButtonPanel: true,
                    changeMonth: true,
                    changeYear: true,
                    yearRange: "c-90:c+5"
                });
            });               
            $(function() {
                $(".chzn-select").chosen();
            });
            $(function() {
                $(".chzn-choices").chosen();
            });
        </script>

    </head>
    <body>
        <div id="container">              
            <table id="infoBar">
                <tr>
                    <td>Admin mód</td>
                    <td>[ <a href="../logout">Kijelentkezés</a> ]</td>
                </tr>    
            </table>
            <div id="header">
                <div class="content">    
                    <ul>
                        <li><a href="main">Hozzáadás</a></li>
                        <li><a href="">Beállítások</a></li>
                    </ul>                
                </div>                 
            </div>        
            <div id="subHeader">
                <div class="content">
                    <ul>
                        <li><a href="addStudent.jsp">Diák hozzáadása</a></li>
                        <li><a href="addTeacher.jsp">Tanár hozzáadása</a></li>
                        <li><a href="addParent.jsp">Család hozzáadása</a></li>
                        <li><a href="addClass.jsp">Osztály hozzáadása</a></li>
                    </ul>
                </div>
            </div>

            <c:if test="${errorMessage != null}">
                <div class="errorDiv">
                    <table>
                        <tr>
                            <td><img src="../images/exclamation.png" alt="errorExclamation" /></td>
                            <td><h2>${errorMessage}</h2> </td>
                        </tr>                     
                    </table>
                </div>
            </c:if>
            <c:if test="${successMessage != null}">
                <div class="successDiv">
                    <table>
                        <tr>
                            <td><img src="../images/accept.png" alt="accept" /></td>
                            <td><h2>${successMessage}</h2> </td>
                        </tr>                     
                    </table>
                </div>
            </c:if>