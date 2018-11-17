package nas.villalobos.scanner.services;

import java.util.List;

import brv.tools.model.ScanResult;

public interface ServerService {

	List<ScanResult> findAllScanResults();
}
