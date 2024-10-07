import java.util.LinkedList;

public class Snake {

    private LinkedList<Point> body;

    private Direction direction;

    public Snake() {
        // Inicializar a cobra com um tamanho inicial e posição inicial
        body = new LinkedList<>();
        body.add(new Point(0, 0));  // Ajuste conforme necessário
        direction = Direction.RIGHT;  // Direção inicial, ajuste conforme necessário
    }

    public void move() {
        // Obter a cabeça da cobra
        Point head = body.getFirst();

        // Calcular a nova posição da cabeça com base na direção
        int newHeadX = head.getX();
        int newHeadY = head.getY();

        switch (direction) {
            case UP:
                newHeadY--;
                break;
            case DOWN:
                newHeadY++;
                break;
            case LEFT:
                newHeadX--;
                break;
            case RIGHT:
                newHeadX++;
                break;
        }

        // Criar a nova cabeça da cobra
        Point newHead = new Point(newHeadX, newHeadY);

        // Adicionar a nova cabeça à frente do corpo
        body.addFirst(newHead);

        // Remover a última parte do corpo para manter o tamanho constante
        body.removeLast();
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }


    public void grow() {
        // Adicionar uma nova parte ao corpo da cobra na direção oposta à sua última movimentação
        Point tail = body.getLast();
        Point newPart;

        switch (direction) {
            case UP:
                newPart = new Point(tail.getX(), tail.getY() + 1);
                break;
            case DOWN:
                newPart = new Point(tail.getX(), tail.getY() - 1);
                break;
            case LEFT:
                newPart = new Point(tail.getX() + 1, tail.getY());
                break;
            case RIGHT:
                newPart = new Point(tail.getX() - 1, tail.getY());
                break;
            default:
                newPart = tail; // Fallback para o caso padrão
        }

        body.addLast(newPart);
    }


    public LinkedList<Point> getBody() {
        return body;
    }

}