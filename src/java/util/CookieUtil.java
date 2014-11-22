package util;

import javax.servlet.http.*;

public class CookieUtil {
    public static String getCookieValue(Cookie[] cookies, String cookieName) {
        String cookieValue = "";
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (cookieName.equals(c.getName())) {
                    cookieValue = c.getValue();
                }
            }
        }
        return cookieValue;
    }
}
