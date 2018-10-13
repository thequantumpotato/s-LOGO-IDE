//External Frontend API

//These are the methods of Viewthat are called from the back-end (the controller)

interface View {
	public void updateVar(String Varname, String ValVal);

	public void displayError(String errmEssage);
}