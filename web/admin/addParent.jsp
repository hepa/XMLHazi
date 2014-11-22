<%@page contentType="text/html" pageEncoding="UTF-8"%>

<form action="addParent" method="post" >
    <div id="left">
        <div class="box">
            <div class="header"><h2>Családi adatok</h2></div>
            <div class="content">        
                <table>
                    <tr>
                        <td>Anyja neve:</td>
                        <td><input type="text" name="motherName" /></td>
                        <td>A diák anyjának a neve.</td>
                    </tr>
                    <tr>
                        <td>Apja neve:</td>
                        <td><input type="text" name="fatherName" /></td>
                        <td>A diák apjának a neve.</td>
                    </tr>
                    <tr>
                        <td>Telefonszám:</td>
                        <td><input type="text" name="phoneNumber" /></td>
                        <td>A diák telefonszáma.</td>
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
                        <td>Bankszámlaszám:</td>
                        <td><input type="text" name="account" /></td>
                        <td>A diák bankszámla száma.</td>
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
                        <td><input type="text" name="password" /></td>
                        <td>A diák jelszava.</td>
                    </tr>
                </table>
            </div>
        </div>
        <input type="submit" value="Hozzáadás" />
    </div>
</form>