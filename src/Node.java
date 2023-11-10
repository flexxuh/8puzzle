/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */
package src;
public class Node<Item> {
    Item data = null;
    Node<Item> next = null;
    Node<Item> back = null;

    public Node() {

    }

    public Node(Item item) {
        data = item;
    }

    public Item getData() {
        return data;
    }

    public void setData(Item item) {
        data = item;
    }

    public void setNext(Node node) {
        next = node;
    }

    public Node getNext() {
        return next;
    }

    public void setBack(Node back1) {
        this.back = back1;
    }

    public Node getBack() {
        return back;
    }

    public static void main(String[] args) {

    }
}
