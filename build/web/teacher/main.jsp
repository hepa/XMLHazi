<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="left">                
    <div class="box">
        <div class="header"><h2>${c.name}</h2></div>
        <div class="content">
            <form method="post" action="/Neptun/teacher/addMeeting.jsp" style="float: left; padding-right: 10px;">                
                <a onclick="this.parentNode.submit()" style="cursor: pointer;"><img src="../images/alarm-clock--plus.png" style="vertical-align: middle;"/> Találkozó hozzáadása</a>
            </form>
            <form method="post" action="message.jsp?classId=${c.id}" style="float: left; padding-right: 10px;">                
                <a onclick="this.parentNode.submit()" style="cursor: pointer;"><img src="../images/mails-stack.png" style="vertical-align: middle;"/> Üzenet küldése mindenkinek</a>
            </form>
            <c:choose>
                <c:when test="${c.account != null}">
                    <form method="post" action="accountManagement.jsp?number=${c.account.number}&classId=${c.id}">                
                        <a onclick="this.parentNode.submit()" style="cursor: pointer;"><img src="../images/money--pencil.png" style="vertical-align: middle;"/> Számlakezelés</a>
                    </form> 
                </c:when>
                <c:otherwise>
                    <form method="post" action="addAccount.jsp?classId=${c.id}">                
                        <a onclick="this.parentNode.submit()" style="cursor: pointer;"><img src="../images/money--plus.png" style="vertical-align: middle;"/> Számla hozzáadása</a>
                    </form> 
                </c:otherwise>
            </c:choose>           
            <br />
            <table class="sortableTable">
                <thead>
                    <tr>
                        <th>Név</th>
                        <th></th>
                        <th></th>
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
                            <td>
                                <a href="grade.jsp?studentId=${s.studentIdNumber}"><img src="../images/paint-brush--plus.png" alt="grade" title="Jegy beírása"/></a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>            
        </div>
    </div>  
    <div class="box">
        <div class="header"><h2>Mai óráim</h2></div>
        <div class="content">
            <c:choose>
                <c:when test="${fn:length(lessons) != 0}">
                    <table class="sortableTable">
                        <thead>
                            <tr>
                                <th><b>Évfolyam</b></th>                           
                                <th><b>Tárgy</b></th>
                                <th><b>Osztály</b></th>
                                <th><b>Terem</b></th>
                            </tr>
                        </thead>
                        <c:forEach var="lesson" items="${lessons}">
                            <tr>
                                <td><img src="../images/calendar-month.png" alt="year" style="vertical-align: middle;"/> <a href="holiday.jsp?id=${lesson.year.id}" >${lesson.year.name}</a></td>                                                
                                <td><img src="../images/chalkboard.png" alt="form" style="vertical-align: middle;"/> <a href="lesson.jsp?id=${lesson.id}" >${lesson.subject.name}</a></td>
                                <td><img src="../images/users.png" alt="form" style="vertical-align: middle;"/> <a href="class.jsp?id=${lesson.form.id}" >${lesson.form.name}</td>
                                <td><img src="../images/building-low.png" alt="classroom" style="vertical-align: middle;"/> <a href="classroom.jsp?name=${lesson.classRoom.name}" > ${lesson.classRoom.name}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <i>Ma nincsenek óráid.</i>
                </c:otherwise>
            </c:choose>
        </div>
    </div>  
    <div class="box">
        <div class="header"><h2>Holnapi óráim</h2></div>
        <div class="content">
            <c:choose>
                <c:when test="${fn:length(tomorrowLessons) != 0}">
                    <table class="sortableTable">                
                        <thead>
                            <tr>
                                <th><b>Évfolyam</b></th>                           
                                <th><b>Tárgy</b></th>                        
                                <th><b>Osztály</b></th>
                                <th><b>Terem</b></th>
                            </tr>
                        </thead>                
                        <c:forEach var="lesson" items="${tomorrowLessons}">
                            <tr>
                                <td><img src="../images/calendar-month.png" alt="year" style="vertical-align: middle;"/> <a href="holiday.jsp?id=${lesson.year.id}" >${lesson.year.name}</a></td>                                                
                                <td><img src="../images/chalkboard.png" alt="form" style="vertical-align: middle;"/> <a href="lesson.jsp?id=${lesson.id}" >${lesson.subject.name}</a></td>                        
                                <td><img src="../images/users.png" alt="form" style="vertical-align: middle;"/> <a href="class.jsp?id=${lesson.form.id}" >${lesson.form.name}</td>
                                <td><img src="../images/building-low.png" alt="classroom" style="vertical-align: middle;"/> <a href="classroom.jsp?name=${lesson.classRoom.name}" > ${lesson.classRoom.name}</a></td>                                
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <i>Holnap nem lesznek óráid.</i>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
<div id="right">
    <div class="pin">
        <div class="header">Fogadóórák</div>
        <div class="content">
            <form method="post" action="/Neptun/teacher/addConsultingHour.jsp">                
                <a onclick="this.parentNode.submit()" style="cursor: pointer;"><img src="../images/address-book-open.png" style="vertical-align: middle;"/> Fogadóórák szerkesztése</a>
            </form><br />  
            <c:if test="${consults == null}">
                <i>Még nincs fogadóórád.</i>
            </c:if>  
            <ul id="sortable" style="list-style: none; cursor: pointer;">
                <c:forEach var="c" items="${consults}">
                    <li>
                        <table style="width: 100%;">
                            <tr>
                                <td>
                                    <img src="../images/calendar-select-days.png" alt="day" style="vertical-align: middle;"/> <b>${c.day}</b>
                                    <a onclick='deleteConsultingHour(${c.teacher.idCardNumber}, "${c.day}", ${c.from}, $(this))' style="cursor: pointer; float: right;"><img src="../images/cross.png" style="vertical-align: middle;" title="Törlés"/></a>
                                </td>
                            </tr>
                            <tr>
                                <td style="padding-bottom: 15px;">
                                    <img src="../images/clock.png" alt="clock" style="vertical-align: middle;"/> ${c.from}:${c.to}
                                </td>
                            </tr>
                        </table>
                    </li>
                </c:forEach>       
            </ul>
        </div>
    </div>
</div>
<div class="clear"></div>            
<script>
    function deleteConsultingHour(tid, day, from, anchor)
    {             
        $.ajax({
            type:       "post",
            url:        "deleteConsultingHour",
            data:       {"teacherId": tid.toString(), "day": day.toString(), "from" : from.toString()},
            success:    function(msg) {
                $(anchor).closest("li").hide("drop", 500);
            },
            error:function (xhr, ajaxOptions, thrownError){
                alert(xhr.status);
                alert(thrownError);
            }  
        });
        return false;
    }
</script>