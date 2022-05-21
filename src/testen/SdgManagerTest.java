package testen;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.mockito.junit.MockitoJUnitRunner;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import domain.SdgAbstract;
import domain.SdgChild;
import domain.SdgComp;
import domain.SdgManager;
import persistence.GenericMapperJpa;
@RunWith(MockitoJUnitRunner.class)
class SdgManagerTest {
	
	
	
	
	
	
	@Test
	void insertSdg_IntoDatabase_Test_ExpectedTrue() {
		
		GenericMapperJpa<SdgAbstract> sdgMapperMock = (GenericMapperJpa<SdgAbstract>) mock(GenericMapperJpa.class);
		SdgManager manager = new SdgManager(sdgMapperMock); 
		SdgAbstract comp = new SdgComp("TESTNAME", "TESTDESCRIPTION"); 
		
		
		manager.addSdgJpa(comp);
		
		verify(sdgMapperMock).insert(comp);
		
	}
	
	
	
	@Test
	void findAll_FromDatabase_Test_ExpectedTrue() {
		
		GenericMapperJpa<SdgAbstract> sdgMapperMock = (GenericMapperJpa<SdgAbstract>) mock(GenericMapperJpa.class);
		SdgManager manager = new SdgManager(sdgMapperMock); 
		SdgAbstract comp = new SdgComp("TESTNAME", "TESTDESCRIPTION"); 
		
		
		manager.getAll();
		verify(sdgMapperMock).findAll();
		
	}
	
	
	@Test
	void deleteSDG_FromDatabase_Test_ExpectedTrue() {
		
		GenericMapperJpa<SdgAbstract> sdgMapperMock = (GenericMapperJpa<SdgAbstract>) mock(GenericMapperJpa.class);
		SdgManager manager = new SdgManager(sdgMapperMock); 
		SdgAbstract comp = new SdgComp("TESTNAME", "TESTDESCRIPTION"); 
		
		
		manager.deleteSdg(comp);
		verify(sdgMapperMock).delete(comp);
		
	}
	
	
	@Test
	void updateSDG_FromDatabase_Test_ExpectedTrue() {
		
		GenericMapperJpa<SdgAbstract> sdgMapperMock = (GenericMapperJpa<SdgAbstract>) mock(GenericMapperJpa.class);
		SdgManager manager = new SdgManager(sdgMapperMock); 
		SdgAbstract comp = new SdgComp("TESTNAME", "TESTDESCRIPTION"); 
		
		
		manager.updateSdg(comp);
		verify(sdgMapperMock).update(comp);
		
	}
	
	
	@Test 
	void getSDGById_FromDatabase_Test_EqualObjects() {
			
		GenericMapperJpa<SdgAbstract> sdgMapperMock = (GenericMapperJpa<SdgAbstract>) mock(GenericMapperJpa.class);
		List<SdgAbstract> list = new ArrayList<SdgAbstract>(); 
		when(sdgMapperMock.findAll()).thenReturn(list); 
		SdgAbstract comp = new SdgComp("TESTNAME", "TESTDESCRIPTION"); 

		list.add(comp); 		
		SdgManager manager = new SdgManager(sdgMapperMock); 
		comp.setId(1);
		
		
		SdgComp toTest = manager.getSdg(1);
		
		
		
		assertEquals(comp,toTest);
		
		
	}
	
	
	@Test
	void addSubSdgToSdgComp_Test_EqualsTrue() {
		
		
		GenericMapperJpa<SdgAbstract> sdgMapperMock = (GenericMapperJpa<SdgAbstract>) mock(GenericMapperJpa.class);
		SdgAbstract comp = new SdgComp("TESTNAME", "TESTDESCRIPTION"); 
		SdgAbstract child = new SdgChild("TEST", "TESTICON", null, (SdgComp) comp, 500); 
		List<SdgAbstract> list = new ArrayList<SdgAbstract>(); 
		
		
		when(sdgMapperMock.findAll()).thenReturn(list); 
		comp.setId(1);
		list.add(comp);
		SdgManager manager = new SdgManager(sdgMapperMock); 
		manager.addSubSdg((SdgChild) child, 1);

		
		
		assertEquals(child, comp.getChild(0));
		
		
		
		
	}



}
