</div>
<div class="popup">
    <img src="../images/mail.png" style="vertical-align: middle;"/> <b>Új üzeneted érkezett!</b>
    <table>
        <tr>
            <td><b>Tárgy:</b> <i class="from"></i></td>
        </tr>
        <tr>
            <td class="subject"></td>
        </tr>
    </table>
</div>
<div class="popup_success"></div>
<div class="popup_error"></div>
<script>  
    $(document).ready(function() {
        refresh();
        setInterval(function(){refresh()}, 5000);
    });    
    
    function refresh() {
        $.post('refreshMail', function(msg){            
            var substr = msg.split("`");                  
            
            parseInt(substr[0]);            
            if (substr[0] > 20) {
                $("#notify").attr("src", "../images/notification-counter-20-plus.png");
            } else if (substr[0] == 0) {
                $("#notify").removeAttr("src");
            } else {                
                $("#notify").attr("src", "../images/notification-counter-" + substr[0] + ".png");                
            }
            if (substr[1] == 1) {
                $(".popup .from").html(substr[2]);
                $(".popup .subject").html("<b>Küldte:</b> " + substr[3]);
                $(".popup").show("drop", 200).delay(5000).fadeOut(200);
                substr[1] = 0;
            }
        });
        
    }   
    
</script>
</body>
</html>