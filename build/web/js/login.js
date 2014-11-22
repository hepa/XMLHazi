$(document).ready(function() {
    
    closeAll();
    $("#loginFormStudent").show();
    
    $("#loginFormIconTeacher").click(function() {
        $("#loginFormTeacher").toggle("slow");
        $("#loginFormStudent").hide("slow");
        $("#loginFormParent").hide("slow");        
        clearAllErrorField();
    });
    $("#loginFormIconStudent").click(function() {
        $("#loginFormStudent").toggle("slow");
        $("#loginFormTeacher").hide("slow");
        $("#loginFormParent").hide("slow");
        clearAllErrorField();
    });
    $("#loginFormIconParent").click(function() {
        $("#loginFormParent").toggle("slow");
        $("#loginFormStudent").hide("slow");
        $("#loginFormTeacher").hide("slow");
        clearAllErrorField();
    });
});

function closeAll() {
    $("#loginFormTeacher").hide();
    $("#loginFormStudent").hide();
    $("#loginFormParent").hide();
}

function clearAllErrorField() {    
    $("input[name=identifier]").css({
        "border": "1px solid #bbb"
    }).attr("placeholder", "");
    $("input[name=password]").css({
        "border": "1px solid #bbb"
    }).attr("placeholder", "");
}

$("input").blur(function() {
    if ($(this).val() == "") {
        $(this).css({
            "border": "1px solid red",            
        }).attr("placeholder", "Nem lehet üres!");
    } else {
        $(this).css({
            "border": "1px solid #bbb" 
        });
    }
});

function validate(form) {        
    var identifier = $("input[name=identifier]");
    var password = $("input[name=password]");
        
    identifier = $(form).find(identifier);
    password = $(form).find(password);       
        
    if (identifier.val() == "") {
        identifier.css({
            "border": "1px solid red"
        }).attr("placeholder", "Nem lehet üres!");        
    } else {
        identifier.css({
            "border": "1px solid #bbb"
        }); 
    }
    if (password.val() == "") {
        password.css({
            "border": "1px solid red"
        }).attr("placeholder", "Nem lehet üres!");        
    } else {
        password.css({
            "border": "1px solid #bbb"
        }); 
    }
    if ((identifier.val() != "") && (password.val() != "")) {        
        return true;
    }    
    return false;        
}