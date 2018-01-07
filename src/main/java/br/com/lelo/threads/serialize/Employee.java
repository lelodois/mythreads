package br.com.lelo.threads.serialize;

import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable {

    private static final long serialVersionUID = -2830361100959697290L;

    private int id;
    private String name;
    private Date dtNascimento;

    public Employee() {}

    public Employee(int id, String name, Date dtNascimento) {
        this.id = id;
        this.name = name;
        this.dtNascimento = dtNascimento;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dtNascimento == null) ? 0 : dtNascimento.hashCode());
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Employee other = (Employee) obj;
        if (dtNascimento == null) {
            if (other.dtNascimento != null) return false;
        } else if (!dtNascimento.equals(other.dtNascimento)) return false;
        if (id != other.id) return false;
        return true;
    }


}
