package cz.muni.fi.PA165.flight.service;

import cz.muni.fi.PA165.flight.dao.StewardDAO;
import cz.muni.fi.PA165.flight.entity.Steward;
import cz.muni.fi.PA165.flight.service.impl.StewardServiceImpl;
import cz.muni.fi.PA165.flight.transfer.StewardTO;
import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

/**
 * Created by M on 7.11.2014.
 */

@ContextConfiguration({"/application-context.xml",})
@TransactionConfiguration(defaultRollback=false)


public class StewardServiceTest extends AbstractTestNGSpringContextTests {

    @Spy
    DozerBeanMapper dozerBeanMapper;

    @Mock
    StewardDAO stewardDAO;

    @InjectMocks
    StewardServiceImpl stewardService;

    public Steward steward1;
    public Steward steward2;

    public StewardTO stewardTO1;
    public StewardTO stewardTO2;

    @Before
    public void setup() {

        stewardTO1 = new StewardTO();
        stewardTO1.setId(1);

        stewardTO2 = new StewardTO();
        stewardTO2.setId(2);

        steward1 = new Steward();
        steward1.setId(1);

        steward2 = new Steward();
        steward2.setId(2);


        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddSteward(){
        stewardService.addSteward(stewardTO1);
        verify(stewardDAO).addSteward(steward1);
    }

    @Test
    public void testDeleteSteward(){
        stewardService.deleteSteward(stewardTO1);
        verify(stewardDAO).deleteSteward(steward1);
    }

    @Test
    public void testGetAllStewards(){
        List<Steward> stewardList = new ArrayList<>();

        stewardList.add(steward1);
        stewardList.add(steward2);

        when(stewardDAO.getAllStewards()).thenReturn(stewardList);

        List<StewardTO> stewardTOList = stewardService.getAllStewards();
        verify(stewardDAO).getAllStewards();

        assertEquals(stewardTOList.get(0).getId(),stewardList.get(0).getId());
        assertEquals(stewardTOList.get(1).getId(),stewardList.get(1).getId());
        assertEquals(stewardTOList.size(),stewardList.size());

    }

    @Test
    public void testStewardGetById(){
        long id = steward1.getId();

        when(stewardDAO.getStewardById(id)).thenReturn(steward1);
        StewardTO stewardTO = stewardService.getStewardById(id);
        verify(stewardDAO).getStewardById(id);

        assertEquals(id,stewardTO.getId());

    }
}
