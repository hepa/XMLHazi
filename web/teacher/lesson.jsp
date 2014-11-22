<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:choose>
    <c:when test="${param.all == null}">
        <div id="secondSubHeader">
            <form method="post" action="/Neptun/teacher/attendance.jsp?id=${lesson.id}" style="float: left; padding-right: 10px;">                
                <a onclick="this.parentNode.submit()" style="cursor: pointer;"><img src="../images/bell--exclamation.png" style="vertical-align: middle;"/> Hiányzások kezelése</a>
            </form>
            <form method="post" action="/Neptun/teacher/homework.jsp?id=${lesson.id}">                
                <a onclick="this.parentNode.submit()" style="cursor: pointer;"><img src="../images/home.png" style="vertical-align: middle;"/> Házi feladatok kezelése</a>
            </form>
        </div> 
        <div id="left">                
            <div class="box">
                <div class="header">
                    <h2>${lesson.subject.name} - ${lesson.form.name}</h2>
                </div>
                <div class="content">   
                    <table>
                        <c:forEach var="s" items="${students}">
                            <tr>
                                <td>${s.firstName} ${s.lastName}</td>
                                <td><a href="grade.jsp?lessonId=${lesson.id}&studentId=${s.studentIdNumber}"><img src="../images/paint-brush--plus.png" alt="grade" title="Jegy beírása"/></a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
        <div id="right">
            <div class="pin">
                <div class="header">
                    Időpont
                </div>
                <div class="content">
                    <table>                
                        <c:forEach var="date" items="${lesson.dates}">
                            <tr>
                                <td colspan="4">
                                    <a href="lessonsPerDay.jsp?day=${date.day}"><img src="../images/calendar-select-days.png" alt="day" style="vertical-align: middle;"/>${date.day}</a> <img src="../images/clock.png" alt="clock" style="vertical-align: middle;"/>${date.hour}:${date.minutes}
                                </td>
                            </tr>
                        </c:forEach>
                    </table>            
                </div>
            </div>
            <div class="pin">
                <div class="header">
                    Helyszín
                </div>
                <div class="content">
                    <table>
                        <tr>
                            <td>
                                <form method="post" action="/Neptun/teacher/classroom.jsp?name=${lesson.classRoom.name}">                
                                    <a onclick="this.parentNode.submit()" style="cursor: pointer;"><img src="../images/building-low.png" style="vertical-align: middle;"/> ${lesson.classRoom.name}</a>
                                </form>                        
                        </tr>
                    </table>            
                </div>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <div id="left">      
            <c:forEach var="l" items="${lessons}">
                <div class="box">
                    <div class="header">
                        <h2>${l.subject.name}</h2>
                    </div>
                    <div class="content">   
                        <table>
                            <tr>
                                <td><img src="../images/users.png" alt="form" style="vertical-align: middle;"/> ${l.form.name}</td>                                
                            </tr>
                            <c:forEach var="date" items="${l.dates}">
                                <tr>
                                    <td><img src="../images/calendar-select-days.png" alt="form" style="vertical-align: middle;"/> ${date.day}  <img src="../images/clock.png" alt="form" style="vertical-align: middle;"/> ${date.hour}:${date.minutes}</td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td>
                                    <img src="../images/building-low.png" alt="classroom" style="vertical-align: middle;"/> ${l.classRoom.name}
                                </td>
                            </tr>
                        </table>
                    </div>                
                </div>
            </c:forEach>
        </div>
    </c:otherwise>
</c:choose>
