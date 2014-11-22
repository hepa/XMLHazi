<div id="left">
    <div class="box">
        <div class="header"><h2>Óráim</h2></div>
        <div class="content">
            <table>
                <thead>
                    <tr>
                        <td><b>Évfolyam</b></td>
                        <td><b>Nap</b></td>
                        <td><b>Óra</b></td>
                        <td><b>Tárgy</b></td>
                        <td><b>Osztály</b></td>
                        <td><b>Terem</b></td>
                    </tr>
                </thead>
                <c:forEach var="lesson" items="${lessons}">
                    <tr>
                        <td><img src="../images/calendar-month.png" alt="year" style="vertical-align: middle;"/> ${lesson.year.name}</td>                            
                        <td><a href="lesson.jsp?id=${lesson.id}" >${lesson.subject.name}</a></td>
                        <td><img src="../images/users.png" alt="form" style="vertical-align: middle;"/> <a href="class.jsp?id=${lesson.form.id}" >${lesson.form.name}</td>
                        <td><img src="../images/building-low.png" alt="classroom" style="vertical-align: middle;"/> <a href="classroom.jsp?name=${lesson.classRoom.name}" > ${lesson.classRoom.name}</td>
                    </tr>
                    <c:forEach var="date" items="${lesson.dates}">
                        <tr>
                            <td colspan="4">
                                <a href="lessonsPerDay.jsp?day=${date.day}"><img src="../images/calendar-select-days.png" alt="day" style="vertical-align: middle;"/>${date.day}</a> <img src="../images/clock.png" alt="clock" style="vertical-align: middle;"/>${date.hour}:${date.minutes}
                            </td>
                        </tr>
                    </c:forEach>
                </c:forEach>
            </table>
        </div>
    </div>
</div>