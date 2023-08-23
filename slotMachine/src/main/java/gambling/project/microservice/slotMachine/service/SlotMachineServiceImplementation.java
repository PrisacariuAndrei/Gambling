package gambling.project.microservice.slotMachine.service;

import gambling.project.microservice.slotMachine.gameEngine.MultiThreadEngine;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SlotMachineServiceImplementation implements SlotMachineService{

    @Autowired
    private RestTemplate template;
    private final ModelMapper modelMapper;
    private Map<Integer, List<String>> gamesHistory = new HashMap<>();
    private int gameCount = 0;

    public SlotMachineServiceImplementation(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public List<String> spin() throws InterruptedException {
        MultiThreadEngine engine1 = new MultiThreadEngine(3);
        Thread myThread1 = new Thread(engine1);
        myThread1.start();
        myThread1.join();

        gamesHistory.put(++gameCount,engine1.getFinalResult());
        return engine1.getFinalResult();
    }

    @Override
    public Map<Integer, List<String>> getGameHistory() {
        return gamesHistory;
    }
}
