<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="now" value="<%=new java.util.Date()%>"></c:set>
<c:if test="${param.lessonId == null}">
    <form method="post" action="addGrade?studentId=${param.studentId}">
    </c:if>
    <c:if test="${param.lessonId != null}">
        <form method="post" action="addGrade?lessonId=${param.lessonId}&studentId=${param.studentId}">
        </c:if>
        <div id="left">
            <div class="box">
                <div class="header"><h2>Jegy be�r�sa - ${student.firstName} ${student.lastName}</h2></div>
                <div class="content">            
                    <table>
                        <tr>
                            <td colspan="2">
                                <b>D�tum:</b>
                            </td>
                        </tr>
                        <tr>                        
                            <td colspan="2">
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
                            <td colspan="2">
                                <b>�ra:</b>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
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
                        <c:if test="${param.lessonId == null}">
                            <tr>
                                <td colspan="2">
                                    <b>Tant�rgy:</b>
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
                            <td colspan="2">
                                <b>T�pusa:</b>
                            </td>
                        </tr>
                        <tr>
                            <td>                        
                                <select name="examType" class="chzn-select" style="width: 200px;">
                                    <c:forEach var="t" items="${examtypes}">
                                        <option value="${t.id}">${t.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <select name="grade" class="chzn-select" style="width: 50px;">
                                    <c:forEach var="i" begin="1" end="5">
                                        <option value="${i}">${i}</option>
                                    </c:forEach>

                                </select>                    
                            </td>
                        </tr>
                    </table>            
                    <input type="submit" value="Be�r" />
                </div>
            </div>
        </div>
    </form>
    <div id="right">
        <div class="pin">
            <div class="header">Be�rt jegyek</div>
            <div class="content">
                <c:if test="${fn:length(grades) == 0}">
                    <i>M�g nincsenek jegyei.</i>
                </c:if>
                <c:if test="${fn:length(grades) != 0}">
                    <table class="sortableTable">
                        <thead>
                            <tr>
                                <th><b>D�tum</b></th>
                                <th><b>T�rgy</b></th>
                                <th><b>T�pusa</b></th>
                                <th><b>Jegy</b></th>
                                <th></th>
                            </tr>
                        </thead>
                        <c:forEach var="g" items="${grades}">
                            <tr>
                                <td><fmt:formatDate pattern="yyyy.MM.dd HH:mm" value="${g.created}"/></td>
                                <td>${g.lesson.subject.name}</td>
                                <td>${g.examtype.name}</td>
                                <td>${g.mark.id}</td>
                                <td><a onclick="deleteGrade(${g.id}, $(this))" style="cursor: pointer; float: right;"><img src="../images/cross.png" style="vertical-align: middle;" title="T�rl�s"/></a></td>
                            </tr>
                        </c:forEach>
                    </table>
                    <br />
                    <b>List�zva:</b> 1 - ${fn:length(grades)}, <b>�sszesen:</b> ${fn:length(grades)}
                </c:if>
            </div>
        </div>
    </div>
    <script>
        function deleteGrade(id, anchor)
        {                
            $.ajax({
                type:       "post",
                url:        "deleteGrade",
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