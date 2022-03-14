package de.metanome.algorithms.normalize.fddiscovery;

import de.metanome.algorithm_integration.AlgorithmExecutionException;
import de.metanome.algorithm_integration.input.RelationalInputGenerator;
import de.metanome.algorithms.normalize.aspects.NormiConversion;
import de.metanome.algorithms.normalize.aspects.NormiPersistence;
import de.metanome.backend.result_receiver.ResultCache;
import de.uni_potsdam.hpi.metanome.algorithms.fdmine.FdMine;

import java.io.IOException;



public class FdMineDiscoverer extends FdDiscoverer{
    public FdMineDiscoverer(NormiConversion converter, NormiPersistence persister,String tempResultPath) {
        super(converter,persister,tempResultPath);
    }

    @Override
    protected ResultCache executeAlgorithm(RelationalInputGenerator inputGenerator, Boolean nullEqualsNull) throws AlgorithmExecutionException {
        ResultCache resultFds = null;
        try {
            FdMine fdMine = new FdMine();

            resultFds = new ResultCache("MetanomeMock",null);

            fdMine.setRelationalInputConfigurationValue("Relational Input",inputGenerator);
            fdMine.setResultReceiver(resultFds);
            fdMine.execute();
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new AlgorithmExecutionException(e.getMessage());
        }
        return resultFds;
    }
}
