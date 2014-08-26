package groundtruth.ocl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.ocl.OCL;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.helper.OCLHelper;

import PIMM.Document;
import PIMM.PIMMPackage;

public class Evaluator {

	public static void main(String[] args) {
		OCL ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);
		OCLHelper helper = ocl.createOCLHelper();
		helper.setContext(PIMMPackage.Literals.DOCUMENT);
		try {
			OCLExpression<EClassifier> query = helper
					.createQuery("self.pages->size()");

			Query eval = ocl.createQuery(query);
			
			List<Document> documents = getDocuments();
			
			for (Document doc : documents) {
				System.out.println(eval.evaluate(doc));
			}
			
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static List<Document> getDocuments() {
		List<Document> docs = new ArrayList<Document>();
		
		PIMMPackage.eINSTANCE.eClass();
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
	    Map<String, Object> m = reg.getExtensionToFactoryMap();
	    m.put("test", new XMIResourceFactoryImpl());
	    
	    ResourceSet resSet = new ResourceSetImpl();
	    
	    for (int i=0; i<10; i++) {
	    	Resource resource = resSet.getResource(URI
	    	        .createURI("PIM/test"+i+".test"), true);
	    	Document doc = (Document) resource.getContents().get(0);
	    	docs.add(doc);
	    }
	    
	    return docs;
	}
}
