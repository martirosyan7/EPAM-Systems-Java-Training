package com.epam.rd.autocode;

import com.epam.rd.autocode.domain.Employee;

public class RowMapperFactory {

    public RowMapper<Employee> employeeRowMapper() {
        return new com.epam.rd.autocode.RowMapperImpl();
    }
}
