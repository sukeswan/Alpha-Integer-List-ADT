
/*
 * Author: Surya Keswani 
 * Contact: suryakeswani@gmail.com
 * School: University of California, Santa Cruz
 * Date: August 2018
 */
public class List {

	/*
	 * a list has a front and back along with length with represents the number of
	 * items in the list the index is the index of the cursor the Node cursor is
	 * highlights a specific node
	 */

	private Node front = null;
	private Node back = null;
	private int length = 0;
	private int index = -1;
	private Node cursor = null;

	/*
	 * private node class had a previous node and next node also has an integer data
	 */
	private class Node {
		int data;
		Node next;
		Node previous;

		Node(int data) {
			this.data = data;
			next = null;
			previous = null;
		}
	}

	// returns the length of the list
	int length() {
		return length;
	}

	// returns the index of the cursor
	int index() {
		return index;
	}

	// if the list is not empty the data at the front of the list is returned
	int front() {
		if (length() > 0) {
			return front.data;
		} else {
			throw new RuntimeException("List Error: front() called on empty List");
		}
	}

	// if the list is not empty the data at the back of the list is returned
	int back() {
		if (length > 0) {
			return back.data;
		} else {
			throw new RuntimeException("List Error: back() called on empty List");
		}
	}

	// returns the data the cursor holds if the list is nonempty and the cursor is
	// defined
	int get() {
		if (length() > 0 && index() >= 0) {
			return cursor.data;
		} else {
			throw new RuntimeException("List Error: get() called on empty List or cursor is undefined");
		}
	}

	// compares 2 lists to see if they are equal
	boolean equals(List L) {
		// before checking elements, check to see if both lists have the same size
		if (length() != L.length) {
			return false;
		}
		// iterate through the lists comparing nodes
		for (int i = 0; i < length(); i++) {
			Node one = front;
			Node two = L.front;
			// if the nodes are the same continue on to the next nodes
			if (one.data == two.data) {
				one = one.next;
				two = two.next;
			}
			// otherwise return false
			else {
				return false;
			}
		}
		return true;
	}

	// resets this List to its original empty state
	void clear() {
		// reset front and back to null and length to 0
		front = null;
		back = null;
		length = 0;
		// cursor is set to null and its index is set to -1 aka undefined
		cursor = null;
		index = -1;
	}

	// moves cursor to front node
	void moveFront() {
		// if the list is not empty
		if (length() > 0) {
			// have cursor point to front
			index = 0;
			cursor = front;
		}
	}

	// moves cursor to back node
	void moveBack() {
		// if the list is not empty
		if (length > 0) {
			// have cursor point to back
			index = length() - 1;
			cursor = back;
		}
	}

	// moves cursor towards the front if preconditions are met
	void movePrev() {
		// if the cursor is not at the front and is defined
		if (index() > 0) {
			// move the cursor a step towards the front;
			index--;
			cursor = cursor.previous;
		}
		// if the cursor is at the front then it become undefined
		else {
			cursor = null;
			index = -1;
		}

	}

	// moves cursor one step towards the back
	void moveNext() {
		// if the cursor is not at the back and defined
		if (index() < length - 1 && index() >= 0) {
			// move it one step towards the back
			index++;
			cursor = cursor.next;
		} else {
			// make cursor undefined if it is at the back;
			cursor = null;
			index = -1;
		}
	}

	// inserting a new element into the list at the front
	void prepend(int data) {
		if (length() > 0) {
			// make a newFront node with parameter data
			Node newFront = new Node(data);
			// set the next and previous nodes for new front
			newFront.next = front;
			newFront.previous = null;
			// set the previous node for original front
			front.previous = newFront;
			// set front to new front
			front = newFront;
			// index of cursor has to be incremented because a new element is going before
			// it
			index++;
		}
		// if the list is empty
		else {
			// establish the node being both the front and back
			front = new Node(data);
			front.previous = null;
			front.next = null;
			back = front;
		}

		// increase the length because a new node is added
		length++;
	}

	// inserting element into the list at the back
	void append(int data) {
		if (length() > 0) {
			// make new back node with parameter data
			Node newBack = new Node(data);
			// set previous and next node for new back
			newBack.previous = back;
			newBack.next = null;
			// set next for original back to new back
			back.next = newBack;
			// set back to be new back
			back = newBack;
		}
		// the list is empty
		else {
			// establish element being both the front and the back
			back = new Node(data);
			back.previous = null;
			back.next = null;
			front = back;
		}
		// increase the length because a new node is added
		length++;
	}

