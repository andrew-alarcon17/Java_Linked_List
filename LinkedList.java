import java.util.NoSuchElementException;

public class LinkedList<E> implements List<E>, Stack<E> {
    private Node<E> first, last;
    private int size = 0;

    // Construct a new empty list.
    public LinkedList() {
        first = last = new Node<>(null, null);
    }


    // Adds element e to the end of the list.
    public void add(E e) {
        Node<E> newNode = last;
        newNode.next = new Node<>(e, null);
        last = last.next;
        ++size;
    }


    // Returns the element at the specified index,
    // or throws an IndexOutOfBoundsException if the index is out of range.
    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        Node<E> newNode = first.next;
        for (int i = 0; i < index; ++i)
            newNode = newNode.next;
        return newNode.data;
    }


    /**
     * Removes and returns the element at the specified index,
     * or throws an IndexOutOfBoundsException if the index is out of range.
     * @param index
     * @return
     */
    public E remove(int index) {
        if(index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException();
        Node<E> newNode = first; //w/out .next, points to the dummy.
        Node<E> removeNode = newNode;
        for(int i = 0; i < this.size; i++){
            if(i == index){
                removeNode = newNode.next; //We set removeNode equal to the node we want to remove.
                newNode.next = newNode.next.next;
            } else {
                newNode = newNode.next; //to move onto the next node.
            } if(i == this.size - 1) //after the above 2 conditions, check if we're at the end of the list
                last = newNode; //if so, reassign last so that it is pointing to the correct last node.
        }
        size--;
        return  removeNode.data; //return the data of the node we removed
    }


    // Adds element e to the top of the stack.
    public void push(E e) { //insert first method.
        Node<E> newNode = first; //we set the newNode to be at the dummy node
        newNode.next = new Node<>(e, first.next);//after first (newNode.next) should come the new node we are pushing
        //first = t; //is this line needed???
        size++;
    }


    // Removes and returns the top element of the stack,
    // or throws a NoSuchElementException if the stack is empty.
    public E pop() {
        if(size == 0)
            throw new NoSuchElementException();
        Node<E> newNode = first.next;
        first = first.next;
        size--;
        return newNode.data;
    }


    // Returns but does not remove the top element of the stack,
    // or throws a NoSuchElementException if the stack is empty.
    public E top() {
        if(size == 0)
            throw new NoSuchElementException();
        return first.next.data; //first.next is the 0th index of the list.
    }


    // Returns true if the list contains at least one element equal to e.
    public boolean contains(E e) {
        Node<E> current = first;
        for(int i = 0; i < size(); i++){
            if(current.next.data.equals(e)) //need data to read what value is at the node.
                return true;
        }
        return false;
    }


    /*could also do this:
    The Object class has a toString method but we don't want to use that.
    We want to override that method with our own toString method!
    return super.toString(); //super would return the address of the object b.c it is
    referring to the parent object class! We want to override this with our own toString
   */
    @Override
    public String toString() {
        String list = "[";
        System.out.println("Linked List: ");
        Node<E> current = first.next;
        while(current != null){
            list += current.data;
            if(current.next != null){
                list += ", ";
            }
            current = current.next;
        }
        return list + "]";
    }

    // Returns the number of elements.
    public int size() {
        return size;
    }


    private static class Node<E> {
        E data;
        Node<E> next;
        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }
}
