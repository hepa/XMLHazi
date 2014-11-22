<%@page contentType="text/html" pageEncoding="UTF-8"%>

<form action="addClass" method="post" >
    <div id="left">
        <div class="box">
            <div class="header"><h2>Osztály adatai</h2></div>
            <div class="content">        
                <table>
                    <tr>
                        <td>Megnevezés:</td>
                        <td><input type="text" name="name" /></td>
                        <td>Az adott osztály megnevezése.</td>
                    </tr>
                    <tr>
                        <td>Osztályfőnök:</td>
                        <td>
                            <select name="formMaster" class="chzn-select" style="width:150px;">
                                <c:forEach var="t" items="${teachers}">
                                    <option value="${t.idCardNumber}">${t.firstName} ${t.lastName}</option>                                    
                                </c:forEach>
                            </select>
                        </td>
                        <td>Az osztály osztályfőnöke.</td>
                    </tr>
                    <tr>
                        <td>Számlaszám:</td>
                        <td><input type="text" name="account_number" /></td>
                        <td>Az osztály saját számlájának, osztálypénz befizetésére.</td>
                    </tr>                                                                                                            
                </table>        
            </div>
        </div>        
        <input type="submit" value="Hozzáadás" />
    </div>
</form>