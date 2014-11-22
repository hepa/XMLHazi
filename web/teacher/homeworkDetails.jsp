<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="box" style="width: 99%">
    <div class="header"><h2>Részletek</h2></div>
    <div class="content">
        <table>
            <tr>
                <td><b>Tárgy:</b></td>                
            </tr>
            <tr>
                <td>${hw.lesson.subject.name}</td>                
            </tr>
            <tr>
                <td><b>Létrehozva:</b></td>                
            </tr>
            <tr>
                <td><fmt:formatDate pattern="yyyy.MM.dd" value="${hw.created}"/> <fmt:formatDate pattern="HH:mm" value="${hw.created}"/></td>                
            </tr>
            <tr>
                <td><b>Leírás:</b></td>                
            </tr>
            <tr>
                <td>${hw.content}</td>                
            </tr>
        </table>
    </div>
</div>
<div class="box" style="width: 99%">
    <div class="header"><h2>Diákok</h2></div>
    <div class="content">
        <form method="post" action="addHomeworkGrade?homeworkId=${param.id}">
            <table>
                <c:forEach var="s" items="${students}">                
                    <tr>                        
                        <td>${s.firstName} ${s.lastName} <input type="hidden" name="studentId" value="${s.studentIdNumber}" /></td>
                        <td>
                            <select data-placeholder="Érdemjegy" class="chzn-select-deselect" name="grade" style="width: 120px;">                                                                
                                <option value=""></option>                                
                                <c:forEach begin="1" end="5" var="i">
                                    <c:if test="${s.homeworks[0].mark.id == i}">
                                        <option value="${i}" selected="">${i}</option>
                                    </c:if>
                                    <c:if test="${s.homeworks[0].mark.id != i}">
                                        <option value="${i}">${i}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>                
                </c:forEach>
            </table>
            <input type="submit" value="Mehet" />
        </form>
    </div>   
</div>
