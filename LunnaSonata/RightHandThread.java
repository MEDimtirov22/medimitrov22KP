import javax.sound.midi.*;

public class RightHandThread extends Thread {
    private java.util.List<Note> notes;
    private boolean running = true;

    public RightHandThread(java.util.List<Note> notes) {
        this.notes = notes;
        setName("Right-Hand-Thread");
    }
    
    public void run() {
    try (Synthesizer synth = MidiSystem.getSynthesizer()) {
        synth.open();
        Receiver r = synth.getReceiver();
        
        long bpmDelay = 1100;
        double lastBeat = 0;

        for (Note n : notes) {
            if (!running) break;

            double waitTime = n.startBeat - lastBeat;
            if (waitTime > 0) {
                Thread.sleep((long) (waitTime * bpmDelay));
            }
            lastBeat = n.startBeat;

            new Thread(() -> {
                try {
                    r.send(new ShortMessage(ShortMessage.NOTE_ON, 0, n.key, n.volume), -1);
                    Thread.sleep((long) (n.lengthInBeats * bpmDelay));
                    r.send(new ShortMessage(ShortMessage.NOTE_OFF, 0, n.key, 0), -1);
                } catch (Exception ignored) {}
            }).start();
        }
    } catch (Exception ignored) {}
}

    public void stopPlaying() {
        running = false;
        interrupt();
    }
}