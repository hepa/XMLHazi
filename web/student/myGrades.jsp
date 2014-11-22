<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="box" style="width: 99%;">
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
                        <th><b>Módosító</b></th>                            
                    </tr>
                </thead>
                <tr>
                    <td></td>
                    <td>
                        <select onchange="refreshTable($(this))" name="lessonId" class="chzn-select" style="width: 150px;">
                            <c:forEach var="l" items="${lessons}">
                                <c:choose>
                                    <c:when test="${param.lessonId != null}">
                                        <c:choose>
                                            <c:when test="${param.lessonId == l.id}">
                                                <option value="${l.id}" selected>${l.subject.name}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${l.id}">${l.subject.name}</option>
                                            </c:otherwise>
                                        </c:choose> 
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${l.id}">${l.subject.name}</option>
                                    </c:otherwise>
                                </c:choose>                                
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <select onchange="refreshTable($(this))" name="examtype" class="chzn-select" style="width: 150px;">
                            <c:forEach var="e" items="${examtypes}">
                                <c:choose>
                                    <c:when test="${param.examtype != nul}">
                                        <c:choose>
                                            <c:when test="${param.examtype == e.id}">
                                                <option value="${e.id}" selected>${e.name}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${e.id}">${e.name}</option>
                                            </c:otherwise>
                                        </c:choose> 
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${e.id}">${e.name}</option>
                                    </c:otherwise>
                                </c:choose>                                
                            </c:forEach>
                        </select>
                    </td>
                    <td></td>
                    <td></td>
                </tr>
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
<script>
    function refreshTable(link)
    {             
        document.location.href='myGrades.jsp?' + $(link).attr("name") + '=' + $(link).val();
        
        /*
        $.ajax({
            type:       "post",
            url:        "myGrades.jsp",
            data:       {"id": $(link).val()},
            success:    function(msg) {                
            },
            error:function (xhr, ajaxOptions, thrownError){
                alert(xhr.status);
                alert(thrownError);
            }  
        });
        return false;    
         */
    }
</script>