import javax.sound.midi.*;
import java.util.List;

public class Converter {

    private static final int RESOLUTION = 480;

    public static Sequence convertToMidiSequence(SequenceData data) throws InvalidMidiDataException {
        Sequence seq = new Sequence(Sequence.PPQ, RESOLUTION);

        Track channelLeft  = seq.createTrack();
        Track channelRight = seq.createTrack();

        fillTrack(channelLeft,  data.getLeftHand(),  0);
        fillTrack(channelRight, data.getRightHand(), 1);

        return seq;
    }

    private static void fillTrack(Track track, List<Note> notes, int channel)
            throws InvalidMidiDataException {

        for (Note n : notes) {
            long startTick = Math.round(n.startBeat * RESOLUTION);
            long durationTick = Math.round(n.lengthInBeats * RESOLUTION);
            long endTick = startTick + durationTick;

            ShortMessage on = new ShortMessage(ShortMessage.NOTE_ON, channel, n.key, n.volume);
            track.add(new MidiEvent(on, startTick));

            ShortMessage off = new ShortMessage(ShortMessage.NOTE_OFF, channel, n.key, 0);
            track.add(new MidiEvent(off, endTick));
        }
    }
}