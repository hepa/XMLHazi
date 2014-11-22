<div id="secondSubHeader">
    <form method="post" action="inbox.jsp" style="float: left; padding-right: 10px;">                
        <a onclick="this.parentNode.submit()" style="cursor: pointer;"><img src="../images/arrow-curve-180-left.png" style="vertical-align: middle;"/> Vissza</a>
    </form>                   
    <a onclick="deleteMessage(${m.id})" style="cursor: pointer;"><img src="../images/cross.png" style="vertical-align: middle;"/> Törlés</a>    
</div>
<div id="all">
    <div id="left">
        <div class="box">
            <div class="header"><h2>Üzenet</h2></div>
            <div class="content">
                <table>
                    <tr>
                        <td><b>Tárgy:</b></td>                    
                    </tr>
                    <tr>
                        <td>${m.subject}</td>
                    </tr>
                    <tr>
                        <td><b>Üzenet:</b></td>                    
                    </tr>
                    <tr>
                        <td>${m.body}</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <div id="right">
        <div class="pin">
            <div class="header">Részletek</div>
            <div class="content">
                <table>
                    <tr>
                        <td><b>Küldte:</b></td>                    
                    </tr>
                    <tr>
                        <td>${m.from.username}</td>
                    </tr>
                    <tr>
                        <td><b>Feladva:</b></td>                    
                    </tr>
                    <tr>
                        <td><img src="../images/calendar-select-days.png" alt="day" style="vertical-align: middle;"/> <fmt:formatDate pattern="yyyy.MM.dd" value="${m.created}"/> <img src="../images/clock.png" alt="clock" style="vertical-align: middle;"/> <fmt:formatDate pattern="HH:mm" value="${m.created}"/></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
<script>
    function deleteMessage(id)
    {                
        $.ajax({
            type:       "post",
            url:        "deleteMessage",
            data:       {"id": id.toString()},
            success:    function(msg) {
                $("#all").hide("drop", 500);
            },
            error:function (xhr, ajaxOptions, thrownError){
                alert(xhr.status);
                alert(thrownError);
            }  
        });
        return false;
    }
</script>