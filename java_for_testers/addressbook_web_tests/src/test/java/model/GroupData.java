package model;

public record GroupData(String name, String header) {
    public GroupData(){
        this("","");
    }

    public GroupData withName(String name) {
        return new GroupData(name, this.header);
    }

    public GroupData withHeader(String header) {
        return new GroupData(this.name, header);
    }
}