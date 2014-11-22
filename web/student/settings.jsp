<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="left">
    <div class="box">
        <div class="header"><h2>Üzenetek a rendszeren keresztül</h2></div>
        <div class="content">
            <table class="settingsTable">
                <tr>
                    <td>A jegybeírásról:</td>
                    <td>
                        <div class="onoffswitch">
                            <c:choose>
                                <c:when test="${settings.enabledMessageAboutGrade}">
                                    <input type="checkbox" onclick="off('message_grade', $(this))" class="onoffswitch-checkbox" id="messageAboutGrade" checked>
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" onclick="on('message_grade', $(this))" class="onoffswitch-checkbox" id="messageAboutGrade">
                                </c:otherwise>
                            </c:choose>
                            <label class="onoffswitch-label" for="messageAboutGrade">
                                <div class="onoffswitch-inner"></div>
                                <div class="onoffswitch-switch"></div>
                            </label>
                        </div>
                    </td>
                </tr> 
                <tr>
                    <td>A házi feladatok kiírásáról:</td>
                    <td>                    
                        <div class="onoffswitch">
                            <c:choose>
                                <c:when test="${settings.enabledMessageAboutHomework}">
                                    <input type="checkbox" onclick="off('message_homework', $(this))" class="onoffswitch-checkbox" id="messageAboutHomework" checked>
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" onclick="on('message_homework', $(this))" class="onoffswitch-checkbox" id="messageAboutHomework">
                                </c:otherwise>
                            </c:choose>
                            <label class="onoffswitch-label" for="messageAboutHomework">
                                <div class="onoffswitch-inner"></div>
                                <div class="onoffswitch-switch"></div>
                            </label>
                        </div>                    
                    </td>
                </tr>
                <tr>
                    <td>A házi feladatra történő jegybeírásról:</td>
                    <td>                    
                        <div class="onoffswitch">
                            <c:choose>
                                <c:when test="${settings.enabledMessageAboutHomeworkGrade}">
                                    <input type="checkbox" onclick="off('message_homework_grade', $(this))" class="onoffswitch-checkbox" id="messageAboutHomeworkGrade" checked>
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" onclick="on('message_homework_grade', $(this))" class="onoffswitch-checkbox" id="messageAboutHomeworkGrade">
                                </c:otherwise>
                            </c:choose>
                            <label class="onoffswitch-label" for="messageAboutHomeworkGrade">
                                <div class="onoffswitch-inner"></div>
                                <div class="onoffswitch-switch"></div>
                            </label>
                        </div>                    
                    </td>
                </tr>
                <tr>
                    <td>Befizetési kötelezettségről:</td>
                    <td>                    
                        <div class="onoffswitch">
                            <c:choose>
                                <c:when test="${settings.enabledMessageAboutItem}">
                                    <input type="checkbox" onclick="off('message_item', $(this))" class="onoffswitch-checkbox" id="messageAboutItem" checked>
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" onclick="on('message_item', $(this))" class="onoffswitch-checkbox" id="messageAboutItem">
                                </c:otherwise>
                            </c:choose>
                            <label class="onoffswitch-label" for="messageAboutItem">
                                <div class="onoffswitch-inner"></div>
                                <div class="onoffswitch-switch"></div>
                            </label>
                        </div>                    
                    </td>
                </tr>
                <tr>
                    <td>Katalógusba történő beírásról:</td>
                    <td>                    
                        <div class="onoffswitch">
                            <c:choose>
                                <c:when test="${settings.enabledMessageAboutAttendance}">
                                    <input type="checkbox" onclick="off('message_attendance', $(this))" class="onoffswitch-checkbox" id="messageAboutAttendance" checked>
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" onclick="on('message_attendance', $(this))" class="onoffswitch-checkbox" id="messageAboutAttendance">
                                </c:otherwise>
                            </c:choose>
                            <label class="onoffswitch-label" for="messageAboutAttendance">
                                <div class="onoffswitch-inner"></div>
                                <div class="onoffswitch-switch"></div>
                            </label>
                        </div>                    
                    </td>
                </tr>                
            </table>
        </div>
    </div>
    <div class="box">
        <div class="header"><h2>E-mailek</h2></div>
        <div class="content">
            <table class="settingsTable">
                <tr>
                    <td>A jegybeírásról:</td>
                    <td>
                        <div class="onoffswitch">
                            <c:choose>
                                <c:when test="${settings.enabledEmailAboutGrade}">
                                    <input type="checkbox" onclick="off('email_grade', $(this))" class="onoffswitch-checkbox" id="emailAboutGrade" checked>
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" onclick="on('email_grade', $(this))" class="onoffswitch-checkbox" id="emailAboutGrade">
                                </c:otherwise>
                            </c:choose>
                            <label class="onoffswitch-label" for="emailAboutGrade">
                                <div class="onoffswitch-inner"></div>
                                <div class="onoffswitch-switch"></div>
                            </label>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>A házi feladatok kiírásáról:</td>
                    <td>                    
                        <div class="onoffswitch">
                            <c:choose>
                                <c:when test="${settings.enabledEmailAboutHomework}">
                                    <input type="checkbox" onclick="off('email_homework', $(this))" class="onoffswitch-checkbox" id="emailAboutHomework" checked>
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" onclick="on('email_homework', $(this))" class="onoffswitch-checkbox" id="emailAboutHomework">
                                </c:otherwise>
                            </c:choose>
                            <label class="onoffswitch-label" for="emailAboutHomework">
                                <div class="onoffswitch-inner"></div>
                                <div class="onoffswitch-switch"></div>
                            </label>
                        </div>                    
                    </td>
                </tr>
                <tr>
                    <td>A házi feladatra történő jegybeírásról:</td>
                    <td>                    
                        <div class="onoffswitch">
                            <c:choose>
                                <c:when test="${settings.enabledEmailAboutHomeworkGrade}">
                                    <input type="checkbox" onclick="off('email_homework_grade', $(this))" class="onoffswitch-checkbox" id="emailAboutHomeworkGrade" checked>
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" onclick="on('email_homework_grade', $(this))" class="onoffswitch-checkbox" id="emailAboutHomeworkGrade">
                                </c:otherwise>
                            </c:choose>
                            <label class="onoffswitch-label" for="emailAboutHomeworkGrade">
                                <div class="onoffswitch-inner"></div>
                                <div class="onoffswitch-switch"></div>
                            </label>
                        </div>                    
                    </td>
                </tr>
                <tr>
                    <td>Befizetési kötelezettségről:</td>
                    <td>                    
                        <div class="onoffswitch">
                            <c:choose>
                                <c:when test="${settings.enabledEmailAboutItem}">
                                    <input type="checkbox" onclick="off('email_item', $(this))" class="onoffswitch-checkbox" id="emailAboutItem" checked>
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" onclick="on('email_item', $(this))" class="onoffswitch-checkbox" id="emailAboutItem">
                                </c:otherwise>
                            </c:choose>
                            <label class="onoffswitch-label" for="emailAboutItem">
                                <div class="onoffswitch-inner"></div>
                                <div class="onoffswitch-switch"></div>
                            </label>
                        </div>                    
                    </td>
                </tr>
                <tr>
                    <td>Katalógusba történő beírásról:</td>
                    <td>                    
                        <div class="onoffswitch">
                            <c:choose>
                                <c:when test="${settings.enabledEmailAboutAttendance}">
                                    <input type="checkbox" onclick="off('email_attendance', $(this))" class="onoffswitch-checkbox" id="emailAboutAttendance" checked>
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" onclick="on('email_attendance', $(this))" class="onoffswitch-checkbox" id="emailAboutAttendance">
                                </c:otherwise>
                            </c:choose>                            
                            <label class="onoffswitch-label" for="emailAboutAttendance">
                                <div class="onoffswitch-inner"></div>
                                <div class="onoffswitch-switch"></div>
                            </label>
                        </div>                    
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
<script>
    
    $("#messageAboutAddGrade").change(function() {
        console.log("changed");
    });
    
    function on(attribute, link)
    {             
        $.ajax({
            type:       "post",
            url:        "updateSettings",
            data:       {"attribute": attribute.toString(), "value": "true"},
            success:    function(msg) {
                $(link).attr("onclick", "off('" + attribute + "', $(this))");
            },
            error:function (xhr, ajaxOptions, thrownError){
                alert(xhr.status);
                alert(thrownError);
            }  
        });
        return false;
    }
    
    function off(attribute, link)
    {             
        $.ajax({
            type:       "post",
            url:        "updateSettings",
            data:       {"attribute": attribute.toString(), "value": "false"},
            success:    function(msg) {
                $(link).attr("onclick", "on('" + attribute + "', $(this))");
            },
            error:function (xhr, ajaxOptions, thrownError){
                alert(xhr.status);
                alert(thrownError);
            }  
        });
        return false;
    }
    
</script>