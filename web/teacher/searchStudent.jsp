<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="box" style="width: 99%;">
    <div class="header">
        <h2><img src="../images/magnifier.png" alt="Search" style="vertical-align: middle;"/> Diák keresése</h2>
    </div>
    <div class="content">
        <form method="post" action="searchStudent">
            <table>
                <tr>
                    <td>Vezetéknév:</td>
                    <td><input type="text" name="firstName" /></td>
                    <td>Keresztnév:</td>
                    <td><input type="text" name="lastName" /></td>                
                    <td>
                        <select class="chzn-select-deselect" name="gender" style="width: 100px;" data-placeholder="Neme">
                            <option></option>
                            <option value="férfi">férfi</option>
                            <option value="nő">nő</option>
                        </select>
                    </td>    
                    <td>
                        <select class="chzn-select-deselect" name="status" style="width: 100px;" data-placeholder="Státusza">
                            <option></option>
                            <option value="true">aktív</option>
                            <option value="false">inaktív</option>
                        </select>
                    </td>
                </tr>            
                <tr>
                    <td>E-mail:</td>
                    <td colspan="5"><input type="text" name="email" size="60"/></td>
                </tr>
                <tr>
                    <td>Születési dátum:</td>
                    <td colspan="5"><input type="text" name="dob" class="datepicker"/></td>
                </tr>
                <tr>
                    <td>Beiratkozás dátuma:</td>
                    <td colspan="5"><input type="text" name="doj" class="datepicker"/></td>
                </tr>
                <tr>
                    <td>Irányítószám:</td>
                    <td colspan="5"><input type="text" name="zipCode" /></td>
                </tr>
                <tr>
                    <td>Osztály:</td>
                    <td colspan="5">
                        <select class="chzn-select-deselect" name="class" style="width: 100px;" data-placeholder="üres">
                            <option value=""></option>    
                            <c:forEach var="c" items="${classes}">
                                <option value="${c.id}">${c.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
            </table>
            ${alma}
            <input type="submit" value="Keres" />
        </form>
    </div>
</div>
<c:if test="${result != null}">
    <div class="box" style="width: 99%;">
        <div class="header">
            <h2>Találatok</h2>
        </div>
        <div class="content">
            <table class="sortableTable">
                <thead>
                    <tr>
                        <th>Vezetéknév</th>
                        <th>Keresztnév</th>
                        <th>E-mail cím</th>
                        <th>Születési dátum</th>
                        <th>Beiratkozási dátum</th>
                        <th>Státusz</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="r" items="${result}">
                        <tr>
                            <td><a href="studentDetails.jsp?id=${r.studentIdNumber}">${r.firstName}</a></td>
                            <td><a href="studentDetails.jsp?id=${r.studentIdNumber}">${r.lastName}</a></td>
                            <td>${r.email}</td>                            
                            <td><img src="../images/calendar-select-days.png" alt="day" style="vertical-align: middle;"/> <fmt:formatDate pattern="yyyy.MM.dd" value="${r.dateOfBirth}"/> </td>
                            <td><img src="../images/calendar-select-days.png" alt="day" style="vertical-align: middle;"/> <fmt:formatDate pattern="yyyy.MM.dd" value="${r.dateOfJoin}"/> </td>
                            <td>
                                <c:choose>
                                    <c:when test="${r.status == true}">
                                        aktív
                                    </c:when>
                                    <c:otherwise>
                                        inaktív
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <br />
            <b>Listázva:</b> 1 - ${fn:length(result)}, <b>Összesen:</b> ${fn:length(result)}
        </div>
    </div>
</c:if>