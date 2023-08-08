package com.epam.rd.autocode.dao;

import com.epam.rd.autocode.DepartmentDaoImpl;
import com.epam.rd.autocode.EmployeeDaoImpl;

public class DaoFactory {
    public EmployeeDao employeeDAO() {
        return new EmployeeDaoImpl();
    }

    public DepartmentDao departmentDAO() {
        return new DepartmentDaoImpl();
    }
}
