<c:if test="${holidays != null}">
<c:forEach var="h" items="${holidays}">
    <div class="box" style="width: 99%;">
        <div class="header"><h2>${h.type.name}</h2></div>
        <div class="content">
            <table class="sortableTable">
                <thead>
                    <tr>
                        <th>Kezdete</th>
                        <th>Vége</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><img src="../images/calendar-select-days.png" style="vertical-align: middle;" /> <fmt:formatDate pattern="yyyy.MM.dd" value="${h.from}"></fmt:formatDate></td>
                        <td><img src="../images/calendar-select-days.png" style="vertical-align: middle;" /> <fmt:formatDate pattern="yyyy.MM.dd" value="${h.to}"></fmt:formatDate></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</c:forEach>
</c:if>
<c:if test="${fn:length(holidays) == 0}">
    <div class="box" style="width: 99%;">
        <div class="header"><h2>Szünetek</h2></div>
        <div class="content"><i>Nincs még szünet hozzáadva.</i></div>
    </div>
</c:if>