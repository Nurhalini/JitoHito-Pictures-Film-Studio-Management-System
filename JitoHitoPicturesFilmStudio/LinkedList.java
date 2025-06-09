public class LinkedList
{
    private Node first; //reference to the first node
    private Node current; // reference to the current node
    private Node last; //reference to the last node
    
    public LinkedList()
    {
        this.first = null;
        this.last = null;
        this.current = null;
    }
    public boolean isEmpty()
    {
        return (this.first == null);
    }
    public void insertAtFront(Object insertItem)
    {
        Node newNode = new Node(insertItem);
        if (isEmpty())
        {
            this.first = newNode;
            this.last = newNode;
        }
        else
        {
            newNode.next = this.first;
            this.first = newNode;
        }
    }
    public void insertAtBack(Object insertItem)
    {
        Node newNode = new Node(insertItem);
        if (isEmpty())
        {
            this.first = newNode;
            this.last = newNode;
        }
        else
        {
            last.next = newNode;
            this.last = newNode;
        }
    }
    public Object removeFromFront()
    {
        Object removeItem = null;
        if (isEmpty())
        {
            return removeItem;
        }
        else
        {
            removeItem = first.data;
            if (this.first == this.last)
            {
                this.first = null;
                this.last = null;
            }
            else
            {
                this.first = first.next;
            }
            return removeItem;
        }
    }
    public Object removeFromBack()
    {
        Object removeItem = null;
        if (isEmpty())
        {
            return removeItem;
        }
        removeItem = last.data;
        if (this.first == last)
        {
            this.first = null;
            this.last = null;
        }
        else
        {
            this.current = this.first;
            while (current.next != this.last)
            {
                this.current = current.next;
            }
            this.last = this.current;
            this.last.next = null;
        }
        return removeItem;
    }
       public Object getFirst()
    {
        if (isEmpty())
        {
            return null;
        }
        else
        {
            current = first;
            return current.data;
        }
    }
    public Object getNext()
    {
        if (current == last)
        {
            return null;
        }
        else
        {
            current = current.next;
            return current.data;
        }
    }
}