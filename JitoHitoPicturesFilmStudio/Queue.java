import java.util.NoSuchElementException;
public class Queue extends LinkedList
{
    public Queue()
    {
        
    }
    public void enqueue(Object elem)
    {
        super.insertAtBack(elem);
    }
    public Object dequeue()
    {
        return super.removeFromFront();
    }
    public Object getFront()
    {
        return super.getFirst();
    }
    public Object getEnd()
    {
        Object obj = super.removeFromBack();
        insertAtBack(obj); //reinsert
        return obj;
    }
}
