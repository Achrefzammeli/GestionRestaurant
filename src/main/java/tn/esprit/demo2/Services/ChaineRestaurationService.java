package tn.esprit.demo2.Services;

import tn.esprit.demo2.entities.ChaineRestauration;
import java.util.List;
import java.util.Optional;

public interface ChaineRestaurationService {
    ChaineRestauration createChaineRestauration(ChaineRestauration chaineRestauration);
    List<ChaineRestauration> getAllChainesRestauration();
    Optional<ChaineRestauration> getChaineRestaurationById(Long id);
    ChaineRestauration updateChaineRestauration(Long id, ChaineRestauration chaineRestaurationDetails);
    void deleteChaineRestauration(Long id);
}
