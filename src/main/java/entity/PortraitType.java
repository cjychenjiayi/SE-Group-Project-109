package entity;

public enum PortraitType {
    Dog("portrait/Dog.jpg"),
    Rabbit("portrait/Rabbit.jpg"),
    Pug("portrait/Pug.jpg"),
    Girl("portrait/Girl.jpg"),
    Pig("portrait/Pig.jpg");
    String picturePath;
    PortraitType(String picturePath) {
        this.picturePath = picturePath;
    }
    public String getPicturePath() {
        return this.picturePath;
    }
}

