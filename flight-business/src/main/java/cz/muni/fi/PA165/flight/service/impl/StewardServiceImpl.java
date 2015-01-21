package cz.muni.fi.PA165.flight.service.impl;

import cz.muni.fi.PA165.flight.dao.StewardDAO;
import cz.muni.fi.PA165.flight.entity.Steward;
import cz.muni.fi.PA165.flight.service.StewardService;
import cz.muni.fi.PA165.flight.transfer.StewardTO;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by M on 7.11.2014.
 */
@Service
public class StewardServiceImpl implements StewardService {


    @Autowired
    private StewardDAO stewardDAO;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;


    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public StewardTO getStewardById(long id) {
        Steward steward = stewardDAO.getStewardById(id);
        if (steward == null) {
            return null;
        }
        return dozerBeanMapper.map(steward, StewardTO.class);
    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public void addSteward(StewardTO stewardTO) {
        Steward steward = dozerBeanMapper.map(stewardTO, Steward.class);
        stewardDAO.addSteward(steward);

    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public void deleteSteward(StewardTO stewardTO) {
        stewardDAO.deleteSteward(stewardDAO.getStewardById(stewardTO.getId()));

    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public void updateSteward(StewardTO stewardTO) {
        Steward steward = dozerBeanMapper.map(stewardTO, Steward.class);
        stewardDAO.updateSteward(steward);

    }

    @Override
    @Transactional
    @Secured("ROLE_ADMIN")
    public List<StewardTO> getAllStewards() {
        List<StewardTO> stewardTOList = new ArrayList<>();

        for (Steward steward : stewardDAO.getAllStewards()) {
            stewardTOList.add(dozerBeanMapper.map(steward, StewardTO.class));
        }
        return stewardTOList;
    }


}
