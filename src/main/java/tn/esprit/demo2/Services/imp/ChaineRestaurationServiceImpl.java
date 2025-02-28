package tn.esprit.demo2.Services.imp;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import tn.esprit.demo2.Services.ChaineRestaurationService;
import tn.esprit.demo2.entities.ChaineRestauration;
@Service
public class ChaineRestaurationServiceImpl  implements  ChaineRestaurationService{
    @Override
    public ChaineRestauration createChaineRestauration(ChaineRestauration chaineRestauration) {
        return null;
    }

    @Override
    public List<ChaineRestauration> getAllChainesRestauration() {
        return null;
    }

    @Override
    public Optional<ChaineRestauration> getChaineRestaurationById(Long id) {
        return Optional.empty();
    }

    @Override
    public ChaineRestauration updateChaineRestauration(Long id, ChaineRestauration chaineRestaurationDetails) {
        return null;
    }

    @Override
    public void deleteChaineRestauration(Long id) {

    }
}
