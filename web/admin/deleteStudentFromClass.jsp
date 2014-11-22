<%@page contentType="text/html" pageEncoding="UTF-8"%>

<br />
<form method="post" action="/Neptun/admin/addStudentToClass.jsp">                
    <a onclick="this.parentNode.submit()" style="cursor: pointer;"><img src="../images/users.png" style="vertical-align: middle;"/> Diákok osztályhoz rendelése</a>
</form>
<form action="deleteStudentFromClass" method="post" >  
    <c:forEach var="c" items="${classes}">     
        <div class="box" style="width: 99%">
            <div class="header"><h2>${c.name}</h2></div>
            <div class="content">                     
                <table>               
                    <tr>
                        <td>Osztályfőnök:</td>
                        <td>${c.formMaster.firstName} ${c.formMaster.lastName}</td>
                    </tr>
                    <c:forEach var="s" items="${c.students}">
                        <tr>
                            <td><input type="checkbox" name="${c.name}" value="${s.studentIdNumber}" />${s.studentIdNumber}</td>
                            <td>${s.firstName}</td>
                            <td>${s.lastName}</td>
                            <td>${s.email}</td>
                            <td>${s.mobileNumber}</td>
                            <td>${s.dateOfBirth}</td>                                    
                            <td>${s.status}</td>
                            <td>${s.username}</td>                            
                        </tr>
                    </c:forEach>
                </table>        
            </div>
        </div>        
    </c:forEach>     
    <input type="submit" value="Hozzáadás" />    
</form>