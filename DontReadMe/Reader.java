package DontReadMe;

import Proxy;
import RWConfig;
import DontReadMe.Processor;
import DontReadMe.RWLogger;
import DontReadMe.SharedResource;

public class Reader extends Processor
{
    public Reader(int tid)
    {
        super(tid);
    }

    public void run()
    {
        RWLogger.debugf("<%d> signaling...", tid);
        dio.signalReady();
        RWLogger.debugf("<%d> starting...", tid);

        while (!dio.shouldStopTheWorld())
        {
            proxy.preRead(tid);
            sharedResource.read(tid);
            proxy.postRead(tid);

            try
            {
                Thread.sleep(Util.boundedNextInt(RWConfig.READER_WAITING_MIN, RWConfig.READER_WAITING_MAX));
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            num_ops.incrementAndGet();
        }

        dio.signalFinished();
        RWLogger.debugf("<%d> finished!", tid);
    }
}
