<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="secondSubHeader">
    <!-- Buttons -->        
    <form method="post" action="deleteAccount?number=${account.number}">                
        <a onclick="this.parentNode.submit()" style="cursor: pointer;"><img src="../images/money--minus.png" style="vertical-align: middle;"/> Számla törlése</a>
    </form>
</div>
<div id="left">
    <div class="box">
        <div class="header"><h2>Számlaadatok</h2></div>
        <div class="content">        
            <table>
                <tr>
                    <td>Számlaszám:</td>
                    <td>${param.number}</td>                    
                </tr>    
                <tr>
                    <td>Összeg:</td>
                    <td id="balance">${account.balance} Ft</td>                    
                </tr>     
            </table>        
        </div>
    </div>
    <div class="box">
        <div class="header"><h2>Tranzakciólista</h2></div>
        <div class="content">        
            <table class="sortableTable">
                <thead>
                    <tr>                       
                        <th>Dátum</th>
                        <th>Leírás</th>
                    </tr>
                </thead>      
                <tbody id="transactionlog">
                    <c:forEach var="t" items="${account.transactions}">
                        <tr>
                            <td><img src="../images/calendar-select-days.png" alt="day" style="vertical-align: middle;"/> <fmt:formatDate pattern="yyyy.MM.dd" value="${t.created}"/> <img src="../images/clock.png" alt="clock" style="vertical-align: middle;"/> <fmt:formatDate pattern="HH:mm" value="${t.created}"/></td>
                            <td>${t.description}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>            
        </div>
    </div>
</div>
<div id="right">    
    <form action="accountDeposit?number=${param.number}" method="post" >
        <div class="pin">
            <div class="header">Befizetés</div>
            <div class="content">        
                <table>
                    <tr>
                        <td>Összeg:</td>
                        <td><input type="text" name="deposit" /></td>                    
                        <td><input onclick="accountDeposit()" type="button" value="Befizet" /></td>
                    </tr>                 
                </table>                    
            </div>
        </div> 
    </form>    
    <div class="pin">
        <div class="header">Kifizetés</div>
        <div class="content">        
            <table>
                <tr>
                    <td>Összeg:</td>
                    <td><input type="text" name="withdraw" /></td>                    
                    <td><input onclick="accountWithdraw()" type="button" value="Kifizet" /></td>
                </tr>                 
            </table>                    
        </div>
    </div>    
    <c:if test="${param.classId != null}">
        <form action="addItem?number=${param.number}&classId=${param.classId}" method="post" >
            <div class="pin">
                <div class="header">Tétel kiírása</div>
                <div class="content">        
                    <table>
                        <tr>
                            <td>Típus:</td>
                            <td>
                                <select name="type" class="chzn-select" style="width: 150px;">
                                    <c:forEach var="t" items="${types}">
                                        <option value="${t.id}">${t.name}</option>
                                    </c:forEach>
                                </select>
                            </td>                                         
                        </tr>                        
                        <tr>
                            <td>Összeg:</td>
                            <td><input type="text" name="amount" /></td>
                            <td><input type="submit" value="Kiír" /></td>
                        </tr>                        
                    </table>                    
                </div>
            </div> 
        </form>
        <div class="pin">
            <div class="header">Eddig kiírt tételek</div>
            <div class="content">
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
                                <td><a onclick="deleteItem(${i.id}, $(this))" style="cursor: pointer; float: right;"><img src="../images/cross.png" style="vertical-align: middle;" title="Törlés"/></a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>                
            </div>
        </div>
    </c:if>
</div>
<div class="clear"></div>
<script>
    function accountDeposit()
    {                
        $.ajax({
            type:       "post",
            url:        "accountDeposit",
            data:       {"number" : ${param.number}, "deposit": $("input[name=deposit]").val() },
            success:    function(msg) {
                //$("#balance").hide().html(msg + " Ft").show(200);
                $.getJSON("/Neptun/api/teacher/accountDetails?number=" + ${param.number}, function(data) {
                    $("#balance").hide().html(data.account.balance + " Ft").show(200);                    
                    
                    var hossz = data.account.transactions.length - 1;
                    var date = new Date(data.account.transactions[hossz].created);
                    
                    $('<tr>').appendTo("#transactionlog")
                    .append($('<td>').html('<img src="../images/calendar-select-days.png" alt="day" style="vertical-align: middle;"/> ' + date.format("yyyy.mm.dd") + ' <img src="../images/clock.png" alt="clock" style="vertical-align: middle;"/> ' + date.format("HH:MM")))  
                    .append($('<td>').text(data.account.transactions[hossz].description));                                        
                });                            
                $(".popup_success").html("Sikeresen befizetve.").show("drop", 200).delay(3000).fadeOut(200);                
            },
            error:function (xhr, ajaxOptions, thrownError){
                alert(xhr.status);
                alert(thrownError);
            }  
        });
        return false;
    };
    
    function accountWithdraw()
    {                
        $.ajax({
            type:       "post",
            url:        "accountWithdraw",
            data:       {"number" : ${param.number}, "withdraw": $("input[name=withdraw]").val() },
            success:    function(msg) {
                $.getJSON("/Neptun/api/teacher/accountDetails?number=" + ${param.number}, function(data) {
                    $("#balance").hide().html(data.account.balance + " Ft").show(200);                    
                    
                    var hossz = data.account.transactions.length - 1
                    var date = new Date(data.account.transactions[hossz].created);
                    
                    $('<tr>').appendTo("#transactionlog")
                    .append($('<td>').html('<img src="../images/calendar-select-days.png" alt="day" style="vertical-align: middle;"/> ' + date.format("yyyy.mm.dd") + ' <img src="../images/clock.png" alt="clock" style="vertical-align: middle;"/> ' + date.format("HH:MM")))  
                    .append($('<td>').text(data.account.transactions[hossz].description));                                        
                });                            
                $(".popup_success").html("Sikeresen kifizetve.").show("drop", 200).delay(3000).hide(200);   
            },
            error:function (xhr, ajaxOptions, thrownError){
                alert(xhr.status);
                alert(thrownError);
            }  
        });
        return false;
    };
    
    function deleteItem(id, anchor)
    {                
        $.ajax({
            type:       "post",
            url:        "deleteItem",
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
    };
    
    
</script>
