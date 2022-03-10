package org.example.proxy.jdk;

import java.util.Arrays;
import java.util.List;

/**
 * <p> @author     :  清风
 * <p> description :
 * <p> create date :  2022/3/3 15:20
 */
public class UserServiceImpl implements UserService {
    @Override
    public List<User> listAll() {
        return Arrays.asList(new User(), new User());
    }
}
