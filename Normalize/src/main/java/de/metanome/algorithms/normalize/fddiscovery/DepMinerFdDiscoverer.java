package de.metanome.algorithms.normalize.fddiscovery;

import de.metanome.algorithm_integration.AlgorithmExecutionException;
import de.metanome.algorithm_integration.input.RelationalInputGenerator;
import de.metanome.algorithms.depminer.depminer_algorithm.AlgorithmGroup2DepMiner;
import de.metanome.algorithms.normalize.aspects.NormiConversion;
import de.metanome.algorithms.normalize.aspects.NormiPersistence;
import de.metanome.backend.result_receiver.ResultCache;

import java.io.IOException;

public class DepMinerFdDiscoverer extends FdDiscoverer{
    public DepMinerFdDiscoverer(NormiConversion converter, NormiPersistence persister,String tempResultPath) {
        super(converter,persister,tempResultPath);
    }

    @Override
    protected ResultCache executeAlgorithm(RelationalInputGenerator inputGenerator, Boolean nullEqualsNull) throws AlgorithmExecutionException {
        ResultCache resultFds = null;
        try {
            AlgorithmGroup2DepMiner algo = new AlgorithmGroup2DepMiner();

            resultFds = new ResultCache("MetanomeMock",null);

            algo.setRelationalInputConfigurationValue(AlgorithmGroup2DepMiner.INPUT_TAG,inputGenerator);
            algo.setResultReceiver(resultFds);
            algo.execute();
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new AlgorithmExecutionException(e.getMessage());
        }
        return resultFds;
    }

}
