package cz.muni.fi.PA165.flight.service;

import cz.muni.fi.PA165.flight.dao.StewardDAO;
import cz.muni.fi.PA165.flight.entity.Steward;
import cz.muni.fi.PA165.flight.service.impl.StewardServiceImpl;
import cz.muni.fi.PA165.flight.transfer.StewardTO;
import org.dozer.DozerBeanMapper;
import org.mockito.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

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


    @BeforeMethod
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
        Mockito.verify(stewardDAO).addSteward(steward1);
    }

    @Test
    public void testDeleteSteward(){
        stewardService.deleteSteward(stewardTO1);
        Mockito.verify(stewardDAO).deleteSteward(steward1);
    }

    @Test
    public void testGetAllStewards(){
        List<Steward> stewardList = new ArrayList<>();

        stewardList.add(steward1);
        stewardList.add(steward2);

        Mockito.when(stewardDAO.getAllStewards()).thenReturn(stewardList);

        List<StewardTO> stewardTOList = stewardService.getAllStewards();
        Mockito.verify(stewardDAO).getAllStewards();

        Assert.assertEquals(stewardTOList.get(0).getId(), stewardList.get(0).getId());
        Assert.assertEquals(stewardTOList.get(1).getId(), stewardList.get(1).getId());
        Assert.assertEquals(stewardTOList.size(), stewardList.size());

    }

    @Test
    public void testStewardGetById(){
        long id = steward1.getId();

        Mockito.when(stewardDAO.getStewardById(id)).thenReturn(steward1);
        StewardTO stewardTO = stewardService.getStewardById(id);
        Mockito.verify(stewardDAO).getStewardById(id);

        Assert.assertEquals(id, stewardTO.getId());

    }
}
