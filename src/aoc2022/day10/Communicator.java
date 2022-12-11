package aoc2022.day10;

public class Communicator {
    private final Interpreter interpreter;
    private final Display display;

    Communicator(String[] program) {
        interpreter = new Interpreter(program);
        display = new Display(6, 40);
    }

    public String execute() {
        String picture = "Display Error\n";
        for (int i = 0; i <= 240; i++) {
            interpreter.proceed(1);
            long position = interpreter.getRegister();
            picture = display.draw(position);
        }

        return picture;
    }
}
