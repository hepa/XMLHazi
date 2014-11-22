<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="secondSubHeader">
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
    <div class="box">
        <div class="header"><h2>Jegyeim</h2></div>
        <div class="content">
            <c:if test="${fn:length(grades) != 0}">
                <table class="sortableTable">
                    <thead>
                        <tr>
                            <th><b>Dátum</b></th>
                            <th><b>Tárgy</b></th>
                            <th><b>Típusa</b></th>
                            <th><b>Jegy</b></th>                            
                        </tr>
                    </thead>
                    <c:forEach var="g" items="${grades}">
                        <tr>
                            <td><img src="../images/calendar-select-days.png" alt="day" style="vertical-align: middle;"/> <fmt:formatDate pattern="yyyy.MM.dd" value="${g.created}"/> <img src="../images/clock.png" alt="clock" style="vertical-align: middle;"/> <fmt:formatDate pattern="HH:mm" value="${g.created}"/></td>
                            <td><a href="lesson.jsp?id=${g.lesson.id}"><img src="../images/chalkboard.png" alt="form" style="vertical-align: middle;"/> ${g.lesson.subject.name}</a></td>
                            <td>${g.examtype.name}</td>
                            <td>${g.mark.description} (${g.mark.id})</td>                            
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            <c:if test="${fn:length(grades) == 0}">
                <i>Még nincsenek jegyei.</i>
            </c:if>
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
                    <td><b>Utoljára az oldalon jártam</b></td>                    
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