package login;

import errors.ProfileInactive;
import errors.PasswordsDontMatch;
import errors.UserNotFound;

public interface ILogin {            
    public boolean isActive() throws UserNotFound;
    public boolean Authenticate() throws UserNotFound, PasswordsDontMatch, ProfileInactive;
    public int updateLoginTime();
}
