import java.util.Scanner;

public class DoubleLinkedList {
	private Node first;
	private Node last;
	private int length;

	static Scanner leitor = new Scanner(System.in);

	public static void main(String[] args) {
		int N = Integer.parseInt(leitor.nextLine());
		String entry = leitor.nextLine();
		Player player = new Player();
		System.out.println("Resposta = " + calcular(entry, player, N, player.getPoints()));
	}

	public static int calcular(String list, Player player, int N, int resultado) {
		String[] values = list.split(" ");
		DoubleLinkedList list1 = new DoubleLinkedList();
		DoubleLinkedList list2 = new DoubleLinkedList();

		for (int i = 0; i < values.length; i++) {
			list1.addLast(Integer.parseInt(values[i]));
			list2.addLast(Integer.parseInt(values[i]));
		}

		if (N > list1.getLength()) {
			System.out.println("Número de termos menor que a quantidade informada!");
			System.exit(0);
		} else if (N < list1.getLength()) {
			System.out.println("Número de termos maior que a quantidade informada!");
			System.exit(0);
		}

		int resultado1 = list1.getFirst().getValue();
		list1.removeFirst();
		player.setPoints(resultado1);
		resultado1 = calcular(list1, player, resultado1);

		int resultado2 = list2.getLast().getValue();
		list2.removeLast();
		player.setPoints(resultado2);
		resultado2 = calcular(list2, player, resultado2);

		return resultado1 > resultado2 ? resultado1 : resultado2;
	}

	public static int calcular(DoubleLinkedList list, Player player, int resultado) {
		if (list.getLength() >= 4) {
			int first = list.getFirst().getValue();
			int nextFirst = list.getFirst().getNext().getValue();
			int last = list.getLast().getValue();
			int prevLast = list.getLast().getPrevious().getValue();

			if (list.getLength() % 2 == 0) {
				player.setPoints(player.getPoints() + play(list, first, nextFirst, last, prevLast));
			} else {
				play(list, first, nextFirst, last, prevLast);
			}
			return calcular(list, player, player.getPoints());
		} else if (list.getLength() >= 2) {

			int escolha;
			if (list.getFirst().getValue() >= list.getLast().getValue()) {
				escolha = list.getFirst().getValue();
				list.removeFirst();
			} else {
				escolha = list.getLast().getValue();
				list.removeLast();
			}
			player.setPoints(player.getPoints() + (list.getLength() == 1 ? escolha : 0));
			return calcular(list, player, player.getPoints());
		} else {
			list.removeLast();
			return player.getPoints();
		}
	}

	private static int play(DoubleLinkedList list, int first, int nextFirst, int last, int prevLast) {
		int escolha;
		if (nextFirst >= first && nextFirst >= prevLast && nextFirst >= last) {
			escolha = last;
			list.removeLast();
		} else if (prevLast >= last && prevLast >= nextFirst && prevLast >= first) {
			escolha = first;
			list.removeFirst();
		} else if (first >= last && first >= nextFirst && first >= prevLast) {
			escolha = first;
			list.removeFirst();
		} else {
			escolha = last;
			list.removeLast();
		}
		return escolha;
	}

	public void print() {
		Node aux = first;
		while (aux != null) {
			System.out.println(aux.getValue());
			aux = aux.getNext();
		}
		System.out.println("printou");
	}

	public void removeLast() {
		if (length > 1) {
			Node aux = last.getPrevious();
			aux.setNext(null);
			last.setPrevious(null);
			last = aux;
			length--;
		} else {
			first = last = null;
			length = 0;
		}
	}

	public void removeFirst() {
		if (length > 1) {
			Node aux = first.getNext();
			aux.setPrevious(null);
			first.setNext(null);
			first = aux;
			length--;
		} else {
			first = last = null;
			length = 0;
		}
	}

	private boolean addEmpty(int value) {
		if (isEmpty()) {
			first = last = new Node(value);
			length++;
			return true;
		}
		return false;
	}

	public void addFirst(int value) {
		if (!addEmpty(value)) {
			Node node = new Node(value);
			node.setNext(first);
			first.setPrevious(node);
			first = node;
			length++;
		}
	}

	public void addLast(int value) {
		if (!addEmpty(value)) {
			Node node = new Node(value);
			node.setPrevious(last);
			last.setNext(node);
			last = node;
			length++;
		}
	}

	public boolean isEmpty() {
		return length == 0;
	}

	public Node getFirst() {
		return first;
	}

	public void setFirst(Node first) {
		this.first = first;
	}

	public Node getLast() {
		return last;
	}

	public void setLast(Node last) {
		this.last = last;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
}