	// insert new node before cursor
	void insertBefore(int data) {
		// if the list is not empty and the cursor is defined
		if (length() > 0 && index() >= 0) {
			if (index() == 0) {
				prepend(data);
			} else {
				// make a new node with the parameter data
				Node newNode = new Node(data);
				// define previous and next node for new node
				newNode.next = cursor;
				newNode.previous = cursor.previous;
				// define cursor previous next node as new node
				(cursor.previous).next = newNode;
				// define cursor previous node
				cursor.previous = newNode;
				// increase length as a new node has been added
				length++;
				// increase index of cursor by one
				index++;
			}

		} else {
			throw new RuntimeException(
					"List Error: insertBefore() was called on an empty List or the cursor is not defined");
		}
	}

	// insert new node after cursor
	void insertAfter(int data) {
		// if the list is not empty and the cursor is defined
		if (length() > 0 && index() >= 0) {
			if (index() == length() - 1) {
				append(data);
			} else {
				// make new node with parameter data
				Node newNode = new Node(data);
				// set next and previous node for new node
				newNode.next = cursor.next;
				newNode.previous = cursor;
				// define the cursor next previous node as new node
				cursor.next.previous = newNode;
				// have cursor next node point to new node
				cursor.next = newNode;
				// increase length as a new node has been added
				length++;
			}
		} else {
			throw new RuntimeException(
					"List Error: insertAfter() was called on an empty List or the cursor is not defined");
		}
	}

	// delete front node from list
	void deleteFront() {
		// list is non empty
		if (length() > 0) {
			// list is 1 element so clear it
			if (length == 1) {
				clear();
			} else {
				// cursor is front element so make it undefined
				if (index() == 0) {
					index = -1;
					cursor = null;
				}
				// move the front point to second item
				front = front.next;
				// have the new front previous node be set to null
				front.previous = null;
				// length decreases as a node is removed
				length--;
				// if cursor is front element that index is already set to -1 above. You do not
				// want to decrement it again to -2
				if (index >= 0) {
					index--;
				}
			}
			// decrease cursor index

		} else {
			throw new RuntimeException("List Error: deleteFront() was called on an empty List");
		}
	}

	// back node is deleted
	void deleteBack() {
		// list is non empty
		if (length() > 0) {
			// list is has only 1 element then clear it
			if (length() == 1) {
				clear();
			} else {
				// cursor is back element make it undefined
				if (index() == length() - 1) {
					index = -1;
					cursor = null;
				}
				// move back to second to last node
				back = back.previous;
				// have the new back next node be set to null
				back.next = null;
				// length decreases as a node is removed
				length--;
			}
		} else {
			throw new RuntimeException("List Error: deleteBack() was called on an empty List");
		}
	}

	// cursor element is deleted
	void delete() {
		// list is not empty and cursor is defined
		if (length() > 0 && index() >= 0) {
			if (index() == 0) {
				deleteFront();
			} else if (index() == length() - 1) {
				deleteBack();
			} else {
				// patch up previous and next nodes of the cursor
				(cursor.previous).next = cursor.next;
				(cursor.next).previous = cursor.previous;
				// subtract from the length
				length--;
				// make cursor null and index invalid
				cursor = null;
				index = -1;
			}
		} else {
			throw new RuntimeException("List Error: delete() was called on an empty List or cursor is undefined");
		}

	}

	List copy() {
		// make a new list
		List newList = new List();
		// make a current node
		Node current = front;
		// iterate through each node adding it to the new list
		for (int i = 0; i < length(); i++) {
			newList.append(current.data);
			current = current.next;
		}
		// set the new list cursor and index to be undefined
		newList.cursor = null;
		newList.index = -1;
		// return new list
		return newList;

	}

	List concat(List L) {
		// make a current node
		Node current = L.front;
		// iterate through parameter list adding each data point to this list
		for (int i = 0; i < L.length(); i++) {
			this.append(current.data);
			current = current.next;
		}
		// set cursor to be undefined
		cursor = null;
		index = -1;
		// return this list
		return this;
	}

	public String toString() {
		// make a resulting string an current node
		String result = "";
		Node current = front;
		// iterate through the list adding each node to the resulting string
		for (int i = 0; i < length(); i++) {
			result = result + current.data + " ";
			current = current.next;
		}
		return result;
	}

}