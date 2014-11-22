<div class="box" id="doboz" style="width: 99%;">
    <div class="header" id="fejlec"><h2>Üzenet írása</h2></div>
    <div class="content" id="tartalom">
        <form method="post" action="sendMessage">                    
            <table>
                <tr>
                    <td>Címzett:</td>
                    <td>
                        <select name="to" class="chzn-choices" multiple style="width: 100%">                                
                            <c:forEach var="p" items="${persons}">
                                <c:choose>                                    
                                    <c:when test="${p.firstName == s.firstName && p.lastName == s.lastName}">
                                        <option value="${p.username}" selected>${p.firstName} ${p.lastName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${p.username}">${p.firstName} ${p.lastName}</option>
                                    </c:otherwise>
                                </c:choose>                              
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Tárgy:</td>
                    <td><input name="subject" type="text" /></td>
                </tr>
                <tr>
                    <td>Üzenet:</td>
                    <td><textarea name="body" rows="8" cols="50"></textarea></td>
                </tr>
            </table>     
            <input onclick="sendMessage()" type="button" value="Küldés" />
        </form> 
    </div>
</div>
<script>
    function sendMessage()
    {                 
        $.ajax({
            type:       "post",
            url:        "sendMessage",
            data:       {"to": $("select[name=to]").val(), "subject": $("input[name=subject]").val(), "body": $("textarea[name=body]").val()},
            success:    function(msg) {                
                $("input[name=subject]").val("");
                $("textarea[name=body]").val("");
                $(".popup_success").html("A levél sikeresen elküldve.").show("drop", 200).delay(3000).fadeOut(200);
                /*
                $("#doboz").hide("drop", {direction: "right"},  500);                
                $.getJSON("/Neptun/api/family/inbox", function(data) {                    
                    $("#tartalom").html("");
                    $("#fejlec").html("<h2>Üzeneteim</h2>");
                    
                    var hossz = data.messages.length;
                    
                    if (hossz == 0) {
                        $("#tartalom").html("<i>Nincs üzeneted.</i>");
                    } else {
                        var string = '<table class="sortableTable"><colgroup><col style="width: 55px;"><col style="width: 202px;"><col style="width: 132px;"><col style="width: 493px;"><col style="width: 56px;"></colgroup><thead><tr><th class="header"></th><th class="header">Feladó</th><th class="header">Tárgy</th><th class="header">Feladva</th><th class="header"></th></tr></thead><tbody>';
                        for (var i=0; i<hossz; i++) { 
                            
                            var date = new Date(data.messages[i].created);
                            
                            string += "<tr>";
                            if (data.messages[i].read == false) {
                                string += '<td><img src="../images/mail.png" style="vertical-align: middle;" /></td>';
                                string += '<td><a href="messageDetails.jsp?id=' + data.messages[i].id + '"><b>' + data.messages[i].from.firstName + ' ' + data.messages[i].from.lastName + '</b></a></td>';
                                string += '<td><a href="messageDetails.jsp?id=' + data.messages[i].id + '"><b>' + data.messages[i].subject + '</b></a></td>';                                
                            } else {
                                string += '<td><img src="../images/mail-open.png" style="vertical-align: middle;" /></td>';
                                string += '<td><a href="messageDetails.jsp?id=' + data.messages[i].id + '">' + data.messages[i].from.firstName + ' ' + data.messages[i].from.lastName + '</a></td>';
                                string += '<td><a href="messageDetails.jsp?id=' + data.messages[i].id + '">' + data.messages[i].subject + '</a></td>';                                
                            }
                            string += '<td><img src="../images/calendar-select-days.png" alt="day" style="vertical-align: middle;"/> '+ date.format("yyyy.mm.dd") +' <img src="../images/clock.png" alt="clock" style="vertical-align: middle;"/> '+ date.format("HH:MM") +'</td>';
                            string += '<td><a onclick="deleteMessage(' + data.messages[i].id + ', $(this))" style="cursor: pointer; float: right;"><img src="../images/cross.png" style="vertical-align: middle;" title="Törlés"/></a></td>';
                            string += '</tr>';
                        }
                        string += '</tbody></table>';
                        $("#tartalom").html(string);
                    }
                                        
                    //$("#doboz").parent().append('<div id="secondSubHeader"><form method="post" action="message.jsp" style="padding-left: 10px;"><a onclick="this.parentNode.submit()" style="cursor: pointer;"><img src="../images/mail--plus.png" style="vertical-align: middle;"/> Levél írása</a></form></div>');
                    $("#doboz").show("drop", 500);                       
                });
                */
            },
            error:function (xhr, ajaxOptions, thrownError){
                alert(xhr.status);
                alert(thrownError);
            }  
        });
        return false;

    }
    
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