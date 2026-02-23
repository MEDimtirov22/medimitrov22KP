import java.util.ArrayList;
import java.util.List;

public class Music {

    private static final int[][] RIGHT_PATTERNS = {
            {62, 65, 69, 62, 65, 69, 62, 65, 69, 62, 65, 69},
            {60, 64, 67, 60, 64, 67, 60, 64, 67, 60, 64, 67},
            {62, 65, 69, 62, 65, 69, 62, 65, 69, 62, 65, 69},
            {59, 62, 65, 59, 62, 65, 59, 62, 65, 59, 62, 65},
            {62, 65, 69, 62, 65, 69, 62, 65, 69, 62, 65, 69} 
    };

    private static final int[] LEFT_BASS_ROOTS = {
            36,
            34,
            33,
            31,
            36,
            29,
            34,
            31
    };

    public static SequenceData createOpeningSequence() {
        List<Note> left = new ArrayList<>();
        List<Note> right = new ArrayList<>();

        double currentBeat = 0.0;
        double measureLength = 4.0;
        double tripletStep = 1.0 / 3.0;

        for (int i = 0; i < RIGHT_PATTERNS.length * 2; i++) {
            int patternIndex = i % RIGHT_PATTERNS.length;
            int root = LEFT_BASS_ROOTS[i % LEFT_BASS_ROOTS.length];

            int bassVel = 90 + (i % 4) * 6;
            left.add(new Note(root - 12, measureLength, bassVel, currentBeat));
            left.add(new Note(root,      measureLength, bassVel - 10, currentBeat));

            if (i % 2 == 0) {
                left.add(new Note(root - 5, 0.5, 110, currentBeat + 2.0));
            }

            int[] pattern = RIGHT_PATTERNS[patternIndex];
            double t = currentBeat;
            int baseVel = 80 + (i % 3) * 8;

            for (int j = 0; j < pattern.length; j++) {
                int pitch = pattern[j];
                if (j % 4 == 3) {
                    pitch += 2;
                }
                int vel = baseVel - (j % 3) * 5;
                right.add(new Note(pitch, tripletStep, vel, t));
                t += tripletStep;
            }

            currentBeat += measureLength;
        }

        double endBeat = currentBeat;
        left.add(new Note(36 - 12, 6.0, 70, endBeat));
        left.add(new Note(36,      6.0, 85, endBeat));
        right.add(new Note(62, 6.0, 60, endBeat));
        right.add(new Note(65, 6.0, 65, endBeat));
        right.add(new Note(69, 6.0, 70, endBeat));
        right.add(new Note(74, 6.0, 75, endBeat));

        return new SequenceData(left, right);
    }
}