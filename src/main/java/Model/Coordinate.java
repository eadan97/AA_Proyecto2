package Model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Coordinate {
    @XmlElement
    public byte x;
    @XmlElement
    public byte y;

    public Coordinate() {
    }

    public Coordinate(int x, int y) {
        this.x = (byte) x;
        this.y = (byte) y;
    }
}
