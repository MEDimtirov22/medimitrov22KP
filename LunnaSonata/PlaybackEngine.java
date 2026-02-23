import javax.sound.midi.*;

public class PlaybackEngine {

    private Sequencer sequencer;

    public void play(Sequence sequence, float tempoBpm) throws MidiUnavailableException, InvalidMidiDataException {
        stop();

        sequencer = MidiSystem.getSequencer();
        sequencer.open();
        sequencer.setSequence(sequence);
        sequencer.setTempoInBPM(tempoBpm);
        sequencer.start();
    }

    public void stop() {
        if (sequencer != null) {
            if (sequencer.isRunning()) {
                sequencer.stop();
            }
            try {
                sequencer.close();
            } catch (Exception ignored) {
            }
            sequencer = null;
        }
    }

    public boolean isActive() {
        return sequencer != null && sequencer.isRunning();
    }
}