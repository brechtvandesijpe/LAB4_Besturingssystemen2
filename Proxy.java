import java.util.concurrent.locks.ReadWriteLock;

public class Proxy
{
    private static Proxy instance = null;

    private ReadWriteLock lock;

    private Proxy()
    {
        lock = new ReadWriteLock();
    }

    public static synchronized Proxy getInstance()
    {
        if (instance == null)
            instance = new Proxy();
        return instance;
    }

    public void preRead(int pid)
    {
        // TODO: synchronization before read goes here
        lock.readLock().lock();
    }

    public void postRead(int pid)
    {
        // TODO: synchronization after read goes here
        lock.readLock().unlock();
    }

    public void preWrite(int pid)
    {
        // TODO: synchronization before write goes here
        lock.writeLock().lock();
    }

    public void postWrite(int pid)
    {
        // TODO: synchronization after write goes here
        lock.writeLock().unlock();
    }
}
