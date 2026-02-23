import javax.sound.midi.*;
import java.util.Scanner;

public class Main {

    private static PlaybackEngine player = null;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Moonlight Sonata – simplified console player");
        System.out.println("Approximate tempo: 50 BPM");
        System.out.println();

        while (true) {
            System.out.println("Commands:");
            System.out.println("  p   - play");
            System.out.println("  s   - stop");
            System.out.println("  q   - quit");
            System.out.print("- ");

            String cmd = sc.nextLine();

            if (cmd.equals("p")) {
                if (player != null && player.isActive()) {
                    System.out.println("Already playing...");
                    continue;
                }

                try {
                    SequenceData data = Music.createOpeningSequence();
                    Sequence midiSequence = Converter.convertToMidiSequence(data);

                    player = new PlaybackEngine();
                    player.play(midiSequence, 50f);
                    System.out.println("Playing... (press 's' to stop)");
                } catch (Exception e) {
                    System.out.println("Cannot start playback: " + e.getMessage());
                }
            }
            else if (cmd.equals("s")) {
                if (player != null) {
                    player.stop();
                    player = null;
                    System.out.println("Stopped.");
                } else {
                    System.out.println("Nothing is playing.");
                }
            }
            else if (cmd.equals("q") || cmd.equals("quit")) {
                if (player != null) {
                    player.stop();
                }
                System.out.println("Goodbye.");
                break;
            }
            else {
                System.out.println("Unknown command.");
            }
        }

        sc.close();
    }
}