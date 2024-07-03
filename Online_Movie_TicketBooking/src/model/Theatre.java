package model;

public class Theatre {
    private int theatreId;
    private String name;
    private int totalScreens;
    private int totalCapacity;
    private String location;
    private int theatreAdmin_id;

    public Theatre(int theatreId,String name, int totalScreens, int totalCapacity,String location,int theatreAdmin_id){
        this.theatreId = theatreId;
        this.name = name;
        this.totalScreens = totalScreens;
        this.totalCapacity = totalCapacity;
        this.location = location;
        this.theatreAdmin_id = theatreAdmin_id;
    }

    public Theatre(String name, int totalScreens, int totalCapacity,String location,int theatreAdmin_id){
        this.name = name;
        this.totalScreens = totalScreens;
        this.totalCapacity = totalCapacity;
        this.location = location;
        this.theatreAdmin_id = theatreAdmin_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTheatreAdmin_id() {
        return theatreAdmin_id;
    }

    public void setTheatreAdmin_id(int theatreAdmin_id) {
        this.theatreAdmin_id = theatreAdmin_id;
    }

    public int getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(int theatreId) {
        this.theatreId = theatreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalScreens() {
        return totalScreens;
    }

    public void setTotalScreens(int totalScreens) {
        this.totalScreens = totalScreens;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    @Override
    public String toString() {
        return "Theatre{" +
                "theatreId=" + theatreId +
                ", name='" + name + '\'' +
                ", totalScreens=" + totalScreens +
                ", totalCapacity=" + totalCapacity +
                ", location='" + location + '\'' +
                ", theatreAdmin_id=" + theatreAdmin_id +
                '}';
    }
}

