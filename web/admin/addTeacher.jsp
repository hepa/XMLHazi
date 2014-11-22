<%@page contentType="text/html" pageEncoding="UTF-8"%>

<form action="addTeacher" method="post" >
    <div id="left">
        <div class="box">
            <div class="header"><h2>Személyes adatok</h2></div>
            <div class="content">        
                <table>
                    <tr>
                        <td>Vezetéknév:</td>
                        <td><input type="text" name="firstName" /></td>
                        <td>A tanár vezetékneve.</td>
                    </tr>
                    <tr>
                        <td>Középső név:</td>
                        <td><input type="text" name="middleName" /></td>
                        <td>A tanár középső neve.</td>
                    </tr>
                    <tr>
                        <td>Keresztnév:</td>
                        <td><input type="text" name="lastName" /></td>
                        <td>A tanár keresztneve.</td>
                    </tr>                    
                    <tr>
                        <td>Neme:</td>
                        <td>
                            <select name="gender">
                                <option value="férfi">férfi</option>
                                <option value="nő">nő</option>
                            </select>
                        </td>
                        <td>A tanár neme.</td>
                    </tr>
                    <tr>
                        <td>Személyigazolványszám:</td>
                        <td><input type="text" name="idCardNumber" /></td>
                        <td>A tanár személyigazolvány száma.</td>
                    </tr>
                    <tr>
                        <td>Mobil szám:</td>
                        <td><input type="text" name="mobileNumber" /></td>
                        <td>A tanár mobiltelefon száma.</td>
                    </tr>
                    <tr>
                        <td>Telefonszám:</td>
                        <td><input type="text" name="phoneNumber" /></td>
                        <td>A tanár telefonszáma.</td>
                    </tr>
                    <tr>
                        <td>E-mail cím:</td>
                        <td><input type="text" name="email" /></td>
                        <td>A tanár e-mail címe.</td>
                    </tr>
                    <tr>
                        <td>Születési idő:</td>
                        <td><input type="text" class="datepicker" name="dateOfBirth" /></td>
                        <td>A tanár születési ideje.</td>
                    </tr>    
                    <tr>
                        <td>Bankszámlaszám:</td>
                        <td><input type="text" name="account" /></td>
                        <td>A tanár bankszámla száma.</td>
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
                        <td>A tanár lakhelyének irányítószáma.</td>
                    </tr>
                    <tr>
                        <td>Település:</td>
                        <td colspan="2"><input type="text" name="cityName" /></td>                        
                    </tr>                    
                    <tr>
                        <td>Lakcím:</td>
                        <td><input type="text" name="address" /></td>
                        <td>A tanár lakcíme.</td>
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
                        <td>A tanár felhasználó neve.</td>
                    </tr>
                    <tr>
                        <td>Jelszava:</td>
                        <td><input type="password" name="password" /></td>
                        <td>A tanár jelszava.</td>
                    </tr>
                    <tr>
                        <td>Aktív:</td>
                        <td>
                            <select name="active">
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