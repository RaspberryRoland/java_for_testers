package model;

public record GroupData(String id, String name, String header, String footer, String groupId) {
    public GroupData(){
        this("", "","", "", "");
    }

    public GroupData withName(String name) {
        return new GroupData(this.id, name, this.header, this.footer, this.groupId);
    }

    public GroupData withId(String id) {
        return new GroupData(id, this.name, this.header, this.footer, this.groupId);
    }

    public GroupData withHeader(String header) {
        return new GroupData(this.id, this.name, header, this.footer, this.groupId);
    }

    public GroupData withFooter(String footer) {
        return new GroupData(this.id, this.name, this.header, footer, this.groupId);
    }

    public GroupData withGroupId(String groupId) {
        return new GroupData(this.id, this.name, this.header, this.footer, groupId);
    }
}