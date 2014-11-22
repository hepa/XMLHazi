<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="today" value="<%=new java.util.Date()%>" />
<fmt:formatDate value="${today}" pattern="yyyy.MM.dd" var="now"></fmt:formatDate>
<form action="addMeeting" method="post" >
    <div id="left">                
        <div class="box">
            <div class="header"><h2><img src="../images/alarm-clock--plus.png" style="vertical-align: central;"/> Találkozó hozzáadása</h2></div>
            <div class="content">      
                <table>
                    <tr>
                        <td>
                            Típusa:
                        </td>
                        <td>
                            <select name="type" class="chzn-select" style="width: 200px;">
                                <c:forEach var="tipus" items="${tipusok}">
                                    <option value="${tipus.id}">${tipus.name}</option>
                                </c:forEach>                        
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Kezdete:
                        </td>
                        <td>
                            <input type="text" class="from" name="from" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Vége:
                        </td>
                        <td>
                            <input type="text" class="to" name="to" />
                        </td>
                    </tr> 
                    <tr>
                        <td>Megjegyzés:</td>
                        <td>
                            <textarea name="comment"></textarea>
                        </td>
                    </tr>
                </table>
                <input type="button" onclick="addMeeting(${my_class.id})" value="Hozzáadás" />
            </div>
        </div>                
    </div>
</form>
<div id="right">                
    <div class="pin">
        <div class="header"><img src="../images/alarm-clock-select-remain.png" style="vertical-align: central;"/> Eddig létrehozott találkozók</div>
        <div class="content" id="prev">             
            <c:forEach var="m" items="${prev}">
                <c:choose>
                    <c:when test="${today > m.to}">
                        <table style="width: 100%; background: #eee;color: #999;font-style: italic;">
                        </c:when>
                        <c:otherwise>
                            <table style="width: 100%;">
                            </c:otherwise>
                        </c:choose>                
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
                                <img src="../images/alarm-clock--pencil.png" style="vertical-align: middle;" title="Szerkesztés"/> <b>${m.type.name}</b>
                                <a onclick='deleteMeeting("${my_class.id}", "${m.from}", $(this))' style="cursor: pointer; float: right;"><img src="../images/cross.png" style="vertical-align: middle;" title="Törlés"/></a>                            
                                <br /> 
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
        </div>
    </div>                
</div>
<script>
    function deleteMeeting(classId, dateFrom, anchor)
    {                
        $.ajax({
            type:       "post",
            url:        "deleteMeeting",
            data:       {"classId": classId.toString(), "dateFrom": dateFrom.toString()},
            success:    function(msg) {
                $(anchor).closest("table").hide("drop", 500);
                $(".popup_success").html("Sikeresen törölve").show("drop", 200).delay(3000).fadeOut(200);
            },
            error:function (xhr, ajaxOptions, thrownError){
                alert(xhr.status);
                alert(thrownError);
            }  
        });
        return false;
    }
    function addMeeting(classID)
    {                
        $.ajax({
            type:       "post",
            url:        "addMeeting",
            data:       {"type": $("select[name=type]").val(), "from": $(".from").val(), "to": $(".to").val(), "comment": $("textarea[name=comment]").val()},                        
            dataType:   "json",
            success:    function(msg) {
                
                console.log(msg);
                                                
                if (msg.error == "true") {
                    $(".popup_error").html("Ebben az idöpontban már van találkozó!").show("drop", 200).delay(3000).fadeOut(200);
                } else {                                                                               
                    var dateFrom = new Date(msg.meeting.from);
                    var dateTo = new Date(msg.meeting.to);
                    
                    var content = '<table style="width: 100%; display:none;"><tr><td style="background: #efefef;"><form method="post" action="/Neptun/teacher/addMeeting.jsp" style="float:left;"><a onclick="this.parentNode.submit()" style="cursor: pointer;"><img src="../images/alarm-clock--pencil.png" style="vertical-align: middle;" title="Szerkesztés"/> <b>';
                    content += msg.meeting.type.name;
                    content += '</b></a></form><a onclick=\'deleteMeeting("' + ${my_class.id} + '", "' + dateFrom.format("yyyy-mm-dd HH:MM:ss") + '", $(this))\' style="cursor: pointer; float: right;"><img src="../images/cross.png" style="vertical-align: middle;" title="Törlés"/></a><br /></td></tr><tr><td><img src="../images/calendar-select-days.png" style="vertical-align: middle;"/> ';
                    content += dateFrom.format("yyyy.mm.dd HH:MM");
                    content += ' - <img src="../images/calendar-select-days.png" style="vertical-align: middle;"/>';
                    content += dateTo.format("yyyy.mm.dd HH:MM");
                    content += '</td></tr><tr><td style="padding-bottom: 15px;"><i>';
                    content += msg.meeting.comment;
                    content += '</i></td></tr></table>';
                    
                    $("#prev").append(content);
                    $("#prev table").last().show("drop", 500);
                    
                    $(".popup_success").html("Sikeresen hozzáadva").show("drop", 200).delay(3000).fadeOut(200);                                                     
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

