package generator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.mwe2.runtime.workflow.IWorkflowComponent;
import org.eclipse.emf.mwe2.runtime.workflow.IWorkflowContext;

import PIMM.Document;
import PIMM.PIMMFactory;
import PIMM.PIMMPackage;
import PIMM.Page;
import PIMM.Text;
import PIMM.impl.PIMMFactoryImpl;

public class PIMMGenerator implements IWorkflowComponent {

	public void main() {
		
		System.out.println("Starting to generate PIMs");
		
		PIMMPackage.eINSTANCE.eClass();

		PIMMFactory factory = PIMMFactoryImpl.eINSTANCE;

		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;

		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("test", new XMIResourceFactoryImpl());
		ResourceSet resSet = new ResourceSetImpl();
		
		Map<String, Object> opts = new HashMap<String, Object>();
		opts.put(XMIResource.OPTION_SCHEMA_LOCATION, true);

		for (int i = 0; i < 10; i++) {

			Document doc = factory.createDocument();
			doc.setName("test" + i);
			
			for (int j = 0; j< 5 + Math.round(Math.random()*10); j++) {
				Text txt = factory.createText();
				Page pg = factory.createPage();
				txt.setValue("Hello "+ j);
				pg.getElements().add(txt);
				Text txt2 = factory.createText();
				txt2.setValue(j+ " Hello");
				pg.getElements().add(txt2);
				doc.getPages().add(pg);
			}


			Resource resource = resSet.createResource(URI
					.createURI("PIM/test"+i+".test"));
			resource.getContents().add(doc);
			try {
				resource.save(opts);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Done generating PIMs");
	}

	@Override
	public void preInvoke() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void invoke(IWorkflowContext ctx) {
		main();
		
	}

	@Override
	public void postInvoke() {
		// TODO Auto-generated method stub
		
	}
}
