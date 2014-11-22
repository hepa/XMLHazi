<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div style="width: 100%;">
    <c:forEach var="l" items="${lessons}">
        <div class="box">
            <div class="header"><h2><img src="../images/chalkboard.png" alt="day" style="vertical-align: middle;"/> <a href="lesson.jsp?id=${l.id}" >${l.subject.name}</a></h2></div>
            <div class="content">                
                <table>                      
                    <tr>    
                        <td><b>Terem:</b></td>
                        <td><a href="classroom.jsp?name=${l.classRoom.name}"><img src="../images/building-low.png" alt="building" style="vertical-align: middle;"/> ${l.classRoom.name}</a></td>
                    </tr>
                    <tr>
                        <td><b>Tanár:</b></td>
                        <td><img src="../images/user-business-gray.png" alt="building" style="vertical-align: middle;"/> ${l.teacher.firstName} ${l.teacher.lastName}</td>
                    </tr>
                                                                                     
                </table>
            </div>
        </div>
        <div class="pin" style="float: right;">
            <div class="header">Időpont</div>
            <div class="content">
                <table>
                    <c:forEach var="date" items="${l.dates}">
                        <tr>                        
                            <td><img src="../images/calendar-select-days.png" alt="day" style="vertical-align: middle;"/>${date.day}  <img src="../images/clock.png" alt="day" style="vertical-align: middle;"/>${date.hour}:${date.minutes}</td>                        
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </c:forEach> 
</div>
<div class="clear"></div>

