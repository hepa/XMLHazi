<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="today" value="<%=new java.util.Date()%>" />
<fmt:formatDate value="${today}" pattern="yyyy.MM.dd" var="now"></fmt:formatDate>
    <div id="left">
        <div class="box">
            <div class="header"><h2>Aktuális házi feladatok</h2></div>
            <div class="content" id="homew">
            <c:choose>
                <c:when test="${fn:length(hws) != 0}">
                    <table class="sortableTable">
                        <thead>
                            <tr>
                                <th><b>Kiírva</b></th>
                                <th><b>Tantárgy</b></th>
                                <th><b>Leírás</b></th>
                                <th><b>Kész?</b></th>
                            </tr>
                        </thead>
                        <c:forEach var="hw" items="${hws}">                            
                            <tr>
                                <td><img src="../images/calendar-select-days.png" style="vertical-align: middle;"/><fmt:formatDate pattern="yyyy.MM.dd" value="${hw.created}"></fmt:formatDate> <img src="../images/clock.png" alt="day" style="vertical-align: middle;"/><fmt:formatDate pattern="HH:mm" value="${hw.created}"></fmt:formatDate></td>
                                <td><img src="../images/chalkboard.png" style="vertical-align: middle;"/> <a href="lesson.jsp?id=${hw.lesson.id}" >${hw.lesson.subject.name}</a></td>
                                <td><img src="../images/document-list.png" style="vertical-align: middle;"/> <a href="homeworkDetails.jsp?id=${hw.id}">${hw.content}</a></td>
                                <td>                            
                                    <form method="post" action="checkHomework?homeworkId=${hw.id}&done">                
                                        <a onclick="checkHomeworkDone(${hw.id}, $(this))" style="cursor: pointer;"><img src="../images/status-busy.png" style="vertical-align: middle;"/></a>
                                    </form>                                                        
                                </td>
                            </tr>                            
                        </c:forEach>
                    </table>  
                </c:when>
                <c:otherwise>
                    <i>Nincs házi feladatod.</i>
                </c:otherwise>
            </c:choose>                      
        </div>
    </div>
    <div class="box">
        <div class="header"><h2>Mai óráim</h2></div>
        <div class="content">
            <c:choose>
                <c:when test="${fn:length(lessons) != 0}">
                    <table class="sortableTable">                
                        <thead>
                            <tr>
                                <th><b>Évfolyam</b></th>                           
                                <th><b>Tárgy</b></th>                        
                                <th><b>Terem</b></th>
                                <th><b>Tanár</b></th>
                            </tr>
                        </thead>                
                        <c:forEach var="lesson" items="${lessons}">
                            <tr>
                                <td><img src="../images/calendar-month.png" alt="year" style="vertical-align: middle;"/> <a href="holiday.jsp?id=${lesson.year.id}" >${lesson.year.name}</a></td>                                                
                                <td><img src="../images/chalkboard.png" alt="form" style="vertical-align: middle;"/> <a href="lesson.jsp?id=${lesson.id}" >${lesson.subject.name}</a></td>                        
                                <td><img src="../images/building-low.png" alt="classroom" style="vertical-align: middle;"/> <a href="classroom.jsp?name=${lesson.classRoom.name}" > ${lesson.classRoom.name}</a></td>
                                <td><img src="../images/user-business-gray.png" alt="classroom" style="vertical-align: middle;"/> ${lesson.teacher.firstName} ${lesson.teacher.lastName}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <i>Ma nincsenek óráid.</i>
                </c:otherwise>
            </c:choose>
        </div>
    </div>  
    <div class="box">
        <div class="header"><h2>Holnapi óráim</h2></div>
        <div class="content">
            <c:choose>
                <c:when test="${fn:length(tomorrowLessons) != 0}">
                    <table class="sortableTable">                
                        <thead>
                            <tr>
                                <th><b>Évfolyam</b></th>                           
                                <th><b>Tárgy</b></th>                        
                                <th><b>Terem</b></th>
                                <th><b>Tanár</b></th>
                            </tr>
                        </thead>                
                        <c:forEach var="lesson" items="${tomorrowLessons}">
                            <tr>
                                <td><img src="../images/calendar-month.png" alt="year" style="vertical-align: middle;"/> <a href="holiday.jsp?id=${lesson.year.id}" >${lesson.year.name}</a></td>                                                
                                <td><img src="../images/chalkboard.png" alt="form" style="vertical-align: middle;"/> <a href="lesson.jsp?id=${lesson.id}" >${lesson.subject.name}</a></td>                        
                                <td><img src="../images/building-low.png" alt="classroom" style="vertical-align: middle;"/> <a href="classroom.jsp?name=${lesson.classRoom.name}" > ${lesson.classRoom.name}</a></td>
                                <td><img src="../images/user-business-gray.png" alt="classroom" style="vertical-align: middle;"/> ${lesson.teacher.firstName} ${lesson.teacher.lastName}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <i>Holnap nem lesznek óráid.</i>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
