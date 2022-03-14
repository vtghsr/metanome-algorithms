package de.metanome.algorithms.normalize.fddiscovery;

import de.metanome.algorithm_helper.data_structures.PLIBuilder;
import de.metanome.algorithm_integration.AlgorithmExecutionException;
import de.metanome.algorithm_integration.input.RelationalInput;
import de.metanome.algorithm_integration.input.RelationalInputGenerator;
import de.metanome.algorithms.normalize.aspects.NormiConversion;
import de.metanome.algorithms.normalize.aspects.NormiPersistence;
import de.metanome.backend.result_receiver.ResultCache;
import de.uni_potsdam.hpi.metanome.algorithms.fun.FunAlgorithm;

import java.io.IOException;

public class FunFdDiscoverer extends FdDiscoverer{
    public FunFdDiscoverer(NormiConversion converter, NormiPersistence persister,String tempResultPath) {
        super(converter,persister,tempResultPath);
    }

    @Override
    protected ResultCache executeAlgorithm(RelationalInputGenerator inputGenerator, Boolean nullEqualsNull) throws AlgorithmExecutionException {
        ResultCache resultFds = null;
        try {
            resultFds = new ResultCache("MetanomeMock",null);

            RelationalInput relationalInput = inputGenerator.generateNewCopy();
            FunAlgorithm fun = new FunAlgorithm(relationalInput.relationName(),relationalInput.columnNames(),resultFds);
            fun.run(new PLIBuilder(relationalInput).getPLIList());
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new AlgorithmExecutionException(e.getMessage());
        }
        return resultFds;
    }
}
