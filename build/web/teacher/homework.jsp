<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="now" value="<%=new java.util.Date()%>"></c:set>
<div id="secondSubHeader">
    <form method="post" action="/Neptun/teacher/homeworkAdd.jsp?id=${param.id}">                
        <a onclick="this.parentNode.submit()" style="cursor: pointer;"><img src="../images/home--plus.png" style="vertical-align: middle;"/> Házi feladat kiíársa</a>
    </form>
</div> 
<div class="box" style="width: 99%">
    <div class="header"><h2>Kiírt házi feladatok</h2></div>
    <div class="content">
        <c:if test="${fn:length(hws) == 0}">
            <i>Még nincs házi feladat.</i>
        </c:if>
        <c:if test="${fn:length(hws) != 0}">
            <table class="sortableTable">
                <thead>
                    <tr>                        
                        <th>Kiírva</th>
                        <th>Leírás</th>
                        <th></th>
                        <th></th>
                    </tr>                
                </thead>
                <tbody>                    
                    <c:forEach var="hw" items="${hws}">
                        <tr>                            
                            <td><img src="../images/calendar-select-days.png" alt="day" style="vertical-align: middle;"/> <fmt:formatDate pattern="yyyy.MM.dd" value="${hw.created}"/> <img src="../images/clock.png" alt="clock" style="vertical-align: middle;"/> <fmt:formatDate pattern="HH:mm" value="${hw.created}"/></td>
                            <td>
                                <c:if test="${fn:length(hw.content) > 31}">
                                    ${fn:substring(hw.content,0,28)}...
                                </c:if>
                                <c:if test="${fn:length(hw.content) <= 33}">
                                    ${hw.content}
                                </c:if>
                            </td>
                            <td><a href="homeworkDetails.jsp?id=${hw.id}" style="cursor: pointer; "><img src="../images/pencil.png" style="vertical-align: middle;" title="Szerkesztés"/></a></td>
                            <td><a onclick='deleteHomework("${hw.id}", $(this))' style="cursor: pointer; "><img src="../images/cross.png" style="vertical-align: middle;" title="Törlés"/></a></td>
                        </tr>                        
                    </c:forEach>                    
                </tbody>
            </table>            
        </c:if>
    </div>
</div>
<script>
    function deleteHomework(id, anchor)
    {                
        $.ajax({
            type:       "post",
            url:        "deleteHomework",
            data:       {"id": id},
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