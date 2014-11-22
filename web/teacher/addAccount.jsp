<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:choose>
    <c:when test="${param.classId != null}">
        <form action="addAccount?classId=${param.classId}" method="post" >
    </c:when>
    <c:otherwise>
        <form action="addAccount" method="post" >
    </c:otherwise>                
</c:choose>
    <div id="left">
        <div class="box">
            <div class="header"><h2>Számlaadatok</h2></div>
            <div class="content">        
                <table>
                    <tr>
                        <td>Számlaszám:</td>
                        <td><input type="text" name="number" /></td>
                        <td>Kötelező mező.</td>
                    </tr>                 
                </table>        
            </div>
        </div>        
        <input type="submit" value="Hozzáadás" />
    </div>
</form>