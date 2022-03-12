package de.metanome.algorithms.normalize.fddiscovery;

import de.metanome.algorithm_integration.AlgorithmExecutionException;
import de.metanome.algorithm_integration.input.RelationalInputGenerator;
import de.metanome.algorithms.normalize.aspects.NormiConversion;
import de.metanome.algorithms.normalize.aspects.NormiPersistence;
import de.metanome.algorithms.tane.TaneAlgorithm;
import de.metanome.backend.result_receiver.ResultCache;

import java.io.IOException;

public class TaneFdDiscoverer extends FdDiscoverer{
    public TaneFdDiscoverer(NormiConversion converter, NormiPersistence persister,String tempResultsPath) {
        super(converter,persister,tempResultsPath);
    }
    @Override
    protected ResultCache executeAlgorithm(RelationalInputGenerator inputGenerator, Boolean nullEqualsNull) throws AlgorithmExecutionException {
        ResultCache resultFds = null;
        try {
            TaneAlgorithm tane = new TaneAlgorithm();

            resultFds = new ResultCache("MetanomeMock",null);

            tane.setRelationalInputConfigurationValue(TaneAlgorithm.INPUT_TAG,inputGenerator);
            tane.setResultReceiver(resultFds);

            tane.execute();
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new AlgorithmExecutionException(e.getMessage());
        }
        return resultFds;
    }
}
