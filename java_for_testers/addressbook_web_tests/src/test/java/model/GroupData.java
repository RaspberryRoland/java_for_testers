package model;

public record GroupData(String id, String name, String header) {
    public GroupData(){
        this("", "","");
    }

    public GroupData withName(String name) {
        return new GroupData(this.id, name, this.header);
    }

    public GroupData withId(String id) {
        return new GroupData(id, this.name, this.header);
    }

    public GroupData withHeader(String header) {
        return new GroupData(this.id, this.name, header);
    }
}