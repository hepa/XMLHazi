<!-- CSS -->
<link type="text/css" href="../css/admin/main.css" rel="stylesheet" /> 

<div class="box">
    <div class="header"><h2>�sszes di�k adata</h2></div>                
    <div class="content">                    
        <form method="post" action="/Neptun/admin/main">                
            <a onclick="this.parentNode.submit()" style="cursor: pointer;"><img src="../images/arrow-circle-225-left.png" style="vertical-align: middle;"/> Friss�t�s</a>
        </form>
        <form method="post" action="/Neptun/admin/addStudent.jsp">                
            <a onclick="this.parentNode.submit()" style="cursor: pointer;"><img src="../images/user--plus.png" style="vertical-align: middle;"/> �j di�k hozz�ad�sa</a>
        </form>
        <div class="clear"></div> 
        <table>
            <thead>
                <tr>   
                    <td></td>
                    <td>Di�kigazolv�ny sz�m</td>
                    <td>Vezet�kn�v</td>
                    <td>Keresztn�v</td>
                    <td>E-mail c�m</td>
                    <td>Mobil sz�m</td>
                    <td>Sz�let�si id�</td>                                
                    <td>Akt�v</td>
                    <td>Azonos�t�</td>
                    <td>Lakc�m</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="student" items="${students}">
                    <tr>   
                        <td>
                            <form method="post" action="/Neptun/admin/studentProfile?id=${student.studentIdNumber}">
                                <a onclick="this.parentNode.submit()" style="cursor: pointer;"><img src="../images/information.png"/></a>
                            </form>
                        </td>
                        <td>${student.studentIdNumber}</td>
                        <td>${student.firstName}</td>
                        <td>${student.lastName}</td>
                        <td>${student.email}</td>
                        <td>${student.mobileNumber}</td>
                        <td>${student.dateOfBirth}</td>                                    
                        <td>${student.status}</td>
                        <td>${student.username}</td>
                        <td>${student.city.zipCode} ${student.city.name} ${student.address}</td>                                    
                        <td>
                            <form method="post" action="/Neptun/admin/deleteStudent?id=${student.studentIdNumber}">
                                <a onclick="this.parentNode.submit()" style="cursor: pointer;"><img src="../images/cross.png"/></a>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table> 
    </div>
</div>
<div class="box">
    <div class="header"><h2>�sszes tan�r adata</h2></div>                
    <div class="content">                    
        <form method="post" action="/Neptun/admin/main">                
            <a onclick="this.parentNode.submit()" style="cursor: pointer;"><img src="../images/arrow-circle-225-left.png" style="vertical-align: middle;"/> Friss�t�s</a>
        </form>
        <form method="post" action="/Neptun/admin/addTeacher.jsp">                
            <a onclick="this.parentNode.submit()" style="cursor: pointer;"><img src="../images/user--plus.png" style="vertical-align: middle;"/> �j tan�r hozz�ad�sa</a>
        </form>
        <div class="clear"></div> 
        <table>
            <thead>
                <tr>   
                    <td></td>
                    <td>Szem�lyigazolv�nysz�m</td>
                    <td>Vezet�kn�v</td>
                    <td>Keresztn�v</td>
                    <td>E-mail c�m</td>
                    <td>Mobil sz�m</td>
                    <td>Sz�let�si id�</td>                                                    
                    <td>Azonos�t�</td>
                    <td>Lakc�m</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="teacher" items="${teachers}">
                    <tr>   
                        <td>
                            <form method="post" action="/Neptun/admin/teacherProfile?id=${teacher.idCardNumber}">
                                <a onclick="this.parentNode.submit()" style="cursor: pointer;"><img src="../images/information.png"/></a>
                            </form>
                        </td>
                        <td>${teacher.idCardNumber}</td>
                        <td>${teacher.firstName}</td>
                        <td>${teacher.lastName}</td>
                        <td>${teacher.email}</td>
                        <td>${teacher.mobileNumber}</td>
                        <td>${teacher.dateOfBirth}</td>                                                            
                        <td>${teacher.username}</td>                        
                        <td>${teacher.account}</td>  
                        <td>${teacher.city.zipCode} ${teacher.city.name} ${teacher.address}</td>                                    
                        <td>
                            <form method="post" action="/Neptun/admin/deleteTeacher?id=${teacher.idCardNumber}">
                                <a onclick="this.parentNode.submit()" style="cursor: pointer;"><img src="../images/cross.png"/></a>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table> 
    </div>
</div>
<div class="clear"></div>            
