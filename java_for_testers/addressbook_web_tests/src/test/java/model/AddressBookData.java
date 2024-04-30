package model;

public record AddressBookData(String id, String firstName, String middleName, String lastName, String nickName,
                              String title, String address, String home, String mobile, String email, String photo) {
    public AddressBookData() {
        this("", "", "", "", "", "", "", "", "",
                "", "");
    }

    public AddressBookData withId(String id) {
        return new AddressBookData(id, this.firstName, this.middleName, this.lastName, this.nickName,
                this.title, address, this.home, mobile, this.email, this.photo);
    }

    public AddressBookData withUselessFields(String middleName, String nickName,
                                             String title, String address, String home, String mobile, String email) {
        return new AddressBookData(this.id, this.firstName, middleName, this.lastName, nickName,
                title, address, home, mobile, email, this.photo);
    }

    public AddressBookData withFirstNameAndLastNameOnly(String firstName, String lastName) {
        return new AddressBookData(this.id, firstName, this.middleName, lastName, this.nickName,
                this.title, this.address, this.home, this.mobile, this.email, this.photo);
    }

    public AddressBookData withPhoto(String photo) {
        return new AddressBookData(this.id, this.firstName, this.middleName, this.lastName, this.nickName,
                this.title, this.address, this.home, this.mobile, this.email, photo);
    }
}
