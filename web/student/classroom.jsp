<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${param.name != null}">
    <div id="secondSubHeader">
        <form method="post" action="/Neptun/student/classroom.jsp">                
            <a onclick="this.parentNode.submit()" style="cursor: pointer;"><img src="../images/magnifier.png" style="vertical-align: middle;"/> Keresés</a>
        </form>        
    </div> 
    <div id="left">                
        <div class="box">
            <div class="header">
                <h2>${param.name}</h2>
            </div>
            <div class="content">                            
                <c:forEach var="l" items="${lessons}">
                    <table>
                        <tr>
                            <td style="font-size: 13px; font-weight: bold;"><img src="../images/chalkboard.png" alt="day" style="vertical-align: middle;"/> ${l.subject.name}</td>
                        </tr>
                        <c:forEach var="date" items="${l.dates}">
                            <tr>                                                    
                                <td style="padding-left: 20px;"><img src="../images/calendar-select-days.png" alt="day" style="vertical-align: middle;"/>${date.day}  <img src="../images/clock.png" alt="day" style="vertical-align: middle;"/>${date.hour}:${date.minutes}</td>
                            </tr>
                        </c:forEach>
                    </table>
                        <br />
                </c:forEach>
            </div>
        </div>
    </div>
    <div id="right">
        <div class="pin">
            <div class="header">Megjegyzés</div>
            <div class="content" id="desc">
                <c:if test='${cr.description == null || cr.description == ""}'><i>Nincs megjegyzés.</i></c:if>
                <c:if test='${cr.description != null || cr.description != ""}'>${cr.description}</c:if>
            </div>
        </div>
    </div>
</c:if>
<c:if test="${param.name == null}">
    <div class="box" style="width: 99%;">
        <div class="header"> 
            <h2><img src="../images/magnifier.png" alt="Search" style="vertical-align: middle;"/> Keresés</h2>
        </div>        
        <div class="content">
            <form method="post" action="searchClassroom">
                <table>
                    <tr>
                        <td>
                            <select name="name" class="chzn-select-deselect" style="width: 200px;" data-placeholder="Osztályterem">
                                <option></option>
                                <c:forEach var="cr" items="${classrooms}">
                                    <option value="${cr.name}">${cr.name}</option>
                                </c:forEach>
                            </select>                        
                        </td>                        
                        <td>
                            <input type="submit" value="Keres" />
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>    
</c:if>
