public class Note {
    public final int key;
    public final double lengthInBeats;
    public final int volume;
    public final double startBeat;

    public Note(int key, double lengthInBeats, int volume, double startBeat) {
        this.key = key;
        this.lengthInBeats = lengthInBeats;
        this.volume = volume;
        this.startBeat = startBeat;
    }
}