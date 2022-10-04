package com.solvd.factory;

public class FactoryPatternTask {

    public static void main(String[] args) {
        FactoryPatternTask factoryPatternTask = new FactoryPatternTask();
        Shape shape = factoryPatternTask.getShape("circle");
        shape.whatShape();
        factoryPatternTask.getSpecificGroup("pro");
    }

    public Shape getShape(String shapeName) {
        if (shapeName.equalsIgnoreCase("circle")) {
            return new Circle();
        } else if (shapeName.equalsIgnoreCase("rectangle")) {
            return new Rectangle();
        } else {
            return null;
        }

    }

    public void getSpecificGroup(String group){
        ShapeFactory shapeFactory = new ShapeFactory();
        shapeFactory.getSpecificShapes(group);
    }

  class ShapeFactory {
        void getSpecificShapes(String shapesGroup){
            if (shapesGroup.equalsIgnoreCase("general")) {
                new Circle().whatShape();
                new Rectangle().whatShape();
            } else if (shapesGroup.equalsIgnoreCase("pro")) {
                new ProCircle().whatShape();
                new ProRectangle().whatShape();
            } else {
                System.out.println("No group");
            }
        }
    }

    interface Shape {
        void whatShape();
    }

    class Rectangle implements Shape {

        @Override
        public void whatShape() {
            System.out.println("Rectangle");
        }
    }

    class Circle implements Shape {
        @Override
        public void whatShape() {
            System.out.println("Circle");
        }
    }

    interface ProShape {
        void whatShape();
    }

    class ProRectangle implements Shape {

        @Override
        public void whatShape() {
            System.out.println("ProRectangle");
        }
    }

    class ProCircle implements Shape {
        @Override
        public void whatShape() {
            System.out.println("ProCircle");
        }
    }


}
