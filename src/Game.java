import java.util.Random;

public class Game {
    // ... (código anterior)

    private Snake snake;
    private Point food;

    private int score;
    private int wallX;
    private int wallY;

    public Game() {
        // ... (código anterior)
        snake = new Snake();
        generateFood();
        score = 0;
        wallX = -1; // Inicialize com um valor que indica que a parede não está presente
        wallY = -1;

        // Defina a posição inicial da cobra no centro
        snake.getBody().clear();
        snake.getBody().add(new Point(7, 5));
        snake.grow();
    }

    private void generateFood() {
        // Gere uma posição aleatória para a comida
        Random rand = new Random();
        int foodX, foodY;

        do {
            foodX = rand.nextInt(14);  // ajuste o valor conforme necessário
            foodY = rand.nextInt(7);  // ajuste o valor conforme necessário
        } while (snake.getBody().contains(new Point(foodX, foodY)) || food != null && food.equals(new Point(foodX, foodY)));

        food = new Point(foodX, foodY);
    }


    public void draw() {
        // Limpar a tela


        // Desenhar a borda superior
        System.out.print("#");
        for (int i = 0; i < 15; i++) {
            System.out.print("#");
        }
        System.out.println("#");

        boolean drawWall = score % 100 == 0 && score !=0;

        // Desenhar a cobra e a área de jogo
        for (int y = 0; y < 8; y++) {
            System.out.print("#");
            for (int x = 0; x < 15; x++) {
                if (x == snake.getBody().getFirst().getX() && y == snake.getBody().getFirst().getY()) {
                    // Se estiver na posição da cabeça da cobra, desenhe "S"
                    System.out.print("S");
                } else if (snake.getBody().contains(new Point(x, y))) {
                    // Se estiver na posição do corpo da cobra, desenhe "*"
                    System.out.print("*");
                } else if (food.equals(new Point(x, y))) {
                    // Se estiver na posição da comida, desenhe "@"
                    System.out.print("@");
                }
                 else if (drawWall && x >= wallX && x < wallX + 3 && y == wallY) {
                    // Adicionar parede de 3 asteriscos a cada 100 pontos
                    System.out.print("***");
                    x += 2;  // Avançar para a próxima posição após a parede
                }

                else {
                    // Caso contrário, deixe o espaço vazio
                    System.out.print(" ");
                }
            }
            // Desenhe a borda vertical no final de cada linha
            System.out.println("#");
        }

        // Desenhar a borda inferior
        System.out.print("#");
        for (int i = 0; i < 15; i++) {
            System.out.print("#");
        }
        System.out.println("#");

        // Mostrar a pontuação
        System.out.println("Pontuação: " + score);
    }





    public void update() {
        Point head = snake.getBody().getFirst();

        // Verificar colisão com as bordas
        if (head.getX() <= 0 || head.getX() >= 15 || head.getY() < 0 || head.getY() >= 7) {
            System.out.println("Game Over - PORFAVOR - BE CAREFUL WITH BOARD!");
            System.exit(0);
        }

        // Verificar colisão com o próprio corpo
        for (int i = 1; i < snake.getBody().size(); i++) {
            if (head.equals(snake.getBody().get(i))) {
                System.out.println("Game Over - PORFAVOR!!!!!!!");
                System.exit(0);
            }
        }

        // Mover a cobra apenas se o jogo não tiver terminado
        snake.move();

        // Verificar colisão com a comida
        if (head.equals(food)) {
            snake.grow();
            generateFood();
            if(score<100) {
                score += 25;  // Ajuste conforme necessário
            }
            else { score+=50;}

        }
    }

    public void handleInput(Direction direction) {
        // Modificar a direção da cobra com base na entrada do usuário
        snake.setDirection(direction);
    }








}

