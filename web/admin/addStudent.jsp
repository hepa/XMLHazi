<%@page contentType="text/html" pageEncoding="UTF-8"%>



<form action="addStudent" method="post" >
    <div id="left">
        <div class="box">
            <div class="header"><h2>Személyes adatok</h2></div>
            <div class="content">        
                <table>
                    <tr>
                        <td>Vezetéknév:</td>
                        <td><input type="text" name="firstName" /></td>
                        <td>A diák vezetékneve.</td>
                    </tr>
                    <tr>
                        <td>Középső név:</td>
                        <td><input type="text" name="middleName" /></td>
                        <td>A diák középső neve.</td>
                    </tr>
                    <tr>
                        <td>Keresztnév:</td>
                        <td><input type="text" name="lastName" /></td>
                        <td>A diák keresztneve.</td>
                    </tr>                    
                    <tr>
                        <td>Neme:</td>
                        <td>
                            <select name="gender" class="chzn-select" style="width: 150px;">
                                <option value="férfi">férfi</option>
                                <option value="nő">nő</option>
                            </select>
                        </td>
                        <td>A diák neme.</td>
                    </tr>
                    <tr>
                        <td>Diákigazolvány szám:</td>
                        <td><input type="text" name="studentIdNumber" /></td>
                        <td>A diák diákigazolvány száma.</td>
                    </tr>
                    <tr>
                        <td>Személyigazolványszám:</td>
                        <td><input type="text" name="idCardNumber" /></td>
                        <td>A diák személyigazolvány száma.</td>
                    </tr>
                    <tr>
                        <td>Mobil szám:</td>
                        <td><input type="text" name="mobileNumber" /></td>
                        <td>A diák mobiltelefon száma.</td>
                    </tr>
                    <tr>
                        <td>E-mail cím:</td>
                        <td><input type="text" name="email" /></td>
                        <td>A diák e-mail címe.</td>
                    </tr>
                    <tr>
                        <td>Születési idő:</td>
                        <td><input type="text" class="datepicker" name="dateOfBirth" /></td>
                        <td>A diák születési ideje.</td>
                    </tr>                                                                       
                </table>        
            </div>
        </div>
        <div class="box">
            <div class="header"><h2>Lakcím adatok</h2></div>
            <div class="content">
                <table>
                    <tr>
                        <td>Település irányítószáma:</td>
                        <td><input type="text" name="zipCode" /></td>
                        <td>A diák lakhelyének irányítószáma.</td>
                    </tr>
                    <tr>
                        <td>Település:</td>
                        <td colspan="2"><input type="text" name="cityName" /></td>                        
                    </tr>
                    <tr>
                        <td>Lakcím:</td>
                        <td><input type="text" name="address" /></td>
                        <td>A diák lakcíme.</td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="box">
            <div class="header"><h2>Iskolai adatok</h2></div>
            <div class="content">
                <table>
                    <tr>
                        <td>Beiratkozás ideje:</td>
                        <td><input type="text" class="datepicker" name="dateOfJoin" /></td>
                        <td>Amikor a diák beiratkozott az iskolába.</td>
                    </tr>
                    <tr>
                        <td>Hallgatói státusz:</td>
                        <td>
                            <select name="status" class="chzn-select" style="width: 150px;">
                                <option value="true">igen</option>
                                <option value="false">nem</option>
                            </select>
                        </td>
                        <td>A diák státusza.</td>
                    </tr> 
                </table>
            </div>
        </div>
        <div class="box">
            <div class="header"><h2>Bejelentkezési adatok</h2></div>
            <div class="content">
                <table>
                    <tr>
                        <td>Felhasználó neve:</td>
                        <td><input type="text" name="username" /></td>
                        <td>A diák felhasználó neve.</td>
                    </tr>
                    <tr>
                        <td>Jelszava:</td>
                        <td><input type="password" name="password" /></td>
                        <td>A diák jelszava.</td>
                    </tr>
                    <tr>
                        <td>Aktív:</td>
                        <td>
                            <select name="active" class="chzn-select" style="width: 150px;">
                                <option value="true">igen</option>
                                <option value="false">nem</option>
                            </select>
                        </td>
                        <td>A fiók aktívsága.</td>
                    </tr>
                </table>
            </div>
        </div>
        <input type="submit" value="Hozzáadás" />
    </div>
</form>