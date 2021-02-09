package com.andrew.common.crudboot.crudboot.service;

import com.andrew.common.crudboot.crudboot.model.Role;
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
            if (st.equals("ADMIN")) { ;
                setRoles.add(Role.ADMIN);
            }
            if (st.equals("USER")) {
                setRoles.add(Role.USER);
            }
        }
        return setRoles;
    }
}

