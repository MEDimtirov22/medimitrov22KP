public class Note {
    public int key;
    public double lengthInBeats;
    public int volume;
    public double startBeat;

    public Note(int key, double lengthInBeats, int volume, double startBeat) {
        this.key = key;
        this.lengthInBeats = lengthInBeats;
        this.volume = volume;
        this.startBeat = startBeat;
    }
}