import java.util.Scanner;

public class DoubleLinkedList {
	private Node first;
	private Node last;
	private int length;
	//teste
	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		int N = Integer.parseInt(in.nextLine());
		if (N % 2 != 0) {
			System.out.println("Quantidade deve ser par!");
			System.exit(0);
		} else if (N < 2 || N > Math.pow(10, 4)) {
			System.out.println("Quantidade deve estar entre o seguinte intervalo: 2 <= N <= 10^4");
			System.exit(0);
		}

		String entry = in.nextLine();
		System.out.println("Resposta = " + calculate(entry, N, 0));
	}

	public static int calculate(String list, int N, int resultado) {
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

		int result1 = list1.getFirst().getValue();
		list1.removeFirst();
		result1 = calculate(list1, result1);

		int result2 = list2.getLast().getValue();
		list2.removeLast();
		result2 = calculate(list2, result2);

		return result1 > result2 ? result1 : result2;
	}

	private static int calculate(DoubleLinkedList list, int result) {
		if (list.getLength() >= 4) {
			int first = list.getFirst().getValue();
			int nextFirst = list.getFirst().getNext().getValue();
			int last = list.getLast().getValue();
			int prevLast = list.getLast().getPrevious().getValue();

			if (list.getLength() % 2 == 0) {
				result += play(list, first, nextFirst, last, prevLast);
			} else {
				play(list, first, nextFirst, last, prevLast);
			}
			return calculate(list, result);
		} else if (list.getLength() >= 2) {

			int choice;
			if (list.getFirst().getValue() >= list.getLast().getValue()) {
				choice = list.getFirst().getValue();
				list.removeFirst();
			} else {
				choice = list.getLast().getValue();
				list.removeLast();
			}
			result += list.getLength() == 1 ? choice : 0;
			return calculate(list, result);
		} else {
			list.removeLast();
			return result;
		}
	}

	private static int play(DoubleLinkedList list, int first, int nextFirst, int last, int prevLast) {
		int choice;
		if (nextFirst >= first && nextFirst >= prevLast && nextFirst >= last) {
			choice = last;
			list.removeLast();
		} else if (prevLast >= last && prevLast >= nextFirst && prevLast >= first) {
			choice = first;
			list.removeFirst();
		} else if (first >= last && first >= nextFirst && first >= prevLast) {
			choice = first;
			list.removeFirst();
		} else {
			choice = last;
			list.removeLast();
		}
		return choice;
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