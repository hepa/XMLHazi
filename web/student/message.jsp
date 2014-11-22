<div class="box" style="width: 99%;">
    <div class="header"><h2>�zenet �r�sa</h2></div>
    <div class="content">
        <c:choose>
            <c:when test="${param.classId != null}">
                <form method="post" action="sendMessage?classId=${param.classId}">
                    <table style="width: 100%;">
                        <tr>
                            <td>C�mzett:</td>
                            <td>
                                <select name="to" class="chzn-choices" multiple style="width: 100%">
                                    <c:forEach var="s" items="${students}">
                                        <option value="${s.username}" selected>${s.firstName} ${s.lastName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>T�rgy:</td>
                            <td><input name="subject" type="text" /></td>
                        </tr>
                        <tr>
                            <td>�zenet:</td>
                            <td><textarea name="body" rows="8" cols="50"></textarea></td>
                        </tr>
                    </table>     
                    <input type="submit" value="K�ld�s" />
                </form>
            </c:when>
            <c:otherwise>
                <form method="post" action="sendMessage">                    
                    <table>
                        <tr>
                            <td>C�mzett:</td>
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
                            <td>T�rgy:</td>
                            <td><input name="subject" type="text" /></td>
                        </tr>
                        <tr>
                            <td>�zenet:</td>
                            <td><textarea name="body" rows="8" cols="50"></textarea></td>
                        </tr>
                    </table>     
                    <input type="submit" value="K�ld�s" />
                </form> 
            </c:otherwise>
        </c:choose>       
    </div>
</div>