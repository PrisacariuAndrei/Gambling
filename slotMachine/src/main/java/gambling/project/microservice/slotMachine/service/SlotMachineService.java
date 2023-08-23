package gambling.project.microservice.slotMachine.service;

import java.util.List;
import java.util.Map;

public interface SlotMachineService {

    public List<String> spin() throws InterruptedException;
    public Map<Integer, List<String>> getGameHistory();
}
