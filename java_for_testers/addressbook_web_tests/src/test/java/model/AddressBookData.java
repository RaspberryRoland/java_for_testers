package model;

public record AddressBookData(String firstName, String middleName, String lastName, String nickName,
                              String title, String address, String home, String mobile, String email) {
    public AddressBookData() {
        this("", "", "", "", "", "", "", "", "");
    }

    public AddressBookData withBasicStrings(String firstName, String lastName, String address, String email, String mobile) {
        return new AddressBookData(firstName, this.middleName, lastName, this.nickName, this.title, address, this.home, mobile, email);
    }
}
