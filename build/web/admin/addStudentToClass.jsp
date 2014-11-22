<%@page contentType="text/html" pageEncoding="UTF-8"%>

<form action="addStudentToClass" method="post" >    
    <div class="box" style="width: 99%">
        <div class="header"><h2>Diákok osztályhoz rendelése</h2></div>
        <div class="content">        
            <table>
                <tr>
                    <td>Osztály:</td>
                    <td>
                        <select name="class" class="chzn-choices" style="width: 150px;">
                            <c:forEach var="c" items="${classes}">
                                <option class="chzn-results" value="${c.id}">${c.name}</option>                                    
                            </c:forEach>
                        </select>                     
                    </td>
                </tr>
                <tr>
                    <td>Diákok:</td>
                    <td>
                        <select multiple="multiple" name="students" class="chzn-choices" style="width: 700px;">
                            <c:forEach var="s" items="${students}">
                                <option class="chzn-results" value="${s.studentIdNumber}">${s.firstName} ${s.lastName} (${s.dateOfBirth})</option>                                    
                            </c:forEach>
                        </select>
                    </td>                        
                </tr>                                                                                                           
            </table>        
        </div>
    </div>        
    <input type="submit" value="Hozzáadás" />    
</form>