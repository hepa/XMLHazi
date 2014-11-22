<div id="secondSubHeader">
    <form method="post" action="message.jsp" style="padding-left: 10px;">                
        <a onclick="this.parentNode.submit()" style="cursor: pointer;"><img src="../images/mail--plus.png" style="vertical-align: middle;"/> Lev�l �r�sa</a>
    </form>
</div>
<div class="box" style="width: 99%">
    <div class="header"><h2>�zeneteim</h2></div>
    <div class="content">
        <c:if test="${fn:length(messages) != 0}">
        <table class="sortableTable">
            <thead>
                <tr>
                    <th></th>
                    <th>Felad�</th>
                    <th>T�rgy</th>                    
                    <th>Feladva</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="m" items="${messages}">                
                    <c:if test="${m.read}">
                        <tr>                    
                            <td><img src="../images/mail-open.png" style="vertical-align: middle;" /></td>
                            <td><a href="messageDetails.jsp?id=${m.id}">${m.from.username}</a></td>
                            <td><a href="messageDetails.jsp?id=${m.id}">${m.subject}</a></td>                            
                            <td><img src="../images/calendar-select-days.png" alt="day" style="vertical-align: middle;"/> <fmt:formatDate pattern="yyyy.MM.dd" value="${m.created}"/> <img src="../images/clock.png" alt="clock" style="vertical-align: middle;"/> <fmt:formatDate pattern="HH:mm" value="${m.created}"/></td>                  
                            <td><a onclick="deleteMessage(${m.id}, $(this))" style="cursor: pointer; float: right;"><img src="../images/cross.png" style="vertical-align: middle;" title="T�rl�s"/></a></td>
                        </tr>
                    </c:if>
                    <c:if test="${!m.read}">
                        <tr>
                            <td><img src="../images/mail.png" style="vertical-align: middle;" /></td>
                            <td><a href="messageDetails.jsp?id=${m.id}"><b>${m.from.username}</b></a></td>
                            <td><a href="messageDetails.jsp?id=${m.id}"><b>${m.subject}</b></a></td>                            
                            <td><img src="../images/calendar-select-days.png" alt="day" style="vertical-align: middle;"/> <fmt:formatDate pattern="yyyy.MM.dd" value="${m.created}"/> <img src="../images/clock.png" alt="clock" style="vertical-align: middle;"/> <fmt:formatDate pattern="HH:mm" value="${m.created}"/></td>
                            <td><a onclick="deleteMessage(${m.id}, $(this))" style="cursor: pointer; float: right;"><img src="../images/cross.png" style="vertical-align: middle;" title="T�rl�s"/></a></td>
                        </tr>                    
                    </c:if>                
                </c:forEach>
            </tbody>            
        </table>        
        <br />
        <b>List�zva:</b> 1 - ${fn:length(messages)}, <b>�sszesen:</b> ${fn:length(messages)}
        </c:if>
        <c:if test="${fn:length(messages) == 0}">
            <i>Nincs �zeneted.</i>
        </c:if>
    </div>
</div>
<script>
    function deleteMessage(id, anchor)
    {                
        $.ajax({
            type:       "post",
            url:        "deleteMessage",
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
