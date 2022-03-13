package de.metanome.algorithms.normalize.fddiscovery;

import de.metanome.algorithm_integration.AlgorithmExecutionException;
import de.metanome.algorithm_integration.input.RelationalInputGenerator;
import de.metanome.algorithms.normalize.aspects.NormiConversion;
import de.metanome.algorithms.normalize.aspects.NormiPersistence;
import de.metanome.backend.result_receiver.ResultCache;
import de.metanome.algorithms.fdep.FdepAlgorithm;

import java.io.IOException;

public class FdepFdDiscoverer extends FdDiscoverer{
    public FdepFdDiscoverer(NormiConversion converter, NormiPersistence persister,String tempResultsPath){
        super(converter,persister,tempResultsPath);
    }

    @Override
    protected ResultCache executeAlgorithm(RelationalInputGenerator inputGenerator, Boolean nullEqualsNull) throws AlgorithmExecutionException {
        ResultCache resultFds = null;
        try {
            FdepAlgorithm fdep = new FdepAlgorithm();

            resultFds = new ResultCache("MetanomeMock",null);

            fdep.setRelationalInputConfigurationValue(FdepAlgorithm.INPUT_TAG,inputGenerator);
            fdep.setResultReceiver(resultFds);

            fdep.execute();
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new AlgorithmExecutionException(e.getMessage());
        }
        return resultFds;
    }
}
