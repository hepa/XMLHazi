<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:if test="${param.all == null}">
    <div id="secondSubHeader">
        <form method="post" action="/Neptun/teacher/attendance.jsp?id=${param.id}&all">                
            <a onclick="this.parentNode.submit()" style="cursor: pointer;"><img src="../images/bell--arrow.png" style="vertical-align: middle;"/> Az �sszes hi�nyz�s list�z�sa</a>
        </form>
    </div>
    <form method="post" action="addAttendance?lessonId=${param.id}">
        <div class="box" style="width: 99%">
            <div class="header">
                <h2>Hi�nyz�sok kezel�se</h2>
            </div>
            <div class="content">   
                <p>Csoportos bejel�l�s: <input type="checkbox" name="byGroup" id="csoportos"></p>
                <table>
                    <thead>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><b>Megjegyz�s</b></td>
                        </tr>
                    </thead>
                    <tr>
                        <td colspan="5"><b>List�zva:</b> 1 - ${fn:length(students)}, <b>�sszesen:</b> ${fn:length(students)}</td>
                    </tr>
                    <c:forEach var="s" items="${students}">
                        <tr>
                            <td><input type="checkbox" name="chck" value="${s.studentIdNumber}" checked="checked"></td>
                            <td>${s.firstName} ${s.lastName}</td>
                            <td>
                                <select name="year" class="chzn-select" style="width: 70px;">   
                                    <option value="${1900 + date.year - 1}">${1900 + date.year - 1}</option>                                                         
                                    <option value="${1900 + date.year}" selected="selected">${1900 + date.year}</option>                                                         
                                </select>
                                <select name="month" class="chzn-select" style="width: 70px;">                            
                                    <c:forEach var="i" begin="1" end="12">
                                        <c:if test="${(1 + date.month) == i}">
                                            <option value="${i}" selected="selected">${i}</option>
                                        </c:if>                                    
                                        <c:if test="${(1 + date.month) != i}">
                                            <option value="${i}">${i}</option>
                                        </c:if>
                                    </c:forEach>                               
                                </select>
                                <select name="day" class="chzn-select" style="width: 70px;">                            
                                    <c:forEach var="i" begin="1" end="31">
                                        <c:if test="${(date.date) == i}">
                                            <option value="${i}" selected="selected">${i}</option>
                                        </c:if>                                    
                                        <c:if test="${(date.date) != i}">
                                            <option value="${i}">${i}</option>
                                        </c:if>
                                    </c:forEach>                               
                                </select>
                            </td>
                            <td>
                                <select name="type" class="chzn-select" style="width: 150px;">
                                    <option value="Megjelent">Megjelent</option>
                                    <option value="Nem jelent meg">Nem jelent meg</option>
                                    <option value="K�sett">K�sett</option>
                                </select>
                            </td>
                            <td>
                                <input type="text" name="desc" style="width: 300px;"/>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="5"><b>List�zva:</b> 1 - ${fn:length(students)}, <b>�sszesen:</b> ${fn:length(students)}</td>
                    </tr>
                </table>
                <input type="submit" value="Mehet" />
            </div>
        </div>
    </form>
</c:if>
<c:if test="${param.all != null}">
    <div id="secondSubHeader">
        <form method="post" action="/Neptun/teacher/attendance.jsp?id=${param.id}">                
            <a onclick="this.parentNode.submit()" style="cursor: pointer;"><img src="../images/bell--plus.png" style="vertical-align: middle;"/> Hi�nyz�s hozz�ad�sa</a>
        </form>
    </div>    
    <div class="box" style="width: 99%;">
        <div class="header"></div>
        <div class="content">
            <table class="sortableTable"> 
                <thead>
                    <tr>
                        <th><b>N�v</b></th>
                        <th><b>D�tum</b></th>
                        <th><b>T�rgy</b></th>
                        <th><b>St�tusz</b></th>                            
                        <th><b>Megjegyz�s</b></th>                            
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="a" items="${attendances}">
                        <tr>
                            <td>${a.student.firstName} ${a.student.lastName}</td>
                            <td><fmt:formatDate pattern="yyyy.MM.dd" value="${a.created}"/></td>
                            <td>${a.lesson.subject.name}</td>                                
                            <td>${a.status.name}</td>                                
                            <td>${a.description}</td>                                
                        </tr>
                    </c:forEach>                            
                </tbody>
            </table> 
            <br />
            <b>List�zva:</b> 1 - ${fn:length(attendances)}, <b>�sszesen:</b> ${fn:length(attendances)}
        </div>
    </div>    
</c:if>
