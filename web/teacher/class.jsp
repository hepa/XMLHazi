<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="contains" value="false"></c:set>
<c:if test="${param.id != null}">
    <c:forEach var="l" items="${lessons}">
        <c:if test="${l.form.id == param.id}">
            <c:set var="contains" value="true"></c:set>
        </c:if>
    </c:forEach>
    <div id="secondSubHeader">
        <form method="post" action="/Neptun/teacher/class.jsp">                
            <a onclick="this.parentNode.submit()" style="cursor: pointer;"><img src="../images/magnifier.png" style="vertical-align: middle;"/> Keresés</a>
        </form>
    </div> 
    <div id="left">                
        <div class="box">
            <div class="header">
                <h2>${c.name}</h2>
            </div>
            <div class="content"> 
                <c:if test="${my_class.id == param.id}">
                    <form method="post" action="/Neptun/teacher/addMeeting.jsp">                
                        <a onclick="this.parentNode.submit()" style="cursor: pointer;"><img src="../images/alarm-clock--plus.png" style="vertical-align: middle;"/> Találkozó hozzáadása</a>
                    </form><br />            
                </c:if>
                <table>
                    <thead>
                        <tr>
                            <td><b>Név</b></td>
                        </tr>
                    </thead>
                    <tbody>                    
                        <c:forEach var="s" items="${c.students}">
                            <tr>
                                <td>
                                    <a href="studentDetails.jsp?id=${s.studentIdNumber}">${s.firstName} ${s.lastName}</a>
                                </td>
                                <td>
                                    <a href="message.jsp?studentId=${s.studentIdNumber}"><img src="../images/mail.png" alt="message" title="Üzenet küldése"/></a>
                                </td>
                                <c:if test="${contains == true}">
                                    <td>
                                        <a href="grade.jsp?studentId=${s.studentIdNumber}"><img src="../images/paint-brush--plus.png" alt="grade" title="Jegy beírása"/></a>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>     
            </div>
        </div>
    </div>
    <div id="right">
        <div class="pin">
            <div class="header">Részletek</div>
            <div class="content">
                <table>
                    <tr>
                        <td><b>Osztályfőnök</b></td>
                    </tr>
                    <tr>
                        <td>
                            ${c.formMaster.firstName} ${c.formMaster.lastName}
                        </td>
                    </tr>
                    <tr>
                        <td><b>Osztály létszáma</b></td>
                    </tr>
                    <tr>
                        <td>
                            ${fn:length(c.students)} fõ
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</c:if>

<!-- Osztály keresése -->
<c:if test="${param.id == null}">
    <div class="box" style="width: 99%;">
        <div class="header"> 
            <h2><img src="../images/magnifier.png" alt="Search" style="vertical-align: middle;"/> Keresés</h2>
        </div>        
        <div class="content">
            <form method="post" action="searchClass">
                <table>
                    <tr>
                        <td>
                            <select name="name" class="chzn-select-deselect" style="width: 100px;" data-placeholder="Osztály">
                                <option></option>
                                <c:forEach var="c" items="${classes}">
                                    <option value="${c.name}">${c.name}</option>
                                </c:forEach>
                            </select>                        
                        </td>
                        <td>
                            <select name="teacher" class="chzn-select-deselect" style="width: 250px;" data-placeholder="Osztályfőnök">
                                <option></option>
                                <c:forEach var="c" items="${classes}">
                                    <option value="${c.formMaster.idCardNumber}">${c.formMaster.firstName} ${c.formMaster.lastName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <select name="student" class="chzn-select-deselect" style="width: 250px;" data-placeholder="Diák">
                                <option></option>
                                <c:forEach var="s" items="${students}">
                                    <option value="${s.studentIdNumber}">${s.firstName} ${s.lastName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <input type="submit" value="Keres" />
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>  
    <c:if test="${results != null}">
        <div class="box" style="width: 99%;">
            <div class="header"><h2>Találat</h2></div>
            <div class="content">
                <table class="sortableTable">
                    <thead>
                        <tr>
                            <th>Név</th>
                            <th>Osztályfőnök</th>
                            <th>Osztály létszám</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="r" items="${results}">
                            <tr>                                
                                <td><a href="class.jsp?id=${r.id}">${r.name}</a></td>
                                <td>${r.formMaster.firstName} ${r.formMaster.lastName}</td>
                                <td>${fn:length(r.students)}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </c:if>
</c:if>