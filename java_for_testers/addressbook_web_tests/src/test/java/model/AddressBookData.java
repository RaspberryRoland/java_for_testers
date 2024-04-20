package model;

public record AddressBookData(String firstName, String middleName, String lastName, String nickName,
                              String title, String address, String home, String mobile, String email) {
    public AddressBookData() {
        this("", "", "", "", "", "", "", "", "");
    }

    public AddressBookData withFirstNameAndEmail(String firstName, String email) {
        return new AddressBookData(firstName, this.middleName, this.lastName,
                this.nickName, this.title, this.address, this.home, this.mobile, email);
    }

    public AddressBookData withBasicStrings(String firstName, String lastName, String address, String email, String mobile) {
        return new AddressBookData(firstName, this.middleName, lastName, this.nickName, this.title, address, this.home, mobile, email);
    }
}
