<%-- 
    Document   : login
    Created on : 2012.10.04., 15:14:07
    Author     : Reményi Szabolcs
    Descripion : A bejelentkezést biztosítja a rendszerbe, 3 módon.            
--%>

<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bejelentkezés</title>

        <!-- CSS -->
        <link type="text/css" href="css/login.css" rel="stylesheet" />   
        <link type="text/css" href="css/messageDivs.css" rel="stylesheet" />           
        <link type="text/css" href="css/input.css" rel="stylesheet" />           
    </head>
    <body>    

        <%
            if (!request.isSecure()) {
                response.sendRedirect("https://localhost:8443/Neptun/login.jsp");
            }            
        %>
        
    <!-- Displays error messages -->
    <c:if test="${errorMessage != null}">
        <div class="errorDiv">
            <table>
                <tr>
                    <td><img src="images/exclamation.png" alt="errorExclamation" /></td>
                    <td><h2>${errorMessage}</h2> </td>
                </tr>                     
            </table>
        </div>
    </c:if>    
    <div id="container">         
        <!-- Teacher login -->            
        <div id="loginFormIconTeacher" class="loginFormIcon"><h1>Tanár</h1></div>
        <div id="loginFormTeacher">
            <form action="login?source=teacher" method="post" onsubmit="return validate(this)">
                <table>
                    <tr>
                        <td>Azonosító:</td>
                        <td><input type="text" name="identifier" tabindex="1"/>    
                        <td rowspan="2"><input type="submit" value="Bejelentkezés" tabindex="3"/></td>
                    </tr>
                    <tr>
                        <td>Jelszó:</td>
                        <td><input type="password" name="password" tabindex="2" />                            
                    </tr>
                </table>
            </form>
        </div>            
        <div class="floatClear"></div>

        <!-- Student login -->            
        <div id="loginFormIconStudent" class="loginFormIcon"><h1>Diák</h1></div>
        <div id="loginFormStudent">
            <form action="login?source=student" method="post" onsubmit="return validate(this)">
                <table>
                    <tr>
                        <td>Azonosító:</td>
                        <td><input type="text" name="identifier" tabindex="1"/>    
                        <td rowspan="2"><input type="submit" value="Bejelentkezés" tabindex="3"/></td>
                    </tr>
                    <tr>
                        <td>Jelszó:</td>
                        <td><input type="password" name="password" tabindex="2" />                            
                    </tr>
                </table>
            </form>
        </div>            
        <div class="floatClear"></div>

        <!-- Parent login -->            
        <div id="loginFormIconParent" class="loginFormIcon"><h1>Szülő</h1></div>
        <div id="loginFormParent">
            <form action="login?source=family" method="post" onsubmit="return validate(this)">
                <table>
                    <tr>
                        <td>Azonosító:</td>
                        <td><input type="text" name="identifier" tabindex="1"/>    
                        <td rowspan="2"><input type="submit" value="Bejelentkezés" tabindex="3"/></td>
                    </tr>
                    <tr>
                        <td>Jelszó:</td>
                        <td><input type="password" name="password" tabindex="2" />                            
                    </tr>
                </table>
            </form>
        </div>            
        <div class="floatClear"></div>
    </div>

    <!-- JavaScript -->
    <script src="js/jquery-1.8.2.js"></script>
    <script src="js/login.js"></script> 
</body>
</html>
