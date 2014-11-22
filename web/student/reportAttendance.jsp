<div id="left">
    <div class="box">
        <div class="header"><h2>Hi�nyz�s bejelent�se</h2></div>
        <div class="content">           
            <table>
                <tr>
                    <td>Kezdete:</td>
                    <td><input type="text" name="from" class="datepicker" /></td>
                </tr>
                <tr>
                    <td>V�ge:</td>
                    <td><input type="text" name="to" class="datepicker" /></td>
                </tr>
                <tr>
                    <td>Megjegyz�s:</td>
                    <td><textarea rows="6" cols="30" name="comment"></textarea></td>
                </tr>
            </table>
            <input onclick="reportAttendance()" type="button" value="Mehet" />            
        </div>
    </div>
</div>
<script>
    function reportAttendance()
    {        
        var from = $("input[name=from]").val();
        var to = $("input[name=to]").val();
        var comment = $("textarea[name=comment]").val();                
                
        $.ajax({
            type:       "post",
            url:        "reportAttendance",
            data:       {"from": from, "to": to, "comment": comment},
            success:    function(msg) {
                $("input[name=from]").val("");
                $("input[name=to]").val("");
                $("textarea[name=comment]").val("");
                $(".popup").attr("class", "popup_success").html("Sikeresen elk�ldve.").show("drop", 200).delay(3000).fadeOut(200);
            },
            error:function (xhr, ajaxOptions, thrownError){
                alert(xhr.status);
                alert(thrownError);
            }  
        });
        return false;        
    }
</script>