<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="secondSubHeader">
    <form method="post" action="searchStudent.jsp" style="float: left; padding-right: 10px;">                
        <a onclick="this.parentNode.submit()" style="cursor: pointer;"><img src="../images/magnifier.png" style="vertical-align: middle;"/> Keresés</a>
    </form>    
    <form method="post" action="grade.jsp?studentId=${s.studentIdNumber}" style="float:left; padding-right: 10px;">                
        <a onclick="this.parentNode.submit()" style="cursor: pointer;"><img src="../images/paint-brush--plus.png" style="vertical-align: middle;"/> Jegy beírása</a>
    </form>
    <form method="post" action="message.jsp?studentId=${s.studentIdNumber}">                
        <a onclick="this.parentNode.submit()" style="cursor: pointer;"><img src="../images/mail.png" style="vertical-align: middle;"/> Üzenet küldése</a>
    </form>
</div>
<div id="left">
    <div class="box">
        <div class="header"><h2>Személyes adatok</h2></div>
        <div class="content">
            <table>
                <tr>
                    <td>Vezetéknév:</td>
                    <td>${s.firstName}</td>                    
                </tr>
                <tr>
                    <td>Középső név: </td>
                    <td>${s.middleName}</td>                    
                </tr>
                <tr>
                    <td>Keresztnév:</td>
                    <td>${s.lastName}</td>                    
                </tr>
                <tr>
                    <td>E-mail cím:</td>
                    <td>${s.email}</td>                    
                </tr>
                <tr>
                    <td>Mobil szám:</td>
                    <td>${s.mobileNumber}</td>                    
                </tr>
                <tr>
                    <td>Születési dátum:</td>
                    <td>${s.dateOfBirth}</td>                    
                </tr>
                <tr>
                    <td>Személyigazolványszám:</td>
                    <td>${s.idCardNumber}</td>                    
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
                    <td>${s.studentIdNumber}</td>                    
                </tr>
                <tr>
                    <td>Beiratkozás időpontja:</td>
                    <td>${s.dateOfJoin}</td>                    
                </tr>
                <tr>
                    <td>Hallgatói státusz:</td>
                    <td>${s.status}</td>                    
                </tr>
                <tr>
                    <td>Felhasználói név:</td>
                    <td>${s.username}</td>                    
                </tr>
            </table>             
        </div>
    </div>
    <div class="box">
        <div class="header"><h2>Jegyei</h2></div>
        <div class="content">
            <c:if test="${fn:length(grades) != 0}">
                <table class="sortableTable">
                    <thead>
                        <tr>
                            <th><b>Dátum</b></th>
                            <th><b>Tárgy</b></th>
                            <th><b>Típusa</b></th>
                            <th><b>Jegy</b></th>
                            <th></th>
                        </tr>
                    </thead>
                    <c:forEach var="g" items="${grades}">
                        <tr>
                            <td><img src="../images/calendar-select-days.png" alt="day" style="vertical-align: middle;"/> <fmt:formatDate pattern="yyyy.MM.dd" value="${g.created}"/> <img src="../images/clock.png" alt="clock" style="vertical-align: middle;"/> <fmt:formatDate pattern="HH:mm" value="${g.created}"/></td>
                            <td><a href="lesson.jsp?id=${g.lesson.id}"><img src="../images/chalkboard.png" alt="form" style="vertical-align: middle;"/> ${g.lesson.subject.name}</a></td>
                            <td>${g.examtype.name}</td>
                            <td>${g.mark.description} (${g.mark.id})</td>
                            <td><a onclick="deleteGrade(${g.id}, $(this))" style="cursor: pointer; float: right;"><img src="../images/cross.png" style="vertical-align: middle;" title="Törlés"/></a></td>
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
                    <td>${s.city.zipCode}</td>
                </tr>
                <tr>
                    <td>Város:</td>
                    <td>${s.city.name}</td>
                </tr>
                <tr>
                    <td>Utca, házszám:</td>
                    <td>${s.address}</td>
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
<script>
    function deleteGrade(id, anchor)
    {                
        $.ajax({
            type:       "post",
            url:        "deleteGrade",
            data:       {"id": id.toString()},
            success:    function(msg) {
                $(anchor).closest("tr").hide("highlight", 500);
            },
            error:function (xhr, ajaxOptions, thrownError){
                alert(xhr.status);
                alert(thrownError);
            }  
        });
        return false;
    }
</script>