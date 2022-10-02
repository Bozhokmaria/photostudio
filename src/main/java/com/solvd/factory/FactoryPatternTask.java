package com.solvd.factory;

public class FactoryPatternTask {

    public static void main(String[] args) {
        FactoryPatternTask factoryPatternTask = new FactoryPatternTask();
        Shape shape = factoryPatternTask.getShape("circle");
        shape.whatShape();
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
}
