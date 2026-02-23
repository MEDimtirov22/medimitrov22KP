import java.util.List;

public class SequenceData {

    private final List<Note> leftHand;
    private final List<Note> rightHand;

    public SequenceData(List<Note> leftHand, List<Note> rightHand) {
        this.leftHand = leftHand;
        this.rightHand = rightHand;
    }

    public List<Note> getLeftHand() {
        return leftHand;
    }

    public List<Note> getRightHand() {
        return rightHand;
    }
}