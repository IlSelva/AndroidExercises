package spastore.unisa.esercitazioni.esame2_listacontatti;

import java.io.Serializable;

public class Contact implements Serializable {
    private static final long serialVersionUID = -4252616950138384573L;
    private String name;
    private String surname;
    private String telephone;

    public Contact(String name, String surname, String telephone){
        this.name = name;
        this.surname = surname;
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
