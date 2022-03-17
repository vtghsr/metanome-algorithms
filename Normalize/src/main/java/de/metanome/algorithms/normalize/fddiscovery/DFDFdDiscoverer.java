package de.metanome.algorithms.normalize.fddiscovery;

import de.metanome.algorithm_integration.AlgorithmExecutionException;
import de.metanome.algorithm_integration.input.FileInputGenerator;
import de.metanome.algorithm_integration.input.RelationalInputGenerator;
import de.metanome.algorithms.dfd.DFDMetanome;
import de.metanome.algorithms.normalize.aspects.NormiConversion;
import de.metanome.algorithms.normalize.aspects.NormiPersistence;
import de.metanome.backend.result_receiver.ResultCache;

import java.io.IOException;

public class DFDFdDiscoverer extends FdDiscoverer{
    public DFDFdDiscoverer(NormiConversion converter, NormiPersistence persister,String tempResultPath) {
        super(converter,persister,tempResultPath);
    }

    @Override
    protected ResultCache executeAlgorithm(RelationalInputGenerator inputGenerator, Boolean nullEqualsNull) throws AlgorithmExecutionException {
        ResultCache resultFds = null;
        try {
            DFDMetanome dfd = new DFDMetanome();

            resultFds = new ResultCache("MetanomeMock",null);

            dfd.setFileInputConfigurationValue(DFDMetanome.Identifier.INPUT_FILE.name(), (FileInputGenerator) inputGenerator);
            dfd.setResultReceiver(resultFds);
            dfd.execute();
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new AlgorithmExecutionException(e.getMessage());
        }
        return resultFds;
    }
}
