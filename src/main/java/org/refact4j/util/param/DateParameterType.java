package org.refact4j.util.param;

import java.util.Date;

public class DateParameterType extends ParameterType {

    public DateParameterType(String name) {
        super(name, Date.class);
    }

}
