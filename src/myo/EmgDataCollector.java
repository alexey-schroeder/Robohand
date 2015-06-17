package myo;

import com.thalmic.myo.AbstractDeviceListener;
import com.thalmic.myo.FirmwareVersion;
import com.thalmic.myo.Myo;
import org.apache.commons.collections4.queue.CircularFifoQueue;

import java.util.ArrayList;
import java.util.Arrays;

public class EmgDataCollector extends AbstractDeviceListener {
    private CircularFifoQueue<byte[]> circularFifoQueue;

    public EmgDataCollector() {
        circularFifoQueue = new CircularFifoQueue(10);
    }

    @Override
    public void onPair(Myo myo, long timestamp, FirmwareVersion firmwareVersion) {
        circularFifoQueue = new CircularFifoQueue(10);
    }

    @Override
    public void onEmgData(Myo myo, long timestamp, byte[] emg) {
        circularFifoQueue.add(emg);
    }

    public ArrayList<byte[]> getAllEmgData() {
        byte[][] array = circularFifoQueue.toArray(new byte[0][]);
        return new ArrayList<>(Arrays.asList(array));
    }

    @Override
    public String toString() {
        return Arrays.toString(circularFifoQueue.element());
    }
}
