package manager.hbm;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "addressbook")

public class ContactRecord {

    @Id
    public int id;

    public String firstname;

    public String lastname;

    public String address;

    public String home;

    public String mobile;

    public String work;

    public String phone2;

    public String email;

    public String email2;

    public String email3;

    public ContactRecord(){

    }

    @ManyToMany
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    public List<ContactRecord> groups;

    public ContactRecord(int id, String firstname, String lastname, String address){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
    }
}
