<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="box" style="width: 99%">
    <div class="header">
        <b>Részletek</b>
        <span style="float: right;">
            <c:if test="${hw.done == false}">
                <form method="post" action="checkHomework?homeworkId=${hw.id}&done">                
                    <a onclick="checkHomeworkDone(${hw.id}, $(this))" style="cursor: pointer;"><img src="../images/status-busy.png" style="vertical-align: middle;" title="Késznek jelöl"/></a>
                </form>
            </c:if>
            <c:if test="${hw.done != false}">
                <form method="post" action="checkHomework?homeworkId=${hw.id}&undone">                
                    <a onclick="checkHomeworkUnDone(${hw.id}, $(this))" style="cursor: pointer;"><img src="../images/status.png" style="vertical-align: middle;" title="Visszavon"/></a>
                </form>
            </c:if>
        </span>
    </div>
    <div class="content">
        <c:choose>
            <c:when test="${hw.mark != null}">
                <div style="float:right; padding-right: 20px;">
                    <span style="text-align: center; display: block;">Jegy</span>
                    <span style="font-size: 60px; text-align: center; display: block; font-weight: bold;">${hw.mark.id}</span>                    
                    <img src="../images/calendar-select-days.png" style="vertical-align: middle;"/><fmt:formatDate pattern="yyyy.MM.dd" value="${hw.markRegistered}"/> <img src="../images/clock.png" alt="day" style="vertical-align: middle;"/><fmt:formatDate pattern="HH:mm" value="${hw.markRegistered}"/>
                </div>                    
            </c:when>            
        </c:choose>
        <table>
            <tr>
                <td><b>Tárgy:</b></td>                
            </tr>
            <tr>
                <td><img src="../images/chalkboard.png" style="vertical-align: middle;"/> ${hw.lesson.subject.name}</td>                
            </tr>
            <tr>
                <td><b>Létrehozva:</b></td>                
            </tr>
            <tr>
                <td><img src="../images/calendar-select-days.png" style="vertical-align: middle;"/><fmt:formatDate pattern="yyyy.MM.dd" value="${hw.created}"/> <img src="../images/clock.png" alt="day" style="vertical-align: middle;"/><fmt:formatDate pattern="HH:mm" value="${hw.created}"/></td>                
            </tr>
            <tr>
                <td><b>Leírás:</b></td>                
            </tr>
            <tr>
                <td><img src="../images/document-list.png" style="vertical-align: middle;"/>${hw.content}</td>                
            </tr>
        </table>                
    </div>
</div>
<script>
    function checkHomeworkUnDone(id, link)
    {                
        $.ajax({
            type:       "post",
            url:        "checkHomework",
            data:       {"homeworkId": id},
            success:    function(msg) {
                $(link).html("<img src=\"../images/status-busy.png\" style=\"vertical-align: middle;\" title=\"Késznek jelöl\"/>");
                $(link).attr("onclick", "checkHomeworkDone(" + id + ", $(this))")
            },
            error:function (xhr, ajaxOptions, thrownError){
                alert(xhr.status);
                alert(thrownError);
            }  
        });
        return false;
    }
    function checkHomeworkDone(id, link)
    {                
        $.ajax({
            type:       "post",
            url:        "checkHomework",
            data:       {"homeworkId": id, "done":1},
            success:    function(msg) {
                $(link).html("<img src=\"../images/status.png\" style=\"vertical-align: middle;\" title=\"Visszavon\"/>");
                $(link).attr("onclick", "checkHomeworkUnDone(" + id + ", $(this))")
            },
            error:function (xhr, ajaxOptions, thrownError){
                alert(xhr.status);
                alert(thrownError);
            }  
        });
        return false;
    }
</script>