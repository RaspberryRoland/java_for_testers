package model;

public record AddressBookData(
        String id,
        String firstName,
        String middleName,
        String lastName,
        String nickName,
        String title,
        String address,
        String home,
        String mobile,
        String email,
        String photo,
        String work,
        String secondary,
        String email2,
        String email3,
        String groupId){
    public AddressBookData() {
        this("", "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "");
    }

    public AddressBookData withGroupId(String groupId) {
        return new AddressBookData(this.id, this.firstName, this.middleName, this.lastName, this.nickName,
                this.title, address, this.home, mobile, this.email, this.photo, this.work, this.secondary, this.email2, this.email3, groupId);
    }

    public AddressBookData withId(String id) {
        return new AddressBookData(id, this.firstName, this.middleName, this.lastName, this.nickName,
                this.title, address, this.home, mobile, this.email, this.photo, this.work, this.secondary, this.email2, this.email3, this.groupId);
    }

    public AddressBookData withUselessFields(String middleName, String nickName,
                                             String title, String address, String home, String mobile, String email) {
        return new AddressBookData(this.id, this.firstName, middleName, this.lastName, nickName,
                title, address, home, mobile, email, this.photo, this.work, this.secondary, this.email2, this.email3, this.groupId);
    }

    public AddressBookData withFirstNameAndLastNameOnly(String firstName, String lastName) {
        return new AddressBookData(this.id, firstName, this.middleName, lastName, this.nickName,
                this.title, this.address, this.home, this.mobile, this.email, this.photo, this.work, this.secondary, this.email2, this.email3, this.groupId);
    }

    public AddressBookData withFirstname(String firstName) {
        return new AddressBookData(this.id, firstName, this.middleName, this.lastName, this.nickName,
                this.title, this.address, this.home, this.mobile, this.email, this.photo, this.work, this.secondary, this.email2, this.email3, this.groupId);
    }

    public AddressBookData withLastname(String lastName) {
        return new AddressBookData(this.id, this.firstName, this.middleName, lastName, this.nickName,
                this.title, this.address, this.home, this.mobile, this.email, this.photo, this.work, this.secondary, this.email2, this.email3, this.groupId);
    }

    public AddressBookData withAddress(String address) {
        return new AddressBookData(this.id, this.firstName, this.middleName, this.lastName, this.nickName,
                this.title, address, this.home, this.mobile, this.email, this.photo, this.work, this.secondary, this.email2, this.email3, this.groupId);
    }

    public AddressBookData withPhoto(String photo) {
        return new AddressBookData(this.id, this.firstName, this.middleName, this.lastName, this.nickName,
                this.title, this.address, this.home, this.mobile, this.email, photo, this.work, this.secondary, this.email2, this.email3, this.groupId);
    }

    public AddressBookData withHome(String home) {
        return new AddressBookData(this.id, this.firstName, this.middleName, this.lastName, this.nickName,
                this.title, this.address, home, this.mobile, this.email, this.photo, this.work, this.secondary, this.email2, this.email3, this.groupId);
    }

    public AddressBookData withMobile(String mobile) {
        return new AddressBookData(this.id, this.firstName, this.middleName, this.lastName, this.nickName,
                this.title, this.address, this.home, mobile, this.email, this.photo, this.work, this.secondary, this.email2, this.email3, this.groupId);
    }

    public AddressBookData withWork(String work) {
        return new AddressBookData(this.id, this.firstName, this.middleName, this.lastName, this.nickName,
                this.title, this.address, this.home, this.mobile, this.email, this.photo, work, this.secondary, this.email2, this.email3, this.groupId);
    }

    public AddressBookData withSecondary(String secondary) {
        return new AddressBookData(this.id, this.firstName, this.middleName, this.lastName, this.nickName,
                this.title, this.address, this.home, this.mobile, this.email, this.photo, this.work, secondary, this.email2, this.email3, this.groupId);
    }

    public AddressBookData withEmail(String email) {
        return new AddressBookData(this.id, this.firstName, this.middleName, this.lastName, this.nickName,
                this.title, this.address, this.home, this.mobile, email, this.photo, this.work, this.secondary, this.email2, this.email3, this.groupId);
    }

    public AddressBookData withEmail2(String email2) {
        return new AddressBookData(this.id, this.firstName, this.middleName, this.lastName, this.nickName,
                this.title, this.address, this.home, this.mobile, this.email, this.photo, this.work, this.secondary, email2, this.email3, this.groupId);
    }

    public AddressBookData withEmail3(String email3) {
        return new AddressBookData(this.id, this.firstName, this.middleName, this.lastName, this.nickName,
                this.title, this.address, this.home, this.mobile, this.email, this.photo, this.work, this.secondary, this.email2, email3, this.groupId);
    }
}
