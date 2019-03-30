package EmployeeActions;

import java.io.Serializable;

public abstract class EmployeeAction implements Serializable {


    public EmployeeAction() {}

    public abstract void execute();

}
