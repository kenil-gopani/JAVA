import java.util.Scanner;

public class java22 {

    interface Shape {
        String getColor();
        void setColor(String color);
        double area();

        default void describe() {
            System.out.println("This is a " + getClass().getSimpleName() + " with color " + getColor());
        }
    }

    static class Circle implements Shape {
        private double radius;
        private String color;

        public Circle(double radius, String color) {
            this.radius = radius;
            this.color = color;
        }

        @Override
        public String getColor() {
            return color;
        }

        @Override
        public void setColor(String color) {
            this.color = color;
        }

        @Override
        public double area() {
            return Math.PI * radius * radius;
        }

        @Override
        public void describe() {
            Shape.super.describe();
            System.out.println("Radius: " + radius);
        }
    }

    static class Rectangle implements Shape {
        private double length;
        private double width;
        private String color;

        public Rectangle(double length, double width, String color) {
            this.length = length;
            this.width = width;
            this.color = color;
        }

        @Override
        public String getColor() {
            return color;
        }

        @Override
        public void setColor(String color) {
            this.color = color;
        }

        @Override
        public double area() {
            return length * width;
        }

        @Override
        public void describe() {
            Shape.super.describe();
            System.out.println("Length: " + length + ", Width: " + width);
        }
    }

    static class Sign {
        private Shape shape;
        private String text;

        public Sign(Shape shape, String text) {
            this.shape = shape;
            this.text = text;
        }

        public void display() {
            shape.describe();
            System.out.println("Sign text: " + text);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\t menu");
        System.out.println("\t 1 - Circle");
        System.out.println("\t 2 - Rectangle");

        System.out.print("Choose an option (1 or 2): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        Shape shape = null;
        Sign sign = null;

        switch (choice) {
            case 1:
                System.out.print("Enter Circle radius: ");
                double circleRadius = scanner.nextDouble();
                System.out.print("Enter Circle color: ");
                scanner.nextLine(); 
                String circleColor = scanner.nextLine();
                shape = new Circle(circleRadius, circleColor);

                System.out.print("Enter Circle sign text: ");
                String circleText = scanner.nextLine();
                sign = new Sign(shape, circleText);
                break;

            case 2:
                System.out.print("Enter Rectangle length: ");
                double rectangleLength = scanner.nextDouble();
                System.out.print("Enter Rectangle width: ");
                double rectangleWidth = scanner.nextDouble();
                System.out.print("Enter Rectangle color: ");
                scanner.nextLine(); 
                String rectangleColor = scanner.nextLine();
                shape = new Rectangle(rectangleLength, rectangleWidth, rectangleColor);

                System.out.print("Enter Rectangle sign text: ");
                String rectangleText = scanner.nextLine();
                sign = new Sign(shape, rectangleText);
                break;

            default:
                System.out.println("Invalid choice. Please select 1 or 2.");
                break;
        }

        if (sign != null) {
            System.out.println("\nSign:");
            sign.display();
        }

        scanner.close();
    }
}
