<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="today" value="<%=new java.util.Date()%>" />
<fmt:formatDate value="${today}" pattern="yyyy.MM.dd" var="now"></fmt:formatDate>
<div id="left">
    <div class="box">
        <div class="header"><h2>Aktuális házi feladatai</h2></div>
        <div class="content" id="homew">
            <c:choose>
                <c:when test="${fn:length(hws) != 0}">
                    <table class="sortableTable">
                        <thead>
                            <tr>
                                <th><b>Kiírva</b></th>
                                <th><b>Tantrágy</b></th>
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
                                    <c:choose>
                                        <c:when test="${hw.done}">
                                            <img src="../images/status.png" style="vertical-align: middle;"/>
                                        </c:when>
                                        <c:otherwise>
                                            <img src="../images/status-busy.png" style="vertical-align: middle;"/>
                                        </c:otherwise>
                                    </c:choose>                                        
                                </td>
                            </tr>                            
                        </c:forEach>
                    </table>  
                </c:when>
                <c:otherwise>
                    <i>Már nincs házi feladata.</i>
                </c:otherwise>
            </c:choose>                      
        </div>
    </div>
    <div class="box">
        <div class="header"><h2>Utolsó 10 jegye</h2></div>
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
                                <th></th>                        
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="i" items="${items}">
                                <tr>
                                    <td><img src="../images/calendar-select-days.png" alt="day" style="vertical-align: middle;"/> <fmt:formatDate pattern="yyyy.MM.dd" value="${i.created}"/> <img src="../images/clock.png" alt="clock" style="vertical-align: middle;"/> <fmt:formatDate pattern="HH:mm" value="${i.created}"/></td>
                                    <td>${i.itemtype.name}</td>
                                    <td>${i.amount} Ft</td>      
                                    <td><img onclick="itemDeposit(${i.id}, $(this))" src="../images/money--arrow.png" alt="befizet" style="vertical-align: middle; cursor: pointer;" title="Befizet"/></td>
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
    <div class="pin">
        <div class="header">Osztályfőnök fogadóórája</div>
        <div class="content">   
            <c:if test="${consults == null}">
                <i>Még nincs fogadóórád.</i>
            </c:if>  
            <ul id="sortable" style="list-style: none; cursor: pointer;">
                <c:forEach var="c" items="${consults}">
                    <li>
                        <table style="width: 100%;">
                            <tr>
                                <td>
                                    <img src="../images/calendar-select-days.png" alt="day" style="vertical-align: middle;"/> <b>${c.day}</b>                                    
                                </td>
                            </tr>
                            <tr>
                                <td style="padding-bottom: 15px;">
                                    <img src="../images/clock.png" alt="clock" style="vertical-align: middle;"/> ${c.from}:${c.to}
                                </td>
                            </tr>
                        </table>
                    </li>
                </c:forEach>       
            </ul>
        </div>    
    </div>
</div>
<script>
    function itemDeposit(id, anchor)
    {                
        $.ajax({
            type:       "post",
            url:        "itemDeposit",
            data:       {"id": id.toString()},
            success:    function(msg) {                
                if (msg == "error") {
                    $(".popup_error").html("Nincs elég pénzed a tevékenységhez!").show("drop", 200).delay(3000).fadeOut(200);
                } else {
                    $(anchor).closest("tr").hide("highlight", 500);
                    $(".popup_success").html("Sikeresen befizetve").show("drop", 200).delay(3000).fadeOut(200);
                }
            },
            error:function (xhr, ajaxOptions, thrownError){
                alert(xhr.status);
                alert(thrownError);
            }  
        });
        return false;
    }
</script>
