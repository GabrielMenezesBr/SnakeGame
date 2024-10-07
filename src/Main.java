import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        Scanner scanner = new Scanner(System.in);

        // Crie uma nova thread para mover automaticamente a cobra
        Thread snakeThread = new Thread(() -> {
            while (true) {

                game.update();
                game.draw();  // Adicione esta linha para mostrar o jogo após cada atualização


                try {
                    Thread.sleep(700);  // ajuste este valor conforme necessário
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        snakeThread.start();


        // Agora, o loop principal apenas lida com a entrada do usuário
        while (true) {
            game.draw();
            System.out.print("Digite a direção (W/A/S/D): ");
            char input = scanner.next().charAt(0);

            Direction direction = getDirectionFromInput(input);
            if (direction != null) {
                game.handleInput(direction);
            }
        }
    }

    private static Direction getDirectionFromInput(char input) {
        switch (input) {
            case 'W':
            case 'w':
                return Direction.UP;
            case 'S':
            case 's':
                return Direction.DOWN;
            case 'A':
            case 'a':
                return Direction.LEFT;
            case 'D':
            case 'd':
                return Direction.RIGHT;
            default:
                return null;
        }
    }
}
