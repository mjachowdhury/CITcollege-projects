package shapeAbstractExample;

//One child have only one parent but multiple child can have one parent as well 
// But they can not have more then one parent
public class Rectangle extends Shape {

	public Rectangle(){}
	// I am implementing the parent class method here
	public void draw(){
		System.out.println("Rectange Drawn.");
	}
}
