//Internal Backend API 

//This class is what actually runs our turtle. It holds a reference to the turtle, and through the process
//of reflection, it's methods are called within frontend.Controller

interface Command {
	public void Forward();

	public void Backward();
}