package shapeInterfaceExample;

//One child have only one parent but multiple child can have one parent as well 
// But they can not have more then one parent
//when us e interface can't use extent have to use implements
//for the interface we can implements more then one class
public class Rectangle implements Shape {

	public Rectangle(){}
	// I am implementing the parent class method here
	public void draw(){
		System.out.println("Rectange Drawn.");
	}
}
