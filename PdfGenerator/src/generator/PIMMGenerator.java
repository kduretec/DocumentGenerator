package generator;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import PIMM.Document;
import PIMM.PIMMFactory;
import PIMM.PIMMPackage;
import PIMM.Page;
import PIMM.Text;
import PIMM.impl.PIMMFactoryImpl;

public class PIMMGenerator {

	public static void main(String[] args) {

		PIMMPackage.eINSTANCE.eClass();

		PIMMFactory factory = PIMMFactoryImpl.eINSTANCE;

		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;

		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("test", new XMIResourceFactoryImpl());
		ResourceSet resSet = new ResourceSetImpl();
		
		for (int i = 0; i < 10; i++) {

			Document doc = factory.createDocument();
			doc.setName("test" + i);
			
			for (int j = 0; j<10; j++) {
				Text txt = factory.createText();
				Page pg = factory.createPage();
				pg.getElements().add(txt);
				doc.getPages().add(pg);
			}


			Resource resource = resSet.createResource(URI
					.createURI("PIM/test"+i+".test"));
			resource.getContents().add(doc);

			try {
				resource.save(Collections.EMPTY_MAP);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
