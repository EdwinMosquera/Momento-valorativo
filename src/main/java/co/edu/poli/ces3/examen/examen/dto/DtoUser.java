package co.edu.poli.ces3.examen.examen.dto;

public class DtoUser {

    public int id;

    protected String mail;

    private String name;

    protected String pass;

    public DtoUser(int id, String mail, String name, String pass){
        this.id = id;
        this.mail = mail;
        this.name = name;
        this.pass = pass;

    }

    public DtoUser(String mail, String name, String pass){
        this.name = name;
        this.mail = mail;
        this.pass = pass;
    }

    public DtoUser() {

    }

    public int getId(){
        return this.id;
    }


    private void setId(int id){
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


    @Override
    public String toString() {
        return "El User se llama: " + this.name +
                " su correo es: " + this.mail;
    }
}
