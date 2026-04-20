import java.util.ArrayList;
import java.util.List;

public class Music {
    public static SequenceData createOpeningSequence() {
        List<Note> left = new ArrayList<>();
        List<Note> right = new ArrayList<>();

        double beat = 0.0;
        double measure = 4.0;
        double triplet = 1.0 / 3.0;

        for (int i = 0; i < 26; i++) {
            left.add(new Note(25, measure, 55, beat));
            left.add(new Note(37, measure, 50, beat));

            for (int group = 0; group < 4; group++) {
                double offset = beat + group;
                right.add(new Note(56, triplet, 45, offset));
                right.add(new Note(61, triplet, 45, offset + triplet));
                right.add(new Note(64, triplet, 45, offset + (triplet * 2)));
            }

            beat += measure;
        }

        left.add(new Note(25, 8.0, 40, beat));
        right.add(new Note(61, 8.0, 40, beat));
        right.add(new Note(64, 8.0, 40, beat));

        return new SequenceData(left, right);
    }
}