<div id="right">
    <div class="pin">
        <div class="header">Befizetési kötelezettségek</div>
        <div class="content">
            <c:choose>
                <c:when test="${fn:length(items) != 0}">
                    <table class="sortableTable">
                        <thead>
                            <tr>
                                <th>Kiírva</th>
                                <th>Típusa</th>
                                <th>Összeg</th>                        
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="i" items="${items}">
                                <tr>
                                    <td><img src="../images/calendar-select-days.png" alt="day" style="vertical-align: middle;"/> <fmt:formatDate pattern="yyyy.MM.dd" value="${i.created}"/> <img src="../images/clock.png" alt="clock" style="vertical-align: middle;"/> <fmt:formatDate pattern="HH:mm" value="${i.created}"/></td>
                                    <td>${i.itemtype.name}</td>
                                    <td>${i.amount} Ft</td>                                                            
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table> 
                </c:when>
                <c:otherwise>
                    <i>Jelenleg nincsen elmaradásod.</i>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <div class="pin">
        <div class="header">Közelgő fogadóórák</div>
        <div class="content">  
            <c:choose>
                <c:when test="${fn:length(meetings) == 0}">
                    <i>Nincs jelenleg fogadóóra.</i>
                </c:when>
                <c:otherwise>
                    <c:forEach var="m" items="${meetings}">
                        <table style="width: 100%;">
                            <tr>
                                <fmt:formatDate pattern="yyyy.MM.dd" value="${m.from}" var="from"></fmt:formatDate>
                                <c:choose>                                    
                                    <c:when test="${now == from}">
                                        <td style="background: #fbb450;">
                                        </c:when>
                                        <c:otherwise>
                                        <td style="background: #efefef;">
                                        </c:otherwise>
                                    </c:choose>                                     
                                    <img src="../images/alarm-clock--arrow.png" style="vertical-align: middle;"/> <b>${m.type.name}</b>                                    
                                </td>                        
                            </tr>
                            <tr>
                                <td>
                                    <img src="../images/calendar-select-days.png" style="vertical-align: middle;"/> <fmt:formatDate pattern="yyyy.MM.dd" value="${m.from}"/> <img src="../images/clock.png" alt="day" style="vertical-align: middle;"/><fmt:formatDate pattern="HH:mm" value="${m.from}"/> - <img src="../images/calendar-select-days.png" style="vertical-align: middle;"/> <fmt:formatDate pattern="yyyy.MM.dd" value="${m.to}"/> <img src="../images/clock.png" alt="day" style="vertical-align: middle;"/><fmt:formatDate pattern="HH:mm" value="${m.to}"/>
                                </td>
                            </tr>
                            <tr>
                                <td style="padding-bottom: 15px;">
                                    <i>${m.comment}</i>
                                </td>
                            </tr>
                        </table>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
<div class="clear"></div>  
<script>    
    function checkHomeworkDone(id, link)
    {                
        $.ajax({
            type:       "post",
            url:        "checkHomework",
            data:       {"homeworkId": id, "done":1},
            success:    function(msg) {
                $(link).html("<img src=\"../images/status.png\" style=\"vertical-align: middle;\"/>");
                $(link).closest("tr").hide("highlight", 200);                
            },
            error:function (xhr, ajaxOptions, thrownError){
                alert(xhr.status);
                alert(thrownError);
            }  
        });
        return false;
    }
</script>