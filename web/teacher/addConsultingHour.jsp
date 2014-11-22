<form action="addConsultingHour" method="post" >
    <div id="left">                
        <div class="box">
            <div class="header"><h2><img src="../images/address-book--plus.png" style="vertical-align: baseline;"/> Fogadóóra hozzáadása</h2></div>
            <div class="content">      
                <table>
                    <tr>
                        <td>
                            <select name="day" class="chzn-select" style="width: 150px;">
                                <option value="Hétfõ">Hétfõ</option>
                                <option value="Kedd">Kedd</option>
                                <option value="Szerda">Szerda</option>
                                <option value="Csütörtök">Csütörtök</option>
                                <option value="Péntek">Péntek</option>
                            </select>
                            <select name="from" class="chzn-select" style="width: 70px;">
                                <option value="08">08</option>
                                <option value="09">09</option>
                                <option value="10">10</option>
                                <option value="11">11</option>
                                <option value="12">12</option>
                                <option value="13">13</option>
                                <option value="14">14</option>
                                <option value="15">15</option>
                                <option value="16">16</option>
                                <option value="17">17</option>
                                <option value="18">18</option>
                                <option value="19">19</option>
                                <option value="20">20</option>                                
                            </select> : 
                            <select name="to" class="chzn-select" style="width: 70px;">
                                <option value="00">00</option>
                                <option value="01">01</option>
                                <option value="02">02</option>
                                <option value="03">03</option>
                                <option value="04">04</option>
                                <option value="05">05</option>
                                <option value="06">06</option>
                                <option value="07">07</option>
                                <option value="08">08</option>
                                <option value="09">09</option>
                                <c:forEach var="i" begin="10" end="59">
                                    <option value="${i}">${i}</option>
                                </c:forEach>                               
                            </select>
                        </td>
                    </tr>
                </table>
                <input type="submit" value="Hozzáadás" />
            </div>
        </div>                
    </div>
</form>
<div id="right">                
    <div class="pin">
        <div class="header"><img src="../images/address-book.png" style="vertical-align: baseline;"/> Jelenlegi fogadóórák</div>
        <div class="content"> 
            <ul id="sortable" style="list-style: none; cursor: pointer;">
                <c:forEach var="c" items="${prev}">
                    <li>
                        <table style="width: 100%">
                            <tr>
                                <td>
                                    <img src="../images/calendar-select-days.png" alt="day" style="vertical-align: text-bottom;"/> <b>${c.day}</b>
                                    <a onclick='deleteConsultingHour(${c.teacher.idCardNumber}, "${c.day}", ${c.from}, $(this))' style="cursor: pointer; float: right;"><img src="../images/cross.png" style="vertical-align: middle;" title="Törlés"/></a>
                                </td>                                                
                            </tr>
                            <tr>
                                <td style="padding-bottom: 10px;">
                                    <img src="../images/clock.png" alt="day" style="vertical-align: text-bottom;"/> ${c.from}:${c.to}
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
    function deleteConsultingHour(tid, day, from, anchor)
    {             
        $.ajax({
            type:       "post",
            url:        "deleteConsultingHour",
            data:       {"teacherId": tid.toString(), "day": day.toString(), "from" : from.toString()},
            success:    function(msg) {
                $(anchor).closest("li").hide("drop", 500);
            },
            error:function (xhr, ajaxOptions, thrownError){
                alert(xhr.status);
                alert(thrownError);
            }  
        });
        return false;
    }
</script>
