package helper;

public class Recorder {
	private Animator animator;
	private float[][] recordArray;
	private int roadLength;
	private int arrayPosition;
	public Recorder(Animator animator) {
		this.animator = animator;
		this.roadLength = animator.getRoad().getROAD_LENGTH();
		this.recordArray = new float[roadLength][roadLength];
	}
	public void record(float x, float distance, float speed) {
		if(arrayPosition < roadLength) {
			recordArray[arrayPosition][1] = x;
			recordArray[arrayPosition][2] = distance;
			recordArray[arrayPosition][3] = speed;
		}
	}
}
