import java.util.Scanner;

public class Main {

    private static LeftHandThread leftThread;
    private static RightHandThread rightThread;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Lunna Sonata");
        System.out.println("p = play | s = stop | q = quit");

        while (true) {
            String cmd = sc.nextLine().trim().toLowerCase();

            if (cmd.equals("p")) {
                if (isPlaying()) {
                    System.out.println("Already playing!");
                    continue;
                }

                SequenceData data = Music.createOpeningSequence();

                leftThread = new LeftHandThread(data.getLeftHand());
                rightThread = new RightHandThread(data.getRightHand());

                leftThread.start();
                rightThread.start();
            }
            else if (cmd.equals("s")) {
                stopMusic();
                System.out.println("Stopped.");
            }
            else if (cmd.equals("q")) {
                stopMusic();
                System.out.println("Goodbye.");
                break;
            }
        }
        sc.close();
    }

    private static boolean isPlaying() {
        return (leftThread != null && leftThread.isAlive()) || (rightThread != null && rightThread.isAlive());
    }

    private static void stopMusic() {
        if (leftThread != null){
            leftThread.stopPlaying();
        }
        if (rightThread != null){
            rightThread.stopPlaying();
        }
    }
}