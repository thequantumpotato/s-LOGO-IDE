//Internal Backend API Model

// These are the methods that are called within the backend_external
interface Model {
	private void moveForward(double moveAmount);

	private void moveBackward(double moveAmount);

	private void changeAngle(double angle);
}
