package de.metanome.algorithms.normalize.fddiscovery;

import de.metanome.algorithm_integration.AlgorithmExecutionException;
import de.metanome.algorithm_integration.input.RelationalInputGenerator;
import de.metanome.algorithms.fastfds.AlgorithmGroup2FastFD8Kerne;
import de.metanome.algorithms.fastfds.fastfds_helper.AlgorithmMetaGroup2;
import de.metanome.algorithms.normalize.aspects.NormiConversion;
import de.metanome.algorithms.normalize.aspects.NormiPersistence;
import de.metanome.backend.result_receiver.ResultCache;

import java.io.IOException;

public class FastFd8KernelDiscoverer extends FdDiscoverer{
    public FastFd8KernelDiscoverer(NormiConversion converter, NormiPersistence persister,String tempResultPath) {
        super(converter,persister,tempResultPath);
    }

    @Override
    protected ResultCache executeAlgorithm(RelationalInputGenerator inputGenerator, Boolean nullEqualsNull) throws AlgorithmExecutionException {
        ResultCache resultFds = null;
        try {
            AlgorithmGroup2FastFD8Kerne algo = new AlgorithmGroup2FastFD8Kerne();

            resultFds = new ResultCache("MetanomeMock",null);

            algo.setRelationalInputConfigurationValue(AlgorithmMetaGroup2.INPUT_TAG,inputGenerator);
            algo.setBooleanConfigurationValue(AlgorithmMetaGroup2.USE_OPTIMIZATIONS_TAG,true);
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