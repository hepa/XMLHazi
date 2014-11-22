<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="secondSubHeader">
    <!-- Buttons -->
    <c:choose>
        <c:when test="${myself.account.number == 0}">
            <form method="post" action="/Neptun/family/addAccount.jsp" style="float: left; padding-right: 10px;">                
                <a onclick="this.parentNode.submit()" style="cursor: pointer;"><img src="../images/money--plus.png" style="vertical-align: middle;"/> Új számla hozzáadása</a>
            </form>
        </c:when>  
        <c:when test="${myself.account.number != 0}">
            <form method="post" action="/Neptun/family/accountManagement.jsp?number=${myself.account.number}" style="float: left; padding-right: 10px;">                
                <a onclick="this.parentNode.submit()" style="cursor: pointer;"><img src="../images/money--pencil.png" style="vertical-align: middle;"/> Számlakezelés</a>
            </form>
        </c:when>
    </c:choose>
    <form method="post" action="password.jsp">                
        <a onclick="this.parentNode.submit()" style="cursor: pointer;"><img src="../images/lock--pencil.png" style="vertical-align: middle;"/> Jelszó megváltoztatása</a>
    </form>    
</div>
<div id="left">
    <div class="box">
        <div class="header"><h2>Személyes adatok</h2></div>
        <div class="content">
            <table>
                <tr>
                    <td>Vezetéknév:</td>
                    <td>${my.firstName}</td>                    
                </tr>
                <tr>
                    <td>Középső név: </td>
                    <td>${my.middleName}</td>                    
                </tr>
                <tr>
                    <td>Keresztnév:</td>
                    <td>${my.lastName}</td>                    
                </tr>
                <tr>
                    <td>E-mail cím:</td>
                    <td>${my.email}</td>                    
                </tr>
                <tr>
                    <td>Mobil szám:</td>
                    <td>${my.mobileNumber}</td>                    
                </tr>
                <tr>
                    <td>Születési dátum:</td>
                    <td>${my.dateOfBirth}</td>                    
                </tr>
                <tr>
                    <td>Személyigazolványszám:</td>
                    <td>${my.idCardNumber}</td>                    
                </tr>
            </table>             
        </div>
    </div> 
    <div class="box">
        <div class="header"><h2>Iskolai adatok</h2></div>
        <div class="content">
            <table>
                <tr>
                    <td>Diákigazolványszám:</td>
                    <td>${my.studentIdNumber}</td>                    
                </tr>
                <tr>
                    <td>Beiratkozás időpontja:</td>
                    <td>${my.dateOfJoin}</td>                    
                </tr>
                <tr>
                    <td>Hallgatói státusz:</td>
                    <td>
                        <c:choose>
                            <c:when test="${my.status}">
                                aktív
                            </c:when>
                            <c:otherwise>                            
                                inaktív
                            </c:otherwise>
                        </c:choose>
                    </td>                  
                </tr>
                <tr>
                    <td>Felhasználói név:</td>
                    <td>${my.username}</td>                    
                </tr>
            </table>             
        </div>
    </div>         
</div>                                
<div id="right">
    <div class="pin">
        <div class="header">Város adatok</div>
        <div class="content">
            <table>
                <tr>
                    <td>Irányítószám:</td>
                    <td>${my.city.zipCode}</td>
                </tr>
                <tr>
                    <td>Város:</td>
                    <td>${my.city.name}</td>
                </tr>
                <tr>
                    <td>Utca, házszám:</td>
                    <td>${my.address}</td>
                </tr>
            </table>
        </div>
    </div>
    <div class="pin">
        <div class="header">Részletek</div>
        <div class="content">
            <table>
                <tr>
                    <td><b>Utoljára az oldalon járt</b></td>                    
                </tr>                
                <tr>
                    <c:choose>
                        <c:when test="${lastLogin == null}">
                            <td><i>Még nem lépett be.</i></td>
                        </c:when>
                        <c:otherwise>
                            <td><img src="../images/calendar-select-days.png" alt="day" style="vertical-align: middle;"/> <fmt:formatDate pattern="yyyy.MM.dd" value="${lastLogin}"/> <img src="../images/clock.png" alt="clock" style="vertical-align: middle;"/> <fmt:formatDate pattern="HH:mm" value="${lastLogin}"/></td>
                            </c:otherwise>
                        </c:choose>                    
                </tr>
            </table>
        </div>
    </div>
</div>
<div class="clear"></div>  