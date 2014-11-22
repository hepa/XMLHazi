<c:choose>
    <c:when test="${param.all == null}">        
        <div id="left">                
            <div class="box">
                <div class="header"><h2>${lesson.subject.name}</h2></div>
                <div class="content">
                    <img src="../images/user-business-gray.png" alt="classroom" style="vertical-align: middle;"/> ${lesson.teacher.firstName} ${lesson.teacher.lastName}
                </div>
            </div>
        </div>
        <div id="right">
            <div class="pin">
                <div class="header">
                    Idopont
                </div>
                <div class="content">
                    <table>                
                        <c:forEach var="date" items="${lesson.dates}">
                            <tr>
                                <td colspan="4">
                                    <img src="../images/calendar-select-days.png" alt="day" style="vertical-align: middle;"/>${date.day} <img src="../images/clock.png" alt="clock" style="vertical-align: middle;"/>${date.hour}:${date.minutes}
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
                                <form method="post" action="/Neptun/student/classroom.jsp?name=${lesson.classRoom.name}">                
                                    <a onclick="this.parentNode.submit()" style="cursor: pointer;"><img src="../images/building-low.png" style="vertical-align: middle;"/> ${lesson.classRoom.name}</a>
                                </form>                        
                        </tr>
                    </table>            
                </div>
            </div>
        </div>
        <div class="box" style="width: 99%;">
            <div class="header">
                <h2>Jegyeim</h2>
            </div>
            <div class="content">   
                <c:if test="${fn:length(grades) != 0}">
                    <table class="sortableTable">
                        <thead>
                            <tr>
                                <th><b>Dátum</b></th>
                                <th><b>Tárgy</b></th>
                                <th><b>Típusa</b></th>
                                <th><b>Jegy</b></th>                            
                                <th><b>Módosító</b></th>                            
                            </tr>
                        </thead>
                        <c:forEach var="g" items="${grades}">
                            <tr>
                                <td><img src="../images/calendar-select-days.png" alt="day" style="vertical-align: middle;"/> <fmt:formatDate pattern="yyyy.MM.dd" value="${g.created}"/> <img src="../images/clock.png" alt="clock" style="vertical-align: middle;"/> <fmt:formatDate pattern="HH:mm" value="${g.created}"/></td>
                                <td><a href="lesson.jsp?id=${g.lesson.id}"><img src="../images/chalkboard.png" alt="form" style="vertical-align: middle;"/> ${g.lesson.subject.name}</a></td>
                                <td>${g.examtype.name}</td>
                                <td>${g.mark.description} (${g.mark.id})</td>                            
                                <td><img src="../images/user-business-gray.png" alt="classroom" style="vertical-align: middle;"/> ${g.lesson.teacher.firstName} ${g.lesson.teacher.lastName}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
                <c:if test="${fn:length(grades) == 0}">
                    <i>Még nincsenek jegyei.</i>
                </c:if>
            </div>
        </div>
        <div class="box" style="width: 99%;">
            <div class="header">
                <h2>Házi feladatok</h2>
            </div>
            <div class="content">   
                <c:if test="${fn:length(hws) != 0}">
                    <table class="sortableTable">
                        <thead>
                            <tr>
                                <th><b>Kiírva</b></th>
                                <th><b>Tantrágy</b></th>
                                <th><b>Leírás</b></th>
                                <th><b>Jegy</b></th>
                                <th><b>Jegy módosítva</b></th>
                                <th><b>Módosította</b></th>
                                <th><b>Kész?</b></th>
                            </tr>
                        </thead>
                        <c:forEach var="hw" items="${hws}">                    
                            <tr>
                                <td><img src="../images/calendar-select-days.png" alt="day" style="vertical-align: middle;"/><fmt:formatDate pattern="yyyy.MM.dd" value="${hw.created}"></fmt:formatDate> <img src="../images/clock.png" alt="day" style="vertical-align: middle;"/><fmt:formatDate pattern="HH:mm" value="${hw.created}"></fmt:formatDate></td>
                                <td><img src="../images/chalkboard.png" alt="day" style="vertical-align: middle;"/> ${hw.lesson.subject.name}</td>
                                <td><img src="../images/document-list.png" alt="day" style="vertical-align: middle;"/> ${hw.content}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${hw.mark != null}">
                                            ${hw.mark.description} (${hw.mark.id})
                                        </c:when>
                                        <c:otherwise>
                                            -
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${hw.mark != null}">
                                            <img src="../images/calendar-select-days.png" alt="day" style="vertical-align: middle;"/><fmt:formatDate pattern="yyyy.MM.dd" value="${hw.markRegistered}"></fmt:formatDate> <img src="../images/clock.png" alt="day" style="vertical-align: middle;"/><fmt:formatDate pattern="HH:mm" value="${hw.markRegistered}"></fmt:formatDate>
                                        </c:when>
                                        <c:otherwise>
                                            -
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${hw.mark != null}">
                                            <img src="../images/user-business-gray.png" alt="classroom" style="vertical-align: middle;"/> ${hw.lesson.teacher.firstName} ${hw.lesson.teacher.lastName}
                                        </c:when>
                                        <c:otherwise>
                                            -
                                        </c:otherwise>
                                    </c:choose>                                    
                                </td>
                                <td>
                                    <c:if test="${hw.done == false}">
                                        <form method="post" action="checkHomework?homeworkId=${hw.id}&done">                
                                            <a onclick="checkHomeworkDone(${hw.id}, $(this))" style="cursor: pointer;"><img src="../images/status-busy.png" style="vertical-align: middle;"/></a>
                                        </form>
                                    </c:if>
                                    <c:if test="${hw.done != false}">
                                        <form method="post" action="checkHomework?homeworkId=${hw.id}&undone">                
                                            <a onclick="checkHomeworkUnDone(${hw.id}, $(this))" style="cursor: pointer;"><img src="../images/status.png" style="vertical-align: middle;"/></a>
                                        </form>
                                    </c:if>
                                </td>
                            </tr>                    
                        </c:forEach>
                    </table>    
                </c:if>
                <c:if test="${fn:length(hws) == 0}">
                    <i>Még nincsenek házi feladatok.</i>
                </c:if>
            </div>
        </div>
        <div class="box" style="width: 99%;">
            <div class="header">
                <h2>Jelenléti ív</h2>
            </div>
            <div class="content">   
                <table class="sortableTable"> 
                    <thead>
                        <tr>                            
                            <th><b>Dátum</b></th>                            
                            <th><b>Státusz</b></th>                            
                            <th><b>Megjegyzés</b></th>                            
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="a" items="${attendances}">
                            <tr>                                
                                <td><img src="../images/calendar-select-days.png" alt="day" style="vertical-align: middle;"/> <fmt:formatDate pattern="yyyy.MM.dd" value="${a.created}"/></td>                                
                                <td>${a.status.name}</td>                                
                                <td>${a.description}</td>                                
                            </tr>
                        </c:forEach>                            
                    </tbody>
                </table> 
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
<script>
    function checkHomeworkUnDone(id, link)
    {                
        $.ajax({
            type:       "post",
            url:        "checkHomework",
            data:       {"homeworkId": id},
            success:    function(msg) {
                $(link).html("<img src=\"../images/status-busy.png\" style=\"vertical-align: middle;\"/>");
                $(link).attr("onclick", "checkHomeworkDone(" + id + ", $(this))")
            },
            error:function (xhr, ajaxOptions, thrownError){
                alert(xhr.status);
                alert(thrownError);
            }  
        });
        return false;
    }
    function checkHomeworkDone(id, link)
    {                
        $.ajax({
            type:       "post",
            url:        "checkHomework",
            data:       {"homeworkId": id, "done":1},
            success:    function(msg) {
                $(link).html("<img src=\"../images/status.png\" style=\"vertical-align: middle;\"/>");
                $(link).attr("onclick", "checkHomeworkUnDone(" + id + ", $(this))")
            },
            error:function (xhr, ajaxOptions, thrownError){
                alert(xhr.status);
                alert(thrownError);
            }  
        });
        return false;
    }
</script>