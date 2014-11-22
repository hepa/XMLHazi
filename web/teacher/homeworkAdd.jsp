<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="now" value="<%=new java.util.Date()%>"></c:set>
<div id="secondSubHeader">
    <form method="post" action="/Neptun/teacher/homework.jsp?id=${param.id}">                
        <a onclick="this.parentNode.submit()" style="cursor: pointer;"><img src="../images/home--arrow.png" style="vertical-align: middle;"/> Házi feladatok listázása</a>
    </form>
</div> 
<div id="left">
    <form method="post" action="addHomework?id=${param.id}">
        <div class="box">
            <div class="header"><h2><img src="../images/home--plus.png" style="vertical-align: central;"/> Házi feladat kiírása</h2></div>
            <div class="content">
                <table> 
                    <tr>
                        <td>
                            <b>Dátum:</b>
                        </td>
                    </tr>
                    <tr>                        
                        <td>
                            <select class="chzn-select" style="width: 100px;" disabled>                                
                                <option><fmt:formatDate pattern="yyyy" value="${now}"/></option>                                
                            </select>
                            <select class="chzn-select" style="width: 70px;" disabled>                                
                                <option><fmt:formatDate pattern="MM" value="${now}"/></option>                                
                            </select>
                            <select class="chzn-select" style="width: 70px;" disabled>                                
                                <option><fmt:formatDate pattern="dd" value="${now}"/></option>                                
                            </select>                            
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <b>Óra:</b>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <select class="chzn-select" style="width: 60px;" disabled>                                
                                <option><fmt:formatDate pattern="HH" value="${now}"/></option>                                
                            </select> :
                            <select class="chzn-select" style="width: 60px;" disabled>                                
                                <option><fmt:formatDate pattern="mm" value="${now}"/></option>                                
                            </select> :
                            <select class="chzn-select" style="width: 60px;" disabled>                                
                                <option><fmt:formatDate pattern="ss" value="${now}"/></option>                                
                            </select>
                        </td>
                    </tr>
                    <c:if test="${param.id == null}">
                        <tr>
                            <td>
                                <b>Tantárgy:</b>
                            </td> 
                        </tr>
                        <tr>
                            <td>
                                <select name="lesson" class="chzn-select" style="width: 150px;">
                                    <c:forEach var="l" items="${lessons}">
                                        <option value="${l.id}">${l.subject.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td>
                            <b>Leírás:</b>
                        </td> 
                    </tr>
                    <tr>
                        <td>
                            <textarea name="content" rows="10" cols="50"></textarea>
                        </td>
                    </tr>
                </table>
                <input type="submit" value="Kiír" />
            </div>
        </div>
    </form>
</div>
<div id="right">
    <div class="pin">
        <div class="header">Eddigi házi feladatok</div>
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
                                <td>                                           
                                    <a onclick='deleteHomework("${hw.id}", $(this))' style="cursor: pointer; "><img src="../images/cross.png" style="vertical-align: middle;" title="Törlés"/></a>                                
                                </td>
                            </tr>                        
                        </c:forEach>                    
                    </tbody>
                </table>            
            </c:if>
        </div>
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