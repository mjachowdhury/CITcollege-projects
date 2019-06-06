package shapeInterfaceExample;
//when us e interface can't use extent have to use implements
public class Circle implements Shape {
	public Circle(){}
	// I am implementing the parent class method here
	public void draw(){
		System.out.println("Circle Drawn");
	}

}
