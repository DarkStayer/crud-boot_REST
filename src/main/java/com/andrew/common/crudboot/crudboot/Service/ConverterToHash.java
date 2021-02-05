package com.andrew.common.crudboot.crudboot.Service;

import com.andrew.common.crudboot.crudboot.Model.Role;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ConverterToHash implements Converter<String [], Set<Role>> {

    @Override
    public Set<Role> convert(String [] role) {
        Set<Role> setRoles = new HashSet<>();
        for (String st : role) {
            if (st.equals("ROLE_ADMIN")) { ;
                Role role_admin = new Role(2L, "ROLE_ADMIN");
                setRoles.add(role_admin);
            }
            if (st.equals("ROLE_USER")) {
                Role role_user = new Role(1L, "ROLE_USER");
                setRoles.add(role_user);
            }
        }
        return setRoles;
    }
}

